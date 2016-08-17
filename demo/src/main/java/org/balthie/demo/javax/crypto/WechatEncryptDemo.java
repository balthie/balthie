package org.balthie.demo.javax.crypto;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;
import org.balthie.demo.jdk.lang.string.StringUtils;

public class WechatEncryptDemo
{
    public static void main(String[] args)
    {
        Map<String, Object> m = new TreeMap<>();
        m.put("mch_appid", 15263L);
        m.put("mchid", 15263L);
        m.put("device_info", "web");
        m.put("nonce_str", "12341234fsdafgsadg1231");
        m.put("openid", "12341234fsdafgsadg1231");
        m.put("amount", 1000);
        
        m.entrySet().stream().forEach(e -> System.out.println(e.getKey() + "=" + e.getValue()));
        
        List<String> s = m.entrySet().stream().map(e -> e.getKey() + "=" + e.getValue()).collect(Collectors.toList());
        s.stream().forEach(System.out::print);
        System.out.println();
        
        s.add("key=fsagaewgeorg");
        System.out.println(StringUtils.concat(s, "&"));
        
        System.out.println();
        
    }
}
