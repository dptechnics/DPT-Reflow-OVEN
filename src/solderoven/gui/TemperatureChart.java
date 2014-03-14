package solderoven.gui;

import java.awt.Color;
import java.awt.Font;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.annotations.XYTitleAnnotation;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.title.LegendTitle;
import org.jfree.data.general.DatasetChangeEvent;
import org.jfree.ui.RectangleAnchor;
import org.jfree.ui.RectangleEdge;
import org.jfree.ui.VerticalAlignment;
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
        
        // Position the chart legend in the plot area and format it
        LegendTitle legend = tempChart.getLegend();
        legend.setPosition(RectangleEdge.BOTTOM);
        legend.setItemFont(new Font("Arial", Font.PLAIN, 14));
        legend.setBackgroundPaint(new Color(200, 200, 255, 100));
        legend.setFrame(new BlockBorder(Color.WHITE));
        XYTitleAnnotation ta = new XYTitleAnnotation(0.98, 0.02, legend, RectangleAnchor.BOTTOM_RIGHT);
        ta.setMaxWidth(0.30);
        ((XYPlot) tempChart.getPlot()).addAnnotation(ta);
        tempChart.removeLegend();
        
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
