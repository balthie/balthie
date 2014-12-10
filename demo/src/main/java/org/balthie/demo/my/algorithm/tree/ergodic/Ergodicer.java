package org.balthie.demo.my.algorithm.tree.ergodic;

import org.balthie.demo.my.algorithm.tree.BinaryTreeNode;

public class Ergodicer
{
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
        BinaryTreeNode head = createTree1();
        
        //BinaryTreeNode head = createTree2();
        
        Ergodicer e = new Ergodicer();
        e.ergodicParentMid(head);
        // e.ergodicParentPre(head);
        
    }

    private static BinaryTreeNode createTree1()
    {
        BinaryTreeNode head = new BinaryTreeNode("0");
        BinaryTreeNode left1 = new BinaryTreeNode("01");
        BinaryTreeNode right1 = new BinaryTreeNode("02");
        head.left = left1;
        head.right = right1;
        
        BinaryTreeNode left11 = new BinaryTreeNode("011");
        BinaryTreeNode right11 = new BinaryTreeNode("012");
        left1.left = left11;
        left1.right = right11;
        
        BinaryTreeNode left111 = new BinaryTreeNode("0111");
        BinaryTreeNode right111 = new BinaryTreeNode("0112");
        left11.left = left111;
        left11.right = right111;
        
        BinaryTreeNode left31 = new BinaryTreeNode("0121");
        right11.left = left31;
        return head;
    }

    private static BinaryTreeNode createTree2()
    {
        BinaryTreeNode head = new BinaryTreeNode("1");
        BinaryTreeNode left1 = new BinaryTreeNode("2");
        BinaryTreeNode right1 = new BinaryTreeNode("3");
        head.left = left1;
        head.right = right1;
        
        BinaryTreeNode left11 = new BinaryTreeNode("4");
        left1.left = left11;
        
        BinaryTreeNode right111 = new BinaryTreeNode("7");
        left11.right = right111;
        
        BinaryTreeNode n021 = new BinaryTreeNode("5");
        BinaryTreeNode n022 = new BinaryTreeNode("6");
        right1.left = n021;
        right1.right = n022;
        
        BinaryTreeNode n0221 = new BinaryTreeNode("8");
        n022.left=n0221;
        return head;
    }
}
