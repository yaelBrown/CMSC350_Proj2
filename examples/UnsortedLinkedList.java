// CMSC 350 Data Structures and Analysis
// Week 4 Examples
// Dr. Duane J. Jarc
// March 1, 2020

// This class defines a unsorted list implemented with a unsorted singly linked
// list.

package list;

import java.util.*;

public class UnsortedLinkedList<T> implements ListInterface<T>
{
	private static class Node<T>
	{
		private T element;
		private Node<T> next;
	}

	public class ListIterator implements Iterator<T>
	{
		private Node<T> current;

		@Override
		public boolean hasNext()
		{
			if (current == null)
				return head != null;
			else
				return current.next != null;
		}

		@Override
		public T next()
		{
			if (current == null)
				current = head;
			else
				current = current.next;
			return current.element;
		}

		@Override
		public void remove()
		{
		}
	}
	
	private Node<T> head, tail;
	private int size = 0;

	@Override
	public boolean add(T element)
	{
		Node<T> newNode = new Node();

		newNode.element = element;
		if (tail != null)
			tail.next = newNode;
		else
			head = newNode;
		tail = newNode;
		size++;
		return true;
	}

	@Override
	public boolean add(T element, int index)
	{
		if (index < 0 || index > size)
			return false;
		Node newNode = new Node();
		newNode.element = element;
		if (index == 0)
		{
			newNode.next = head;
			head = newNode;
		}
		else
		{
			Node previous = findElement(index - 1);
			newNode.next = previous.next;
			previous.next = newNode;
		}
		size++;
		return true;
	}

	@Override
	public boolean remove(T element)
	{
		int location = indexOf(element);
		if (location < 0)
			return false;
		return remove(location) != null;
	}
	
	@Override
	public T remove(int index)
	{
		T element;

		if (index < 0 || index >= size)
			return null;
		if (index == 0)
		{
			element = head.element;
			head = head.next;
		}
		else
		{
			Node<T> previous = findElement(index - 1);
			element = previous.next.element;
			previous.next = previous.next.next;
		}
		size--;
		return element;
	}

	@Override
	public T set(int index, T element)
	{
		Node<T> node = findElement(index);
		if (node == null)
			return null;
		node.element = element;
		return node.element;
	}
	
	@Override
	public T get(int index)
	{
		Node<T> node = findElement(index);
		if (node == null)
			return null;
		return node.element;
	}

	@Override
	public int indexOf(T element)
	{
		int index = 0;
		Node<T> node = head;
		while (head != null)
		{
			if (element.equals(node.element))
				return index;
			index++;
		}	
		return -1;
	}

	@Override
	public boolean contains(T element)
	{
		return indexOf(element) != -1;
	}

	@Override
	public Iterator<T> iterator()
	{
		return new ListIterator();
	}

	@Override
	public int size()
	{
		return size;
	}
	
	private Node findElement(int index)
	{
		int count = 0;
		Node link = head;
		while (link.next != null)
		{
			if (count == index)
				return link;
			count++;
			link = link.next;
		}
		return link;
	}
}