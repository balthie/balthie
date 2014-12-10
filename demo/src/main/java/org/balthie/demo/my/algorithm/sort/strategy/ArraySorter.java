/**
 * @author：balthie + 10050
 * @createtime ： 2015年3月10日 下午4:45:12
 * @description TODO 一句话描述
 */
package org.balthie.demo.my.algorithm.sort.strategy;

/**
 * @author：balthie + 10050
 * @createtime ： 2015年3月10日 下午4:45:12
 * @description TODO 一句话描述
 */
public class ArraySorter
{
    public static void main(String[] args)
    {
        int a[] = { 21, 34, 56, 43, 99, 37, 78, 10 };// 这里对8个元素进行排序
        int low = 0, high = 7;// 初始化low和high的值，即数组的起始和终止的坐标
        // 辅助数组b，作为临时数组
        int b[] = new int[a.length];
        // 输出排序前的数组
        System.out.print("排序前：");
        for(int i = 0; i <= high; i++)
        {
            System.out.print(a[i] + " ");
        }
        // 归并排序
        mergerSort(a, low, high, b);
        // 输出排序后的数组
        System.out.print("排序后：");
        for(int i = 0; i <= high; i++)
        {
            System.out.print(a[i] + " ");
        }
    }
    
    /**
     * 分治和归并
     * 
     * @param a
     * @param low
     * @param high
     * @param b
     */
    public static void mergerSort(int a[], int low, int high, int b[])
    {
        int mid = 0;
        //始终使用同一块辅助内存b
        if(low < high)
        {
            mid = (high + low) / 2;// 分治位置,即将数组拆分的位置
            mergerSort(a, low, mid, b);
            mergerSort(a, mid + 1, high, b);
            merger(a, low, mid, high, b);// 归并
        }
    }
    
    /**
     * 合并两个有序子序列
     * @param a
     * @param low
     * @param mid
     * @param high
     * @param b 辅助数组
     */
    public static void merger(int[] a, int low, int mid, int high, int b[])
    {
        
        int i = low;
        int j = mid + 1;
        int p = 0;
        //在辅助内存b中重排数组，将b复制到a的对应部分
        // 合并两个有序数组 子序列1 a[low..mid] 子序列2 a[mid+1..high]，将较小的元素放到临时数组中
        while (i <= mid && j <= high)
        {
            b[p++] = (a[i] <= a[j]) ? a[i++] : a[j++];
        }
        // 如果子序列1没有合并完则直接复制到复制数组中去（左边数组中的元素较大的情况）
        while (i <= mid)
        {
            b[p++] = a[i++];
        }
        // 如果子序列2没有合并完则直接复制到复制数组中去（右边数组中的元素较大的情况）
        while (j <= high)
        {
            b[p++] = a[j++];
        }
        // 把辅助数组的元素复制到原来的数组中去
        for(p = 0, i = low; i <= high; i++, p++)
        {
            a[i] = b[p];
        }
    }
}

