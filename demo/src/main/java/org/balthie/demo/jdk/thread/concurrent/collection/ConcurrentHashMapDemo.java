/**
 * @author：balthie + 10050
 * @createtime ： 2015年1月27日 上午10:25:12
 * @description TODO 一句话描述
 */
package org.balthie.demo.jdk.thread.concurrent.collection;

public class ConcurrentHashMapDemo
{
    public static void main(String[] args)
    {
        /**
         * ConcurrentHashMap -- segment -- table -- HashEntry
         * 
         * @1 首先ConcurrentHashMap先把数据分到0-16个默认创建好的数组中，数组里面的元素就叫segment，相当于书的大目录； Segment
         *    用来充当数据划分和锁的角色，每个Segment对象守护整个散列映射表的若干个table。每个table是由若干个 HashEntry对象链接起来的链表。
         * 
         * @2 每个segment里面包含一个名叫table的数组 ，这个数组里面的元素就是HashEntry，相当于书的一个子目录；
         * 
         * @3 HashEntry是链表结构的数据，里面有下一个HashEntry的引用，这样一个一个迭代就能找到我们所需要的内容。
         *    HashEntry的一个特点，除了value以外，其他的几个变量都是final的
         *    ，这样做是为了防止链表结构被破坏，出现ConcurrentModification的情况，这种不变性来降低读操作对加锁的需求
         *    ，ConcurrentHashMap才能保证数据在高并发的一致性。
         * 
         * @4 定位元素位置：二次hash 整个数据查找的过程： 1.将key的hash进行二次hash 2.根据hash值定位到数据在哪一个segment中：segmentFor()
         *    3.根据hash值定位到数据在table中的第一个HashEntry 4.根据HashEntry中的next属性，依次比对，直到返回结果
         *    从上述过程中，我们可以理解缓存为什么这么快
         *    ，因为它在查找过程中仅进行一次hash运算，2次位运算就定位到数据所在的数据块，而链式查找的效率也是比较高的，更关键的是绝大多数情况下
         *    ，如果数据存在，缓存会首先进行查询尝试，以避免数据块加锁，所以缓存才能快速的查询到数据。
         */
    }
}
