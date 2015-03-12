package org.balthie.demo.my.algorithm.circle;

import java.util.LinkedList;

//@authorLoveyali_dengforever
public class Yuesefu
{
    /*
     * 约瑟夫环是一个数学的应用问题：已知n个人（以编号1，2，3...n分别表示）围坐在一张圆桌周围。从编号为k的人开始报数，数到m的那个人出列；
     * 他的下一个人又从1开始报数，数到m的那个人又出列；依此规律重复下去，直到圆桌周围的人全部出列。
     */
    private static StringBuffer removedStr = new StringBuffer("");// 记录出列的数字
    
    public static void main(String[] args)
    {
        long startTime = System.currentTimeMillis();// 获取开始时间
        process(5000, 10, 1);
        System.out.println(removedStr.substring(0, removedStr.length() - 1));
        long endTime = System.currentTimeMillis();// 获取结束时间
        System.out.println("程序运行时间：" + (endTime - startTime) + "ms");
    }
    
    public static void process(int n, int k, int m)
    {
        // 构建一个list，存放人数
        LinkedList<Integer> list = new LinkedList<Integer>();
        for(int i = 0; i < n; i++)
        {
            if(i + k > n)
            {
                list.add(i + k - n);
            }
            else
            {
                list.add(i + k);
                int count = 1;// 记录数的人数
                cycleCal(list, count, m);
            }
        }
    }
    
    public static void cycleCal(LinkedList<Integer> list, int count, int m)
    {
        int len = list.size();
        if(len > 1) for(int i = 0; i < len; i++)
            if(count == m)
            {// 第m个时，remove
                removedStr.append(list.get(i)).append(",");
                list.remove(i);
                len = list.size();
                i--;
                count = 0;
                count++;
                cycleCal(list, count, m);
            }
            else
            {
                if(len != 0) removedStr.append(list.get(0)).append(",");
            }
    }
}