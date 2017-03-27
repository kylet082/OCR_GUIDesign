package kgt.dev.ocr_gui.view.charts;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

public class HistogramChart {

	private double[] histData;
	
	/**
	 * CONSTRUCTOR 
	 * 
	 * @param data
	 */
	public HistogramChart(double[] data){
		this.histData = data;
	}
	
	/**
	 * @param docName - name of the chart
	 * @return - drawn chart
	 */
	public JPanel draw(String docName){
		
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		
		for(int x = 0;x < histData.length;x++){
			dataset.addValue(histData[x], ""+x, " ");
		}
		
		JFreeChart histChart = ChartFactory.createBarChart(docName, "Page Length",
				"Density", dataset, PlotOrientation.HORIZONTAL,false,false,false);
		
		ChartPanel chartPane = new ChartPanel(histChart);
		chartPane.setPreferredSize(new Dimension(400,600));
		
		CategoryPlot plot = histChart.getCategoryPlot();
		BarRenderer renderer = (BarRenderer) plot.getRenderer();
		renderer.setItemMargin(-3);
		
		for(int y = 0; y < histData.length;y++){
			renderer.setSeriesPaint(y, new Color(255,0,0));
		}
		
		return chartPane;
	}
}
