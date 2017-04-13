package textgen;

import java.util.AbstractList;

import jdk.nashorn.internal.runtime.Context.ThrowErrorManager;


/** A class that implements a doubly linked list
 *
 * @author UC San Diego Intermediate Programming MOOC team
 *
 * @param <E> The type of the elements stored in the list
 */
public class MyLinkedList<E> extends AbstractList<E> {
	LLNode<E> head;
	LLNode<E> tail;
	int size;
	LLNode<E> toBeSet;

	/** Create a new empty LinkedList */
	public MyLinkedList() {
		// TODO: Implement this method
		size = 0;
		head = new LLNode<E>();
		tail = new LLNode<E>();
		head.next = tail;
		tail.prev = head;
	}

	/**
	 * Appends an element to the end of the list
	 * @param element The element to add
	 */
	public boolean add(E element)
	{
		// TODO: Implement this method
		NullPointerException ne = new NullPointerException();

		if (element == null)
		{
			throw ne;
		}

		try
		{
			LLNode<E> item = new LLNode<E> (element);
			item.next = tail;
			item.prev = tail.prev;
			tail.prev.next = item;
			tail.prev = item;
			size++;
			return false;
		}
		catch (NullPointerException n)
		{

		}

		return false;
	}

	/** Get the element at position index
	 * @throws IndexOutOfBoundsException if the index is out of bounds. */
	public E get(int index)
	{
		// TODO: Implement this method.

		IndexOutOfBoundsException e = new IndexOutOfBoundsException();
		if (size < 1 || index < 0 || index > size - 1)
		{
			throw e;
		}

		try
		{
			LLNode<E> item = head;
			for (int i = 0; i <= index; i++)
			{
				item = item.next;
			}
			toBeSet = item;
			return item.data;
		}
		catch(IndexOutOfBoundsException p)
		{

		}
		return null;
	}

	/**
	 * Add an element to the list at the specified index
	 * @param The index where the element should be added
	 * @param element The element to add
	 */
	public void add(int index, E element )
	{
		// TODO: Implement this method
		IndexOutOfBoundsException ie = new IndexOutOfBoundsException();
		NullPointerException ne = new NullPointerException();

		if (index < 0 || index > size)
		{
			throw ie;
		}

		if (element == null)
		{
			throw ne;
		}

		try
		{
			LLNode<E> item = head;
			int i = 0;
			while(i <= index)
			{
				item = item.next;
				i++;
			}
			new LLNode<E>(element, item.prev);
			size++;
		}

		catch (IndexOutOfBoundsException e)
		{

		}

		catch (NullPointerException e)
		{

		}

	}


	/** Return the size of the list */
	public int size()
	{
		// TODO: Implement this method
		return size;
	}

	/** Remove a node at the specified index and return its data element.
	 * @param index The index of the element to remove
	 * @return The data element removed
	 * @throws IndexOutOfBoundsException If index is outside the bounds of the list
	 *
	 */
	public E remove(int index)
	{
		// TODO: Implement this method
		IndexOutOfBoundsException ie = new IndexOutOfBoundsException();

		if (index < 0 || index > size - 1)
		{
			throw ie;
		}

		try
		{
			LLNode<E> item = head;
			for (int i = 0; i <= index; i++)
			{
				item = item.next;
			}

			item.prev.next = item.next;
			item.next.prev = item.prev;
			size--;
			return item.data;

		}
		catch(IndexOutOfBoundsException p)
		{

		}
		return null;
	}

	/**
	 * Set an index position in the list to a new element
	 * @param index The index of the element to change
	 * @param element The new element
	 * @return The element that was replaced
	 * @throws IndexOutOfBoundsException if the index is out of bounds.
	 */
	public E set(int index, E element)
	{
		// TODO: Implement this method
		IndexOutOfBoundsException ie = new IndexOutOfBoundsException();
		NullPointerException ne = new NullPointerException();
		if (element == null)
		{
			throw ne;
		}

		if (index < 0 || index > size - 1)
		{
			throw ie;
		}


		try
		{
			Object obj = new LLNode().data;
			obj = get(index);
			toBeSet.data = element;
			return (E) obj;
		}

		catch (NullPointerException n)
		{

		}

		catch(IndexOutOfBoundsException e)
		{

		}


		return null;
	}
}

class LLNode<E>
{
	LLNode<E> prev;
	LLNode<E> next;
	E data;

	// TODO: Add any other methods you think are useful here
	// E.g. you might want to add another constructor

	public LLNode()
	{
		this.data = null;
		this.prev = null;
		this.next = null;
	}

	public LLNode(E data)
	{
		this.data = data;
	}

	public LLNode(E data, LLNode<E> prev)
	{
		this(data);
		this.next = prev.next;
		this.prev = prev;
		prev.next.prev = this;
		prev.next = this;
	}
}
