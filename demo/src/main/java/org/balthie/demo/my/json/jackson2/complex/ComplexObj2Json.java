package org.balthie.demo.my.json.jackson2.complex;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.balthie.demo.my.json.jackson2.complex.dataentity.DeviceStatus;
import org.balthie.demo.my.json.jackson2.complex.dataentity.Envelop;
import org.balthie.demo.my.json.jackson2.complex.dataentity.Location;
import org.balthie.demo.my.json.jackson2.complex.dataentity.StepAnalyze;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

// 复杂对象的转换，深层次的对象关系，嵌套的泛型集合
public class ComplexObj2Json
{
    static ObjectMapper mapper = new ObjectMapper();
    
    public static void main(String[] args) throws IOException
    {
        String jsonStr = trans2Str();
        
        //jsonStr = "{  \"loc\" : {    \"time\" : 1434357836605,    \"type\" : 10,    \"latitude\" : 123.12346,    \"longtitude\" : 40.65432,    \"speed\" : 12.0,    \"direction\" : 90.0  },  \"devs\" : {    \"time\" : 1434357836605,    \"voltage\" : 5.123,    \"restVoltage\" : 12,    \"bReset\" : false  }}";
        
        TypeReference<Envelop> ref = new TypeReference<Envelop>() {};
        Envelop newEnvelop = mapper.readValue(jsonStr, ref);
        System.out.println(newEnvelop);
        
        /*
         * 错误1 Exception in thread "main" com.fasterxml.jackson.databind.JsonMappingException: No
         * suitable constructor found for type [simple type, class
         * org.balthie.demo.my.json.jackson2.complex.dataentity.Location]: can not instantiate from
         * JSON object (need to add/enable type information?)
         * 
         * 反序列化的的 类需要有无参构造方法
         */
    }

    private static String trans2Str() throws JsonProcessingException
    {
        Envelop e = buildMockData();
        
        String jsonStr = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(e);
        
        System.out.println(jsonStr);
        return jsonStr;
    }

    private static Envelop buildMockData()
    {
        Envelop e = new Envelop();
        
        e.setLocation(new Location(System.currentTimeMillis(), (byte) 10, 123.123456f, 40.654321f, 12.00f, 90.00f));
        
        e.setDeviceStatus(new DeviceStatus(System.currentTimeMillis(), 5.123f, 12, false));
        
        List<StepAnalyze> stepList = new ArrayList<>();
        for(int i=0;i <5;i++)
        {
            stepList.add(new StepAnalyze("2015-06-14", i*10, i*20, 1200));
        }
        e.setStepList(stepList);
        return e;
    }

}
