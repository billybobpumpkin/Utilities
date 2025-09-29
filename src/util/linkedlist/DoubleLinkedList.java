package util.linkedlist;

import java.io.Serial;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * A class that is useful for manipulating {@link DoubleLinkedListNode}s to convert them into other
 * data structures and simplistic adding and removing of nodes.
 * @param <T> an object that can me compared.
 * <p>
 * @author Abraham Yelifari
 * @version 1.0
 * @since 2025-9-25 12:08
 */
public class DoubleLinkedList<T extends Comparable<T>> implements Serializable
{
	@Serial
	private static final long serialVersionUID = 6572230345233399441L;
	
	private DoubleLinkedListNode<T> rootNode;
	private int size;
	
	/**
	 * Initializes the list with a head node.
	 * @param node the head.
	 */
	public DoubleLinkedList(DoubleLinkedListNode<T> node)
	{
		this.rootNode = node;
	}
	
	/**
	 * Adds the node to the end of the list, by making the tail node point to the new node added.
	 * The new node added then becomes the tail.
	 * @param node the node to be added.
	 */
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
	
	/**
	 * Removes the passed node from the list, updating the appropriate previous and next pointers.
	 * @param node the node to be removed
	 */
	public void remove(DoubleLinkedListNode<T> node)
	{
		DoubleLinkedListNode<T> current = this.rootNode;
		
		while (current != null)
		{
			if (current.equals(node))
			{
				DoubleLinkedListNode<T> after = current.getNext();
				DoubleLinkedListNode<T> prev = current.getPrev();
				
				prev.setNext(after);
				
				return;
			}
			current = current.getNext();
		}
	}
	
	/**
	 * Removes the passed node from the list, updating the appropriate previous and next pointers.
	 * @param index the index in which the node to be removed lies at.
	 */
	public void remove(int index)
	{
		DoubleLinkedListNode<T> current = this.rootNode;
		int cur = 0;
		
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
	
	/**
	 * Reverses the list.
	 */
	public void reverse()
	{
		this.rootNode = this.getReverse();
	}
	
	/**
	 * Reverses the list.
	 * @return the new head node.
	 */
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
	
	/**
	 * Converts the {@link DoubleLinkedList} into a {@link List} of type <T>.
	 * @return the list created.
	 */
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
	
	/**
	 * Converts the list into an array of <T>. It needs the class object so the compiler knows what to convert
	 * the generic to.
	 * @param clazz the class object of <T>.
	 * @return a new array of <T>.
	 */
	public T[] toArray(Class<T> clazz)
	{
		List<T> arrlist = this.toList();
		T[] arr = (T[])Array.newInstance(clazz, arrlist.size());
		return arrlist.toArray(arr);
	}
	
	/**
	 * Gets the size of the list.
	 * @return the size.
	 */
	public int size()
	{
		return this.size;
	}
	
	/**
	 * Creates an iterator for the linked list by converting it into a list and getting the iterator from that.
	 * @return the iterator of the list.
	 */
	public Iterator<T> iterator()
	{
		return this.toList().iterator();
	}
	
	/**
	 * Creates a reversed iterator for the linked list by converting it into a list and getting the iterator from that.
	 * @return the reversed iterator of the list.
	 */
	public Iterator<T> descendingIterator()
	{
		return this.toList().reversed().iterator();
	}
}