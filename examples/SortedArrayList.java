// CMSC 350 Data Structures and Analysis
// Week 4 Examples
// Dr. Duane J. Jarc
// March 1, 2020

// This class defines a sorted list implemented with a sorted array inherited
// from the SortedArrayCollection class.

package list;

import collection.*;
import java.util.*;

public class SortedArrayList<T extends Comparable<T>> extends
	SortedArrayCollection<T> implements SortedListInterface<T>
{
	public class ListIterator implements Iterator<T>
	{
		private int current = 0;

		@Override
		public boolean hasNext()
		{
			return current < size;
		}

		@Override
		public T next()
		{
			return array[current++];
		}

		@Override
		public void remove()
		{
		}
	}

	@Override
	public boolean add(T element, int index)
	{
		throw new UnsupportedOperationException("Not permitted");
	}

	@Override
	public T set(int index, T element)
	{
		throw new UnsupportedOperationException("Not permitted");
	}

	@Override
	public T get(int index)
	{
		if (index < 0 || index > size)
			return null;
		return array[index];
	}

	@Override
	public int indexOf(T element)
	{
		return find(element);
	}

	@Override
	public T remove(int index)
	{
		if (index < 0 || index >= size)
			return null;
		T item = array[index];
		for (int i = index + 1; i < size; i++)
			array[i - 1] = array[i];
		size--;
		return item;
	}

	@Override
	public int size()
	{
		return size;
	}

	@Override
	public Iterator<T> iterator()
	{
		 return new ListIterator();
	}
}
