package org.balthie.demo.jvm.gc;

public class TestTenured
{
    private static final int _1MB = 1024 * 1024;
    
    public static void main(String[] args)
    {
        testAllocationTenured();
    }
    
    // vm 参数 ： -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:SurvivorRatio=8 -XX:+UseSerialGC
    // -XX:+PrintGCDetails -XX:PretenureSizeThreshold=3000000
    // -XX:PretenureSizeThreshold=1024*1024*1 设置超过此长度的对象直接进入老年代，只对 Serial 和 ParNew有效
    public static void testAllocationTenured()
    {
        // 对象优先在 Eden 区分配内存
        byte[] allocation1, allocation2, allocation3;
        // allocation1 = new byte[2 * _1MB];
        // allocation2 = new byte[2 * _1MB];
        allocation3 = new byte[4 * _1MB];
    }
}
