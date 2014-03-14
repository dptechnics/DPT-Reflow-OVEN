package solderoven.exception;

import solderoven.i18n.I18N;

/**
 * @author Daan Pape
 */
public class LocalizedException extends Exception {

    /**
     * The message in the form of the localisation message key. 
     */
    protected String messageKey;
    
    /**
     * Construct a new LocalizedException and set a message
     * key representing the error message.
     * @param messageKey the key representing the error message. 
     * @param message a hardcoded error message.
     */
    public LocalizedException(String messageKey, String message){
        super(message);
        this.messageKey = messageKey;
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
     * @param args optional arguments 
     * @return the localised version of this error message. 
     */
    public String getLocalizedMessage(Object... args){
        try{
            return String.format(I18N.getInstance().getString(this.messageKey), args);
        } catch(Exception ex){
            // Failsafe when localisation fails
            return super.getMessage();
        }
    }
    
        /**
     * Returns the localised error message. 
     * @return the localised version of this error message. 
     */
    @Override
    public String getLocalizedMessage(){
        try{
            return getLocalizedMessage();
        } catch(Exception ex){
            // Failsafe when localisation fails
            return super.getMessage();
        }
    }
}
