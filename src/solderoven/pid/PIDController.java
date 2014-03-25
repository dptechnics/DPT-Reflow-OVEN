package solderoven.pid;

/**
 * @author Daan Pape
 */
public class PIDController {
    
    /**
     * The proportional gain of the PID controller.
     */
    private double proportionalGain;
    
    /**
     * The integral gain of the PID controller.
     */
    private double integralGain;
    
    /**
     * The derivative gain of the PID controller.
     */
    private double derivativeGain;
    
    /**
     * The differentiator state.
     */
    private double derivativeState;
    
    /**
     * The integrator state.
     */
    private double integralState;
    
    /**
     * The maximum allowable integrator state
     */
    private double integratorMax;
    
    /**
     * The minimum allowable integrator state
     */
    private double integratorMin;
    
    /**
     * Calculate the proportional term from the PID control loop.
     * @param error the measured error in the current step.
     * @return the proportional feedback for the plant.
     */
    private double calcProportionalTerm(double error) {
        return this.proportionalGain * error;
    }
}
