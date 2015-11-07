package org.balthie.demo.jdk.lang.system;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

/**
 * 调用cmd本地方法
 * 
 * @author：姓名 + 工号
 * @createtime ： 2015年11月9日 下午2:07:59
 * @description TODO 一句话描述
 * @since version 初始于版本 TODO
 */
public class RuntimeCallCMDDemo
{
    
    public static void main(String[] args) throws IOException
    {
        // openIpconfig();
        
        //mysqlDump();
        
        String[] cmd = new String[]{"cmd.exe","mkdir d:\\test"};;
        Runtime runtime = Runtime.getRuntime();
        
        Process process = runtime.exec(cmd);
        
        
        OutputStream os = process.getOutputStream();
        InputStream is = process.getInputStream();
        BufferedReader read = new BufferedReader(new InputStreamReader(is));
        String s = null;
        /*
         * while ((s = read.readLine()) != null) { System.out.println(s); }
         */
        
        OutputStreamWriter writer = new OutputStreamWriter(os); // 命令1和命令2要放在一起执行
        writer.write("mkdir test");
        writer.flush();
        
        read.close();
        writer.close();
        os.close();
        is.close();
    }

    private static void mysqlDump() throws IOException
    {
        String cmd = "mysqldump -u root -p --default-character-set=utf8 api user_device_base_info> d:/user_device_base_info.sql \r\n 123456";
        Runtime runtime = Runtime.getRuntime();
        
        Process process = runtime.exec(cmd);
        OutputStream os = process.getOutputStream();
        InputStream is = process.getInputStream();
        BufferedReader read = new BufferedReader(new InputStreamReader(is));
        String s = null;
        /*
         * while ((s = read.readLine()) != null) { System.out.println(s); }
         */
        
        OutputStreamWriter writer = new OutputStreamWriter(os); // 命令1和命令2要放在一起执行
        writer.write("123456");
        writer.flush();
        
        read.close();
        writer.close();
        os.close();
        is.close();
    }
    
    private static Process openIpconfig() throws IOException
    {
        Runtime runtime = Runtime.getRuntime();
        
        Process process = runtime.exec("ipconfig");
        
        BufferedReader read = null;
        try
        {
            read = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String s = null;
            while ((s = read.readLine()) != null)
            {
                System.out.println(s);
            }
            
        }
        catch (Exception e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        finally
        {
            if(read != null) read.close();
        }
        
        return process;
    }
}
