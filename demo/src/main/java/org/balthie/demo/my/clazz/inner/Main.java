/**
 * @author：balthie + 10050
 * @createtime ： 2015年3月9日 上午11:41:08
 * @description TODO 一句话描述
 */
package org.balthie.demo.my.clazz.inner;

/**
 * @author：balthie + 10050
 * @createtime ： 2015年3月9日 上午11:41:08
 * @description TODO 一句话描述
 * @since version 初始于版本 TODO
 */
public class Main
{
    public static void main(String[] args)
    {
        Main m = new Main();
        m.anonymousExample();
    }
    
    public void anonymousExample()
    {
        String nonFinalVariable = "Non Final Example";
        String variable = "Outer Method Variable";
        new Thread(new Runnable() {
            String variable = "Runnable Class Member";
            
            public void run()
            {
                String variable = "Run Method Variable";
                // Below line gives compilation error.
                // System.out.println("->" + nonFinalVariable);
                System.out.println("->" + variable);
                System.out.println("->" + this.variable);
            }
        }).start();
    }
    
}
