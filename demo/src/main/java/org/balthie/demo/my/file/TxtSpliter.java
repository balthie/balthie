package org.balthie.demo.my.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * @author：balthie@126.com
 * @createtime ： 2017年3月20日 上午11:55:20
 * @description 文本文件拆分
 */
public class TxtSpliter
{
    private static int length = 1024;// 可以设置文件在读取时一次读取文件的大小
    
    /*
     * 文件的切割
     * String path 切割文件的路径
     * size 子文件的大小
     */
    public static void filesplit(String path, int size) throws Exception
    {
        if(path == null)
            throw new Exception("源文件不能为空...");
        File file = new File(path);
        if(!file.exists())
            throw new Exception("源文件不存在...");
        long num = file.length() % size == 0 ? file.length() / size : file.length() / size + 1;
        String list[] = new String[(int) num];// 用于存放分割后的结果
        FileInputStream reader = new FileInputStream(file);
        long beginIndex = 0, endIndex = 0;
        int readcount = 0;
        FileOutputStream writer = null;
        byte buffer[] = new byte[length];
        try
        {
            for(int i = 0; i < num; i++)
            {
                list[i] = file.getAbsolutePath() + ".depart" + i + ".txt";
                writer = new FileOutputStream(list[i]);
                endIndex = (endIndex + size) > file.length() ? file.length() : endIndex + size;
                for(; beginIndex < endIndex;)
                {
                    if(endIndex - beginIndex >= length)
                    {
                        readcount = reader.read(buffer);
                        beginIndex += readcount;
                        writer.write(buffer);
                    }
                    else
                    {
                        // 下面的就不能直接读取1024个字节了,就要一个一个字节的读取了
                        for(; beginIndex < endIndex; beginIndex++)
                        {
                            writer.write(reader.read());
                        }
                        continue;
                    }
                }
                writer.close();
            }
        }
        catch (Exception e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        finally
        {
            reader.close();
            writer.close();
        }
    }
    
    public static void union(String path, String newString) throws Exception
    {
        File file = new File(path);
        File list[] = file.listFiles();
        File newFile = new File(newString);
        byte buffer[] = new byte[1024];
        int readcount;
        if(!newFile.getParentFile().exists())
            throw new Exception("你合并的文件夹的不存在...");
        FileOutputStream writer = new FileOutputStream(newString);
        for(File f : list)
        {
            FileInputStream reader = new FileInputStream(f);
            while ((readcount = reader.read(buffer)) != -1)
            {
                writer.write(buffer);
            }
            reader.close();
        }
        writer.close();
    }
    
    public static void main(String args[]) throws Exception
    {
        filesplit("E:\\document\\QEZ\\eShop4_20170214.bak", 104857600);
        // union("F:\\movie\\","F:\\movie.rmvb");
    }
}
/*
 * 文件的分割与合并
 * 1．文件的分割，原理是用输入流去读取文件，将读取规定大小的流再输出支指定的文件，直到整个把整个文件读取结束．
 * 2．文件合并，文件的合并原理与分割正好想反，就是把所有的文件都读取到一个输入流中，然后再把输入流中的东西全部输出到
 * 同一个文件输出流中，这样就可以把分割的文件合并到一个文件中去了． 并且文件的大小和原来也会一样 ．
 * 3.上面的程序我试着分割一个600多M 的电影，分割是成功了，但只是分割的第一个文件可以播放，而后面的几个文件都不可以播放，我也不知道为什么，
 * 可能是视频文件里面有什么自定的格式吧．．．不过分割后再把所有的文件合并，合并后文件大小和之前一样，而且还可以插入，说明文件没有分割坏
 * 至于单个的文件为什么不能插入，这个以后用到的时候再去研究．．现在还是把Java的基础搞懂再说．．．．．
 * 2011.10.21 9：43
 * * *
 * 
 * 
 */
