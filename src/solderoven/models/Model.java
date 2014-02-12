package solderoven.models;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * @author Daan Pape
 */
public abstract class Model {
    
    /**
     * Model propertychange support 
     */
    protected PropertyChangeSupport pcs;
    
    /**
     * Constructor
     */
    public Model(){
        pcs = new PropertyChangeSupport(this);
    }
    
    /* Add a propertychangelistener */
    public synchronized void addPropertyChangeListener(PropertyChangeListener l){
        this.pcs.addPropertyChangeListener(l);
    }
    
    /* Remove propertychangelistener */
    public synchronized void removePropertyChangeListener(PropertyChangeListener l){
        this.pcs.removePropertyChangeListener(l);
    }

}
