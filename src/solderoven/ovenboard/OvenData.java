package solderoven.ovenboard;

/**
 * @author Daan Pape
 */
public class OvenData {
    /**
     * Current oven temperature
     */
    private float temperature;
    
    /**
     * Oven thermocouple status, true if status is OK 
     */
    private boolean thermocouple;
    
    /**
     * Oven heater elements status, true if switched on
     */
    private boolean heater;
    
    /**
     * Oven fan, true if switched on
     */
    private boolean fan;
    
    /**
     * Optional oven cool mechanism, true if switched on
     */
    private boolean cool;
    
    public OvenData(float temperature, boolean sensorState, boolean heaterState, boolean fanState, boolean coolState){
        this.temperature = temperature;
        this.thermocouple = sensorState;
        this.heater = heaterState;
        this.fan = fanState;
        this.cool = coolState;
    }
    
    /**
     * Default constructor defaulting all data to zero. 
     */
    public OvenData() {
        temperature = 0.0f;
        thermocouple = false;
        heater = false;
        fan = false;
        cool = false;
    }
    
    /**
     * Get the temperature from the oven. 
     * @return the oven temperature. 
     */
    public float getTemperature(){
        return this.temperature;
    }
    
    /**
     * Get the state of the sensor connection. 
     * @return true if the sensor is connected properly. 
     */
    public boolean isSensorConnected(){
        return this.thermocouple;
    }
    
    /**
     * Get the status of the heating elements in the oven. 
     * @return true if the heating elements are on. 
     */
    public boolean isHeaterOn(){
        return this.heater;
    }
    
    /**
     * Get the status of the fan in the oven. 
     * @return true if the fan is on. 
     */
    public boolean isFanOn(){
        return this.fan;
    }
    
    /**
     * Get the status of the optional cooling mechanism. 
     * @return true if the cooling mechanism is on. 
     */
    public boolean isCoolingOn(){
        return this.cool;
    }
    
    /**
     * Set the current oven temperature. 
     * @param temperature the current oven temperature.
     */
    public void setTemperature(float temperature){
        this.temperature = temperature;
    }
    
    /**
     * Set the state of the sensor connected to the board.
     * @param state true if the sensor is properly connected.
     */
    public void setSensorState(boolean state){
        this.thermocouple = state;
    }
    
    /**
     * Set the state of the oven heating elements.
     * @param state true if the heating elements are switched on.
     */
    public void setHeaterState(boolean state){
        this.heater = state;
    }
    
    /**
     * Set the state of the oven fan.
     * @param state true if the fan is switched on. 
     */
    public void setFanState(boolean state){
        this.fan = state;
    }
    
    /**
     * Set the state of the optional oven cooling mechanism. 
     * @param state true if the mechanism is switched on. 
     */
    public void setCoolingState(boolean state){
        this.cool = state;
    }
}
