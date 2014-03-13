package solderoven.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JPanel;
import solderoven.i18n.I18N;
import solderoven.models.AppModel;

/**
 * @author Daan Pape
 */
public class ManualPanel extends JPanel implements PropertyChangeListener{
    
    /**
     * The application model
     */
    private final AppModel model;
    
    /**
     * Button for connecting and deconnecting to the board. 
     */
    private JButton connectBtn;
    
    /**
     * Button to switch on or off the oven heating elements.
     */
    private JButton heaterBtn;
    
    /**
     * Button to switch on or off the oven fan.
     */
    private JButton fanBtn;
    
    /**
     * Button to switch on or off the cooling button.
     */
    private JButton coolBtn;
    
    /**
     * Constructor 
     * @param model the application model 
     */
    public ManualPanel(final AppModel model){
        this.model = model;
        
        // Set up gridbag layout
        this.setLayout(new GridBagLayout());
        
        // Add connect/disconnect button
        connectBtn = new JButton(new AbstractAction(I18N.getInstance().getString("btnConnect")){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(model.getBoardModel().isConnected()){
                    model.getOvenBoard().closeConnection();
                }else{
                    model.getOvenBoard().connectToBoard();
                }
            }          
        });
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(5, 5, 5, 5);
        this.add(connectBtn, c);
        
        // Add heater control button
        heaterBtn = new JButton(new AbstractAction(I18N.getInstance().getString("btnHeaterOn")){
            @Override
            public void actionPerformed(ActionEvent e) {
                model.getOvenBoard().setHeaterState(!model.getBoardModel().getCurrentStatus().isHeaterOn());
            }
        });
        
        // Register to listen for board events
        model.getBoardModel().addPropertyChangeListener(this);
    }

    /**
     * Handler is called when new relevant data is available.
     * @param evt information about the event.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        switch(evt.getPropertyName()){
            case "connectionState":
                if(model.getBoardModel().isConnected()){
                    connectBtn.setText(I18N.getInstance().getString("btnDisconnect"));
                }else{
                    connectBtn.setText(I18N.getInstance().getString("btnConnect"));
                }
                break;
        }
    }
}
