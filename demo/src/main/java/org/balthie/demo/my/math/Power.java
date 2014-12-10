/**
 * @author：balthie + 10050
 * @createtime ： 2015年3月10日 下午3:30:24
 * @description TODO 一句话描述
 */
package org.balthie.demo.my.math;

/**
 * @author：balthie + 10050
 * @createtime ： 2015年3月10日 下午3:30:24
 * @description 计算整数次方，不使用api，不考虑大数据
 */
public class Power
{
    public static double power(double v, int exp)
    {
        // 底数为0的情况特殊处理
        if(v == 0.0d)
        {
            return 0.0d;
        }
        
        int absExp = exp > 0 ? exp : -exp;
        
        Double powValueAbs = powerAbsAdvance(v, absExp);
        
        return exp > 0 ? powValueAbs : 1.0 / powValueAbs;
    }
    
    /**
     * @author：balthie + 10050
     * @createtime ： 2015年3月10日 下午3:54:36
     * @description 循环求乘方
     */
    private static Double powerAbs(double v, int absExp)
    {
        double temp = v;
        for(int i = 1; i < absExp; i++)
        {
            temp *= v;
        }
        return temp;
    }
    
    /**
     * @author：balthie + 10050
     * @createtime ： 2015年3月10日 下午3:54:36
     * @description 递归求乘方
     */
    private static Double powerAbsAdvance(double v, int absExp)
    {
        double temp = v;
        
        if(absExp == 1)
        {
        }
        else if(absExp % 2 == 0)
        {
            // 偶数 ,
            temp = powerAbsAdvance(v, absExp>>1);
            temp *= temp;
        }
        else
        {
            // 奇数
            temp = powerAbsAdvance(v, (absExp-1)>>1);
            temp *= temp;
            temp *= v;
        }
        
        return temp;
    }
    
    public static void main(String[] args)
    {
        long begin = System.currentTimeMillis();
        
        System.out.println(Math.pow(2d, 100));
        long time1 = System.currentTimeMillis();
        System.out.println(" end 1 during " + (time1 - begin) +" ms");
        
        System.out.println(Power.power(2d, 100));
        long time2 = System.currentTimeMillis();
        System.out.println(" end 2 during " + (time2 - time1) +" ms");
    }
}
