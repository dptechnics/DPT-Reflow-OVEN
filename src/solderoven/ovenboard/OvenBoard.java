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
import solderoven.processcontrol.OnOffController;
import solderoven.processcontrol.PIDController;
import solderoven.processcontrol.PWMControllable;
import solderoven.processcontrol.PWMController;

/**
 * @author Daan Pape
 */
public class OvenBoard implements PWMControllable{
    
    /**
     * The PWMController controlling the heater element from the board
     */
    private PWMController pwmController;
    
    /**
     * The PIDController controlling the PWM controller from the board
     */
    private PIDController pidController;
    private OnOffController onOffController;
    
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
        
        // Set up the boards PWM controller
        this.pwmController = new PWMController(this);
        
        // Set up the boards PID controller
        this.pidController = new PIDController();
        this.onOffController = new OnOffController();
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
            
            // Stop blinking leds
            this.setLedBlinkState(false);
            
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
                // Turn off all elements
                this.setHeaterState(false);
                this.setFanState(false);
                this.setCoolState(false);
                this.setLedBlinkState(true);
                
                serialPort.closePort();
                // Notify listeners the board is not connected
                fireBoardConnectedEvent(false);
            } catch (SerialPortException | OvenBoardException ex) {
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
     * This method is called by the PWM controller to set the board heater state.
     * @param state true if the heater should be turned on.
     */
    @Override
    public void setState(boolean state) {
        try {
            if(this.isConnected()) {
                this.setHeaterState(state);
            }
        } catch (OvenBoardException ex) {
            //TODO: handle exception
            ExceptionHandler.getInstance().handleException(ex);
        }
    }
    
    /**
     * Return the PWM controller from the board.
     * @return the boards PWM controller.
     */
    public PWMController getPWMController() {
        return this.pwmController;
    }
    
    /**
     * Return the PID controller from the board.
     * @return the boards PID controller.
     */
    public PIDController getPIDController() {
        return this.pidController;
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
         * Buffer for receiving data 
         */
        private StringBuilder receiveBuffer;
        
        /**
         * The previously measured temperature for spike compensation
         */
        private float previousTemperature;
        
        /**
         * The constructor.
         * @param port an opened serial port. 
         */
        public SerialPortReader(SerialPort port){
            this.serialPort = port;
            this.receiveBuffer = new StringBuilder();
            this.previousTemperature = -1;
        }
        
        /**
         * Serial port event listener. No extra event checks are needed because
         * only incoming data events are triggering this eventhandler. 
         * @param event the serialport event to handle. 
         */
        @Override
        public void serialEvent(SerialPortEvent event) {
            // The oven board spits out ASCII coded data
            if(event.isRXCHAR() && event.getEventValue() > 0){
                // Try to parse the incoming data. 
                try { 
                    String received = serialPort.readString(1);
                    switch (received) {
                        case "{":
                            // Start of inputstring is received
                            this.receiveBuffer = new StringBuilder();
                            break;
                        case "}":
                            // Parse the received string
                            String[] input = receiveBuffer.toString().split(",");
                            float temperature = Float.parseFloat(input[0]);
                            boolean sensorstate = input[1].charAt(7) == 'K';
                            boolean heaterstate = input[2].charAt(6) == 'N';
                            boolean fanstate = input[3].charAt(5) == 'N';
                            boolean coolstate = input[4].charAt(6) == 'N';
                            
                            // SSR influance correction 
                            if(heaterstate) {
                                temperature += 4.5f;
                            }
                            
                            if(fanstate) {
                                temperature += 4.5f;
                            }
                            
                            if(previousTemperature == -1){
                                previousTemperature = temperature;
                            }
                            
                            if(temperature - previousTemperature > 3) {
                                temperature = previousTemperature + 0.5f;
                            }else if(previousTemperature - temperature > 3) {
                                temperature = previousTemperature - 0.5f;
                            }
                            previousTemperature = temperature;
                            
                            // Input the data into the boards PID controller
                            System.out.println("PID output: " + pidController.updatePID(temperature));
                            onOffController.updateControls(pwmController, temperature);
                            
                            // Save oven data in container
                            OvenData data = new OvenData(temperature, sensorstate, heaterstate, fanstate, coolstate);
                            // Propagate event to all listeners
                            fireBoardDataEvent(data);
                            break;
                        default:
                            // Append received character
                            this.receiveBuffer.append(received);
                            break; 
                    }
                }
                catch (SerialPortException ex) {
                    ExceptionHandler.getInstance().handleException(ex);
                }
            }
        }
    }
}
