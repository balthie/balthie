package org.balthie.demo.my.algorithm.linkedlist;

public class Reverser
{
    public static OneWayListNode reverse(OneWayListNode headNode)
    {
        OneWayListNode preNode = null;
        
        OneWayListNode curNode = headNode;
        
        OneWayListNode nextNode = null;
        
        OneWayListNode tailNode = null;
        
        while(curNode != null)
        {
            nextNode = curNode.next;
            if(nextNode == null)
            {
                tailNode = curNode;
            }
            curNode.next = preNode;
            preNode = curNode;
            curNode = nextNode;
        }
        return tailNode;
    }
    
    public static void main(String[] args)
    {
        OneWayListNode n1 = new OneWayListNode(1);
        OneWayListNode n2 = new OneWayListNode(2);
        OneWayListNode n3 = new OneWayListNode(3);
        OneWayListNode n4 = new OneWayListNode(4);
        OneWayListNode n5 = new OneWayListNode(5);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        
        n1.print();
        
        n1 = Reverser.reverse(n1);
        n1.print();
    }
}
