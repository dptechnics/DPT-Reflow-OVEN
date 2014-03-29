package solderoven.actions;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import solderoven.i18n.I18N;
import solderoven.models.AppModel;

/**
 * @author Daan Pape
 */
public class StartReflowAction extends AbstractAction {
    
    /**
     * The application model
     */
    private AppModel model;
    
    /**
     * Construct a new startReflowAction.
     * @param model the application model.
     */
    public StartReflowAction(AppModel model) {
        super(I18N.getInstance().getString("processStartBtn"));
        this.model = model;
    }
    
    /**
     * Start the reflow process.
     * @param e the action event.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO: start the reflow process
        System.out.println("Start the reflow process");
        model.getOvenBoard().getPWMController().startPWM();
    }

}
