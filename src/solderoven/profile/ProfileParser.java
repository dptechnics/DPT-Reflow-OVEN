package solderoven.profile;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

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
    public ReflowProfile parseFile(){
        // Check if the file is set
        if(this.profileFile != null){
            try {
                // Get the DOM document builder and parse into DOM
                DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
                Document dom = builder.parse(this.profileFile);
                
                // Parse the document
                Element root = dom.getDocumentElement();
                
                // Parse the profile name
                ReflowProfile profile = new ReflowProfile(root.getElementsByTagName("name").item(0).getTextContent());
                
                // Parse the profile products
                NodeList productNodes = root.getElementsByTagName("product");
                for(int i = 0; i < productNodes.getLength(); ++i){
                    NodeList nodeList = productNodes.item(i).getChildNodes();
                    Product product = new Product();
                    for(int j = 0; j < nodeList.getLength(); ++j) {
                        switch(nodeList.item(j).getNodeName()){
                            case "brand":
                                product.setBrand(nodeList.item(j).getTextContent());
                                break;
                            case "name":
                                product.setName(nodeList.item(j).getTextContent());
                                break;
                            case "alloy":
                                product.setAlloy(nodeList.item(j).getTextContent());
                                break;
                            case "leadfree":
                                product.setLeadFree(nodeList.item(j).getTextContent().equals("yes"));
                                break;
                            case "noclean":
                                product.setNoClean(nodeList.item(j).getTextContent().equals("yes"));
                                break;
                            case "powdersize":
                                product.setPowderSize(Integer.parseInt(nodeList.item(j).getTextContent()));
                                break;
                            case "partnr":
                                product.setPartNumber(nodeList.item(j).getTextContent());
                                break;
                            case "description":
                                product.setDescription(nodeList.item(j).getTextContent());
                                break;
                        }
                    }
                    
                    // Add the product to the profile
                    profile.addProduct(product);
                }
                
                // Parse the profile products
                NodeList phaseNodes = root.getElementsByTagName("phase");
                for(int i = 0; i < phaseNodes.getLength(); ++i){
                    NodeList nodeList = phaseNodes.item(i).getChildNodes();
                    ReflowPhase phase = new ReflowPhase();
                    for(int j = 0; j < nodeList.getLength(); ++j) {
                        switch(nodeList.item(j).getNodeName()){
                            case "name":
                                phase.setName(nodeList.item(j).getTextContent());
                                break;
                            case "start":
                                phase.setStart(Integer.parseInt(nodeList.item(j).getTextContent()));
                                break;
                            case "stop":
                                phase.setStop(Integer.parseInt(nodeList.item(j).getTextContent()));
                                break;
                            case "targettemp":
                                phase.setTarget(Integer.parseInt(nodeList.item(j).getTextContent()));
                                break;
                        }
                    }
                    
                    // Add the reflow phase to the profile
                    profile.addReflowPhase(phase);
                }
                
                // Return the reflow profile
                System.out.println(profile.toString());
                return profile;
            } catch (ParserConfigurationException | SAXException | IOException ex) {
                ex.printStackTrace();
                return null;
            }
        } else {
            return null;
        }
    }
}
