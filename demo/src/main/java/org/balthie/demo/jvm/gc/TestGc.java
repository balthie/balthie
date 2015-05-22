package org.balthie.demo.jvm.gc;

/**
 * @1 新生代GC( Minor GC) ：发生在新生代的GC
 * @2 老年代GC( Major GC) ：出现 major GC 一般伴随 至少一次 Minor GC （例外：ParallelScavenge 收集器策略里有直接进行 Major GC的策略）
 * 
 *    对象优先在 Eden 区分配内存
 * 
 */
public class TestGc
{
    private static final int _1MB = 1024 * 1024;
    
    public static void main(String[] args)
    {
        // testAllocationEden();
        testAllocationTenured();
    }
    
    // vm 参数 ： -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:SurvivorRatio=8 -XX:+UseSerialGC
    // -XX:+PrintGCDetails -XXHandlePromotionFailure
    // -XXHandlePromotionFailure: 空间分配担保
    // ,虚拟机每次晋升老年代对象前检查老年代剩余空间，如果大于剩余空间直接进行fullGC，如果小于则看是否允许担保，允许时只进行minor GC
    public static void testHandlePromotionFailure()
    {
        // 对象优先在 Eden 区分配内存
        byte[] allocation1, allocation2, allocation3, allocation4, allocation5, allocation6, allocation7;
        allocation1 = new byte[2 * _1MB];
        allocation2 = new byte[2 * _1MB];
        allocation3 = new byte[4 * _1MB];
        allocation1 = null;
        allocation4 = new byte[4 * _1MB];
        allocation5 = new byte[4 * _1MB];
        allocation6 = new byte[4 * _1MB];
        allocation4 = null;
        allocation5 = null;
        allocation6 = null;
        
        allocation7 = new byte[4 * _1MB];
    }
    
    // 设置进入老年代条件
    // vm 参数 ： -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:SurvivorRatio=8 -XX:+UseSerialGC
    // -XX:+PrintGCDetails -XX:PretenureSizeThreshold=3000000
    // -XX:PretenureSizeThreshold=1024*1024*1 设置超过此长度的对象直接进入老年代，只对 Serial 和 ParNew有效
    // -XX:MaxTenuringThreshold=15 对象经过15次垃圾回收后进入老年代
    public static void testAllocationTenured()
    {
        // 对象优先在 Eden 区分配内存
        byte[] allocation1, allocation2, allocation3;
        // allocation1 = new byte[2 * _1MB];
        // allocation2 = new byte[2 * _1MB];
        allocation3 = new byte[4 * _1MB];
    }
    
    // vm 参数 ： -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:SurvivorRatio=8 -XX:+UseSerialGC
    // -XX:+PrintGCDetails
    // -XX:+PrintGCDetails
    // -verbose:gc 在虚拟机发生内存回收时在输出设备显示信息，格式如下： [Full GC 268K->168K(1984K), 0.0187390 secs]
    // 该参数用来监视虚拟机内存回收的情况。
    // -XX:+UseSerialGC 强制使用 serialGC
    // -Xms20M -Xmx20M 堆最大最小都是 20M
    // -Xmn10M 新生代分配了10M的内存
    // -XX:SurvivorRatio=8 eden:survivor = 8:1:1
    // 虚拟机提供 -XX:+PrintGCDetails 参数，虚拟机在垃圾回收时打印日志，并在退出进程时输出当前内存区域分配情况
    public static void testAllocationEden()
    {
        // 对象优先在 Eden 区分配内存
        byte[] allocation1, allocation2, allocation3, allocation4;
        allocation1 = new byte[1 * _1MB];
        allocation2 = new byte[2 * _1MB];
        allocation3 = new byte[4 * _1MB];
        allocation4 = new byte[2 * _1MB]; // 出现一次minor GC
    }
}
