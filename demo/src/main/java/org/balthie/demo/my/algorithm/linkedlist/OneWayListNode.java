package org.balthie.demo.my.algorithm.linkedlist;

public class OneWayListNode
{
    public int index;
    
    public OneWayListNode next;
    
    public OneWayListNode(int index)
    {
        super();
        this.index = index;
    }

    public void print()
    {
        OneWayListNode node = this;
        while(node!=null)
        {
            System.out.println(this.index+"");
            node = node.next;
        }
    }
}
