/**
 * @author：balthie + 10050
 * @createtime ： 2015年1月26日 上午10:12:34
 * @description TODO 一句话描述
 */
package org.balthie.demo.my.json.jackson2.map;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.balthie.demo.my.json.jackson2.Data;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author：balthie + 10050
 * @createtime ： 2015年1月26日 上午10:12:34
 * @description TODO 一句话描述
 * @since version 初始于版本 TODO 
 */
public class MapJsonDemo1
{
    ObjectMapper mapper = new ObjectMapper();
    
    public static void main(String[] args) throws JsonGenerationException, JsonMappingException, IOException
    {
        MapJsonDemo1 d = new MapJsonDemo1();
       // System.out.println(" stringMap2Json()  = " + d.stringMap2Json());
        d.testData();
    }
    
    /**
     * @return 
     * @throws JsonProcessingException 
     * @author：balthie + 10050
     * @createtime ： 2015年1月26日 上午10:17:48
     * @description TODO 一句话描述
     * @since version 初始于版本 TODO 
     */
    public String stringMap2Json() throws JsonProcessingException
    {
        HashMap<String, String> stringMap = new HashMap<String, String>();
        stringMap.put("key01", "value01");
        stringMap.put("key02", "value02");
        return mapper.writeValueAsString(stringMap);
    }
    
    public void testData() throws JsonGenerationException, JsonMappingException, IOException
    {
        Data user1 = new Data("1", "user1", new Date());
        
        // 仅输出一行json字符串, 调用后 system.out.println方法都不能打印信息到控制台（应该是调用了write方法后，jackson自动关闭了
        // System.out输出流）
        //mapper.writeValue(System.out, user1);
        
        // 获取json字符串
        String jsonStr = mapper.writeValueAsString(user1);
        System.out.println(" jsonStr = " + jsonStr);
        
        // 将字符串美化成多行
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(user1));
        
        
        CommonMapData d = new CommonMapData();
        Map<String, String> hashMap = new HashMap<>(5);
        hashMap.put("k1", "v1");
        hashMap.put("k2", "v2");
        hashMap.put("k3", "v3");
        mapper.writeValue(System.out, hashMap);
        d.setStringMap(hashMap);
        mapper.writeValue(System.out, d);
    }
    
    public class CommonMapData
    {
        Map<String, String> stringMap ;

        public Map<String, String> getStringMap()
        {
            return stringMap;
        }

        public void setStringMap(Map<String, String> stringMap)
        {
            this.stringMap = stringMap;
        }
    }
}
