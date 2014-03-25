package solderoven.models;

import solderoven.ovenboard.OvenBoard;
import solderoven.profile.ReflowProfile;

/**
 * @author Daan Pape
 */
public class AppModel extends Model{
    
    /**
     * The model containing all data from the board 
     */
    private BoardModel boardModel;
    
    /**
     * The oven board data transceiver
     */
    private OvenBoard ovenBoard;
    
    /**
     * The current loaded profile
     */
    private ReflowProfile profile;
    
    /**
     * Default constructor 
     */
    public AppModel(){
        // Create an ovenBoard data transceiver 
        ovenBoard = new OvenBoard();
        
        // Create a model for the board data
        boardModel = new BoardModel();
        ovenBoard.addOvenBoardListener(boardModel);
        
        // Set the current profile to null
        this.profile = null;
    }
    
    /**
     * Get the model containing all board information
     * @return the board model
     */
    public BoardModel getBoardModel(){
        return this.boardModel;
    }
    
    /**
     * Get the oven board interface object.
     * @return the oven board interface
     */
    public OvenBoard getOvenBoard(){
        return this.ovenBoard;
    }
    
    /**
     * Get the currently loaded reflow profile.
     * @return the currently loaded reflow profile.
     */
    public ReflowProfile getReflowProfile() {
        return this.profile;
    }
    
    /**
     * Set a new reflow profile.
     * @param profile the loaded reflow profile.
     */
    public void setReflowProfile(ReflowProfile profile) {
        ReflowProfile oldValue = this.profile;
        this.profile = profile;
       super.pcs.firePropertyChange("reflowProfile", oldValue, this.profile);
    }
}
