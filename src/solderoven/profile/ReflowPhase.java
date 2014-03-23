package solderoven.profile;

/**
 * Represents a reflow phase.
 * @author Daan Pape
 */
public class ReflowPhase {
    
    /**
     * The name of the phase.
     */
    protected String name;
    
    /**
     * The start time in seconds counting from 0.
     */
    protected int start;
    
    /**
     * The stop time in seconds counting from 0.
     */
    protected int stop;
    
    /**
     * The target temperature to reach at the end of the phase.
     */
    protected int target;
    
    /**
     * Construct a new ReflowPhase.
     * @param name the name of the phase.
     * @param start the start time of the phase in seconds counting from 0.
     * @param stop the stop time of the phase in seconds counting from 0.
     * @param target the target temperature to reach at the end of the phase.
     */
    public ReflowPhase(String name, int start, int stop, int target){
        this.name = name;
        this.start = start;
        this.stop = stop;
        this.target = target;
    }
    
    /**
     * Default constructor for lazy constructing.
     */
    public ReflowPhase() {
        this("", 0, 0, 0);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getStop() {
        return stop;
    }

    public void setStop(int stop) {
        this.stop = stop;
    }

    public int getTarget() {
        return target;
    }

    public void setTarget(int target) {
        this.target = target;
    }
    
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("name: ");
        builder.append(this.name);
        builder.append(", start: ");
        builder.append(this.start);
        builder.append(", stop: ");
        builder.append(this.stop);
        builder.append(", targettemp: ");
        builder.append(this.target);
        return builder.toString();
    }
}
