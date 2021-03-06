package org.balthie.demo.opensource.chart.jfreechart;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.io.File;
import java.io.IOException;

import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardCategorySeriesLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

public class BarChartDemo1 extends ApplicationFrame
{
	public BarChartDemo1(String paramString)
	{
		super(paramString);
		JPanel localJPanel = createDemoPanel();
		localJPanel.setPreferredSize(new Dimension(500, 270));
		setContentPane(localJPanel);
	}

	private static CategoryDataset createDataset()
	{
		String str1 = "���ֿͻ���";
		String str2 = "�����ͻ���";
		String str4 = "1";
		String str5 = "2";
		String str6 = "3";
		String str7 = "3";
		String str8 = "4";
		DefaultCategoryDataset localDefaultCategoryDataset = new DefaultCategoryDataset();
		localDefaultCategoryDataset.addValue(1.0D, str1, str4);
		localDefaultCategoryDataset.addValue(4.0D, str1, str5);
		localDefaultCategoryDataset.addValue(3.0D, str1, str6);
		localDefaultCategoryDataset.addValue(5.0D, str1, str7);
		localDefaultCategoryDataset.addValue(5.0D, str1, str8);
		localDefaultCategoryDataset.addValue(5.0D, str2, str4);
		localDefaultCategoryDataset.addValue(7.0D, str2, str5);
		localDefaultCategoryDataset.addValue(6.0D, str2, str6);
		localDefaultCategoryDataset.addValue(8.0D, str2, str7);
		localDefaultCategoryDataset.addValue(4.0D, str2, str8);

		return localDefaultCategoryDataset;
	}

	private static JFreeChart createChart(CategoryDataset paramCategoryDataset)
	{
		JFreeChart localJFreeChart = ChartFactory.createBarChart("Bar Chart Demo 1", "�·�", "����", paramCategoryDataset,
				PlotOrientation.VERTICAL, true, true, false);
		CategoryPlot localCategoryPlot = (CategoryPlot) localJFreeChart.getPlot();
		//�Ƿ�ʵ�ֺ���������
		localCategoryPlot.setDomainGridlinesVisible(false);
		
		//��������������
	    localCategoryPlot.setRangeGridlinesVisible(true);
		
	    //�Ƿ���ʾʮ��׼����
	    //localCategoryPlot.setRangeCrosshairVisible(false);

	    //���ñ�����ɫ
		localCategoryPlot.setBackgroundPaint(new Color(143,200,255));
		
		NumberAxis localNumberAxis = (NumberAxis) localCategoryPlot.getRangeAxis();
		localNumberAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
		BarRenderer localBarRenderer = (BarRenderer) localCategoryPlot.getRenderer();
		localBarRenderer.setDrawBarOutline(false);
		GradientPaint localGradientPaint1 = new GradientPaint(0.0F, 0.0F, Color.blue, 0.0F, 0.0F, new Color(0, 0, 64));
		GradientPaint localGradientPaint2 = new GradientPaint(0.0F, 0.0F, Color.green, 0.0F, 0.0F, new Color(0, 64, 0));
		//GradientPaint localGradientPaint3 = new GradientPaint(0.0F, 0.0F, Color.red, 0.0F, 0.0F, new Color(64, 0, 0));
		localBarRenderer.setSeriesPaint(0, localGradientPaint1);
		localBarRenderer.setSeriesPaint(1, localGradientPaint2);
		//localBarRenderer.setSeriesPaint(2, localGradientPaint3);
		
		/*---------����ÿһ����״��֮��ļ��---------*/
		localBarRenderer.setItemMargin(0.00);
		
		localBarRenderer.setLegendItemToolTipGenerator(new StandardCategorySeriesLabelGenerator("Tooltip: {0}"));
		CategoryAxis localCategoryAxis = localCategoryPlot.getDomainAxis();
		localCategoryAxis.setCategoryLabelPositions(CategoryLabelPositions.createUpRotationLabelPositions(0.5235987755982988D));

		JFreeChart chart = localJFreeChart;
		/* ------- ������������ --------*/
		chart.getTitle().setFont(new Font("����", Font.BOLD, 20));
		
		//������״ͼ���·�˵��������
        chart.getLegend().setItemFont(new Font("΢���ź�",Font.BOLD,12));

		CategoryAxis domainAxis = chart.getCategoryPlot().getDomainAxis();
		/*------����X�������ϵ�����-----------*/
		domainAxis.setTickLabelFont(new Font("����", Font.PLAIN, 12));
		/*------����X��ı�������------------*/
		domainAxis.setLabelFont(new Font("����", Font.PLAIN, 12));

		NumberAxis numberaxis = (NumberAxis) chart.getCategoryPlot().getRangeAxis();
		/*------����Y�������ϵ�����-----------*/
		numberaxis.setTickLabelFont(new Font("����", Font.PLAIN, 12));
		/*------����Y��ı�������------------*/
		numberaxis.setLabelFont(new Font("����", Font.PLAIN, 12));
		
		return localJFreeChart;
	}

	public static JPanel createDemoPanel()
	{
		JFreeChart localJFreeChart = createChart(createDataset());
		return new ChartPanel(localJFreeChart);
	}

	public static void main(String[] paramArrayOfString)
	{
		BarChartDemo1 localBarChartDemo1 = new BarChartDemo1("JFreeChart: BarChartDemo1.java");
		localBarChartDemo1.pack();
		RefineryUtilities.centerFrameOnScreen(localBarChartDemo1);
		localBarChartDemo1.setVisible(true);

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