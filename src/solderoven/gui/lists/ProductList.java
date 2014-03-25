package solderoven.gui.lists;

import java.awt.Component;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import solderoven.profile.Product;

/**
 * @author Daan Pape
 */
public class ProductList extends JList{
    
    /**
     * Construct a new productList.
     * @param listModel the ProductListModel containing list elements.
     */
    public ProductList(ProductListModel listModel) {
        super(listModel);
    
        // Set up list behavior
        this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.setLayoutOrientation(JList.VERTICAL);
        this.setVisibleRowCount(5);
        this.setCellRenderer(new ProductCellRenderer());
    }
    
    /**
     * The renderer that renders this 
     */
    private class ProductCellRenderer extends DefaultListCellRenderer {

        @Override
        public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            // Get the default JLabel and the product
            JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            Product product = (Product) value;
            
            // Build the text
            StringBuilder builder = new StringBuilder(product.getBrand());
            builder.append(" ");
            builder.append(product.getName());
            builder.append(" [");
            builder.append(product.getAlloy());
            builder.append("]");
            
            // Change the text from the product
            label.setText(builder.toString());
            
            return label;
        }
        
    }
}
