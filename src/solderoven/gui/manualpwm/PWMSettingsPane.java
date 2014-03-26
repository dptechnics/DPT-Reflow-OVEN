package solderoven.gui.manualpwm;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import solderoven.i18n.I18N;
import solderoven.models.AppModel;
import solderoven.processcontrol.PWMController;

/**
 * @author Daan Pape
 */
public class PWMSettingsPane extends JPanel{
    
    /**
     * The pwm controller
     */
    private PWMController controller;
    
    /**
     * Textfield containing the pwm period
     */
    private JTextField pwmPeriod;
    
    /**
     * Textfield containing the pwm duty cycle
     */
    private JTextField pwmDutyCycle;
    
    public PWMSettingsPane(AppModel model) {
        this.controller = model.getOvenBoard().getPWMController();
        
        // Use a gridbag layout manager
        this.setLayout(new GridBagLayout());
        
        // Add the period label
        JLabel periodLabel = new JLabel(I18N.getInstance().getString("pwmPeriod") + ":");
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(5, 5, 5, 5);
        c.fill = GridBagConstraints.HORIZONTAL;
        this.add(periodLabel, c);
        
        // Add the period textfield
        pwmPeriod = new JTextField(Integer.toString(controller.getPeriod()), 15);
        c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 0;
        c.insets = new Insets(5, 5, 5, 5);
        c.fill = GridBagConstraints.HORIZONTAL;
        this.add(pwmPeriod, c);
        
        // Add the period units label
        JLabel pwmPeriodUnitLabel = new JLabel("ms");
        c = new GridBagConstraints();
        c.gridx = 2;
        c.gridy = 0;
        c.insets = new Insets(5, 0, 5, 5);
        c.fill = GridBagConstraints.HORIZONTAL;
        this.add(pwmPeriodUnitLabel, c);

                // Add the period label
        JLabel dutyCycleLabel = new JLabel(I18N.getInstance().getString("pwmDutyCycle") + ":");
        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 1;
        c.insets = new Insets(5, 5, 5, 5);
        c.fill = GridBagConstraints.HORIZONTAL;
        this.add(dutyCycleLabel, c);
        
        // Add the period textfield
        pwmDutyCycle = new JTextField(Double.toString(controller.getDutyCycle()), 15);
        c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 1;
        c.insets = new Insets(5, 5, 5, 5);
        c.fill = GridBagConstraints.HORIZONTAL;
        this.add(pwmDutyCycle, c);
        
        // Add the period units label
        JLabel pwmDutyCycleUnitLabel = new JLabel("%");
        c = new GridBagConstraints();
        c.gridx = 2;
        c.gridy = 1;
        c.insets = new Insets(5, 0, 5, 5);
        c.fill = GridBagConstraints.HORIZONTAL;
        this.add(pwmDutyCycleUnitLabel, c);
    }
}
