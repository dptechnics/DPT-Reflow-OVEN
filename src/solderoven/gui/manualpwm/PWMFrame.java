package solderoven.gui.manualpwm;

import javax.swing.JFrame;
import solderoven.i18n.I18N;
import solderoven.models.AppModel;

/**
 * @author Daan Pape
 */
public class PWMFrame extends JFrame{
    
    /**
     * The application model
     */
    private AppModel model;
    
    /**
     * Construct a new PWMFrame
     */
    public PWMFrame(){
        this.setTitle(I18N.getInstance().getString("pwmTitle"));
    }
}
