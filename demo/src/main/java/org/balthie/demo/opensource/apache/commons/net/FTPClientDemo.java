package org.balthie.demo.opensource.apache.commons.net;

import java.io.IOException;
import java.net.SocketException;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//http://commons.apache.org/proper/commons-net/apidocs/org/apache/commons/net/ftp/FTPClient.html
// 2.2 版本不支持SFTP
public class FTPClientDemo
{
    static Logger LOGGER = LoggerFactory.getLogger(FTPClientDemo.class);
    
    public static void main(String[] args)
    {
        //FTPClient client = getFTPClient("114.215.148.222","1be792ff", "root", 22);
        firstStep();
    }
    
    /** 
     * 获取FTPClient对象 
     * @param ftpHost FTP主机服务器 
     * @param ftpPassword FTP 登录密码 
     * @param ftpUserName FTP登录用户名 
     * @param ftpPort FTP端口 默认为21 
     * @return 
     */  
    public static FTPClient getFTPClient(String ftpHost, String ftpPassword,  
            String ftpUserName, int ftpPort) {  
        FTPClient ftpClient = null;  
        try {  
            ftpClient = new FTPClient();  
            ftpClient.connect(ftpHost, ftpPort);// 连接FTP服务器  
            ftpClient.login(ftpUserName, ftpPassword);// 登陆FTP服务器  
            if (!FTPReply.isPositiveCompletion(ftpClient.getReplyCode())) {  
                LOGGER.info("未连接到FTP，用户名或密码错误。");  
                ftpClient.disconnect();  
            } else {  
                LOGGER.info("FTP连接成功。");  
            }  
        } catch (SocketException e) {  
            e.printStackTrace();  
            LOGGER.info("FTP的IP地址可能错误，请正确配置。");  
        } catch (IOException e) {  
            e.printStackTrace();  
            LOGGER.info("FTP的端口错误,请正确配置。");  
        }  
        return ftpClient;  
    }  
    
    // 与其他 SocketClient用法一致，首先建立连接，再检查服务器相应码，最后释放连接
    public static void firstStep()
    {
        String server = "114.215.148.222";
        // ftp默认端口 21
        //int port=21;
        
        FTPClient ftp = new FTPClient();
        FTPClientConfig config = new FTPClientConfig();
        // change required options, 如果需要就改变
        // config.setXXX("YYY");
        ftp.configure(config);
        boolean error = false;
        try
        {
            int reply;
            ftp.connect(server);
            System.out.println("Connected to " + server + ".");
            // 所有的ftp指令，都有返回值，在连接后，立即检查服务器响应码
            System.out.print(ftp.getReplyString());
            
            ftp.login("root", "1be792ff");
            // 所有的ftp指令，都有返回值，在连接后，立即检查服务器响应码
            System.out.print(ftp.getReplyString());
            
            
            // After connection attempt, you should check the reply code to verify
            // success.
            reply = ftp.getReplyCode();
            
            if(!FTPReply.isPositiveCompletion(reply))
            {
                ftp.disconnect();
                System.err.println("FTP server refused connection.");
                System.exit(1);
            }
            // transfer files
            ftp.logout();
        }
        catch (IOException e)
        {
            error = true;
            e.printStackTrace();
        }
        finally
        {
            if(ftp.isConnected())
            {
                try
                {
                    ftp.disconnect();
                }
                catch (IOException ioe)
                {
                    // do nothing
                }
            }
            System.exit(error ? 1 : 0);
        }
    }
}
