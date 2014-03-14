package solderoven.profile;

import java.io.File;
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
     * Create a new reflow profile from a file.
     * @param name the name of the reflow profile.
     * @param products list of products associated with this reflow profile.
     * @products phases the phases forming this reflow profile.
     */
    public ReflowProfile(String name, List<Product> products, List<ReflowPhase> phases) {
        this.name = name;
        this.products = products;
        this.phases = phases;
    }
}
