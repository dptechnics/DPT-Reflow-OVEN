package solderoven.ovenboard;

import java.util.ArrayList;
import java.util.List;
import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;
import solderoven.config.Config;
import solderoven.exception.ExceptionHandler;
import solderoven.exception.OvenBoardException;

/**
 * @author Daan Pape
 */
public class OvenBoard {
    /**
     * The listeners listing to incoming board data.
     */
    private List<OvenBoardListener> ovenBoardListenerList;
    
    /**
     * The port connecting to the physical board. 
     */
    private SerialPort serialPort;
    
    /**
     * Byte buffer for incoming data.
     */
    private byte[] byteBuffer;
    
    /**
     * Default constructor initializing the listenerlist. 
     */
    public OvenBoard(){
        this.ovenBoardListenerList = new ArrayList<>();
    }
    
    /**
     * Add a listener to the OvenBoard. 
     * @param listener the listener that will listen to incoming data. 
     */
    public void addOvenBoardListener(OvenBoardListener listener){
        ovenBoardListenerList.add(listener);
    }
    
    /**
     * Remove a listener from the OvenBoard.
     * @param listener the listener not longer interested in data from the board. 
     */
    public void removeOvenBoardListener(OvenBoardListener listener){
        ovenBoardListenerList.remove(listener);
    }
    
    /**
     * Let all registered listeners know new data is available.
     * @param data the oven data container. 
     */
    protected void fireBoardDataEvent(OvenData data){
        for(int i = 0; i < ovenBoardListenerList.size(); ++i){
            ovenBoardListenerList.get(i).infoReceived(data);
        }
    }
    
    /**
     * Let all the registered listeners know the connection state has changed
     * @param connectionState the new connectionState
     */
    protected void fireBoardConnectedEvent(boolean connectionState){
        for(int i = 0; i < ovenBoardListenerList.size(); ++i){
            ovenBoardListenerList.get(i).boardConnectionChange(connectionState);
        }
    }
    
    /**
     * Send a command to the oven board 
     * @param command the command to send
     */
    protected void sendBoardCommand(char command) throws OvenBoardException{
        try{
            serialPort.writeByte((byte) command);
        }catch(SerialPortException ex){
            // Save error in the log
            ExceptionHandler.getInstance().handleException(ex);
            
            // Throw the error to the user
            throw new OvenBoardException(ex.getPortName(), ex.getExceptionType(), ex.toString());
        } catch (NullPointerException e) {
            throw new OvenBoardException("UNKNOWN", "Port not open", "Port is not open");
        }
    }
    
    /**
     * Connect to the oven reflow board
     */
    public void connectToBoard() throws OvenBoardException{
        // Create the serial port instance 
        this.serialPort = new SerialPort(Config.getInstance().getPort());
        
        try {
            // Open the serial port
            serialPort.openPort();
            serialPort.setParams(9600, 8, 1, 0);
            
            // Only listen for incoming data events
            serialPort.setEventsMask(SerialPort.MASK_RXCHAR);
            serialPort.addEventListener(new SerialPortReader(serialPort));
            
            // Notify listeners the board is connected
            fireBoardConnectedEvent(true);
        }
        catch (SerialPortException ex) {
            // Save the error in the log
            ExceptionHandler.getInstance().handleException(ex);
            
            // Throw the error to the user
            throw new OvenBoardException(ex.getPortName(), ex.getExceptionType(), ex.toString());
        }
    }
    
    /**
     * Returns true if the board is connected. 
     * @return true if the board is connected
     */
    public boolean isConnected(){
        return serialPort != null && serialPort.isOpened();
    }
    
    /**
     * Close the serial port connection to the board
     */
    public void closeConnection(){
        if(serialPort != null && serialPort.isOpened()){
            try {
                serialPort.closePort();
                // Notify listeners the board is not connected
                fireBoardConnectedEvent(false);
            } catch (SerialPortException ex) {
                ExceptionHandler.getInstance().handleException(ex);
            }
        }
    }
    
    /**
     * Set the state of the oven heater.
     * @param state true if the heating elements should be turned on.
     */
    public void setHeaterState(boolean state) throws OvenBoardException {
        this.sendBoardCommand(state ? 'h' : 'j');
    }
    
    /**
     * Set the state of the oven fan.
     * @param state true if the fan should be turned on.
     */
    public void setFanState(boolean state) throws OvenBoardException {
        this.sendBoardCommand(state ? 'f' : 'g');
    }
    
    /**
     * Set the state of the oven cooling mechanism.
     * @param state true if the oven cooling mechanism should be turned on. 
     */
    public void setCoolState(boolean state) throws OvenBoardException {
        this.sendBoardCommand(state ? 'c' : 'v');
    }
    
    /**
     * Should the board LED's blink or not
     * @param state true if the LED's should blink
     */
    public void setLedBlinkState(boolean state) throws OvenBoardException {
        this.sendBoardCommand(state ? 'b' : 'n');
    }
    
    /**
     * Should the heat phase LED be on or not
     * @param state true if the heat phase LED should be turned on.
     */
    public void setHeatLedState(boolean state) throws OvenBoardException {
        this.sendBoardCommand(state ? 'a' : 'q');
    }
    
    /**
     * Should the soak phase LED be on or not
     * @param state true if the soak phase LED should be turned on.
     */
    public void setSoakLedState(boolean state) throws OvenBoardException {
        this.sendBoardCommand(state ? 'z' : 's');
    }
    
    /**
     * Should the reflow phase LED be on or not
     * @param state true if the reflow phase LED should be turned on.
     */
    public void setReflowLedState(boolean state) throws OvenBoardException {
        this.sendBoardCommand(state ? 'e' : 'd');
    }
    
    /**
     * Should the cool phase LED be on or not
     * @param state true if the cool phase LED should be turned on.
     */
    public void setCoolLedState(boolean state) throws OvenBoardException {
        this.sendBoardCommand(state ? 'r' : 't');
    }
    
    /**
     * Private class reading and parsing the data coming from the oven board.
     */
    private class SerialPortReader implements SerialPortEventListener{
        /**
         * The serial port to listen to 
         */
        private SerialPort serialPort;
        
        /**
         * The constructor.
         * @param port an opened serial port. 
         */
        public SerialPortReader(SerialPort port){
            this.serialPort = port;
        }
        
        /**
         * Serial port event listener. No extra event checks are needed because
         * only incoming data events are triggering this eventhandler. 
         * @param event the serialport event to handle. 
         */
        @Override
        public void serialEvent(SerialPortEvent event) {
            // The oven board spits out 39 bytes per info line, ASCII coded 
            if(event.getEventValue() == 1){
                // Try to parse the incoming data. 
                try {
                    String received = new String(serialPort.readBytes());
                    
                    /* Parse the data */
                    float temperature = Float.parseFloat(received.substring(0, 5));
                    boolean sensorstate = received.charAt(13) == 'K';
                    boolean heaterstate = received.charAt(21) == 'N';
                    boolean fanstate = received.charAt(28) == 'N';
                    boolean coolstate = received.charAt(36) == 'N';
                    
                    // Save oven data in container
                    OvenData data = new OvenData(temperature, sensorstate, heaterstate, fanstate, coolstate);
                    
                    // Propagate event to all listeners           
                    fireBoardDataEvent(data);
                }
                catch (SerialPortException ex) {
                    ExceptionHandler.getInstance().handleException(ex);
                }
            }
        }
    }
}
