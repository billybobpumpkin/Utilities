package util.linkedlist;

import java.io.Serial;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DoubleLinkedList<T extends Comparable<T>> implements Serializable
{
	@Serial
	private static final long serialVersionUID = 6572230345233399441L;
	
	private DoubleLinkedListNode<T> rootNode;
	private int size;
	
	public DoubleLinkedList(DoubleLinkedListNode<T> node)
	{
		this.rootNode = node;
	}
	
	public void add(DoubleLinkedListNode<T> node)
	{
		DoubleLinkedListNode<T> current = this.rootNode;
		
		while (current != null)
		{
			if (current.getNext() == null)
			{
				current.setNext(node);
				node.setPrev(current);
				return;
			}
			this.size++;
			current = current.getNext();
		}
	}
	
	public void remove(DoubleLinkedListNode<T> node)
	{
		DoubleLinkedListNode<T> current = this.rootNode;
		
		while (current != null)
		{
			if (current == node)
			{
				DoubleLinkedListNode<T> after = current.getNext();
				DoubleLinkedListNode<T> prev = current.getPrev();
				
				prev.setNext(after);
				
				return;
			}
			current = current.getNext();
		}
	}
	
	public void remove(int index)
	{
		DoubleLinkedListNode<T> current = this.rootNode;
		int cur = 0;;
		
		while (current != null)
		{
			if (cur == index)
			{
				DoubleLinkedListNode<T> after = current.getNext();
				DoubleLinkedListNode<T> prev = current.getPrev();
				
				prev.setNext(after);
				
				return;
			}
			cur++;
			current = current.getNext();
		}
	}
	
	public void reverse()
	{
		this.rootNode = this.getReverse();
	}
	
	public DoubleLinkedListNode<T> getReverse()
	{
		DoubleLinkedListNode<T> current = this.rootNode;
		DoubleLinkedListNode<T> current2 = null;
		DoubleLinkedListNode<T> newRoot = null; 
		
		while (current.getNext() != null)
			current = current.getNext();
		
		newRoot = current;
		current2 = current;
		while (current.getPrev() != null)
		{
			current2.setNext(current);
			current2 = current2.getNext();
			current = current.getPrev();
		}
		
		return newRoot;
	}
	
	public List<T> toList()
	{
		List<T> arrayList = new ArrayList<T>();
		DoubleLinkedListNode<T> current = this.rootNode;
		
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