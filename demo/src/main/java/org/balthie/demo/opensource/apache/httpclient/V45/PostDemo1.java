package org.balthie.demo.opensource.apache.httpclient.V45;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class PostDemo1
{
    
    public static void main(String[] args) throws ClientProtocolException, IOException
    {
        httppost();
    }
    
    public static void httppost() throws ClientProtocolException, IOException
    {
        
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try
        {
            // http://101.200.197.247:7080/wechatserver/services/systemDictionary/division/listByParentCode.json?code=330300
            HttpPost httppost = new HttpPost(
                    "http://localhost:9090/wechatserver/services/systemDictionary/division/listByParentCode.json");
            
            // 创建参数队列
            List<NameValuePair> formparams = new ArrayList<NameValuePair>();
            formparams.add(new BasicNameValuePair("code", ""));
            formparams.add(new BasicNameValuePair("test", "中文参数"));
            UrlEncodedFormEntity uefEntity = new UrlEncodedFormEntity(formparams, "UTF-8");
            httppost.setEntity(uefEntity);
            
            httpclient.execute(httppost);
            System.out.println("Executing request " + httppost.getURI());
            CloseableHttpResponse response = httpclient.execute(httppost);
            BufferedReader reader = null;
            try
            {
                System.out.println("----------------------------------------");
                System.out.println(response.getStatusLine());
                int status = response.getStatusLine().getStatusCode();
                if(status >= 200 && status < 300)
                {
                    HttpEntity resEntity = response.getEntity();
                    System.out.println("Response content: " + EntityUtils.toString(resEntity, "UTF-8")); 
                  /*  if (resEntity != null) {  
                        System.out.println("Response content length: " + resEntity.getContentLength());  
                        
                        reader = new BufferedReader(new InputStreamReader(resEntity.getContent(), "utf-8"));
                        String line =  reader.readLine();
                        while(line!=null)
                        {
                            System.out.println(line);
                            line = reader.readLine();
                        }
                    }  */
                    EntityUtils.consume(response.getEntity());
                }
                else
                {
                    throw new ClientProtocolException("Unexpected response status: " + status);
                }
            }
            finally
            {
                if(reader!=null) reader.close();
                response.close();
            }
        }
        finally
        {
            httpclient.close();
        }
    }
    
    public static void chunkEncodePost() throws ClientProtocolException, IOException
    {
        String filePath = "";
        if(StringUtils.isBlank(filePath))
        {
            System.out.println("File path not given");
            System.exit(1);
        }
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try
        {
            HttpPost httppost = new HttpPost("http://localhost/");
            
            File file = new File(filePath);
            
            InputStreamEntity reqEntity = new InputStreamEntity(new FileInputStream(file), -1,
                    ContentType.APPLICATION_OCTET_STREAM);
            reqEntity.setChunked(true);
            // It may be more appropriate to use FileEntity class in this particular
            // instance but we are using a more generic InputStreamEntity to demonstrate
            // the capability to stream out data from any arbitrary source
            //
            // FileEntity entity = new FileEntity(file, "binary/octet-stream");
            
            httppost.setEntity(reqEntity);
            
            System.out.println("Executing request: " + httppost.getRequestLine());
            CloseableHttpResponse response = httpclient.execute(httppost);
            try
            {
                System.out.println("----------------------------------------");
                System.out.println(response.getStatusLine());
                EntityUtils.consume(response.getEntity());
            }
            finally
            {
                response.close();
            }
        }
        finally
        {
            httpclient.close();
        }
    }
}
