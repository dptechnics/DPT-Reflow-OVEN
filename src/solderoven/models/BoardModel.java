package solderoven.models;

import solderoven.ovenboard.OvenBoardListener;
import solderoven.ovenboard.OvenData;

/**
 * @author Daan Pape
 */
public class BoardModel extends Model implements OvenBoardListener{
    /**
     * The current data update
     */
    private int currentTime;
    
    /**
     * The current oven data
     */
    private OvenData ovenStatus;
    
    /**
     * This boolean is true if the board is connected
     */
    private boolean isConnected;
    
    /**
     * Constructor
     */
    public BoardModel(){
        this.currentTime = 0;
        this.isConnected = false;
        ovenStatus = new OvenData();
    }
    
    /**
     * Get the current timestamp of the measured data.
     * @return the current timestamp.
     */
    public int getCurrentTime(){
        return this.currentTime;
    }
    
    /**
     * Get the ovenStatus at the current timestamp.
     * @return the current oven status.
     */
    public OvenData getCurrentStatus(){
        return this.ovenStatus;
    }
    
    /**
     * Get the status of the board connection.
     * @return true if the board is connected
     */
    public boolean isConnected(){
        return this.isConnected;
    }
    
    /**
     * This handler is called when new data is received from the oven
     * @param data the new ovendata 
     */
    @Override
    public void infoReceived(OvenData data) {        
        // Update data
        this.ovenStatus = data;
        ++currentTime;
        
        // Notify all listeners
        pcs.firePropertyChange("ovenData", null, this.ovenStatus);
    }   

    @Override
    public void boardConnectionChange(boolean connectionState) {
        // Update connection state 
        this.isConnected = connectionState;
        
        // Notify all listeners
        pcs.firePropertyChange("connectionState", !connectionState, connectionState);
    }
}
