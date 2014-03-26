package solderoven.main;

import java.awt.EventQueue;
import java.awt.Toolkit;
import java.net.URL;
import javax.swing.JFrame;
import solderoven.gui.mainpanels.MainPanel;
import solderoven.gui.menus.Menubar;
import solderoven.i18n.I18N;
import solderoven.models.AppModel;
import solderoven.processcontrol.PWMControllable;
import solderoven.processcontrol.PWMController;

/**
 *
 * @author Daan Pape
 */
public class SolderOven {
    
    /**
     * The main application model
     */
    private AppModel model;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {        
        new SolderOven().createAndShowGUI();
    }
    
    /**
     * Solder oven board constructor
     */
    public SolderOven(){
        model = new AppModel();
        
        // Add a shutdown hook to close communication when shuttings down
        Runtime.getRuntime().addShutdownHook(new Thread(){
            @Override
            public void run(){
                if(model.getBoardModel().isConnected()){
                    // Close the connection
                    model.getOvenBoard().closeConnection();
                }
            }
        });
    }
    
    /**
     * Create and show the GUI on a thread safe manner
     */
    public void createAndShowGUI(){
        EventQueue.invokeLater(new Runnable(){
            @Override
            public void run() {     
                // Create the window and add the main panel
                JFrame window = new JFrame(I18N.getInstance().getString("appTitle"));
                window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                window.setContentPane(new MainPanel(model));
                window.setJMenuBar(new Menubar(model, window));
                window.pack();
                
                // Set the application icon
                URL url = ClassLoader.getSystemResource("solderoven/icons/favicon_small.png");
                window.setIconImage(Toolkit.getDefaultToolkit().createImage(url));
                
                // Display the applictation
                window.setVisible(true);
            }         
        });
    }
}
