package solderoven.models;

import solderoven.i18n.I18N;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 * @author Daan Pape
 */
public class TemperatureChartModel extends Model implements PropertyChangeListener{
    
    /**
     * The dataset containing the measured temperature data
     */
    private XYSeries measuredProfile;
    
    /**
     * The targeted reflow profile data;
     */
    private XYSeries targetProfile;
    
    /**
     * The model containing oven board data 
     */
    private BoardModel boardModel;
    
    /**
     * The title of the temperature graph
     */
    private String graphTitle;
    
    /**
     * The label of the x-axis 
     */
    private String xAxisLabel;
    
    /**
     * The label of the y-axis;
     */
    private String yAxisLabel;
    
    /**
     * Constructor initialising the XY data series 
     */
    public TemperatureChartModel(BoardModel model){
        // Set default graph names
        graphTitle = I18N.getInstance().getString("graphTitle");
        xAxisLabel = I18N.getInstance().getString("graphXAxis");
        yAxisLabel = I18N.getInstance().getString("graphYAxis");
        
        // Instantiate data series
        measuredProfile = new XYSeries(I18N.getInstance().getString("graphMeasured"));
        targetProfile = new XYSeries(I18N.getInstance().getString("graphTarget"));
        
        // Register with the board model
        this.boardModel = model;
        this.boardModel.addPropertyChangeListener(this);
        
        // STUB
        targetProfile.add(0, 20);
        targetProfile.add(90, 150);
        targetProfile.add(210, 183);
        targetProfile.add(240, 220);
        targetProfile.add(270, 183);
        targetProfile.add(300, 150);
    }
    
    /**
     * Get the up to date board data as XYSeriesCollection for the chart
     * @return the XYSeriesCollection
     */
    public XYDataset getChartDataset(){
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(measuredProfile);
        dataset.addSeries(targetProfile);
        return dataset;
    }
    
    /**
     * Set the label of the measured temperature graph.
     * @param label the label of the measured temperature graph.
     */
    public void setMeasuredLabel(String label){
        this.measuredProfile.setKey(label);
        pcs.firePropertyChange("measuredLabel", null, this.measuredProfile);
    }
    
    /**
     * Set the label of the target temperature graph.
     * @param label the label.
     */
    public void setTargetLabel(String label){
        this.targetProfile.setKey(label);
        pcs.firePropertyChange("targetLabel", null, this.targetProfile);
    }
    
    /**
     * Get the title of the graph.
     * @return the title of the graph.
     */
    public String getGraphTitle(){
        return this.graphTitle;
    }
    
    /**
     * Set the name of the x axis.
     * @param axisName the new x axis name.
     */
    public void setXAxisName(String axisName){
        this.xAxisLabel = axisName;
        pcs.firePropertyChange("xAxisLabel", null, this.xAxisLabel);
    }
    
    /**
     * Get the name of the x axis.
     * @return the name of the x axis.
     */
    public String getXAxisName(){
        return this.xAxisLabel;
    }
    
    /**
     * Set the name of the y axis.
     * @param axisName the new y axis name.
     */
    public void setYAxisName(String axisName){
        this.yAxisLabel = axisName;
        pcs.firePropertyChange("yAxisLabel", null, this.yAxisLabel);
    }
    
    /**
     * Get the name of the y axis.
     * @return the name of the y axis.
     */
    public String getYAxisName(){
        return this.yAxisLabel;
    }
    
    /**
     * Set the title of the temperature chart.
     * @param title the new title.
     */
    public void setGraphTitle(String title){
        this.graphTitle = title;
        pcs.firePropertyChange("graphTitle", null, this.graphTitle);
    }
    
    /**
     * This handler is called when the BoardModel changes
     * @param evt the event containing information
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        switch(evt.getPropertyName()){
            case "ovenData":
                // Handle new data in the BoardModel
                measuredProfile.add(boardModel.getCurrentTime(), boardModel.getCurrentStatus().getTemperature());
        
                // Propagate event to registered 
                pcs.firePropertyChange("chartData", null, this.getChartDataset());   
                break;
        }
    }
}
