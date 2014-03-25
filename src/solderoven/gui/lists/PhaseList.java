package solderoven.gui.lists;

import java.awt.Component;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import solderoven.i18n.I18N;
import solderoven.profile.ReflowPhase;

/**
 * @author Daan Pape
 */
public class PhaseList extends JList{
    
    /**
     * Construct a new phaseList.
     * @param listModel the PhaseListModel containing list elements.
     */
    public PhaseList(PhaseListModel listModel) {
        super(listModel);
    
        // Set up list behavior
        this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.setLayoutOrientation(JList.VERTICAL);
        this.setVisibleRowCount(5);
        this.setCellRenderer(new PhaseCellRenderer());
    }
    
    /**
     * The renderer that renders this 
     */
    private class PhaseCellRenderer extends DefaultListCellRenderer {

        @Override
        public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            // Get the default JLabel and the phase
            JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            ReflowPhase phase = (ReflowPhase) value;
            
            // Build the text
            StringBuilder builder = new StringBuilder(I18N.getInstance().getString("phaseStart"));
            builder.append(": ");
            builder.append(phase.getStart());
            builder.append("s, ");
            builder.append(I18N.getInstance().getString("phaseStop"));
            builder.append(": ");
            builder.append(phase.getStop());
            builder.append("s, ");
            builder.append(I18N.getInstance().getString("phaseTarget"));
            builder.append(": ");
            builder.append(phase.getTarget());
            builder.append("°C");
            
            // Change the text from the phase label
            label.setText(builder.toString());
            
            return label;
        }
        
    }
}
