package solderoven.gui;

import javax.swing.JPanel;
import solderoven.models.AppModel;
import solderoven.models.TemperatureChartModel;

/**
 * @author Daan Pape
 */
public class MainPanel extends JPanel{
    
    /**
     * The application main model.
     */
    private AppModel model;
    
    /**
     * MainPanel constructor.
     * @param model the main application model.
     */
    public MainPanel(AppModel model){
        this.model = model;
        
        // Construct the GUI for the app
        constructGUI();
    }
    
    /**
     * Construct the application GUI
     */
    private void constructGUI(){
        this.add(new StatusPanel(model.getBoardModel()));
        this.add(new TemperatureChart(new TemperatureChartModel(model.getBoardModel())));
    }
}
