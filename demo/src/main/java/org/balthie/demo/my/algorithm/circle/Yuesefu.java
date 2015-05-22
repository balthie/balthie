package org.balthie.demo.my.algorithm.circle;

import java.util.LinkedList;

/**
 * http://www.doc88.com/p-0641669286766.html http://blog.sina.com.cn/s/blog_4d8f3f920101ebpd.html
 */
public class Yuesefu
{
    /*
     * 数学解法 将这些人从0到n编号，假设除去第k个人。
     * @ 0, 1, 2, 3, ..., k-2, k-1, k, ..., n-1　　//original sequence
     * @(1) 0, 1, 2, 3, ..., k-2, , k, ..., n-1　　//get rid of kth person
     * @(2) k, k+1, ..., n-1, 0, 1, ..., k-2　　//rearrange the sequence
     * @(3) 0, 1, ..., n-k-1, n-k, n-k+1, ..., n-2　　//the n-1 person
     * @(4) 我们假设f(n)的值为n个人中最后存活的人的序号，则 注意到(2)式(3)式(4)式其实是同一个序列。 注意(1)式和(4)式，是同一个问题，不同的仅仅是人数。
     * 对比(3)(4)两式，可以看出(3)中的编号x'与(4)中的编号x对应关系即为x'= (x+k) mod n
     * 
     * 故，最终出列人编号   x = f(n) = (f(n-1) + m) % n = (((f(n-2) + m) % n-1) + m) % n 
     */
    public static void mathAnwser()
    {
        int n,m,i;
        //只有一个人时出列的就是第一个人
        int s = 0; 
        n = 10;
        m =2;
        
        for(i = 2; i <= n; i++)
         {
            s = (s + m) % i;
         }
         System.out.println("\n The winner is "+ (s+1));
    }
    
    /*
     * 约瑟夫环是一个数学的应用问题：已知n个人（以编号1，2，3...n分别表示）围坐在一张圆桌周围。从编号为k的人开始报数，数到m的那个人出列；
     * 他的下一个人又从1开始报数，数到m的那个人又出列；依此规律重复下去，直到圆桌周围的人全部出列。
     */
    private static StringBuffer removedStr = new StringBuffer("");// 记录出列的数字
    
    public static void main(String[] args)
    {
       /* long startTime = System.currentTimeMillis();// 获取开始时间
        process(5000, 10, 1);
        System.out.println(removedStr.substring(0, removedStr.length() - 1));
        long endTime = System.currentTimeMillis();// 获取结束时间
        System.out.println("程序运行时间：" + (endTime - startTime) + "ms");*/
        
        mathAnwser();
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