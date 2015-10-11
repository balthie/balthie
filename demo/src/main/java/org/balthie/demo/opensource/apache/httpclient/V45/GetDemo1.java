package org.balthie.demo.opensource.apache.httpclient.V45;

import java.io.IOException;
import java.net.InetAddress;
import java.net.URLDecoder;
import java.net.UnknownHostException;
import java.nio.charset.CodingErrorAction;
import java.util.Arrays;
import javax.net.ssl.SSLContext;
import org.apache.commons.lang.StringUtils;
import org.apache.http.Consts;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.config.AuthSchemes;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.config.ConnectionConfig;
import org.apache.http.config.MessageConstraints;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.config.SocketConfig;
import org.apache.http.conn.DnsResolver;
import org.apache.http.conn.HttpConnectionFactory;
import org.apache.http.conn.ManagedHttpClientConnection;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.impl.DefaultHttpResponseFactory;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.DefaultHttpResponseParser;
import org.apache.http.impl.conn.DefaultHttpResponseParserFactory;
import org.apache.http.impl.conn.ManagedHttpClientConnectionFactory;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.impl.conn.SystemDefaultDnsResolver;
import org.apache.http.impl.io.DefaultHttpRequestWriterFactory;
import org.apache.http.io.HttpMessageParser;
import org.apache.http.io.HttpMessageParserFactory;
import org.apache.http.io.HttpMessageWriterFactory;
import org.apache.http.io.SessionInputBuffer;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicLineParser;
import org.apache.http.message.LineParser;
import org.apache.http.util.CharArrayBuffer;
import org.apache.http.util.EntityUtils;

//  demo from http://hc.apache.org/httpcomponents-client-4.5.x/examples.html
public class GetDemo1
{
    String hostAddress;
    
    Integer port;
    
    String path;
    
    String param;
    
    public GetDemo1(String hostAddress, Integer port)
    {
        super();
        this.hostAddress = hostAddress;
        this.port = port;
    }
    
    public static void main(String[] args) throws ClientProtocolException, IOException
    {
        // httpGet1();
        httpGetFullURL();
        //abortResponse();
    }
    
    public static void httpGetFullURL() throws ClientProtocolException, IOException
    {
        /* GetDemo1 d1 = new GetDemo1("http://101.200.197.247", 7080);
       d1.path = "wechatserver/services/systemDictionary/division/listByParentCode.json";
        d1.param = "startCity=" + URLEncoder.encode("中文参数", "utf-8") + "&code=";*/
        
        GetDemo1 d1 = new GetDemo1("http://a.apix.cn", null);
        d1.path = "apixlife/basestation/telecom";
        d1.param = "sid=13824&nid=0&cellid=291&ishex=0";
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try
        { 
            HttpGet httpget = new HttpGet(d1.getURI());
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
            System.out.println(URLDecoder.decode(responseBody)); 
            
        }
        finally
        {
            httpclient.close();
        }
    }
    
    public static void httpGet1() throws ClientProtocolException, IOException
    {
        GetDemo1 d1 = new GetDemo1("http://localhost", 8080);
        
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try
        {
            HttpGet httpget = new HttpGet(d1.getURI());
            httpget.addHeader("Accept", "text/html");
            httpget.addHeader("Accept-Charset", "utf-8");
            httpget.addHeader("Accept-Encoding", "gzip");
            httpget.addHeader("Accept-Language", "en-US,en");
            httpget.addHeader("User-Agent",
                    "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.22 (KHTML, like Gecko) Chrome/25.0.1364.160 Safari/537.22");
            
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
        }
        finally
        {
            httpclient.close();
        }
    }
    
    public static void abortResponse() throws ClientProtocolException, IOException
    {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try
        {
            HttpGet httpget = new HttpGet("http://www.apache.org/");
            
            System.out.println("Executing request " + httpget.getURI());
            CloseableHttpResponse response = httpclient.execute(httpget);
            try
            {
                System.out.println("----------------------------------------");
                System.out.println(response.getStatusLine());
                // Do not feel like reading the response body
                // Call abort on the request object
                httpget.abort();
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
    
    public static void connectionConfiguration() throws IOException
    {
        // Use custom message parser / writer to customize the way HTTP
        // messages are parsed from and written out to the data stream.
        HttpMessageParserFactory<HttpResponse> responseParserFactory = new DefaultHttpResponseParserFactory() {
            
            @Override
            public HttpMessageParser<HttpResponse> create(SessionInputBuffer buffer, MessageConstraints constraints)
            {
                LineParser lineParser = new BasicLineParser() {
                    
                    @Override
                    public Header parseHeader(final CharArrayBuffer buffer)
                    {
                        try
                        {
                            return super.parseHeader(buffer);
                        }
                        catch (ParseException ex)
                        {
                            return new BasicHeader(buffer.toString(), null);
                        }
                    }
                    
                };
                return new DefaultHttpResponseParser(buffer, lineParser, DefaultHttpResponseFactory.INSTANCE, constraints) {
                    
                    @Override
                    protected boolean reject(final CharArrayBuffer line, int count)
                    {
                        // try to ignore all garbage preceding a status line infinitely
                        return false;
                    }
                    
                };
            }
            
        };
        HttpMessageWriterFactory<HttpRequest> requestWriterFactory = new DefaultHttpRequestWriterFactory();
        
        // Use a custom connection factory to customize the process of
        // initialization of outgoing HTTP connections. Beside standard connection
        // configuration parameters HTTP connection factory can define message
        // parser / writer routines to be employed by individual connections.
        HttpConnectionFactory<HttpRoute, ManagedHttpClientConnection> connFactory = new ManagedHttpClientConnectionFactory(
                requestWriterFactory, responseParserFactory);
        
        // Client HTTP connection objects when fully initialized can be bound to
        // an arbitrary network socket. The process of network socket initialization,
        // its connection to a remote address and binding to a local one is controlled
        // by a connection socket factory.
        
        // SSL context for secure connections can be created either based on
        // system or application specific properties.
        SSLContext sslcontext = SSLContexts.createSystemDefault();
        
        // Create a registry of custom connection socket factories for supported
        // protocol schemes.
        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory> create()
                .register("http", PlainConnectionSocketFactory.INSTANCE)
                .register("https", new SSLConnectionSocketFactory(sslcontext)).build();
        
        // Use custom DNS resolver to override the system DNS resolution.
        DnsResolver dnsResolver = new SystemDefaultDnsResolver() {
            
            @Override
            public InetAddress[] resolve(final String host) throws UnknownHostException
            {
                if(host.equalsIgnoreCase("myhost"))
                {
                    return new InetAddress[] { InetAddress.getByAddress(new byte[] { 127, 0, 0, 1 }) };
                }
                else
                {
                    return super.resolve(host);
                }
            }
            
        };
        
        // Create a connection manager with custom configuration.
        PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry,
                connFactory, dnsResolver);
        
        // Create socket configuration
        SocketConfig socketConfig = SocketConfig.custom().setTcpNoDelay(true).build();
        // Configure the connection manager to use socket configuration either
        // by default or for a specific host.
        connManager.setDefaultSocketConfig(socketConfig);
        connManager.setSocketConfig(new HttpHost("somehost", 80), socketConfig);
        // Validate connections after 1 sec of inactivity
        connManager.setValidateAfterInactivity(1000);
        
        // Create message constraints
        MessageConstraints messageConstraints = MessageConstraints.custom().setMaxHeaderCount(200).setMaxLineLength(2000).build();
        // Create connection configuration
        ConnectionConfig connectionConfig = ConnectionConfig.custom().setMalformedInputAction(CodingErrorAction.IGNORE)
                .setUnmappableInputAction(CodingErrorAction.IGNORE).setCharset(Consts.UTF_8)
                .setMessageConstraints(messageConstraints).build();
        // Configure the connection manager to use connection configuration either
        // by default or for a specific host.
        connManager.setDefaultConnectionConfig(connectionConfig);
        connManager.setConnectionConfig(new HttpHost("somehost", 80), ConnectionConfig.DEFAULT);
        
        // Configure total max or per route limits for persistent connections
        // that can be kept in the pool or leased by the connection manager.
        connManager.setMaxTotal(100);
        connManager.setDefaultMaxPerRoute(10);
        connManager.setMaxPerRoute(new HttpRoute(new HttpHost("somehost", 80)), 20);
        
        // Use custom cookie store if necessary.
        CookieStore cookieStore = new BasicCookieStore();
        // Use custom credentials provider if necessary.
        CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        // Create global request configuration
        RequestConfig defaultRequestConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.DEFAULT)
                .setExpectContinueEnabled(true)
                .setTargetPreferredAuthSchemes(Arrays.asList(AuthSchemes.NTLM, AuthSchemes.DIGEST))
                .setProxyPreferredAuthSchemes(Arrays.asList(AuthSchemes.BASIC)).build();
        
        // Create an HttpClient with the given custom dependencies and configuration.
        CloseableHttpClient httpclient = HttpClients.custom().setConnectionManager(connManager)
                .setDefaultCookieStore(cookieStore).setDefaultCredentialsProvider(credentialsProvider)
                .setProxy(new HttpHost("myproxy", 8080)).setDefaultRequestConfig(defaultRequestConfig).build();
        
        try
        {
            HttpGet httpget = new HttpGet("http://www.apache.org/");
            // Request configuration can be overridden at the request level.
            // They will take precedence over the one set at the client level.
            RequestConfig requestConfig = RequestConfig.copy(defaultRequestConfig).setSocketTimeout(5000).setConnectTimeout(5000)
                    .setConnectionRequestTimeout(5000).setProxy(new HttpHost("myotherproxy", 8080)).build();
            httpget.setConfig(requestConfig);
            
            // Execution context can be customized locally.
            HttpClientContext context = HttpClientContext.create();
            // Contextual attributes set the local context level will take
            // precedence over those set at the client level.
            context.setCookieStore(cookieStore);
            context.setCredentialsProvider(credentialsProvider);
            
            System.out.println("executing request " + httpget.getURI());
            CloseableHttpResponse response = httpclient.execute(httpget, context);
            try
            {
                HttpEntity entity = response.getEntity();
                
                System.out.println("----------------------------------------");
                System.out.println(response.getStatusLine());
                if(entity != null)
                {
                    System.out.println("Response content length: " + entity.getContentLength());
                }
                System.out.println("----------------------------------------");
                
                // Once the request has been executed the local context can
                // be used to examine updated state and various objects affected
                // by the request execution.
                
                // Last executed request
                context.getRequest();
                // Execution route
                context.getHttpRoute();
                // Target auth state
                context.getTargetAuthState();
                // Proxy auth state
                context.getTargetAuthState();
                // Cookie origin
                context.getCookieOrigin();
                // Cookie spec used
                context.getCookieSpec();
                // User security token
                context.getUserToken();
                
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
    
    private String getURI()
    {
        StringBuilder builder = new StringBuilder(hostAddress);
        if(port != null)
        {
            builder.append(":").append(port);
        }
        if(StringUtils.isNotBlank(path))
        {
            builder.append("/").append(path);
        }
        if(StringUtils.isNotBlank(param))
        {
            builder.append("?").append(param);
        }
        
        return builder.toString();
    }
}
