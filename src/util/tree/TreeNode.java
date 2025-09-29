package util.tree;

import java.io.Serial;
import java.io.Serializable;

public class TreeNode<T extends Comparable<T>> implements Comparable<TreeNode<T>>, Serializable
{
	@Serial
	private static final long serialVersionUID = 7963522615635235701L;
	
	private TreeNode<T> right;
	private TreeNode<T> left;
	private T value;
	
	public TreeNode(T value, TreeNode<T> right, TreeNode<T> left)
	{
		this.right = right;
		this.left = left;
		this.value = value;
	}
	
	public TreeNode(T value)
	{
		this.value = value;
		this.right = null;
		this.left = null;
	}
	
	public T getValue()
	{
		return this.value;
	}
	
	public void setRight(TreeNode<T> right)
	{
		this.right = right;
	}
	
	public void setLeft(TreeNode<T> left)
	{
		this.left = left;
	}
	
	public TreeNode<T> getRight()
	{
		return this.right;
	}
	
	public TreeNode<T> getLeft()
	{
		return this.left;
	}

	@Override
	public int compareTo(TreeNode<T> o)
	{
		return this.value.compareTo(o.getValue());
	}
}