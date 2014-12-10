/**
 * @author：balthie + 10050
 * @createtime ： 2014年12月30日 上午11:00:00
 * @description TODO 一句话描述
 */
package org.balthie.demo.jdk.lang.string;

import org.apache.commons.lang.StringUtils;

/**
 * @author：balthie + 10050
 * @createtime ： 2014年12月30日 上午11:00:00
 * @description TODO 一句话描述
 * @since version 初始于版本 TODO
 */
public class Demo1
{
    
    public static void main(String[] args)
    {
        formatDemo();
    }
    
    public static void formatDemo()
    {
        String.format("%1$s", 1234, 5678); // 指向第一个参数转换为字符串
        String.format("%1$s%2$s", 1234, 5678);// 将两个参数格式化为字符串,并连接在一起
        String.format("%s", 1234, 5678); // 指向第一个参数转换为字符串
        String.format("%s%06d", 1234, 5678); // 将第一个格式化为“1234” 第二个格式化005678 w
        
        System.out.println(String.format("%2$08d", -3123, -5566));
        System.out.println(String.format("%1$9d", -31));
        System.out.println(String.format("%1$-9d", -31));
        System.out.println(String.format("%1$(9d", -31));
        System.out.println(String.format("%1$#9x", 5689));
        
        // 小数点后面两位
        System.out.println(String.format("%1$.2f", 5689.0)); // 必须是同类型的才能进行转换
        
        // 格式化的位置
        String str = "I love ni %s, you love me %s";
        String str2 = "I love ni %2$s, you love me %1$s";
        
        System.out.println(String.format(str, "renjunjie", "songliyu"));
        
        System.out.println(String.format(str2, "renjunjie", "songliyu"));
        // 数组的操作
        Object[] sendData = new Object[4];
        sendData[0] = Integer.valueOf(1);
        sendData[1] = "172.12.1.2";
        sendData[2] = Integer.valueOf(123);
        sendData[3] = "testadfaerfa";
        String sendDataString = String.format("%d,%s,%d,%s", (Object[]) sendData);
        System.out.println(sendDataString);
    }
}
