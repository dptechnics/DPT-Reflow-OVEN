package solderoven.gui.lists;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;
import javax.swing.AbstractListModel;
import solderoven.models.AppModel;
import solderoven.profile.Product;

/**
 * @author Daan Pape
 */
public class ProductListModel extends AbstractListModel implements PropertyChangeListener{
    
    /**
     * The application model.
     */
    private AppModel model;
    
    /**
     * The product list.
     */
    private List<Product> productList;
    
    /**
     * Create a new ProductListModel.
     * @param model the application model
     */
    public ProductListModel(AppModel model) {
        this.model = model;
        
        // Register the list model as listener to the app model
        this.model.addPropertyChangeListener(this);
        
        // Load list data
        if(this.model.getReflowProfile() != null) {
            this.productList = this.model.getReflowProfile().getReflowProducts();
        }
    }
    
    /**
     * Get the size of list.
     * @return the list size. 
     */
    @Override
    public int getSize() {
        if(this.productList != null) {
            return this.productList.size();
        } else {
            return 0;
        }
    }
    
    /**
     * Get the product at position index in the list.
     * @param index the products index.
     * @return the product at index 'index' in the list.
     */
    @Override
    public Object getElementAt(int index) {
        return this.productList.get(index);
    }

    /**
     * Handle reflow profile change events.
     * @param evt the event data.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if(evt.getPropertyName().equals("reflowProfile")) {
            // The loaded reflow profile has changed
            this.productList = this.model.getReflowProfile().getReflowProducts();
            
            // Update all lists
            this.fireContentsChanged(this, 0, this.getSize() - 1);
        }
    }

}
