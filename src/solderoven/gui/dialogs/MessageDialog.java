package solderoven.gui.dialogs;

import java.awt.Component;
import java.awt.EventQueue;
import java.io.File;
import javax.swing.Icon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import javax.swing.filechooser.FileView;
import solderoven.exception.ExceptionHandler;

/**
 * This class contains all code for easy display of
 * messageboxes in the application. 
 * @author Daan Pape
 */
public class MessageDialog {
    
    /**
     * Singleton design pattern is used for uniform MessageDialog access.
     */
    private static MessageDialog INSTANCE = new MessageDialog();
    
    /**
     * The applications fileChooser. Only creating this instance once means the
     * user won't have to browse to a far directory every time again.
     */
    private JFileChooser fileChooser;
    
    /**
     * Constructor hided so no other objects can make a MessageDialog object.
     */
    private MessageDialog(){
        // Make the filechooser
        fileChooser = new JFileChooser();
        
        // Set up the fileChooser icon views
        fileChooser.setFileView(new FileView(){
            @Override
            public Icon getIcon(File f){
                FileSystemView view = FileSystemView.getFileSystemView();
                return view.getSystemIcon(f);
            }
        });
        
        // Make the XML file filter
        fileChooser.setFileFilter(new FileNameExtensionFilter("DPT Reflow Profiles", "xml"));
    }
    
    /**
     * Get the singleton MessageDialog instance.
     * @return the singleton instance of the MessageDialog object.
     */
    public static MessageDialog getInstance(){
        return INSTANCE;
    }
    
    /**
     * Show an error dialog given a localised string and error. 
     * No further localisation will happen in this method. 
     * @param title the localised title of the error dialog.
     * @param error the localised error message of the error dialog.
     */
    public void showErrorDialog(final String title, final String error){
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
    
    /**
     * Show a JFileDialog to open an XML reflow profile.
     * @param parent the parent component for showing the JFileChooser.
     * @return the chosen file or null if no file has been chosen.
     */
    public File openReflowProfile(Component parent){
        if(fileChooser.showOpenDialog(parent) == JFileChooser.APPROVE_OPTION){
            return fileChooser.getSelectedFile();
        } else {
            return null;
        }
    }
}
