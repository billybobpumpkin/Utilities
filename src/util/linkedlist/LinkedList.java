package util.linkedlist;

import java.io.Serial;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * A class that is useful for manipulating {@link LinkedListNode}s to convert them into other
 * data structures and simplistic adding and removing of nodes.
 * @param <T> an object that can me compared.
 * <p>
 * @author Abraham Yelifari
 * @version 1.0
 * @since 2025-9-25 12:08
 */
public class LinkedList<T extends Comparable<T>> implements Serializable
{
	@Serial
	private static final long serialVersionUID = 6572230345233399441L;
	
	private LinkedListNode<T> rootNode;
	private int size;
	
	/**
	 * Initializes the list with a head node.
	 * @param node the head.
	 */
	public LinkedList(LinkedListNode<T> node)
	{
		this.rootNode = node;
	}
	
	/**
	 * Adds the node to the end of the list, by making the tail node point to the new node added.
	 * The new node added then becomes the tail.
	 * @param node the node to be added.
	 */
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
	
	/**
	 * Removes the passed node from the list, updating the appropriate next pointers.
	 * @param node the node to be removed
	 */
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
	
	/**
	 * Removes the passed node from the list, updating the appropriate next pointers.
	 * @param index the index in which the node to be removed lies at.
	 */
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
	public LinkedListNode<T> getReverse()
	{	
		List<T> list = this.toList().reversed();
		LinkedListNode<T> newHead = new LinkedListNode<T>(list.get(0));
		LinkedListNode<T> node = newHead;
		
		for (int i = 1; i < list.size(); i++)
		{
			node.setNext(new LinkedListNode<T>(list.get(i)));
			node = node.getNext();
		}
		
		return newHead;
	}
	
	/**
	 * Converts the {@link LinkedList} into a {@link List} of type <T>.
	 * @return the list created.
	 */
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