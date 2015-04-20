/**
 * @author：balthie + 10050
 * @createtime ： 2015年1月6日 上午9:40:36
 * @description TODO 一句话描述
 */
package org.balthie.demo.jdk.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

/**
 * @author：balthie + 10050
 * @createtime ： 2015年1月6日 上午9:40:36
 * @description TODO 一句话描述
 * @since version 初始于版本 TODO 
 */
public class CollectionsDemo
{
    /**
     * @author：balthie + 10050
     * @createtime ： 2015年1月6日 上午9:40:36
     * @description TODO 一句话描述
     * @since version 初始于版本 TODO 
     */
    public static void main(String[] args)
    {
        //UnmodifiableCollection
        Collections.unmodifiableCollection(new ArrayList<String>());
        
        LinkedList<String> list =new LinkedList<String>();
    }
}

