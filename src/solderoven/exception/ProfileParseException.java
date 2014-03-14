package solderoven.exception;

import java.io.File;

/**
 * @author Daan Pape
 */
public class ProfileParseException extends LocalizedException {
    
    /**
     * The file on which the error occurred.
     */
    private File profileFile;
    
    /**
     * Construct a new ProfileParseException.
     * @param messageKey the key representing the error message.
     * @param message a hardcoded error message.
     * @param profileFile the file on which the error occurred.
     */
    public ProfileParseException(String messageKey, String message, File profileFile){
        super(messageKey, message);
        this.profileFile = profileFile;
    }
    
    /**
     * Returns the localised error message. 
     * @return the localised version of this error message. 
     */
    @Override
    public String getLocalizedMessage(){
        try{
            return getLocalizedMessage(this.profileFile.getName());
        } catch(Exception ex){
            // Failsafe when localisation fails
            return super.getMessage();
        }
    }
}
