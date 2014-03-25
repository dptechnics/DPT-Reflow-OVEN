package solderoven.actions;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.io.File;
import javax.swing.AbstractAction;
import solderoven.exception.ProfileParseException;
import solderoven.gui.dialogs.MessageDialog;
import solderoven.i18n.I18N;
import solderoven.models.AppModel;
import solderoven.profile.ProfileParser;

/**
 * @author Daan Pape
 */
public class LoadProfileAction extends AbstractAction{
    
    /**
     * The application model
     */
    private AppModel model;
    
    /**
     * The parent component;
     */
    private Component parent;
    
    /**
     * Constructor for setting text content.
     */
    public LoadProfileAction(AppModel model, Component parent) {
        super(I18N.getInstance().getString("btnLoad"));
        this.model = model;
        this.parent = parent;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        File file = MessageDialog.getInstance().openReflowProfile(this.parent);
                
        // Testing
        try {
            this.model.setReflowProfile(new ProfileParser(file).parseFile());
        } catch (ProfileParseException ex) {
            //TODO: handle
        }
    }

}
