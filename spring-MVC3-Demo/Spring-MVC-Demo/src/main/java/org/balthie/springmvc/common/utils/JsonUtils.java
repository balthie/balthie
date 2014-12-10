package org.balthie.springmvc.common.utils;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class JsonUtils
{
    private static final Logger log = LoggerFactory.getLogger(JsonUtils.class);
    
    final static ObjectMapper objectMapper;
    
    static
    {
        objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }
    
    public static ObjectMapper getObjectMapper()
    {
        return objectMapper;
    }
    
    /**
     * JSON串转换为Java泛型对象，可以是各种类型，此方法最为强大。用法看测试用例。
     * 
     * @param <T>
     * @param jsonString
     *            JSON字符串
     * @param tr
     *            TypeReference,例如: new TypeReference< List<FamousUser> >(){}
     * @return List对象列表
     */
    public static <T> T json2GenericObject(String jsonString, TypeReference<T> tr)
    {
        
        if(jsonString == null || "".equals(jsonString))
        {
            return null;
        }
        else
        {
            try
            {
                return (T) objectMapper.readValue(jsonString, tr);
            }
            catch (Exception e)
            {
                log.warn("json error:" + e.getMessage());
            }
        }
        return null;
    }
    
    /**
     * Json字符串转Java对象
     * 
     * @param jsonString
     * @param c
     * @return
     */
    public static Object json2Object(String jsonString, Class<?> c)
    {
        
        if(jsonString == null || "".equals(jsonString))
        {
            return "";
        }
        else
        {
            try
            {
                return objectMapper.readValue(jsonString, c);
            }
            catch (Exception e)
            {
                log.warn("json error:" + e.getMessage());
            }
            
        }
        return "";
    }
    
    /**
     * 根据json串和节点名返回节点
     * 
     * @param json
     * @param nodeName
     * @return
     */
    public static JsonNode getNode(String json, String nodeName)
    {
        JsonNode node = null;
        try
        {
            node = JsonUtils.getObjectMapper().readTree(json);
            return node.get(nodeName);
        }
        catch (JsonProcessingException e)
        {
            log.warn("json error:" + e.getMessage());
        }
        catch (IOException e)
        {
            log.warn("json error:" + e.getMessage());
        }
        return node;
    }
    
    /**
     * 将Java对象转换成Json字符串
     * 
     * @param object
     *            Java对象，可以是对象，数组，List,Map等
     * @return json 字符串
     */
    public static String toJson(Object object)
    {
        String jsonString = "";
        try
        {
            jsonString = objectMapper.writeValueAsString(object);
        }
        catch (Exception e)
        {
            log.warn("json error:" + e.getMessage());
        }
        return jsonString;
        
    }
    
    /**
     * 将json字符串转换成列表
     * 
     * @author 7729
     * @date 2013-12-20 下午2:40:09
     * @return List<?>
     * @param json
     */
    public static List<?> jsonToList(String json)
    {
        try
        {
            return (List<?>) objectMapper.readValue(json, List.class);
        }
        catch (Exception e)
        {
            log.warn("json error:" + e.getMessage());
        }
        return null;
    }
    
    /**
     * 将json字符串转换成Map
     * 
     * @author 7729
     * @date 2013-12-20 下午2:40:19
     * @return Map<String,?>
     * @param json
     */
    @SuppressWarnings("unchecked")
    public static Map<String, ?> jsonToMap(String json)
    {
        try
        {
            // ObjectMapper mapper = new ObjectMapper();
            return (Map<String, ?>) objectMapper.readValue(json, Map.class);
        }
        catch (Exception e)
        {
            log.warn("json error:" + e.getMessage());
        }
        return null;
    }
    
    public static void writeValue(OutputStream out, Object obj) throws JsonGenerationException, JsonMappingException, IOException
    {
        objectMapper.writeValue(out, obj);
    }
}
