package util.linkedlist;

import java.io.Serial;
import java.io.Serializable;

/**
 * A class used to represent a node inside of the {@link LinkedList class}. The node class by itself can
 * be used for pure linked list operations, but it is recommended to use the {@link LinkedList class}
 * due to it usefulness.
 * @param <T>
 */
public class LinkedListNode<T extends Comparable<T>> implements Comparable<LinkedListNode<T>>, Serializable
{
	@Serial
	private static final long serialVersionUID = -3830436935315567395L;

	private T value;
	private LinkedListNode<T> next = null;
	
	/**
	 * Creates the node.
	 * @param value the value inside of the node.
	 */
	public LinkedListNode(T value)
	{
		this.value = value;
	}
	
	/**
	 * Used to get the next node being pointed to.
	 */
	public LinkedListNode<T> getNext()
	{
		return next;
	}

	/**
	 * Used to set the next node that is to be pointed to. This can be null, which is used to
	 * represent if the node is the tail.
	 * @param next the node to be pointed to.
	 */
	public void setNext(LinkedListNode<T> next)
	{
		this.next = next;
	}
	
	/**
	 * Gets the value of the current node.
	 * @return the value.
	 */
	public T getValue()
	{
		return this.value;
	}
	
	@Override
	public int compareTo(LinkedListNode<T> o)
	{
		return this.value.compareTo(o.getValue());
	}
	
	@Override
	public String toString()
	{
		return String.valueOf(this.value);
	}
}