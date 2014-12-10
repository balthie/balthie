/**
 * @author：balthie + 10050
 * @createtime ： 2015年3月10日 下午4:46:13
 * @description TODO 一句话描述
 */
package org.balthie.demo.my.algorithm.sort.strategy;

/**
 * @author：balthie + 10050
 * @createtime ： 2015年3月10日 下午4:46:13
 * @description TODO 一句话描述
 * @since version 初始于版本 TODO 
 */
public interface SortStrategy
{
    /**
     * 合并两个有序子序列
     * @param a
     * @param low
     * @param mid
     * @param high
     * @param b 辅助数组
     */
    void merge(int[] a, int low, int mid, int high, int[] b);
    
}
