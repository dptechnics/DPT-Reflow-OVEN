package solderoven.profile;

import java.io.File;

/**
 * Internal representation of a reflow profile.
 * @author Daan Pape
 */
public class ReflowProfile {
    
    /**
     * The location of the XML profile file on disk.
     */
    private File profileFile;
    
    /**
     * The name of this profile
     */
    protected String name;
    
    /**
     * Create a new reflow profile from a file.
     * @param profileFile the reflow profile file.
     */
    public ReflowProfile(File profileFile) {
        this.profileFile = profileFile;
    }
    
    /**
     * Create a new reflow profile.
     */
    public ReflowProfile(){
        this(null);
    }
}
