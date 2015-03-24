package org.balthie.demo.my.algorithm.tree.ergodic;

import java.util.Deque;
import java.util.concurrent.LinkedBlockingDeque;
import org.balthie.demo.my.algorithm.tree.BinaryTreeNode;

public class Ergodicer
{
    public void ergodicEachLevel(BinaryTreeNode head)
    {
        Deque<BinaryTreeNode> deque = new LinkedBlockingDeque<BinaryTreeNode>();
        BinaryTreeNode node = null;
        if(head != null)
        {
            deque.push(head);
            
            while (deque.size() > 0)
            {
                node = deque.pop();
                System.out.println(node.value);
                
                if(node.left != null)
                {
                    deque.addLast(node.left);
                }
                if(node.right != null)
                {
                    deque.addLast(node.right);
                }
            }
        }
    }
    
    // 前序遍历
    public void ergodicParentPre(BinaryTreeNode node)
    {
        if(node != null)
        {
            System.out.println(node.value);
            if(node.left != null)
            {
                ergodicParentPre(node.left);
            }
            if(node.right != null)
            {
                ergodicParentPre(node.right);
            }
        }
    }
    
    // 中序遍历
    public void ergodicParentMid(BinaryTreeNode node)
    {
        if(node != null)
        {
            if(node.left != null)
            {
                ergodicParentMid(node.left);
            }
            System.out.println(node.value);
            if(node.right != null)
            {
                ergodicParentMid(node.right);
            }
        }
    }
    
    public static void main(String[] args)
    {
        // BinaryTreeNode head = BinaryTreeNode.createTree1();
        
        BinaryTreeNode head = BinaryTreeNode.createTree2();
        
        Ergodicer e = new Ergodicer();
        e.ergodicEachLevel(head);
        
        // e.ergodicParentMid(head);
        // e.ergodicParentPre(head);
        
    }
    
}
