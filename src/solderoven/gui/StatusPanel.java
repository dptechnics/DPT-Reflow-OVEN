package solderoven.gui;

import java.awt.GridLayout;
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
        
        // Use grid layout
        this.setLayout(new GridLayout(5,2));
        
        // Add temperature status 
        this.add(new JLabel(I18N.getInstance().getString("lblTemperature")+":"));
        temperatureField = new JLabel(model.getCurrentStatus().getTemperature() + "°C");
        this.add(temperatureField);
        
        // Add sensor status 
        this.add(new JLabel(I18N.getInstance().getString("lblSensor")+":"));
        sensorField = new JLabel(model.getCurrentStatus().isSensorConnected() ? "OK" : "NC");
        this.add(sensorField);
        
        // Add the heater state 
        this.add(new JLabel(I18N.getInstance().getString("lblHeater")+":"));
        heaterField = new JLabel(model.getCurrentStatus().isHeaterOn() ? "ON" : "OFF");
        this.add(heaterField);
        
        // Add the fan state
        this.add(new JLabel(I18N.getInstance().getString("lblFan")+":"));
        fanField = new JLabel(model.getCurrentStatus().isFanOn() ? "ON" : "OFF");
        this.add(fanField);
        
        // Add the cooling state
        this.add(new JLabel(I18N.getInstance().getString("lblCool")+":"));
        coolingField = new JLabel(model.getCurrentStatus().isCoolingOn() ? "ON" : "OFF");
        this.add(coolingField);
        
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
                temperatureField.setText(model.getCurrentStatus().getTemperature() + "°C");
                sensorField.setText(model.getCurrentStatus().isSensorConnected() ? "OK" : "NC");
                heaterField.setText(model.getCurrentStatus().isHeaterOn() ? "ON" : "OFF");
                fanField.setText(model.getCurrentStatus().isFanOn() ? "ON" : "OFF");
                coolingField.setText(model.getCurrentStatus().isCoolingOn() ? "ON" : "OFF");
                break;        
        }
    }
}
