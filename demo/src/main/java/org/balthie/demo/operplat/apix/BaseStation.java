package org.balthie.demo.operplat.apix;

import java.io.IOException;
import java.net.URLDecoder;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class BaseStation
{
    public static void main(String[] args) throws ClientProtocolException, IOException
    {
        locate();
    }
    
    public static void locate() throws ClientProtocolException, IOException
    {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try
        {
            // 电信基站查询接口
            //HttpGet httpget = new HttpGet("http://a.apix.cn/apixlife/basestation/telecom?sid=13824&nid=0&cellid=291&ishex=0");
            
            HttpGet httpget = new HttpGet("http://a.apix.cn/apixlife/basestation/mob_unic?mnc=0&lac=9365&cellid=4052&ishex=0");
            httpget.addHeader("apix-key", "0bb621efe5144ef06b762f7015623e45");
            System.out.println("Executing request " + httpget.getRequestLine());
            System.out.println("Executing request " + httpget.getURI());
            
            // Create a custom response handler
            ResponseHandler<String> responseHandler = new ResponseHandler<String>() {
                @Override
                public String handleResponse(final HttpResponse response) throws ClientProtocolException, IOException
                {
                    int status = response.getStatusLine().getStatusCode();
                    if(status >= 200 && status < 300)
                    {
                        HttpEntity entity = response.getEntity();
                        return entity != null ? EntityUtils.toString(entity) : null;
                    }
                    else
                    {
                        throw new ClientProtocolException("Unexpected response status: " + status);
                    }
                }
            };
            String responseBody = httpclient.execute(httpget, responseHandler);
            System.out.println("----------------------------------------");
            System.out.println(responseBody);
            System.out.println(convertUnicodeStr(responseBody));
            
        }
        finally
        {
            httpclient.close();
        }
    }
    
    public static String convertUnicodeStr(String originalStr)
    {
        StringBuilder s = new StringBuilder();
        
        if(originalStr != null)
        {
            // 
            int i = -1;
            // unicode指针位置
            int pos = 0;
            while ((i = originalStr.indexOf("\\u", pos)) != -1)
            {
                s.append(originalStr.substring(pos, i));
                if(i+5 < originalStr.length())
                {
                    pos = i+6;
                    String sub = originalStr.substring(i+2, pos);
                    System.out.println(sub + " = integer  " + Integer.parseInt(sub, 16));
                    s.append((char) Integer.parseInt(sub, 16));
                }
            }
        }
        return s.toString();
    }
}
