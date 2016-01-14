package org.balthie.demo.jdk.io.file;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import org.apache.commons.io.FileUtils;

public class FileTest
{
    public static void main(String[] args) throws IOException 
    { 
        System.out.println(File.separator);
        System.out.println(File.pathSeparator);
        
        URL url = new URL("http://wx.qlogo.cn/mmopen/hAszInO0OwLoXtPe4Biar5m8JxAHwibn6Qcw2E1COticsuGIy6bCFfPMf0yt7QUReK6foCQHhzHYTymQw2OyLQrTsfdGUWKicXcO/0");
        getImage(url);
    }
    
    /**
     * http://lavasoft.blog.51cto.com/62575/265821/
     * @createtime ： 2015年3月13日 下午4:56:35
     * @description 测试文件相对路径
     */
    public void testPath()
    {
        
    }
    
    // get URL Image
    public static void getImage(URL url) throws IOException 
    {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.connect();
        // InputStream in = urlConnection.getInputStream();
        InputStream in = url.openStream();
        
        ByteArrayOutputStream bos = new ByteArrayOutputStream(1024);
        
        byte[] buf = new byte[256];
        int n;
        while ((n = in.read(buf)) != -1)
        {
            bos.write(buf, 0, n);
        }
        
        FileUtils.writeByteArrayToFile(new File("D:\\test_1.jpg"), bos.toByteArray());
    }
}
