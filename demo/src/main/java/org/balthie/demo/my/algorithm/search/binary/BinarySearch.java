/**
 * @author：balthie + 10050
 * @createtime ： 2015年3月10日 上午11:02:47
 * @description TODO 一句话描述
 */
package org.balthie.demo.my.algorithm.search.binary;

/**
 * @author：balthie + 10050
 * @createtime ： 2015年3月10日 上午11:02:47
 * @description 【算法复杂度】：假设其数组长度为n，其算法复杂度为o（log（n）） 【二分查找要求】：1.必须采用顺序存储结构 2.必须按关键字大小有序排列。
 *              【优点】折半查找法的优点是比较次数少，查找速度快，平均性能好; 【缺点】要求待查表为有序表，且插入删除困难。因此，折半查找方法适用于不经常变动而查找频繁的有序列表。
 */
public class BinarySearch
{
    public static void main(String[] args)
    {
        int[] array = {1,1,2, 2, 3, 5, 7, 8, 9 };
        
        int result = binarySearch(array, 3);
        
        if(result != -1)
            System.out.println("已经在数组中找到，索引位置为：" + result);
        else System.out.println("没有此原始");
    }
    
    public static int binarySearch(int[] list, int key)
    {
        // 最低线
        int low = 0;
        
        // 最高线
        int high = list.length - 1;
        
        while (low <= high)
        {
            // 取中间值
            int middle = (low + high) / 2;
            
            if(list[middle] == key)
            {
                return middle;
            }
            else if(list[middle] > key)
            {
                // 下降一半
                high = middle - 1;
            }
            else
            {
                // 上升一半
                low = middle + 1;
            }
        }
        // 未找到
        return -1;
    }
    
}
