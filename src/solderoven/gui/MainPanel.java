package solderoven.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import solderoven.i18n.I18N;
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
        
        // Set up gridbag layout
        this.setLayout(new GridBagLayout());
        
        // Construct the GUI for the app
        constructGUI();
    }
    
    /**
     * Construct the application GUI
     */
    private void constructGUI(){
        
        // Add the manual control panel
        ManualPanel mPanel = new ManualPanel(model);
        TitledBorder mBorder = BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), 
                I18N.getInstance().getString("titleControl"),
                TitledBorder.CENTER,
                TitledBorder.DEFAULT_POSITION
                );
        mPanel.setBorder(mBorder);
        
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(5, 5, 5, 5);
        c.anchor = GridBagConstraints.NORTH;
        c.fill = GridBagConstraints.HORIZONTAL;
        this.add(mPanel, c);
        
        
        // Add the status panel with a titled border
        StatusPanel sPanel = new StatusPanel(model.getBoardModel());
        TitledBorder sBorder = BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), 
                I18N.getInstance().getString("titleStatus"),
                TitledBorder.CENTER,
                TitledBorder.DEFAULT_POSITION
                );
        sPanel.setBorder(sBorder);
        
        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 1;
        c.insets = new Insets(5, 5, 5, 5);
        c.anchor = GridBagConstraints.NORTH;
        c.fill = GridBagConstraints.HORIZONTAL;
        this.add(sPanel, c);
        
        
        // Add the temperature chart
        TemperatureChart tChart = new TemperatureChart(new TemperatureChartModel(model.getBoardModel()));
        TitledBorder cBorder = BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), 
                I18N.getInstance().getString("graphTitle"),
                TitledBorder.CENTER,
                TitledBorder.DEFAULT_POSITION
                );
        tChart.setBorder(cBorder);
        c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 0;
        c.gridheight = 2;
        c.insets = new Insets(5, 5, 5, 5);
        this.add(tChart, c);
    }
}
