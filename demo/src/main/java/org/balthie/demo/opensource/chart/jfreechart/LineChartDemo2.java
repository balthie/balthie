package org.balthie.demo.opensource.chart.jfreechart;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.geom.Ellipse2D;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.swing.ImageIcon;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

public class LineChartDemo2
{
	  public static void main(String[] paramArrayOfString)
	  {
	    File file = new File("d:\\xxx.jpg");
	    JFreeChart chart = createChart(createDataset());
	    chart.getTitle().setFont(new Font("宋体",Font.BOLD,20));
	    
	    CategoryAxis domainAxis = chart.getCategoryPlot().getDomainAxis();   
	    /*------设置X轴坐标上的文字-----------*/ 
	    domainAxis.setTickLabelFont(new Font("黑体", Font.PLAIN, 12));   
	    /*------设置X轴的标题文字------------*/ 
	    domainAxis.setLabelFont(new Font("宋体", Font.PLAIN, 12));   
	    
	    NumberAxis numberaxis = (NumberAxis) chart.getCategoryPlot().getRangeAxis();   
	    /*------设置Y轴坐标上的文字-----------*/ 
	    numberaxis.setTickLabelFont(new Font("黑体", Font.PLAIN, 12));   
	    /*------设置Y轴的标题文字------------*/ 
	    numberaxis.setLabelFont(new Font("黑体", Font.PLAIN, 12));
	    
	    try
		{
			ChartUtilities.saveChartAsJPEG(file, chart, 500, 400);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	  }
	
	private static CategoryDataset createDataset()
	{
		DefaultCategoryDataset localDefaultCategoryDataset = new DefaultCategoryDataset();
		
		localDefaultCategoryDataset.addValue(212.0D, "xxx", "JDK 1.0");
		localDefaultCategoryDataset.addValue(504.0D, "xxx", "JDK 1.1");
		localDefaultCategoryDataset.addValue(1520.0D, "xxx", "JDK 1.2");
		localDefaultCategoryDataset.addValue(1842.0D, "xxx", "JDK 1.3");
		localDefaultCategoryDataset.addValue(2991.0D, "xxx", "JDK 1.4");
		localDefaultCategoryDataset.addValue(3500.0D, "xxx", "JDK 1.5");
		
		localDefaultCategoryDataset.addValue(14234.0D, "ccc", "JDK 1.3");
		localDefaultCategoryDataset.addValue(2123.0D, "ccc", "JDK 1.4");
		localDefaultCategoryDataset.addValue(1200.0D, "ccc", "JDK 1.5");
		return localDefaultCategoryDataset;
	}

	private static JFreeChart createChart(CategoryDataset paramCategoryDataset)
	{
		JFreeChart localJFreeChart = ChartFactory.createLineChart("本门店最近6个月积分趋势","积分","月份",
				paramCategoryDataset, PlotOrientation.VERTICAL, false, true, false);
		
		//TODO　副标题
		/*localJFreeChart.addSubtitle(new TextTitle("Number of Classes By Release"));
		TextTitle localTextTitle = new TextTitle("Source: Java In A Nutshell (5th Edition) by David Flanagan (O'Reilly)");
		localTextTitle.setFont(new Font("SansSerif", 0, 10));
		localTextTitle.setPosition(RectangleEdge.BOTTOM);
		localTextTitle.setHorizontalAlignment(HorizontalAlignment.RIGHT);
		localJFreeChart.addSubtitle(localTextTitle);
		*/
		
		CategoryPlot localCategoryPlot = (CategoryPlot) localJFreeChart.getPlot();
		localCategoryPlot.setRangePannable(true);
		localCategoryPlot.setRangeGridlinesVisible(false);
		
		Object localObject = null;
		
		//背景图片
		URL localURL = LineChartDemo1.class.getClassLoader().getResource("OnBridge11small.png");
		if (localURL != null)
		{
			localObject = new ImageIcon(localURL);
			localJFreeChart.setBackgroundImage(((ImageIcon) localObject).getImage());
			localCategoryPlot.setBackgroundPaint(null);
		}
		
		localObject = (NumberAxis) localCategoryPlot.getRangeAxis();
		((NumberAxis) localObject).setStandardTickUnits(NumberAxis.createIntegerTickUnits());
		ChartUtilities.applyCurrentTheme(localJFreeChart);
		
		LineAndShapeRenderer localLineAndShapeRenderer = (LineAndShapeRenderer) localCategoryPlot.getRenderer();
		//节点图标
		localLineAndShapeRenderer.setBaseShapesVisible(true);
		localLineAndShapeRenderer.setDrawOutlines(true);
		//背景
		localLineAndShapeRenderer.setUseFillPaint(true);
		localLineAndShapeRenderer.setBaseFillPaint(Color.white);
		localLineAndShapeRenderer.setSeriesStroke(0, new BasicStroke(3.0F));
		localLineAndShapeRenderer.setSeriesOutlineStroke(0, new BasicStroke(2.0F));
		localLineAndShapeRenderer.setSeriesShape(0, new Ellipse2D.Double(-5.0D, -5.0D, 10.0D, 10.0D));
		
		return ((JFreeChart) localJFreeChart);
	}
}
