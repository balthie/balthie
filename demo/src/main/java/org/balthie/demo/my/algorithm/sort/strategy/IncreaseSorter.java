/**
 * @author：balthie + 10050
 * @createtime ： 2015年3月10日 下午4:46:49
 * @description TODO 一句话描述
 */
package org.balthie.demo.my.algorithm.sort.strategy;

/**
 * @author：balthie + 10050
 * @createtime ： 2015年3月10日 下午4:46:49
 * @description TODO 一句话描述
 */
public class IncreaseSorter implements SortStrategy
{
    public void merge(int[] a, int low, int mid, int high, int b[])
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
