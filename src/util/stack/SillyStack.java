package util.stack;

import java.io.Serial;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import util.ArrayUtils;
import util.linkedlist.DoubleLinkedList;
import util.linkedlist.DoubleLinkedListNode;
import util.linkedlist.LinkedList;
import util.linkedlist.LinkedListNode;

/**
 *	The {@code SillyStack} class represents a last-in-first-out
 * (LIFO) stack of objects, implementing {@link Deque} to make it a complete
 * stack.
 * <p>
 * @author Abraham Yelifari
 * @version 1.0
 * @since 2025-09-18 2:01
 */
public class SillyStack<E extends Comparable<E>> implements Deque<E>, Serializable
{
	@Serial
	private static final long serialVersionUID = 2148511408716525724L;
	
	transient Object[] elementData;
	private boolean isSizeRestrict = false;
	private int initSize = 0;
	private int maxSize = 10;
	
	/**
	 * Creates a empty stack.
	 */
	public SillyStack()
	{
		this(10);
	}
	
	/**
	 * Creates a stack with the provided array.
	 * @param arr the array to be converted into a {@code SillyStack}.
	 * 
	 */
	public SillyStack(E[] arr)
	{
		elementData = (Object[])arr;
		initSize = arr.length;
		maxSize = 10;
	}
	
	/**
	 * Creates a empty stack, with a size.
	 * @param size the size of the stack.
	 * 
	 */
	public SillyStack(int size)
	{
		elementData = new Object[size];
		initSize = size;
		maxSize = size;
	}
	
	/**
	 * Creates a stack with the provided array, with a boolean to restrict the size.
	 * This prevents elements getting added to anywhere in the stack once it is full.
 T	 * @param 	arr  The arr to be converted into the {@code SillyStack}.
	 * @param 	b    Whether or not the size will be restricted. This is mean't to be used
	 * 			  	 to restrict the size.
	 * 
	 */
	public SillyStack(E[] arr, boolean lockSize)
	{
		elementData = (Object[])arr;
		isSizeRestrict = lockSize;
		initSize = arr.length;
		maxSize = 10;
	}
	
	/**
	 * Creates a empty stack with a size and a boolean to restrict the size.
	 * This prevents elements getting added to anywhere in the stack once it is full.
 T	 * @param 	size  The size for the stack.
	 * @param 	b     Whether or not the size will be restricted. This is mean't to be used
	 * 			  	  to restrict the size.
	 * 
	 */
	public SillyStack(int size, boolean b)
	{
		elementData = new Object[size];
		isSizeRestrict = b;
		initSize = size;
		maxSize = size;
	}
	
	/**
	 * Creates a empty stack with a boolean to restrict the size.
	 * This prevents elements getting added to anywhere in the stack once it is full.
	 * The default size is 10.
 T	 * @param 	b     Whether or not the size will be restricted. This is mean't to be used
	 * 			  	  to restrict the size.
	 * 
	 */
	public SillyStack(boolean b)
	{
		this(10, b);
	}
	
	
	/**
	 * To determine if the {@code SillyStack} is size restricted.
	 * @return {@code true} if it is restricted and {@code false} if not.
	 */
	public boolean isSizeRestricted()
	{
		return isSizeRestrict;
	}
	
	/**
	 * Determines if the stack is full. Will only return false if there are not restrictions.
	 * @return {@code true} if it is full and {@code false} if not.
	 */
	public boolean isFull()
	{
		return isSizeRestrict && size() == elementData.length;
	}
	
	/**
	 * Converts the {@code SillyStack} into {@link List} of the provided generic {@code E}.
	 * @return A new list created from the stack.
	 */
	public List<E> toList()
	{
		return (List<E>) Arrays.asList(this.toArray());
	}
	
	/**
	 * Converts the {@code SillyStack} into {@link LinkedList} of the provided generic {@code E}.
	 * @return The head of the linked list created from the stack.
	 */
	public LinkedList<E> toLinkedList()
	{
		if (isEmpty())
			return null;
		
		int size = this.size();
		LinkedListNode<E> root = new LinkedListNode<E>((E) elementData[0]);
		LinkedListNode<E> current = root;
		LinkedList<E> ll = new LinkedList<E>(root);
		
		for (int i = 1; i < size; i++)
		{
			LinkedListNode<E> node = new LinkedListNode<E>((E) elementData[i]);
			current.setNext(node);
			current = node;
		}
		
		return ll;
	}
	
	/**
	 * Converts the {@code SillyStack} into {@link DoubleLinkedList} of the provided generic {@code E}.
	 * @return The head of the double linked list created from the stack.
	 */
	public DoubleLinkedList<E> toDoubleLinkedList()
	{
		if (isEmpty())
			return null;
		
		int size = this.size();
		DoubleLinkedListNode<E> root = new DoubleLinkedListNode<E>((E) elementData[0]);
		DoubleLinkedListNode<E> current = root;
		DoubleLinkedListNode<E> prev = null;
		DoubleLinkedList<E> dll = new DoubleLinkedList<E>(root);
		
		for (int i = 1; i < size; i++)
		{
			DoubleLinkedListNode<E> node = new DoubleLinkedListNode<E>((E) elementData[i]);
			current.setNext(node);
			
			if (prev != null)
				current.setPrev(prev);
			prev = current;
			current = node;
		}
		
		return dll;
	}
	
	/**
	 * Returns the maximum size of the stack, if it is size restricted. If it is not restricted,
	 * it will return -1.
	 * @return
	 */
	public int getMaxSize()
	{
		return this.isSizeRestrict ? this.maxSize : -1;
	}
	
	
	/**
	 * Determines if the stack is empty.
	 * @return {@code true} if the stack is empty and {@code false} if it is empty.
	 */
	@Override
	public boolean isEmpty()
	{
		return this.size() == 0;
	}

	/**
	 * Converts the {@code SillyStack} into {@link Array} of type {@link Object}
	 * @return The array created from the stack.
	 */
	@Override
	public Object[] toArray()
	{
		int s = size();
		Object[] arr = new Object[s];
		
		for (int i = 0; i < s; i++)
		{
			arr[i] = elementData[i];
		}
		
		return arr;
	}

	/**
	 * Converts the {@code SillyStack} into {@link Array} of the provided generic, {@code T}
	 * @param  The generic array that the stack will convert to.
	 * @return The array created from the stack.
	 */
	@Override
	public <T> T[] toArray(T[] a)
	{
		int s = size();
		T[] arr = (T[])Array.newInstance(a.getClass().getComponentType(), s);
		
		for (int i = 0; i < s; i++)
		{
			arr[i] = (T)elementData[i];
		}
		
		return arr;
	}

	/**
	 * Determines if the provided collection is contained in the stack, in its entirety.
	 * @param  The collection to be compared to.
	 * @return {@code true} if it was successful in determining if the collection was present
	 * 						in the stack and {@code false} if it was not.
	 */
	@Override
	public boolean containsAll(Collection<?> c)
	{
		List<Object> elementDataList = Arrays.asList(elementData);
		return elementDataList.containsAll(c);
	}

	/**
	 * Removes all instances that appear in the stack.
	 * @param  The collection to be compared to.
	 * @return {@code true} if it was successful in determining if the collection was present
	 * 						and removed from the stack and {@code false} if it was not.
	 */
	@Override
	public boolean removeAll(Collection<?> c)
	{
		List<Object> elementDataList = Arrays.asList(elementData);
		return elementDataList.removeAll(c);
	}

	/**
     * Replaces each element of this list with the result of applying the
     * operator to that element. Errors or runtime exceptions thrown by
     * the operator are relayed to the caller.
     * @param  The collection to be compared to.
	 * @return {@code true} if it was successful in retaining the collection
	 *		   and {@code false} if it was not.
     */
	@Override
	public boolean retainAll(Collection<?> c)
	{
		List<Object> elementDataList = Arrays.asList(elementData);
		return elementDataList.retainAll(c);
	}

	/**
	 * Clears the entire stack, whilst keeping the size from before it was cleared.
	 */
	@Override
	public void clear()
	{
		int s = size();
		elementData = new Object[s];
	}
	
	/**
	 * Adds a new element to the end of the stack.
	 * @throws IllegalStateException if there are size restrictions.
	 */
	@Override
	public void addLast(E e)
	{
		int size = this.size();
		if (size < elementData.length)
		{
			elementData[size] = e;
		}
		else
		{
			if (isSizeRestrict)
				throw new IllegalStateException("Can't add more elements because the stack's size is restricted.");
			elementData = Arrays.copyOf(elementData, size + 1);
			elementData[size] = e;
		}
	}

	/**
	 * Adds a new element to the beginning of the stack.
	 * @throws IllegalStateException if there are size restrictions.
	 */
	@Override
	public void addFirst(E e)
	{
		int size = this.size();
		if (size < elementData.length)
		{
			ArrayUtils.shiftRight(elementData, 1);
			elementData[0] = e;
		}
		else
		{
			if (isSizeRestrict)
				throw new IllegalStateException("Can't add more elements because the stack's size is restricted.");
			elementData = Arrays.copyOf(elementData, size + 1);
			ArrayUtils.shiftRight(elementData, 1);
			elementData[0] = e;
		}
	}

	/**
	 * Adds a new element to the beginning of the stack.
	 */
	@Override
	public boolean offerFirst(E e)
	{
		if (isSizeRestrict)
			return false;
		
		int size = this.size();
		if (size < elementData.length)
		{
			ArrayUtils.shiftRight(elementData, 1);
			elementData[0] = e;
		}
		else
		{
			elementData = Arrays.copyOf(elementData, size + 1);
			ArrayUtils.shiftRight(elementData, 1);
			elementData[0] = e;
		}
		
		return true;
	}

	/**
	 * Adds a new element to the end of the stack.
	 */
	@Override
	public boolean offerLast(E e)
	{
		if (isSizeRestrict)
			return false;
		
		int size = this.size();
		if (size < elementData.length)
		{
			elementData[size] = e;
		}
		else
		{
			elementData = Arrays.copyOf(elementData, size + 1);
			elementData[size] = e;
		}
		
		return true;
	}

	/**
	 * Removes the first element of the stack.
	 * @return the element from the stack.
	 * @throws NoSuchElementException if the stack is empty.
	 */
	@Override
	public E removeFirst()
	{
		int size = this.size();
		if (size == 0)
			throw new NoSuchElementException("Can't remove element because there are no more.");
		Object meep = elementData[0];
		elementData[0] = null;
		ArrayUtils.shiftLeft(elementData, 1);
		return (E) meep;
	}

	/**
	 * Removes the last element of the stack.
	 * @return the element from the stack.
	 * @throws NoSuchElementException if the stack is empty.
	 */
	@Override
	public E removeLast()
	{
		int size = this.size();
		if (size == 0)
			throw new NoSuchElementException("Can't remove element because there are no more.");
		Object meep = elementData[size - 1];
		elementData[size - 1] = null;
		return (E) meep;
	}

	/**
	 * Removes the first element of the stack.
	 * @return the element from the stack.
	 */
	@Override
	public E pollFirst()
	{
		return elementData.length == 0 ? null : removeFirst();
	}

	/**
	 * Removes the first element of the stack.
	 * @return the element from the stack.
	 */
	@Override
	public E pollLast()
	{
		return elementData.length == 0 ? null : removeLast();
	}

	/**
	 * Retrieves the first element of the stack.
	 * @return the element from the stack.
	 * @throws NoSuchElementException if the stack is empty.
	 */
	@Override
	public E getFirst()
	{
		int size = this.size();
		if (size == 0)
			throw new NoSuchElementException("Can't remove element because there are no more.");
		return (E) elementData[0];
	}

	/**
	 * Retrieves the last element of the stack.
	 * @return the element from the stack.
	 * @throws NoSuchElementException if the stack is empty.
	 */
	@Override
	public E getLast()
	{
		int size = this.size();
		if (size == 0)
			throw new NoSuchElementException("Can't remove element because there are no more.");
		return (E) elementData[size - 1];
	}

	/**
	 * Retrieves the first element of the stack.
	 * @return the element from the stack or null.
	 */
	@Override
	public E peekFirst()
	{
		return elementData.length == 0 ? null : getFirst();
	}

	/**
	 * Retrieves the first element of the stack.
	 * @return the element from the stack or null.
	 */
	@Override
	public E peekLast()
	{
		return elementData.length == 0 ? null : getLast();
	}

	/**
	 * Removes the first occurrence element of the stack.
	 * @param o the object to be removed if present.
	 * @return {@code true} if the element was found and removed, false, if it was not found.
	 */
	@Override
	public boolean removeFirstOccurrence(Object o)
	{
		return remove(o);
	}

	/**
	 * Removes the last occurrence element of the stack.
	 * @return {@code true} if the element was found and removed, false, if it was not found.
	 * @throws NoSuchElementException if the stack is empty.
	 */
	@Override
	public boolean removeLastOccurrence(Object o)
	{
		for (int i = elementData.length - 1; i > 0; i--)
		{
			if (elementData[i].equals(o))
			{
				elementData[i] = null;
				ArrayUtils.ramLeft(elementData);
				return true;
			}
		}
		
		return false;
	}

	/**
	 * Adds the element to the end of the stack.
	 * @param e the element to be added.
	 * @return {@code true} if the element was added and false if it could not be added. Will essentially
	 * 		   always return true and will throw an exception when it failed.
	 * @throws IllegalStateException if the stack is full.
	 */
	@Override
	public boolean add(E e)
	{
		if (isSizeRestrict && isFull())
			throw new IllegalStateException("Can't add more elements because the stack's size is restricted.");
		
		addLast(e);
		
		return true;
	}

	/**
	 * Adds the element to the end of the stack.
	 * @param e the element to be added.
	 * @return {@code true} if the element was added and false if it could not be added.
	 */
	@Override
	public boolean offer(E e)
	{
		if (isSizeRestrict && isFull())
			return false;
		
		addLast(e);
		
		return true;
	}

	/**
	 * Removes the end of the stack.
	 * @return the element removed.
	 * @throws NoSuchElementException if the stack is empty.
	 */
	@Override
	public E remove()
	{
		int size = this.size();
		if (size == 0)
			throw new NoSuchElementException("Can't remove element because there are no more.");
		Object meep = elementData[size - 1];
		elementData[size - 1] = null;
		return (E) meep;
	}

	/**
	 * Removes the end of the stack.
	 * @return the element removed.
	 */
	@Override
	public E poll()
	{
		int size = this.size();
		if (size == 0)
			return null;
		
		Object meep = elementData[size - 1];
		elementData[size - 1] = null;
		return (E) meep;
	}

	/**
	 * Retrieves the element at the end of the stack. Will not remove the element from the stack if found.
	 * @return the element found.
	 * @throws NoSuchElementException if the stack is empty.
	 */
	@Override
	public E element()
	{
		int size = this.size();
		if (size == 0)
			throw new NoSuchElementException("Can't retrive element because there are no more elements.");
		
		Object meep = elementData[size - 1];
		return (E) meep;
	}

	/**
	 * Retrieves the element at the end of the stack. Will not remove the element from the stack if found.
	 * @return the element found or null if none were found.
	 */
	@Override
	public E peek()
	{
		int size = this.size();
		if (size == 0)
			return null;
		
		Object meep = elementData[size - 1];
		return (E) meep;
	}

	/**
	 * Adds all of the elements from the collection into the stack at the end.
	 * @param c the collection to be added to the end of the stack.
	 * @return {@code true} if it was successful in adding all of the values in the collection,
	 * 		   {@code false} if it could not add all of them.
	 */
	@Override
	public boolean addAll(Collection<? extends E> c)
	{
		int collSize = c.size();
		int size = this.size();
		
		if (isSizeRestrict)
			if (size + collSize > initSize)
				return false;
		
		Object[] arrToAdd = c.toArray();
		elementData = Arrays.copyOf(elementData, size + collSize);
		
		for(int i = size - 1; i < collSize + size; i++)
			elementData[i] = arrToAdd[i];
		
		return true;
	}
	
	/**
	 * Adds the first element of the stack.
	 * @throws IllegalStateException if the stack is full.
	 */
	@Override
	public void push(E e)
	{
		if (isSizeRestrict && isFull())
			throw new IllegalStateException("Can't add more elements because the stack's size is restricted.");
		
		addFirst(e);
	}

	/**
	 * Removes the first element of the stack.
	 * @return the element that is at the beginning.
	 * @throws IllegalStateException if the stack is full.
	 */
	@Override
	public E pop()
	{
		if (isSizeRestrict && isFull())
			throw new IllegalStateException("Can't add more elements because the stack's size is restricted.");
		
		return removeFirst();
	}

	/**
	 * Removes the first occurrence element of the stack.
	 * @param o the object to be removed if present.
	 * @return {@code true} if the element was found and removed, false, if it was not found.
	 */
	@Override
	public boolean remove(Object o)
	{
		int size = this.size();
		for (int i = 0; i < size; i++)
		{
			if (elementData[i].equals(o))
			{
				elementData[i] = null;
				ArrayUtils.ramLeft(elementData);
				return true;
			}
		}
		
		return false;
	}

	/**
	 * Used to determine if the stack contains the passed object.
	 * @param o the object in question.
	 */
	@Override
	public boolean contains(Object o)
	{
		return Arrays.asList(elementData).contains(o);
	}

	/**
	 * Used to find the size of the stack.
	 * @return the size of the stack.
	 */
	@Override
	public int size()
	{
		return getValidEntrySize();
	}

	/**
	 * Converts the stack into an iterator.
	 * @return the stack as an iterator.
	 */
	@Override
	public Iterator<E> iterator()
	{
		return (Iterator<E>) Arrays.asList(elementData).iterator();
	}

	/**
	 * Converts the stack into an iterator that is reversed.
	 * @return the stack as an iterator that was reversed.
	 */
	@Override
	public Iterator<E> descendingIterator()
	{
		return (Iterator<E>) Arrays.asList(elementData).reversed().iterator();
	}
	
	@Override
	public String toString()
	{
		return Arrays.toString(this.toArray());
	}
	
	
	private int getValidEntrySize()
	{
		int cur = 0;
		for (int i = 0; i < elementData.length; i++)
		{
			if (elementData[i] != null)
				cur++;
		}
		return cur;
	}
}