package solderoven.gui.manualpwm;

import java.awt.Toolkit;
import java.net.URL;
import javax.swing.JFrame;
import solderoven.i18n.I18N;
import solderoven.models.AppModel;

/**
 * @author Daan Pape
 */
public class PWMFrame extends JFrame{
    
    /**
     * Construct a new PWMFrame
     */
    public PWMFrame(AppModel model){
        this.setTitle(I18N.getInstance().getString("pwmTitle"));
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setContentPane(new PWMSettingsPane(model));
        this.pack();
        
        // Set the frame icon
        URL url = ClassLoader.getSystemResource("solderoven/icons/favicon_small.png");
        this.setIconImage(Toolkit.getDefaultToolkit().createImage(url));
    }
}
