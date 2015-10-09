/**
 * @author：balthie + 10050
 * @createtime ： 2015年3月9日 下午2:41:10
 * @description TODO 一句话描述
 */
package org.balthie.demo.jdk.util.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @description TODO 一句话描述
 * http://lavasoft.blog.51cto.com/62575/179324/
 */
public class Demo
{
    public static void main(String[] args)
    {
        
        // 测试组模式匹配
        //groupTest();
    }

    private static void groupTest()
    {
        Pattern p = Pattern.compile("f(.+?)k");
        Matcher m = p.matcher("fckfkkfkf");
        while (m.find())
        {
            /*
             * 组的概念，这个概念很重要，组是用括号划分的正则表达式，可以通过编号来引用组。组号从0开始，有几对小括号就表示有几个组，并且组可以嵌套，组号为0的表示整个表达式，
             * 组号为1的表示第一个组，依此类推。 例如：A(B)C(D)E正则式中有三组，组0是ABCDE，组1是B，组2是D；
             * A((B)C)(D)E正则式中有四组：组0是ABCDE，组1是BC，组2是B；组3是C，组4是D。
             */
            String s0 = m.group(0);
            String s1 = m.group(1);
            System.out.println(s0 + "||" + s1);
        }
        System.out.println("---------");
        m.reset("fucking!");
        while (m.find())
        {
            String s0 = m.group(0);
            String s1 = m.group(1);
            System.out.println(s0 + "||" + s1);
        }
        
        System.out.println("---------");
        Pattern p1 = Pattern.compile("f(.+?)i(.+?)h");
        Matcher m1 = p1.matcher("finishabigfishfrish");
        while (m1.find())
        {
            String s0 = m1.group();
            String s1 = m1.group(1);
            String s2 = m1.group(2);
            System.out.println(s0 + "||" + s1 + "||" + s2);
        }
        
        System.out.println("---------");
        Pattern p3 = Pattern.compile("(19|20)\\d\\d([- /.])(0[1-9]|1[012])\\2(0[1-9]|[12][0-9]|3[01])");
        Matcher m3 = p3.matcher("1900-01-01 2007/08/13 1900.01.01 1900 01 01 1900-01.01 1900 13 01 1900 02 31");
        while (m3.find())
        {
            System.out.println(m3.group());
        }
    }
}
