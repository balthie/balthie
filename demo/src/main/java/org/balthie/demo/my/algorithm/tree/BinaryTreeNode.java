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
}
