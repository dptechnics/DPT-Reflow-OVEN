package solderoven.processcontrol;

import solderoven.exception.ExceptionHandler;

/**
 * @author Daan Pape
 */
public class PWMController {
    
    /**
     * The period in milliseconds for a full square wave.
     */
    private int period;
    
    /**
     * The duty cycle in percent.
     */
    private double dutyCycle;
    
    /**
     * The number of milliSeconds the device must be turned on.
     */
    private int deviceOnTime;
    
    /**
     * The number of milliSeconds the device must be turned of.
     */
    private int deviceOffTime;
    
    /**
     * The running state of the PWM Controller, true
     * if the controller is running.
     */
    private boolean runningState;
    
    /**
     * The device to control via PWM.
     */
    private PWMControllable device;
    
    /**
     * Construct a new PWM controller with a standard
     * period of 2.5 seconds and 50% duty cycle. 
     * @param device the device to control.
     */
    public PWMController(PWMControllable device) {
        this.period = 2500;
        this.dutyCycle = 50;
        this.device = device;
        this.runningState = false;
        
        // Update the times
        updateTiming();
    }
    
    /**
     * Start the PWM Control
     */
    public void startPWM() {
        this.runningState = true;
        new PWMThread().start();
    }
    
    public void stopPWM() {
        this.runningState = false;
    }
    
    /**
     * Recalculate the device on/off times.
     */
    private synchronized void updateTiming() {
        
        this.deviceOnTime = (int) ((this.period * this.dutyCycle)/100);
        this.deviceOffTime = this.period - this.deviceOnTime;
    }
    
    /**
     * Get the PWM period in milliseconds.
     * @return the PWM period.
     */
    public int getPeriod() {
        return this.period;
    }
    
    /**
     * Get the PWM duty cycle in percent.
     * @return the PWM duty cycle.
     */
    public double getDutyCycle() {
        return this.dutyCycle;
    }
    
    /**
     * Set a new PWM period.
     * @param period the new PWM period.
     */
    public void setPeriod(int period) {
        this.period = period;
        
        // Update PWM timing to adjust to new period
        this.updateTiming();
    }
    
    /**
     * Set a new PWM dutyCycle.
     * @param dutyCycle the new PWM duty cycle.
     */
    public void setDutyCycle(double dutyCycle) {
        this.dutyCycle = dutyCycle;
        
        // Update PWM timing to adjust to new period
        this.updateTiming();
    }
    
    /**
     * Get the time the device should be turned on.
     * @return the time in milliseconds the device should be turned on.
     */
    public synchronized int getDeviceOnTime() {
        return this.deviceOnTime;
    }
    
    /**
     * Get the time the device should be turned off.
     * @return the time in milliseconds the device should be turned off.
     */
    public synchronized int getDeviceOffTime() {
        return this.deviceOffTime;
    }
    
    /**
     * Thread for controlling the PWM device
     */
    private class PWMThread extends Thread {
        
        /**
         * Construct a new PWM Thread
         */
        public PWMThread() {
            super(new Runnable() {

                @Override
                public void run() {
                    // Make sure the device is turned off
                    device.setState(false);
                    
                    while(runningState) {
                        try {
                            device.setState(true);
                            Thread.sleep(getDeviceOnTime());
                            device.setState(false);
                            Thread.sleep(getDeviceOffTime());
                        } catch (InterruptedException ex) {
                            // TODO handle this error
                            ExceptionHandler.getInstance().handleException(ex);
                        }
                    }
                }
            }, "PWM Control");
        }
    }
}
