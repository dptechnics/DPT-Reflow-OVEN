package solderoven.ovenboard;

/**
 * @author Daan Pape
 */
public interface OvenBoardListener {
    
    /**
     * This method is called when the board sends new information. 
     * @param data data container containing all board parameters.
     */
    public void infoReceived(OvenData data);
    
    /**
     * This method is called when the board connections state changes
     * @param connectionState true if the board is connected
     */
    public void boardConnectionChange(boolean connectionState);
}
