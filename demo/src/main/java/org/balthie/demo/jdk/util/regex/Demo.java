/**
 * @author：balthie + 10050
 * @createtime ： 2015年3月9日 下午2:41:10
 * @description TODO 一句话描述
 */
package org.balthie.demo.jdk.util.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @description TODO 一句话描述 http://lavasoft.blog.51cto.com/62575/179324/
 */
public class Demo
{
    public static void main(String[] args)
    {
        stringTokenRegex();
        // testIsMatch();
        // extractSubMatch();
        // extractSubDatePattern();
        
        // noCaptureGroup();
    }
    
    private static void noCaptureGroup()
    {
        // Pattern p = Pattern.compile("(\\d+)(\\.?)(\\d+)([￥$])$");
        Pattern p = Pattern.compile("(\\d+)(?:\\.?)(?:\\d+)([￥$])$");
        String str = "8899￥";
        Matcher m = p.matcher(str);
        if(m.matches())
        {
            System.out.println("货币金额: " + m.group(1));
            System.out.println("货币种类: " + m.group(2));
        }
    }
    
    private static void testIsMatch()
    {
        System.out.println(Float.valueOf("-0000000000000000000000000000000." + ""));
        
        // 判断是否 正负整型或浮点型
        Pattern p3 = Pattern.compile("[+-]?\\d*(.\\d+)?");
        
        System.out.println(p3.matcher("+123. 2423").matches());
        System.out.println(p3.matcher("-123").matches());
        System.out.println(p3.matcher("123").matches());
        
        System.out.println(p3.matcher("-0.").matches());
        System.out.println(p3.matcher("-120b").matches());
        System.out.println(p3.matcher("-0.123").matches());
        System.out.println(p3.matcher("0.123").matches());
        /*
         * Matcher m = p3.matcher("+123  2423.00"); System.out.println(m.group());
         */
        
    }
    
    private static void extractSubDatePattern()
    {
        Pattern p3 = Pattern.compile("(19|20)\\d\\d([- /.])(0[1-9]|1[012])\\2(0[1-9]|[12][0-9]|3[01])");
        Matcher m3 = p3.matcher("1900-01-01 2007/08/13 1900.01.01 1900 01 01 1900-01.01 1900 13 01 1900 02 31");
        while (m3.find())
        {
            System.out.println(m3.group());
        }
    }
    
    private static void stringTokenRegex()
    {
        // 从正则语法的角度来讲，被匹配优先量词修饰的子表达式使用的就是贪婪模式，如“(Expression)+”；被忽略优先量词修饰的子表达式使用的就是非贪婪模式，如“(Expression)+?”。
        // .+? 最小匹配，非贪婪模式 http://www.jb51.net/article/31491.htm
        Pattern p = Pattern.compile("(?=@IS@)(.+?)(?=@IS@)");
        Matcher m = p.matcher("@IS@5I67@IS@1@Ihg@IS@@IS@");
        
        while (m.find())
        {
            /*
             * 组的概念，这个概念很重要，组是用括号划分的正则表达式，可以通过编号来引用组。组号从0开始，有几对小括号就表示有几个组，并且组可以嵌套，组号为0的表示整个表达式，
             * 组号为1的表示第一个组，依此类推。 例如：A(B)C(D)E正则式中有三组，组0是ABCDE，组1是B，组2是D；
             * A((B)C)(D)E正则式中有四组：组0是ABCDE，组1是BC，组2是B；组3是C，组4是D。
             */
            int count = m.groupCount();
            String s0 = m.group(0);
            String s1 = m.group(1);
            System.out.println(count + ":  " + s0 + "||" + s1);
            // String s2 = m.group(2);
            // System.out.println(s0 + "||" + s1 + " || "+s2);
        }
        System.out.println("---------");
    }
    
    private static void extractSubMatch()
    {
        Pattern p = Pattern.compile("(@IS@([^(@IS@)]+?)@IS@)");
        Matcher m = p.matcher("@IS@567@IS@11hg@IS@23@IS@");
        while (m.find())
        {
            /*
             * 组的概念，这个概念很重要，组是用括号划分的正则表达式，可以通过编号来引用组。组号从0开始，有几对小括号就表示有几个组，并且组可以嵌套，组号为0的表示整个表达式，
             * 组号为1的表示第一个组，依此类推。 例如：A(B)C(D)E正则式中有三组，组0是ABCDE，组1是B，组2是D；
             * A((B)C)(D)E正则式中有四组：组0是ABCDE，组1是BC，组2是B；组3是C，组4是D。
             */
            String s0 = m.group(0);
            String s1 = m.group(1);
            String s2 = m.group(2);
            System.out.println(s0 + "||" + s1 + "||" + s2);
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
    }
}
