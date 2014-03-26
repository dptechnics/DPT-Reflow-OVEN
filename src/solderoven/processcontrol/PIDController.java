package solderoven.processcontrol;

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
    private double differentialGain;
    
    /**
     * The sample period in milliseconds
     */
    private double samplePeriod;
    
    /**
     * The differentiator state.
     */
    private double derivativeState;
    
    /**
     * The integrator state.
     */
    private double integralState;
    
    /**
     * The maximum allowable integrator state.
     */
    private double integratorMax;
    
    /**
     * The minimum allowable integrator state.
     */
    private double integratorMin;
    
    /**
     * The error in the current step.
     */
    private double error;
    
    /**
     * The error in the previous step.
     */
    private double previousError;
    
    /**
     * The current target setPoint.
     */
    private double setPoint;
    
    /**
     * Construct a new PID controller
     */
    public PIDController() {
        
    }
    
    /**
     * Step the PID to the next step.
     * @param currentValue the current measured value from the plant
     * @return the PID feedback.
     */
    public double updatePID(double currentValue) {
        // Save the previous error and calculate new one.
        this.previousError = this.error;
        this.error = this.setPoint - currentValue;
        
        // Calculate P, I, and D terms and return.
        return this.calcProportionalTerm() + this.calcIntegralTerm() + this.calcDifferentialTerm();
    }
    
    /**
     * Calculate the proportional term from the PID control loop.
     * @return the proportional feedback for the plant.
     */
    private double calcProportionalTerm() {
        return this.proportionalGain * this.error;
    }
    
    /**
     * Calculate the integral term from the PID control loop. 
     * @return the integrator feedback for the plant.
     */
    private double calcIntegralTerm() {
        integralState += this.error * this.samplePeriod;
        
        // Limit the integral
        if(this.integralState > this.integratorMax) {
            this.integralState = this.integratorMax;
        } else if(this.integralState < this.integratorMin) {
            this.integralState = this.integratorMin;
        }
        
        return this.integralState * this.integralGain;
    }
    
    /**
     * Calculate the differential term from the PID control loop.
     * @return the differential 
     */
    private double calcDifferentialTerm() {
        return ((this.error - this.previousError)/this.samplePeriod) * this.differentialGain;
    }
}
