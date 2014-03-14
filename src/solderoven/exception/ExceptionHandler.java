package solderoven.exception;

/**
 * @author Daan Pape
 */
public class ExceptionHandler {
    /**
     * Singleton design pattern is used for uniform ExceptionHandler access.
     */
    private static ExceptionHandler INSTANCE = new ExceptionHandler();
    
    /**
     * Constructor hided so no other objects can make a ExceptionHandler object.
     */
    private ExceptionHandler(){};
    
    /**
     * Get the singleton ExceptionHandler instance
     * @return 
     */
    public static ExceptionHandler getInstance(){
        return INSTANCE;
    }
    
    /**
     * Handle exceptions on a uniform way for logging.
     * @param ex the exception to handle
     */
    public void handleException(Exception ex) {
        ex.printStackTrace();
    }
}
