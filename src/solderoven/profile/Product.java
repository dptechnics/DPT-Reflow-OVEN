package solderoven.profile;

/**
 * Represents a solderpaste product.
 * @author Daan Pape
 */
public class Product {
    /**
     * The brand of solderpaste.
     */
    protected String brand;
    
    /**
     * The name of the solderpaste.
     */
    protected String name;
    
    /**
     * The alloy of the metal used in the paste.
     */
    protected String alloy;
    
    /**
     * True if the paste contains no lead.
     */
    protected boolean leadFree;
    
    /**
     * True if the paste contains no-clean flux.
     */
    protected boolean noClean;
    
    /**
     * The size of the metal balls according to IPC-J-STD-006B standard.
     */
    protected int powderSize;
    
    /**
     * The manufacturer part number.
     */
    protected String partNumber;
    
    /**
     * Description of this paste.
     */
    protected String description;
    
    /**
     * Construct a new solderpaste product.
     * @param brand the brand of solderpaste.
     * @param name the name of the solderpaste.
     * @param alloy the alloy of the metal used in the paste.
     * @param leadFree true if the paste contains no lead.
     * @param noClean true if the paste contains no-clean flux.
     * @param powderSize the size of the metal balls according to IPC-J-STD-006B standard. 
     * @param partNumber the manufacturer part number.
     * @param description description of this paste.
     */
    public Product(String brand, String name, String alloy, boolean leadFree, boolean noClean, int powderSize, String partNumber, String description){
        this.brand = brand;
        this.name = name;
        this.leadFree = leadFree;
        this.noClean = noClean;
        this.powderSize = powderSize;
        this.partNumber = partNumber;
        this.description = description;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlloy() {
        return alloy;
    }

    public void setAlloy(String alloy) {
        this.alloy = alloy;
    }

    public boolean isLeadFree() {
        return leadFree;
    }

    public void setLeadFree(boolean leadFree) {
        this.leadFree = leadFree;
    }

    public boolean isNoClean() {
        return noClean;
    }

    public void setNoClean(boolean noClean) {
        this.noClean = noClean;
    }

    public int getPowderSize() {
        return powderSize;
    }

    public void setPowderSize(int powderSize) {
        this.powderSize = powderSize;
    }

    public String getPartNumber() {
        return partNumber;
    }

    public void setPartNumber(String partNumber) {
        this.partNumber = partNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
