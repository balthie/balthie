package org.balthie.demo.my.xml.digester;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.digester3.Digester;
import org.balthie.demo.my.xml.Child;
import org.balthie.demo.my.xml.Parent;

//http://blog.csdn.net/chifengxin/article/details/6556675

// 解析xml报文，没看到拼装的文章
public class DigesterTest
{
    
    /**
     */
    public static void main(String[] args)
    {
        Digester digester = new Digester();
        // digester.setValidating(false);
        digester.addObjectCreate("root", ArrayList.class);// create an ArrayList when got a ‘/root’
                                                          // element
        digester.addObjectCreate("root/parent", Parent.class);
        digester.addObjectCreate("root/parent/child", Child.class);
        
        digester.addSetProperties("root/parent", "pname", "name");
        digester.addSetProperties("root/parent/child", "cname", "name");// set propertiy:cname to
                                                                        // Child.name
        digester.addBeanPropertySetter("root/parent/child", "value");// set node value to
                                                                     // Child.value
        
        digester.addSetNext("root/parent", "add");// use add() method of List
        digester.addSetNext("root/parent/child", "addChild");
        
        try
        {
            
            FileInputStream in1 = new FileInputStream("E:\\git_repository\\balthie\\demo\\src\\main\\java\\org\\balthie\\demo\\my\\xml\\digester\\config.xml");
            List<Parent> parents = (ArrayList<Parent>) digester.parse(in1);
            System.out.println(parents.get(0));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
}