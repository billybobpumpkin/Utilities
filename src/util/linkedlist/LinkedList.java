package util.linkedlist;

import java.io.Serial;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LinkedList<T extends Comparable<T>> implements Serializable
{
	@Serial
	private static final long serialVersionUID = 6572230345233399441L;
	
	private LinkedListNode<T> rootNode;
	private int size;
	
	public LinkedList(LinkedListNode<T> node)
	{
		this.rootNode = node;
	}
	
	public void add(LinkedListNode<T> node)
	{
		LinkedListNode<T> current = this.rootNode;
		
		while (current != null)
		{
			if (current.getNext() == null)
			{
				current.setNext(node);
				return;
			}
			this.size++;
			current = current.getNext();
		}
	}
	
	public void remove(LinkedListNode<T> node)
	{
		LinkedListNode<T> current = this.rootNode;
		LinkedListNode<T> prev = null;
		
		while (current != null)
		{
			if (current == node && prev != null)
			{
				prev.setNext(current.getNext());
				return;
			}
			prev = current;
			current = current.getNext();
		}
	}
	
	public void remove(int index)
	{
		LinkedListNode<T> current = this.rootNode;
		LinkedListNode<T> prev = null;
		int cur = 0;;
		
		while (current != null)
		{
			if (cur == index)
			{
				prev.setNext(current.getNext());
				return;
			}
			cur++;
			prev = current;
			current = current.getNext();
		}
	}
	
	public List<T> toList()
	{
		List<T> arrayList = new ArrayList<T>();
		LinkedListNode<T> current = this.rootNode;
		
		while(current != null)
		{
			arrayList.add(current.getValue());
			current = current.getNext();
		}
		
		return arrayList;
	}
	
	public T[] toArray(Class<T> clazz)
	{
		List<T> arrlist = this.toList();
		T[] arr = (T[])Array.newInstance(clazz, arrlist.size());
		return arrlist.toArray(arr);
	}
	
	public int size()
	{
		return this.size;
	}
	
	public Iterator<T> iterator()
	{
		return this.toList().iterator();
	}
	
	public Iterator<T> descendingIterator()
	{
		return this.toList().reversed().iterator();
	}
}