package solderoven.exception;

import solderoven.i18n.I18N;

/**
 * @author Daan Pape
 */
public class OvenBoardException extends Exception{
    
    /**
     * The port on which the error occurred. 
     */
    private String port;
    
    /**
     * The message in the form of the localisation message key. 
     */
    private String messageKey;
    
    /**
     * Construct a new OvenBoardException and set a message
     * key representing the error message. 
     * @param port the port on which the error occurred.
     * @param messageKey the key representing the error message. 
     * @param message a hardcoded error message.
     */
    public OvenBoardException(String port, String messageKey, String message){
        super(message);
        this.messageKey = messageKey;
        this.port = port;
    }
    
    /**
     * Gets the key describing this error before it is localised. 
     * @return the unlocalised message key.
     */
    public String getMessageKey() {
        return this.getMessageKey();
    }
    
    
    public String getLocalizedTitle(){
        try{
            return I18N.getInstance().getString(this.messageKey + "_title");
        } catch(Exception ex){
            // Failsafe when localisation fails
            return "Error";
        }    
    }
    
    /**
     * Returns the localised error message. 
     * @return the localised version of this error message. 
     */
    @Override
    public String getLocalizedMessage(){
        try{
            return String.format(I18N.getInstance().getString(this.messageKey), this.port);
        } catch(Exception ex){
            // Failsafe when localisation fails
            return super.getMessage();
        }
    }
}
