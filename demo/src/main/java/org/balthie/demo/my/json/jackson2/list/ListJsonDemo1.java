/**
 * @author：balthie + 10050
 * @createtime ： 2015年1月26日 上午9:32:15
 * @description TODO 一句话描述
 */
package org.balthie.demo.my.json.jackson2.list;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import org.balthie.demo.my.json.jackson2.Data;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author：balthie + 10050
 * @createtime ： 2015年1月26日 上午9:32:15
 * @description TODO 一句话描述
 * @since version 初始于版本 TODO
 */
public class ListJsonDemo1
{
    ObjectMapper mapper = new ObjectMapper();
    
    public static void main(String[] args) throws JsonGenerationException, JsonMappingException, IOException
    {
        ListJsonDemo1 d = new ListJsonDemo1();
        
        // d.testData();
        
        // 泛型类型 String 转换
        String jsonStr = d.stringListToJson();
        System.out.println(" testStringList jsonStr = " + jsonStr);
        List<String> list = d.jsonToStringList(jsonStr);
        System.out.println(" ------------- get from str string list -------------" + list);
        
        // 泛型类型 Data 转换
        jsonStr = d.dataListToJson();
        System.out.println(" dataListToJson jsonStr = " + jsonStr);
        List<Data> dataList = d.jsonToDataList(jsonStr);
        System.out.println(" -------------  get from str data List -------------" + dataList);
    }
    
    public static String stringListToJson() throws JsonProcessingException
    {
        ArrayList<String> lists = new ArrayList<String>();
        lists.add("testlist01");
        lists.add("testlist02");
//        return mapper.writeValueAsString(lists);
        return null;
    }
    
    public List<String> jsonToStringList(String jsonStr) throws JsonParseException, JsonMappingException, IOException
    {
        TypeReference<List<String>> ref = new TypeReference<List<String>>() {};
        List<String> user = mapper.readValue(jsonStr, ref);
        return user;
    }
    
    public String dataListToJson() throws JsonProcessingException
    {
        List<Data> list = new ArrayList<Data>();
        list.add(new Data("1", "user1", new Date()));
        list.add(new Data("2", "user2", new Date()));
        list.add(new Data("3", "user3", new Date()));
        return mapper.writeValueAsString(list);
    }
    
    public List<Data> jsonToDataList(String jsonStr) throws JsonParseException, JsonMappingException, IOException
    {
        TypeReference<List<Data>> ref = new TypeReference<List<Data>>() {};
        List<Data> user = mapper.readValue(jsonStr, ref);
        return user;
    }
    
    public void testData() throws JsonGenerationException, JsonMappingException, IOException
    {
        HashMap<String, String> maps = new HashMap<String, String>();
        maps.put("mapkey01", "mapvalue01");
        maps.put("mapkey02", "mapvalue02");
        Data user1 = new Data("1", "user1", new Date());
        
        // 仅输出一行json字符串, 调用后 system.out.println方法都不能打印信息到控制台（应该是调用了write方法后，jackson自动关闭了
        // System.out输出流）
        mapper.writeValue(System.out, user1);
        
        // 获取json字符串
        String jsonStr = mapper.writeValueAsString(user1);
        System.out.println(" jsonStr = " + jsonStr);
        
        // 将字符串美化成多行
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(user1));
    }
}
