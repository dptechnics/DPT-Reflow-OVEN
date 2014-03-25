package solderoven.gui.lists;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;
import javax.swing.AbstractListModel;
import solderoven.models.AppModel;
import solderoven.profile.ReflowPhase;

/**
 * @author Daan Pape
 */
public class PhaseListModel extends AbstractListModel implements PropertyChangeListener{
    
    /**
     * The application model.
     */
    private AppModel model;
    
    /**
     * The product list.
     */
    private List<ReflowPhase> phaseList;
    
    /**
     * Create a new PhaseListModel.
     * @param model the application model
     */
    public PhaseListModel(AppModel model) {
        this.model = model;
        
        // Register the list model as listener to the app model
        this.model.addPropertyChangeListener(this);
        
        // Load list data
        if(this.model.getReflowProfile() != null) {
            this.phaseList = this.model.getReflowProfile().getReflowPhases();
        }
    }
    
    /**
     * Get the size of list.
     * @return the list size. 
     */
    @Override
    public int getSize() {
        if(this.phaseList != null) {
            return this.phaseList.size();
        } else {
            return 0;
        }
    }
    
    /**
     * Get the reflow phase at position index in the list.
     * @param index the phases index.
     * @return the phase at index 'index' in the list.
     */
    @Override
    public Object getElementAt(int index) {
        return this.phaseList.get(index);
    }

    /**
     * Handle reflow profile change events.
     * @param evt the event data.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if(evt.getPropertyName().equals("reflowProfile")) {
            // The loaded reflow profile has changed
            this.phaseList = this.model.getReflowProfile().getReflowPhases();
            
            // Update all lists
            this.fireContentsChanged(this, 0, this.getSize() - 1);
        }
    }

}
