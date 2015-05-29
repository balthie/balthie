package org.balthie.demo.jdk.lang.string;

import javax.swing.JOptionPane;

// http://blog.sina.com.cn/s/blog_69dcd5ed0101171h.html
// 动态扩充 常量池
public class StringIntern
{
    public static void main(String args[])
    {
        String s1, s2, s3, s4, output;
        s1 = new String("hello");
        s2 = new String("hello");
        
        if(s1 == s2)
            output = "s1   and   s2   are   the   same   object   in   memory";
        else output = "s1   and   s2   are   not   the   same   object   in   memory";
        
        if(s1.equals(s2))
            output += "\ns1   and   s2   are   equal";
        else output += "\ns1   and   s2   are   not   equal";
        
        s3 = s1.intern();
        s4 = s2.intern();
        
        if(s3 == s4)
            output += "\ns3   and   s4   are   the   same   object   in   memory";
        else output += "\ns3   and   s4   are   not   the   same   object   in   memory";
        
        if(s1 == s3)
            output += "\ns1   and   s3   are   the   same   object   in   memory";
        else output += "\ns1   and   s3   are   not   the   same   object   in   memory";
        
        if(s2 == s4)
            output += "\ns2   and   s4   are   the   same   object   in   memory";
        else output += "\ns2   and   s4   are   not   the   same   object   in   memory";
        
        if(s1 == s4)
            output += "\ns1   and   s4   are   the   same   object   in   memory";
        else output += "\ns1   and   s4   are   not   the   same   object   in   memory";
        
        JOptionPane.showMessageDialog(null, output, "Demonstrating   String   Method   intern", JOptionPane.INFORMATION_MESSAGE);
        
        System.exit(0);
    }
}