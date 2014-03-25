package solderoven.exception;

/**
 * @author Daan Pape
 */
public class OvenBoardException extends LocalizedException{
    
    /**
     * The port on which the error occurred. 
     */
    private String port;
    
    /**
     * Construct a new OvenBoardException and set a message
     * key representing the error message. 
     * @param port the port on which the error occurred.
     * @param messageKey the key representing the error message. 
     * @param message a hardcoded error message.
     */
    public OvenBoardException(String port, String messageKey, String message){
        super(messageKey,message);
        this.port = port;
    }
    
    /**
     * Returns the localised error message. 
     * @return the localised version of this error message. 
     */
    @Override
    public String getLocalizedMessage(){
        try{
            return getLocalizedMessage(this.port);
        } catch(Exception ex){
            // Failsafe when localisation fails
            return super.getMessage();
        }
    }
}
