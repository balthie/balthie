package org.balthie.demo.my.algorithm.tree;

public class BinaryTreeNode
{
    public BinaryTreeNode left;
    
    public BinaryTreeNode right;
    
    public String value;
    
    public BinaryTreeNode(String value)
    {
        this.value = value;
    }

    public boolean isLeaf()
    {
        return left == null && right == null;
    }
    
    public static BinaryTreeNode createTree1()
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
    
    public static BinaryTreeNode createTree2()
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
        n022.left = n0221;
        return head;
    }
}
