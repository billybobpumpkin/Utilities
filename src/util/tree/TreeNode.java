package util.tree;

import java.io.Serial;
import java.io.Serializable;

/**
 * A class representing a binary tree, a non-linear data structure
 * where each node can have at most two child nodes, a left child and a right child.
 * @param <E> the data type of the values.
 * <p>
 * @author Abraham Yelifari
 * @version 1.0
 * @since 2025-9-25 12:08
 */
public class TreeNode<T extends Comparable<T>> implements Comparable<TreeNode<T>>, Serializable
{
	@Serial
	private static final long serialVersionUID = 7963522615635235701L;
	
	private TreeNode<T> right;
	private TreeNode<T> left;
	private T value;
	
	/**
	 * Creates the tree node with a value and the left and right
	 * being pointed to another node. Values can be null.
	 * @param value the value of the node.
	 * @param right the node to the right.
	 * @param left the node to the left.
	 */
	public TreeNode(T value, TreeNode<T> right, TreeNode<T> left)
	{
		this.right = right;
		this.left = left;
		this.value = value;
	}
	
	/**
	 * Creates the tree node with a value and the left and right
	 * being pointed nothing.
	 * @param value the value of the node.
	 */
	public TreeNode(T value)
	{
		this.value = value;
		this.right = null;
		this.left = null;
	}
	
	/**
	 * Gets the value of the node.
	 * @return the value of the node.
	 */
	public T getValue()
	{
		return this.value;
	}
	
	/**
	 * Sets the node to the right of this node.
	 * @param right the node to be set to the right.
	 */
	public void setRight(TreeNode<T> right)
	{
		this.right = right;
	}
	
	/**
	 * Sets the node to the left of this node.
	 * @param right the node to be set to the left.
	 */
	public void setLeft(TreeNode<T> left)
	{
		this.left = left;
	}
	
	/**
	 * Gets the right node.
	 * @return the right node.
	 */
	public TreeNode<T> getRight()
	{
		return this.right;
	}
	
	/**
	 * Gets the left node.
	 * @return the left node.
	 */
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