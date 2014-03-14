package solderoven.gui;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DatasetChangeEvent;
import solderoven.models.TemperatureChartModel;

/**
 * @author Daan Pape
 */
public class TemperatureChart extends JPanel implements PropertyChangeListener{
    
    /**
     * The chart to be displayed to the user
     */
    private JFreeChart tempChart;
    
    /**
     * The panel containing the chart
     */
    private ChartPanel chartPanel;
    
    /**
     * The temperature chart data model 
     */
    private TemperatureChartModel model;
    
    /**
     * Constructor
     */
    public TemperatureChart(TemperatureChartModel model){
        this.model = model;

        // Create and show the temperature chart
        createAndShowChart();
        
        // Register as listener from the chartmodel
        model.addPropertyChangeListener(this);
    }
    
    /**
     * Create a new temperature chart 
     */
    private void createAndShowChart(){
        // Create line chart showing the temperature
        tempChart = ChartFactory.createXYLineChart(
                "",
                model.getXAxisName(),
                model.getYAxisName(),
                this.model.getChartDataset());
        
        // Set transparent background on the chart
        tempChart.setBackgroundPaint(null);
        
        // Add the chart to this panel
        chartPanel = new ChartPanel(tempChart); 
        this.add(chartPanel);
    }
    
    /**
     * This event handler is called when new chart data is available in the model. 
     * @param evt the event containing the chart data. 
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        switch(evt.getPropertyName()){
            case "chartData":
                // Update the chart dataset 
            case "measuredLabel":
                // Update the label of the measured graph
            case "targetLabel":
                // Update the label of the targat graph
                tempChart.getPlot().datasetChanged(new DatasetChangeEvent(this, model.getChartDataset()));
                tempChart.fireChartChanged();
                this.repaint();
                break;
            case "graphTitle":
                tempChart.setTitle(model.getGraphTitle());
                tempChart.fireChartChanged();
                this.repaint();
                break;
            case "xAxisLabel":
                // Change the x axis label
            case "yAxisLabel":
                // Change the y axis label
                this.remove(chartPanel);
                createAndShowChart();
        }
    }
}
