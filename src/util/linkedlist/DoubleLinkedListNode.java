package util.linkedlist;

import java.io.Serial;
import java.io.Serializable;

public class DoubleLinkedListNode<T extends Comparable<T>> implements Comparable<DoubleLinkedListNode<T>>, Serializable
{
	@Serial
	private static final long serialVersionUID = -3830436935315567395L;

	private T value;
	private DoubleLinkedListNode<T> next;
	private DoubleLinkedListNode<T> prev;
	
	public DoubleLinkedListNode(T value)
	{
		this.value = value;
	}

	public DoubleLinkedListNode<T> getNext()
	{
		return next;
	}

	public void setNext(DoubleLinkedListNode<T> next)
	{
		this.next = next;
	}
	

	public DoubleLinkedListNode<T> getPrev()
	{
		return prev;
	}

	public void setPrev(DoubleLinkedListNode<T> prev)
	{
		this.prev = prev;
	}
	
	public T getValue()
	{
		return this.value;
	}
	
	@Override
	public int compareTo(DoubleLinkedListNode<T> o)
	{
		return this.value.compareTo(o.getValue());
	}
	
	@Override
	public String toString()
	{
		return String.valueOf(this.value);
	}
}