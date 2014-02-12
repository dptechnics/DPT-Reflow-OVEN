package solderoven.models;

import solderoven.ovenboard.OvenBoard;

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
     * Default constructor 
     */
    public AppModel(){
        // Create an ovenBoard data transceiver 
        ovenBoard = new OvenBoard();
        
        // Create a model for the board data
        boardModel = new BoardModel();
        ovenBoard.addOvenBoardListener(boardModel);
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
}
