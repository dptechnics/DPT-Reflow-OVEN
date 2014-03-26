package solderoven.processcontrol;

/**
 * @author Daan Pape
 */
public interface PWMControllable {
    
    /**
     * This function is called to turn the PWM Controllable
     * device on and of. Processing in this function must
     * be as low as possible. 
     * @param state true if the device should be turned on.
     */
    public void setState(boolean state);
}
