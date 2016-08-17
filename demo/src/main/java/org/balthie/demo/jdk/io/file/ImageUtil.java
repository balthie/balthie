/**
 * @author：balthie + 10050
 * @createtime ： 2014年11月15日 下午2:07:31
 * @description TODO 一句话描述
 */
package org.balthie.demo.jdk.io.file;

import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.RenderedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.Iterator;
import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageTypeSpecifier;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.plugins.jpeg.JPEGImageWriteParam;
import javax.imageio.stream.ImageOutputStream;
import javax.management.RuntimeErrorException;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;

/**
 * @author：balthie + 10050
 * @createtime ： 2014年11月15日 下午2:07:31
 * @since version 初始于版本 TODO
 */
public class ImageUtil
{
    private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(ImageUtil.class);
    
    /**
     * @author：balthie + 10050
     * @createtime ： 2014年11月15日 下午5:04:32
     * @description 压缩图片（有损）
     * @param data
     * @param compressionRate
     *            压缩比（取值0-1）
     * @return
     * @throws IOException
     */
    public static byte[] compress(File imgFile, float compressionRate) throws IOException
    {
        LOGGER.debug(" compress invoke ");
        
        // commons-io-2.0.1.jar
        byte[] data = FileUtils.readFileToByteArray(imgFile);
        ByteArrayInputStream is = new ByteArrayInputStream(data);
        BufferedImage src = null;
        ByteArrayOutputStream out = null;
        ImageWriter imgWrier;
        ImageWriteParam imgWriteParams;
        
        // 指定写图片的方式为 jpg
        imgWrier = ImageIO.getImageWritersByFormatName("jpg").next();
        imgWriteParams = new JPEGImageWriteParam(null);
        
        // 要使用压缩，必须指定压缩方式为MODE_EXPLICIT
        imgWriteParams.setCompressionMode(JPEGImageWriteParam.MODE_EXPLICIT);
        // 这里指定压缩的程度，参数qality是取值0~1范围内，
        /* imgWriteParams.setCompressionQuality((float) 0.1 / data.length); */
        imgWriteParams.setCompressionQuality(compressionRate);
        
        imgWriteParams.setProgressiveMode(JPEGImageWriteParam.MODE_DISABLED);
        
        ColorModel colorModel = ColorModel.getRGBdefault();
        // 指定压缩时使用的色彩模式
        imgWriteParams.setDestinationType(new javax.imageio.ImageTypeSpecifier(colorModel, colorModel
                .createCompatibleSampleModel(16, 16)));
        
        try
        {
            src = ImageIO.read(is);
            out = new ByteArrayOutputStream(data.length);
            
            imgWrier.reset();
            // 必须先指定 out值，才能调用write方法, ImageOutputStream可以通过任何 OutputStream构造
            imgWrier.setOutput(ImageIO.createImageOutputStream(out));
            // 调用write方法，就可以向输入流写图片
            imgWrier.write(null, new IIOImage(src, null, null), imgWriteParams);
            
            out.flush();
            out.close();
            is.close();
            data = out.toByteArray();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return data;
    }
    
    public static void writeImage(RenderedImage img, String formatName, String savePath) throws IOException
    {
        ImageIO.write(img, formatName, new File(savePath));
    }
    
    public static void writeImage(String savePath, byte[] data) throws IOException
    {
        FileUtils.writeByteArrayToFile(new File(savePath), data);
    }
    
    public static File generateZoomImageFile(File oriImage, float resizeTimes, String destiFilePath) throws IOException
    {
        BufferedImage im = zoomImage(oriImage, resizeTimes);
        
        ImageWriter writer = null;
        ImageTypeSpecifier type = ImageTypeSpecifier.createFromRenderedImage(im);
        Iterator<ImageWriter> iter = ImageIO.getImageWriters(type, "jpg");
        File output = null;
        if(iter.hasNext())
        {
            writer = iter.next();
            
            if(writer == null)
            {
                throw new IOException("generateZoomImageFile failed , could not get ImageWriter ");
            }
            ImageOutputStream stream = null;
            output = new File(destiFilePath);
            try
            {
                stream = ImageIO.createImageOutputStream(output);
            }
            catch (IOException e)
            {
                throw new IOException("Can't create output stream!", e);
            }
            
            try
            {
                writer.setOutput(stream);
                try
                {
                    writer.write(im);
                }
                finally
                {
                    writer.dispose();
                    stream.flush();
                }
            }
            finally
            {
                stream.close();
            }
        }
        return output;
    }
    
    /**
     * @param im
     *            原始图像
     * @param resizeTimes
     *            倍数,比如0.5就是缩小一半,0.98等等double类型
     * @return 返回处理后的图像
     */
    public static BufferedImage zoomImage(File oriImage, float resizeTimes)
    {
        BufferedImage result = null;
        try
        {
            BufferedImage im = ImageIO.read(oriImage);
            
            /* 原始图像的宽度和高度 */
            int width = im.getWidth();
            int height = im.getHeight();
            
            // 压缩计算
            /* float resizeTimes = 0.3f; */
            /* 这个参数是要转化成的倍数,如果是1就是转化成1倍 */
            
            /* 调整后的图片的宽度和高度 */
            int toWidth = (int) (width * resizeTimes);
            int toHeight = (int) (height * resizeTimes);
            
            /* 新生成结果图片 */
            result = new BufferedImage(toWidth, toHeight, BufferedImage.TYPE_INT_RGB);
            
            result.getGraphics().drawImage(im.getScaledInstance(toWidth, toHeight, java.awt.Image.SCALE_SMOOTH), 0, 0, null);
            
        }
        catch (Exception e)
        {
            LOGGER.error("创建缩略图发生异常", e);
        }
        
        return result;
    }
    
    public static void main(String[] args) throws Exception
    {
        String origiFilePath = "d:\\webwxgetmsgimg.jpg";
        System.out.println(" ------------ begin ------------  ");
        
        File srcfile = new File(origiFilePath);
        if(!srcfile.exists())
        {
            String msg = MessageFormat.format("zoomImage failed : file not exists【{0}】", origiFilePath);
            LOGGER.error(msg);
            throw new RuntimeException(msg);
        }
        
        /*byte[] data = ImageUtil.compress(srcfile, (float) 1 / srcfile.length());
        writeImage("d:\\upload\\compress1.jpg", data);
        
        BufferedImage img = zoomImage(srcfile, 0.2f);
        writeImage(img, "jpg", "d:\\upload\\narrow1.jpg");*/
        
        generateZoomImageFile(srcfile, 0.333f, "d:\\webwxgetmsgimg_60.jpg");
        
        
        System.out.println(" ----------  end  ------------  ");
    }
}
