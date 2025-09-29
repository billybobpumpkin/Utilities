package util.linkedlist;

import java.io.Serial;
import java.io.Serializable;

public class LinkedListNode<T extends Comparable<T>> implements Comparable<LinkedListNode<T>>, Serializable
{
	@Serial
	private static final long serialVersionUID = -3830436935315567395L;

	private T value;
	private LinkedListNode<T> next;
	
	public LinkedListNode(T value)
	{
		this.value = value;
	}

	public LinkedListNode<T> getNext()
	{
		return next;
	}

	public void setNext(LinkedListNode<T> next)
	{
		this.next = next;
	}
	
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