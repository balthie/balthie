package org.balthie.demo.my.json.jackson2.xml;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.balthie.demo.my.json.jackson2.Data;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

// http://blog.csdn.net/douxiaoning1987/article/details/9158687

//Jackson也可以完成java对象到xml的转换，转换后的结果要比json-lib更直观，不过它依赖于stax2-api.jar这个jar包。

/**
 * <b>function:</b>java对象转换成xml文档 需要额外的jar包 stax2-api.jar
 * 
 * @author hoojo
 * @createDate 2010-11-23 下午06:11:21
 */
public class JacksonXMLTest
{
    public static void main(String[] args)
    {
        JacksonXMLTest test = new JacksonXMLTest();
        test.writeObject2Xml();
    }
    
    public void writeObject2Xml()
    {
        // stax2-api-3.0.2.jar
        System.out.println("XmlMapper");
        
        // jackson-annotations-2.2.0.jar,jackson-core-2.2.2.jar,
        // jackson-databind-2.2.0.jar,jackson-dataformat-xml-2.2.2.jar。
        XmlMapper xml = new XmlMapper();
        
        Data bean = new Data("1", "name1", new Date());
                
        try
        {
            // javaBean转换成xml
            // xml.writeValue(System.out, bean);
            StringWriter sw = new StringWriter();
            xml.writeValue(sw, bean);
            System.out.println(sw.toString());
            // List转换成xml
            List<Data> list = new ArrayList<Data>();
            list.add(bean);
            list.add(bean);
            System.out.println(xml.writeValueAsString(list));
            
            // Map转换xml文档
            Map<String, Data> map = new HashMap<String, Data>();
            map.put("A", bean);
            map.put("B", bean);
            System.out.println(xml.writeValueAsString(map));
        }
        catch (JsonGenerationException e)
        {
            e.printStackTrace();
        }
        catch (JsonMappingException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
