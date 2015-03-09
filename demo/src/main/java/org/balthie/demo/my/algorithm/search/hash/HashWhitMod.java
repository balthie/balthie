/**
 * @author：balthie + 10050
 * @createtime ： 2015年3月10日 上午9:54:53
 * @description TODO 一句话描述
 */
package org.balthie.demo.my.algorithm.search.hash;

/**
 * @author：balthie + 10050
 * @createtime ： 2015年3月10日 上午9:54:53
 * @description 除法取余算法
 */
public class HashWhitMod
{
    public static void main(String[] args)
    {
        // “除法取余法”
        int hashLength = 13;
        
        int[] array = { 13, 29, 27, 28, 26, 30, 38 };
        
        // 哈希表长度
        int[] hash = new int[hashLength];
        // 创建hash
        for(int i = 0; i < array.length; i++)
        {
            insertHash(hash, hashLength, array[i]);
        }
        
        int result = searchHash(hash, hashLength, 29);
        
        if(result != -1)
            System.out.println("已经在数组中找到，索引位置为：" + result);
        else System.out.println("没有此原始");
    }
    
    /****
     * Hash表检索数据
     * 
     * @param hash
     * @param hashLength
     * @param key
     * @return
     */
    public static int searchHash(int[] hash, int hashLength, int key)
    {
        // 取余哈希函数
        int hashAddress = key % hashLength;
        
        // 指定hashAdrress对应值存在但不是关键值，则用开放寻址法解决
        while (hash[hashAddress] != 0 && hash[hashAddress] != key)
        {
            hashAddress = (++hashAddress) % hashLength;
        }
        
        // 查找到了开放单元，表示查找失败
        if(hash[hashAddress] == 0) return -1;
        return hashAddress;
        
    }
    
    /***
     * 数据插入Hash表
     * 
     * @param hash 哈希表
     * @param hashLength
     * @param data
     */
    public static void insertHash(int[] hash, int hashLength, int data)
    {
        // 哈希函数
        int hashAddress = data % hashLength;
        
        // 如果key存在，则说明已经被别人占用，此时必须解决冲突
        while (hash[hashAddress] != 0)
        {
            // 用开放寻址法找到
            hashAddress = (++hashAddress) % hashLength;
        }
        
        // 将data存入字典中
        hash[hashAddress] = data;
    }
}
