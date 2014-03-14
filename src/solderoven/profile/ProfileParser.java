package solderoven.profile;

import java.io.File;

/**
 * @author Daan Pape
 */
public class ProfileParser {

    /**
     * The location of the XML profile file on disk.
     */
    private File profileFile;
    
    /**
     * Construct a new profileParser.
     * @param profileFile the file to parse
     */
    public ProfileParser(File profileFile){
        this.profileFile = profileFile;
    }
    
    /**
     * Parse the XML file and read data.
     */
    public void parseFile(){
        // Check if the file is set
        if(this.profileFile != null){
            
        } else {
            
        }
    }
}
