package org.balthie.demo.opensource.chart.jfreechart;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.geom.Ellipse2D;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.HorizontalAlignment;
import org.jfree.ui.RectangleEdge;
import org.jfree.ui.RefineryUtilities;

public class LineChartDemo1 extends ApplicationFrame
{
	public LineChartDemo1(String paramString)
	  {
	    super(paramString);
	    JPanel localJPanel = createDemoPanel();
	    localJPanel.setPreferredSize(new Dimension(500, 270));
	    setContentPane(localJPanel);
	  }

	  private static CategoryDataset createDataset()
	  {
	    DefaultCategoryDataset localDefaultCategoryDataset = new DefaultCategoryDataset();
	    localDefaultCategoryDataset.addValue(212.0D, "Classes", "JDK 1.0");
	    localDefaultCategoryDataset.addValue(504.0D, "Classes", "JDK 1.1");
	    localDefaultCategoryDataset.addValue(1520.0D, "Classes", "JDK 1.2");
	    localDefaultCategoryDataset.addValue(1842.0D, "Classes", "JDK 1.3");
	    localDefaultCategoryDataset.addValue(2991.0D, "Classes", "JDK 1.4");
	    localDefaultCategoryDataset.addValue(3500.0D, "Classes", "JDK 1.5");
	    return localDefaultCategoryDataset;
	  }

	  private static JFreeChart createChart(CategoryDataset paramCategoryDataset)
	  {
	    JFreeChart localJFreeChart = ChartFactory.createLineChart("Java Standard Class Library", null, "Class Count", paramCategoryDataset, PlotOrientation.VERTICAL, false, true, false);
	    localJFreeChart.addSubtitle(new TextTitle("Number of Classes By Release"));
	    TextTitle localTextTitle = new TextTitle("Source: Java In A Nutshell (5th Edition) by David Flanagan (O'Reilly)");
	    localTextTitle.setFont(new Font("SansSerif", 0, 10));
	    localTextTitle.setPosition(RectangleEdge.BOTTOM);
	    localTextTitle.setHorizontalAlignment(HorizontalAlignment.RIGHT);
	    localJFreeChart.addSubtitle(localTextTitle);
	    
	    //TODO ���ñ�����ɫ
/*	    localJFreeChart.setBackgroundPaint(Color.blue);
	    localJFreeChart.setBorderPaint(Color.blue);*/
	    
	    CategoryPlot localCategoryPlot = (CategoryPlot)localJFreeChart.getPlot();
	    
	    localCategoryPlot.setRangePannable(true);
	    //���ñ���������
	    localCategoryPlot.setRangeGridlinesVisible(true);
	    //TODO ���ñ�����ɫ ���������ò�����Ч����֪���������ĸ����������� �ڷ���ǰ��������
	    localCategoryPlot.setBackgroundPaint(Color.BLUE);
	    System.out.println(localCategoryPlot.getBackgroundPaint());
	    
	    localCategoryPlot.setBackgroundAlpha(1F);
	    
	    URL localURL = LineChartDemo1.class.getClassLoader().getResource("test/jfreechart/01-2.png");
	    Object localObject = null;
	    if (localURL != null)
	    {
	      localObject = new ImageIcon(localURL);
	      localCategoryPlot.setBackgroundPaint(Color.BLUE);
	      localCategoryPlot.setBackgroundImage(((ImageIcon)localObject).getImage());
	    }
	    localObject = (NumberAxis)localCategoryPlot.getRangeAxis();
	    ((NumberAxis)localObject).setStandardTickUnits(NumberAxis.createIntegerTickUnits());
	    
	    ChartUtilities.applyCurrentTheme(localJFreeChart);
	    System.out.println(localCategoryPlot.getBackgroundPaint());
	    LineAndShapeRenderer localLineAndShapeRenderer = (LineAndShapeRenderer)localCategoryPlot.getRenderer();
	    localLineAndShapeRenderer.setBaseShapesVisible(true);
	    localLineAndShapeRenderer.setDrawOutlines(true);
	    localLineAndShapeRenderer.setUseFillPaint(true);
	    localLineAndShapeRenderer.setBaseFillPaint(Color.white);
	    localLineAndShapeRenderer.setSeriesStroke(0, new BasicStroke(3.0F));
	    localLineAndShapeRenderer.setSeriesOutlineStroke(0, new BasicStroke(2.0F));
	    localLineAndShapeRenderer.setSeriesPaint(0, Color.blue);

	    localLineAndShapeRenderer.setSeriesShape(0, new Ellipse2D.Double(-5.0D, -5.0D, 10.0D, 10.0D));
	    
	    
	    localCategoryPlot.setBackgroundPaint(new Color(153,200,255));
	    return ((JFreeChart)localJFreeChart);
	  }

	  public static JPanel createDemoPanel()
	  {
	    JFreeChart localJFreeChart = createChart(createDataset());
	    localJFreeChart.setBackgroundPaint(Color.WHITE);
	    
	    ChartPanel localChartPanel = new ChartPanel(localJFreeChart);
	    localChartPanel.setMouseWheelEnabled(true);
	    return localChartPanel;
	  }

	  public static void main(String[] paramArrayOfString)
	  {
	    LineChartDemo1 localLineChartDemo1 = new LineChartDemo1("JFreeChart: LineChartDemo1.java");
	    localLineChartDemo1.pack();
	    RefineryUtilities.centerFrameOnScreen(localLineChartDemo1);
	    localLineChartDemo1.setBackground(Color.RED);
	    localLineChartDemo1.setVisible(true);
	    
	    
	    File file = new File("d:\\xxx.jpg");
	    try
		{
			ChartUtilities.saveChartAsJPEG(file, createChart(createDataset()), 500, 400);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	  }
}
