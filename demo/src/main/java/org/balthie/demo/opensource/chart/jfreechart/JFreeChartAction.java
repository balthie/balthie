package org.balthie.demo.opensource.chart.jfreechart;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.block.BlockContainer;
import org.jfree.chart.block.BorderArrangement;
import org.jfree.chart.block.EmptyBlock;
import org.jfree.chart.labels.StandardCategoryToolTipGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.DatasetRenderingOrder;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.title.CompositeTitle;
import org.jfree.chart.title.LegendTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.RectangleEdge;
import org.jfree.ui.RectangleInsets;

public class JFreeChartAction
{
    public static void main(String[] args)
    {
        // TODO Auto-generated method stub
        JFreeChart jfreechart = createChart();
        File file = new File("d:\\xxx.jpg");
        try
        {
            ChartUtilities.saveChartAsJPEG(file, jfreechart, 500, 400);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    
    private static CategoryDataset createDataset1()
    {
        String s = "S1";
        String s1 = "S2";
        String s2 = "S3";
        String s3 = "Category 1";
        String s4 = "Category 2";
        String s5 = "Category 3";
        String s6 = "Category 4";
        String s7 = "Category 5";
        String s8 = "Category 6";
        String s9 = "Category 7";
        String s10 = "Category 8";
        DefaultCategoryDataset defaultcategorydataset = new DefaultCategoryDataset();
        defaultcategorydataset.addValue(1.0D, s, s3);
        defaultcategorydataset.addValue(4D, s, s4);
        defaultcategorydataset.addValue(3D, s, s5);
        defaultcategorydataset.addValue(5D, s, s6);
        defaultcategorydataset.addValue(5D, s, s7);
        defaultcategorydataset.addValue(7D, s, s8);
        defaultcategorydataset.addValue(7D, s, s9);
        defaultcategorydataset.addValue(8D, s, s10);
        defaultcategorydataset.addValue(5D, s1, s3);
        defaultcategorydataset.addValue(7D, s1, s4);
        defaultcategorydataset.addValue(6D, s1, s5);
        defaultcategorydataset.addValue(8D, s1, s6);
        defaultcategorydataset.addValue(4D, s1, s7);
        defaultcategorydataset.addValue(4D, s1, s8);
        defaultcategorydataset.addValue(2D, s1, s9);
        defaultcategorydataset.addValue(1.0D, s1, s10);
        defaultcategorydataset.addValue(4D, s2, s3);
        defaultcategorydataset.addValue(3D, s2, s4);
        defaultcategorydataset.addValue(2D, s2, s5);
        defaultcategorydataset.addValue(3D, s2, s6);
        defaultcategorydataset.addValue(6D, s2, s7);
        defaultcategorydataset.addValue(3D, s2, s8);
        defaultcategorydataset.addValue(4D, s2, s9);
        defaultcategorydataset.addValue(3D, s2, s10);
        return defaultcategorydataset;
    }
    
    private static CategoryDataset createDataset2()
    {
        String s = "S4";
        String s1 = "Category 1";
        String s2 = "Category 2";
        String s3 = "Category 3";
        String s4 = "Category 4";
        String s5 = "Category 5";
        String s6 = "Category 6";
        String s7 = "Category 7";
        String s8 = "Category 8";
        DefaultCategoryDataset defaultcategorydataset = new DefaultCategoryDataset();
        defaultcategorydataset.addValue(15D, s, s1);
        defaultcategorydataset.addValue(24D, s, s2);
        defaultcategorydataset.addValue(31D, s, s3);
        defaultcategorydataset.addValue(25D, s, s4);
        defaultcategorydataset.addValue(56D, s, s5);
        defaultcategorydataset.addValue(37D, s, s6);
        defaultcategorydataset.addValue(77D, s, s7);
        defaultcategorydataset.addValue(18D, s, s8);
        return defaultcategorydataset;
    }
    
    private static JFreeChart createChart()
    {
        JFreeChart jfreechart = ChartFactory.createAreaChart("JFreeChartDemo", "Category", "Value", createDataset1(),
                PlotOrientation.VERTICAL, false, true, false);
        CategoryPlot categoryplot = (CategoryPlot) jfreechart.getPlot();
        CategoryDataset categorydataset = createDataset2();
        categoryplot.setDataset(1, categorydataset);
        categoryplot.mapDatasetToRangeAxis(1, 1);
        CategoryAxis categoryaxis = categoryplot.getDomainAxis();
        categoryaxis.setCategoryLabelPositions(CategoryLabelPositions.DOWN_45);
        NumberAxis numberaxis = new NumberAxis("Secondary");
        categoryplot.setRangeAxis(1, numberaxis);
        
        
        LineAndShapeRenderer lineandshaperenderer = new LineAndShapeRenderer();
        lineandshaperenderer.setBaseToolTipGenerator(new StandardCategoryToolTipGenerator());
        categoryplot.setRenderer(1, lineandshaperenderer);
                
        categoryplot.setDatasetRenderingOrder(DatasetRenderingOrder.FORWARD);
        
        
        LegendTitle legendtitle = new LegendTitle(categoryplot.getRenderer(0));
        legendtitle.setMargin(new RectangleInsets(2D, 2D, 2D, 2D));
        legendtitle.setFrame(new BlockBorder());
        LegendTitle legendtitle1 = new LegendTitle(categoryplot.getRenderer(1));
        legendtitle1.setMargin(new RectangleInsets(2D, 2D, 2D, 2D));
        legendtitle1.setFrame(new BlockBorder());        
        BlockContainer blockcontainer = new BlockContainer(new BorderArrangement());
        blockcontainer.add(legendtitle, RectangleEdge.LEFT);
        blockcontainer.add(legendtitle1, RectangleEdge.RIGHT);
        blockcontainer.add(new EmptyBlock(2000D, 0.0D));
        CompositeTitle compositetitle = new CompositeTitle(blockcontainer);
        compositetitle.setPosition(RectangleEdge.BOTTOM);
        jfreechart.addSubtitle(compositetitle);
        ChartUtilities.applyCurrentTheme(jfreechart);
        return jfreechart;
    }
}