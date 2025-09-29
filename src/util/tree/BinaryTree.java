package util.tree;

import java.io.Serial;
import java.io.Serializable;

public class BinaryTree<E extends Comparable<E>> implements Serializable
{
	@Serial
	private static final long serialVersionUID = 7963522615635235701L;
	
	private int size = 1;
	private TreeNode<E> rootNode;
	
	public BinaryTree(TreeNode<E> root)
	{
		this.rootNode = root;
	}
	
	public void add(TreeNode<E> node)
	{
		this.rootNode = this.addRecursively(this.rootNode, node);
	}
	
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
	
	public TreeNode<E> getRoot()
	{
		return this.rootNode;
	} 
	
	public int size()
	{
		return this.size;
	}
	
	public boolean isEmpty()
	{
		return this.size == 1;
	}
	
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