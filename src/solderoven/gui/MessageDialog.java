package solderoven.gui;

import java.awt.EventQueue;
import javax.swing.JOptionPane;

/**
 * This class contains all code for easy display of
 * messageboxes in the application. 
 * @author Daan Pape
 */
public class MessageDialog {
    
    /**
     * Show an error dialog given a localised string and error. 
     * No further localisation will happen in this method. 
     * @param title the localised title of the error dialog.
     * @param error the localised error message of the error dialog.
     */
    public static void showErrorDialog(final String title, final String error){
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                JOptionPane.showMessageDialog(
                null, 
                error, 
                title,
                JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}
