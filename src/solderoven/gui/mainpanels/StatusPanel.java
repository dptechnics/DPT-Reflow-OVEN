package solderoven.gui.mainpanels;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JLabel;
import javax.swing.JPanel;
import solderoven.i18n.I18N;
import solderoven.models.BoardModel;

/**
 * @author Daan Pape
 */
public class StatusPanel extends JPanel implements PropertyChangeListener{
    
    /**
     * The model containing all information for the status
     */
    private BoardModel model;
    
    /**
     * The oven temperature field
     */
    private JLabel temperatureField;
    
    /**
     * The sensor status field 
     */
    private JLabel sensorField;
    
    /**
     * The heater state field
     */
    private JLabel heaterField;
    
    /**
     * The fan state field
     */
    private JLabel fanField;
    
    /**
     * The cooling state field
     */
    private JLabel coolingField;
    
    /**
     * Constructor
     * @param model the boardModel to which this panel should listen.
     */
    public StatusPanel(BoardModel model){
        this.model = model;
        
        // Set up gridbag layout
        this.setLayout(new GridBagLayout());
        
        // Add temperature status
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.anchor = GridBagConstraints.WEST;
        c.insets = new Insets(5, 5, 0, 10);
        this.add(new JLabel(I18N.getInstance().getString("lblTemperature")+":"), c);
        c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 0;
        c.anchor = GridBagConstraints.WEST;
        c.insets = new Insets(5, 5, 0, 10);
        temperatureField = new JLabel(String.format("%05.2f", model.getCurrentStatus().getTemperature()) + "°C");
        this.add(temperatureField, c);
        
        // Add sensor status 
        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 1;
        c.anchor = GridBagConstraints.WEST;
        c.insets = new Insets(5, 5, 0, 0);
        this.add(new JLabel(I18N.getInstance().getString("lblSensor")+":"), c);
        c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 1;
        c.anchor = GridBagConstraints.WEST;
        c.insets = new Insets(5,5,0,0);
        sensorField = new JLabel(model.getCurrentStatus().isSensorConnected() ? "OK" : "NC");
        this.add(sensorField, c);
        
        // Add the heater state 
        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 2;
        c.anchor = GridBagConstraints.WEST;
        c.insets = new Insets(5, 5, 0, 0);
        this.add(new JLabel(I18N.getInstance().getString("lblHeater")+":"), c);
        c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 2;
        c.anchor = GridBagConstraints.WEST;
        c.insets = new Insets(5,5,0,0);
        heaterField = new JLabel(model.getCurrentStatus().isHeaterOn() ? "ON" : "OFF");
        this.add(heaterField, c);
        
        // Add the fan state
        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 3;
        c.anchor = GridBagConstraints.WEST;
        c.insets = new Insets(5, 5, 0, 0);
        this.add(new JLabel(I18N.getInstance().getString("lblFan")+":"), c);
        c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 3;
        c.anchor = GridBagConstraints.WEST;
        c.insets = new Insets(5,5,0,0);
        fanField = new JLabel(model.getCurrentStatus().isFanOn() ? "ON" : "OFF");
        this.add(fanField, c);
        
        // Add the cooling state
        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 4;
        c.anchor = GridBagConstraints.WEST;
        c.insets = new Insets(5, 5, 5, 0);
        this.add(new JLabel(I18N.getInstance().getString("lblCool")+":"), c);
        c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 4;
        c.anchor = GridBagConstraints.WEST;
        c.insets = new Insets(5,5,5,0);
        coolingField = new JLabel(model.getCurrentStatus().isCoolingOn() ? "ON" : "OFF");
        this.add(coolingField, c);
        
        // Register this view as listener
        this.model.addPropertyChangeListener(this);
    }

    /**
     * This handler is called when a status changes
     * @param evt 
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        switch(evt.getPropertyName()){
            case "ovenData":
                // Update data
                temperatureField.setText(String.format("%05.2f", model.getCurrentStatus().getTemperature()) + "°C");
                sensorField.setText(model.getCurrentStatus().isSensorConnected() ? "OK" : "NC");
                heaterField.setText(model.getCurrentStatus().isHeaterOn() ? "ON" : "OFF");
                fanField.setText(model.getCurrentStatus().isFanOn() ? "ON" : "OFF");
                coolingField.setText(model.getCurrentStatus().isCoolingOn() ? "ON" : "OFF");
                break;        
        }
    }
}