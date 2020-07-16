// CMSC 350 Data Structures and Analysis
// Week 4 Examples
// Dr. Duane J. Jarc
// March 1, 2020

// This class defines a sorted list implemented with a sorted singly linked
// list.

package list;

import java.util.*;

public class SortedLinkedList<T extends Comparable<T>> implements
	SortedListInterface<T>
{
	Comparator<T> compare;
	
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

	public SortedLinkedList()
	{
		compare = (T left, T right) -> left.compareTo(right);
	}
	
	public SortedLinkedList(Comparator<T> compare)
	{
		this.compare = compare;
	}
	
	@Override
	public boolean add(T element)
	{
		head = add(head, element);
		size++;
		return true;
	}

	private Node<T> add(Node<T> node, T element)
	{
		if (node == null || compare.compare(element, node.element) < 0)
		{
			Node<T> newNode = new Node();
			newNode.element = element;
			newNode.next = node;
			return newNode;
		}
		else
		{
			node.next = add(node.next, element);
			return node;
		}
	}

	@Override
	public boolean add(T element, int index)
	{
		throw new UnsupportedOperationException("Not permitted");
	}

	@Override
	public boolean remove(T element)
	{
		size--;
		return (head = remove(head, element)) != null;
	}

	private Node<T> remove(Node<T> node, T element)
	{
		if (node == null || compare.compare(element, node.element) < 0)
			return null;
		else if (compare.compare(element, node.element) == 0)
			return node.next;
		else
		{
			node.next = remove(node.next, element);
			return node;
		}
	}

	@Override
	public T remove(int index)
	{
		if (index >= size || head == null)
			return null;
		T element;
		if (index == 0)
		{	
			element = head.element;
			head = head.next;
		}
		else
		{
			Node<T> node = remove(head, 0, index);
			element = node.next.element;
			node.next = node.next.next;
		}
		size--;
		return element;
	}

	private Node<T> remove(Node<T> node, int current, int index)
	{
		if (current == index - 1)
			return node;
		else
			return remove(node.next, current + 1, index);
	}
	
	@Override
	public T set(int index, T element)
	{
		throw new UnsupportedOperationException("Not permitted");
	}
	
	@Override
	public T get(int index)
	{
		return get(head, 0, index);
	}

	private T get(Node<T> node, int current, int index)
	{
		if (node == null)
			return null;
		if (index == current)
			return node.element;
		return get(node.next, current + 1, index);
	}

	@Override
	public int indexOf(T element)
	{
		return indexOf(head, 0, element);
	}

	@Override
	public boolean contains(T element)
	{
		return indexOf(head, 0, element) != -1;
	}

	private int indexOf(Node<T> node, int index, T element)
	{
		if (node == null || compare.compare(element, node.element) < 0)
			return -1;
		if (compare.compare(element, node.element) == 0)
			return index;
		return indexOf(node.next, index + 1, element);
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
}