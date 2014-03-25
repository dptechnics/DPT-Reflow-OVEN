package solderoven.profile;

import java.util.ArrayList;
import java.util.List;

/**
 * Internal representation of a reflow profile.
 * @author Daan Pape
 */
public class ReflowProfile {
    
    /**
     * The name of this profile.
     */
    protected String name;
    
    /**
     * List of products associated with this reflow profile.
     */
    protected List<Product> products;
    
    /**
     * List of phases forming this reflow profile.
     */
    protected List<ReflowPhase> phases;
    
    /**
     * Create a new reflow profile given all data.
     * @param name the name of the reflow profile.
     * @param products list of products associated with this reflow profile.
     * @products phases the phases forming this reflow profile.
     */
    public ReflowProfile(String name, List<Product> products, List<ReflowPhase> phases) {
        this.name = name;
        this.products = products;
        this.phases = phases;
    }
    
    /**
     * Create a new reflow profile given a name but no data.
     * @param name the name of the reflow profile.
     */
    public ReflowProfile(String name) {
        this(name, new ArrayList<Product>(), new ArrayList<ReflowPhase>());
    }
    
    /**
     * Default empty constructor for lazy constructing.
     */
    public ReflowProfile() {
        this("");
    }
    
    /**
     * Set the name of the reflow profile.
     * @param name the reflow profile name.
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * Add a product to the reflow profile.
     * @param product the product to add.
     */
    public void addProduct(Product product){
        this.products.add(product);
    }
    
    /**
     * Add a reflow phase to the reflow profile.
     * @param reflowPhase the reflowphase to add.
     */
    public void addReflowPhase(ReflowPhase reflowPhase) {
        this.phases.add(reflowPhase);
    }
    
    /**
     * Get the name of this reflow profile.
     * @return the name of the reflow profile.
     */
    public String getName() {
        return this.name;
    }
    
    /**
     * Get all the products supported by this profile.
     * @return the list of products supported by this profile.
     */
    public List<Product> getReflowProducts() {
        return this.products;
    }
    
    /**
     * Get all the reflow phases present in this profile.
     * @return all the reflow phases.
     */
    public List<ReflowPhase> getReflowPhases() {
        return this.phases;
    }
    
    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder("Name: ");
        builder.append(this.name);
        builder.append(System.lineSeparator());
        builder.append("Products:");
        builder.append(System.lineSeparator());
        for(Product p : this.products) {
            builder.append("\t");
            builder.append(p.toString());
            builder.append(System.lineSeparator());
        }
        builder.append(System.lineSeparator());
        builder.append("Reflow phases:");
        builder.append(System.lineSeparator());
        for(ReflowPhase p : this.phases) {
            builder.append("\t");
            builder.append(p.toString());
            builder.append(System.lineSeparator());
        }
        
        // Return the string
        return builder.toString();
    }
}
