package util.tree;

import java.io.Serial;
import java.io.Serializable;

/**
 * A class representing binary tree nodes as a full tree; to easily add and remove leaves.
 * @param <E> the data type of the values.
 * <p>
 * @author Abraham Yelifari
 * @version 1.0
 * @since 2025-9-25 12:08
 */
public class BinaryTree<E extends Comparable<E>> implements Serializable
{
	@Serial
	private static final long serialVersionUID = 7963522615635235701L;
	
	private int size = 1;
	private TreeNode<E> rootNode;
	
	/**
	 * Creates a binary tree with a root.
	 * @param root the node that will be the root.
	 */
	public BinaryTree(TreeNode<E> root)
	{
		this.rootNode = root;
	}
	
	/**
	 * Adds the node value to the tree using recursion. The new node is compared to
	 * each node to determine where to put the new node. This helps order the tree from least to greatest.
	 * @param node the new node to be added.
	 */
	public void add(TreeNode<E> node)
	{
		this.rootNode = this.addRecursively(this.rootNode, node);
	}
	
	/**
	 * Retrieves the value from the tree, and returns null if there is no such value in the tree.
	 * @param value the value to be found.
	 * @return the {@link TreeNode} that was found or {@code null} if there is no such node.
	 */
	public TreeNode<E> get(E value)
	{
		int initcmp = this.rootNode.getValue().compareTo(value);
		TreeNode<E> current = this.rootNode.getLeft();
		
		if (initcmp == 0)
			return this.rootNode;
		
		while (current != null)
		{
			int cmp = current.getValue().compareTo(value);
			if (cmp == 0)
				return current;
			else if (cmp < 0)
				current = current.getLeft();
			else
				current = current.getRight();
		}
		
		return null;
	}
	
	/**
	 * Gets the root of the tree.
	 * @return the root node.
	 */
	public TreeNode<E> getRoot()
	{
		return this.rootNode;
	} 
	
	/**
	 * Gets the size of the tree.
	 * @return the size of the tree.
	 */
	public int size()
	{
		return this.size;
	}
	
	/**
	 * Determines if the tree is empty.
	 * @return {@code true} if the tree is empty, {@code false} if the tree is not empty.
	 */
	public boolean isEmpty()
	{
		return this.size == 0;
	}
	
	
	//Private method to add recursively.
	private TreeNode<E> addRecursively(TreeNode<E> current, TreeNode<E> node)
	{
		if (node == null)
			return current;
		else if (current == null)
			return null;
		
		int cmp = node.getValue().compareTo(current.getValue());
		if (cmp == 0)
		{
			return current;
		}
		else if (cmp < 0)
		{
			this.size += 1;
			current.setLeft(this.addRecursively(current.getLeft(), node));
		}
		else
		{
			this.size += 1;
			current.setRight(this.addRecursively(current.getRight(), node));
		}
		
		return current;
	}
}