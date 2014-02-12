package solderoven.i18n;

import java.util.Locale;
import java.util.ResourceBundle;
import solderoven.config.Config;

/**
 * @author Daan Pape
 */
public class I18N {
    
    /**
     * Singleton design pattern is used for uniform internationalisation access.
     */
    private static I18N INSTANCE = new I18N();
    
    /**
     * The locale used to display the GUI
     */
    private Locale locale;
    
    /**
     * The resource bundle used to search messages
     */
    private ResourceBundle bundle;
    
    /**
     * Default constructor that uses the system locale or locale configured
     */
    private I18N(){
        // Get the preferred locale
        locale = Config.getInstance().getLocale();

        // Locale is null when the default system locale should be used
        if(locale == null){
            locale = Locale.getDefault();
        }
        
        // Load the resource bundle
        bundle = ResourceBundle.getBundle("solderoven.i18n.messages", locale);
    }
    
    /**
     * Get the singleton internationalisation instance.
     * @return the I18N instance.
     */
    public static I18N getInstance(){
        return INSTANCE;
    }
    
    /**
     * Get an internationalised string giving a key.
     * @param key the key for selecting the string.
     * @return the internationalised string given the key.
     */
    public String getString(String key){
        return bundle.getString(key);
    }
}
