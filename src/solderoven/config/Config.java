package solderoven.config;

import java.util.Locale;

/**
 * @author Daan Pape
 */
public class Config {
    
    /**
     * Singleton design pattern is used for uniform config access.
     */
    private static Config INSTANCE = new Config();
    
    /**
     * Constructor hided so no other objects can make a Config object.
     */
    private Config(){};
    
    /**
     * Get the singleton Config instance
     * @return 
     */
    public static Config getInstance(){
        return INSTANCE;
    }
    
    /**
     * Return the used serial port for communication with the  board.
     * @return the serial communication port name.
     */
    public String getPort(){
        return "COM2";
    }
    
    /**
     * Return the preferred application locale.
     * @return the preferred locale, this can be null when no locale is configured. 
     */
    public Locale getLocale(){
        return new Locale("nl", "BE");
        //return null;
    }

}
