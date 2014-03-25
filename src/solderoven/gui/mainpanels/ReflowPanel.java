package solderoven.gui.mainpanels;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import solderoven.actions.LoadProfileAction;
import solderoven.actions.StartReflowAction;
import solderoven.gui.lists.PhaseList;
import solderoven.gui.lists.PhaseListModel;
import solderoven.gui.lists.ProductList;
import solderoven.gui.lists.ProductListModel;
import solderoven.i18n.I18N;
import solderoven.models.AppModel;

/**
 * @author Daan Pape
 */
public class ReflowPanel extends JPanel implements PropertyChangeListener{

    /**
     * The application model.
     */
    private AppModel appModel;
    
    /**
     * Button for loading a new profile.
     */
    private JButton loadBtn;
    
    /**
     * Label for the profile name.
     */
    private JLabel loadedProfileLbl;
    
    /**
     * Label for displaying the loaded profile name.
     */
    private JTextField loadedProfileName;
    
    /**
     * Label for the supported products list.
     */
    private JLabel supportedProductsLbl;
    
    /**
     * The list of supported products from this profile.
     */
    private ProductList supportedProductList;
    
    /**
     * Label for the phases list.
     */
    private JLabel reflowPhasesLbl;
    
    /**
     * The reflow phases list in this profile.
     */
    private PhaseList phaseList;
    
    /**
     * Button to start the reflow process.
     */
    private JButton startReflowBtn;
    
    /**
     * Construct a new reflow panel.
     * @param appModel the application model.
     */
    public ReflowPanel(AppModel appModel) {
        this.appModel = appModel;
        
        // User gridbag layou
        this.setLayout(new GridBagLayout());
        
        // Register this panel to listen to changes from the appModel
        this.appModel.addPropertyChangeListener(this);
        
        // Add load button
        loadBtn = new JButton(new LoadProfileAction(this.appModel, SwingUtilities.getWindowAncestor(this)));
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(5, 5, 5, 5);
        c.fill = GridBagConstraints.HORIZONTAL;
        this.add(loadBtn, c);
        
        // Add the profile name label
        loadedProfileLbl = new JLabel(I18N.getInstance().getString("profileNameLbl") + ":");
        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 1;
        c.insets = new Insets(5, 5, 5, 5);
        c.fill = GridBagConstraints.HORIZONTAL;
        this.add(loadedProfileLbl, c);
        
        // Add the profile name 
        loadedProfileName = new JTextField(I18N.getInstance().getString("profileNameNone"));
        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 2;
        c.insets = new Insets(0, 5, 5, 5);
        c.fill = GridBagConstraints.HORIZONTAL;
        loadedProfileName.setEditable(false);
        this.add(loadedProfileName, c);
    
        // Add the supported products label
        supportedProductsLbl = new JLabel(I18N.getInstance().getString("productsSupportedLbl") + ":");
        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 3;
        c.insets = new Insets(5, 5, 5, 5);
        c.fill = GridBagConstraints.HORIZONTAL;
        this.add(supportedProductsLbl, c);
        
        // Add the supported products list
        supportedProductList = new ProductList(new ProductListModel(this.appModel));
        JScrollPane productListScroller = new JScrollPane(supportedProductList);
        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 4;
        c.insets = new Insets(0, 5, 5, 5);
        c.fill = GridBagConstraints.HORIZONTAL;
        this.add(productListScroller, c);
        
        // Add the phases list label
        reflowPhasesLbl = new JLabel(I18N.getInstance().getString("phasesLbl") + ":");
        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 5;
        c.insets = new Insets(5, 5, 5, 5);
        c.fill = GridBagConstraints.HORIZONTAL;
        this.add(reflowPhasesLbl, c);
        
        // Add the phases list
        phaseList = new PhaseList(new PhaseListModel(this.appModel));
        JScrollPane phaseListScroller = new JScrollPane(phaseList);
        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 6;
        c.insets = new Insets(0, 5, 5, 5);
        c.fill = GridBagConstraints.HORIZONTAL;
        this.add(phaseListScroller, c);
        
        // Add the start reflow button
        startReflowBtn = new JButton(new StartReflowAction(this.appModel));
        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 7;
        c.insets = new Insets(5, 5, 5, 5);
        c.fill = GridBagConstraints.HORIZONTAL;
        this.add(startReflowBtn, c);
    }
    
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        switch(evt.getPropertyName()){
            case "reflowProfile":
                // A new reflow profile is loaded
                this.loadedProfileName.setText(this.appModel.getReflowProfile().getName());
                
                
                // Repaint the panel
                this.repaint();
                break;
        }
    }

}
