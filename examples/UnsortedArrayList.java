// CMSC 350 Data Structures and Analysis
// Week 4 Examples
// Dr. Duane J. Jarc
// March 1, 2020

// This class defines an unsorted list implemented with a unsorted array 
// inherited from the UnsortedArrayCollection class.

package list;

import collection.*;
import java.util.Iterator;

public class UnsortedArrayList<T> extends UnsortedArrayCollection<T> 
	implements ListInterface<T>
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
		if (index < 0 || index > size)
			return false;
		if (size == array.length)
		{
			T[] newArray = (T[]) new Object[array.length * 2];
			System.arraycopy(array, 0, newArray, 0, array.length);
			array = newArray;
		}
		for (int i = size; i > index; i--)
			array[i] = array[i - 1];
		array[index] = element;
		size++;
		return true;
	}

	@Override
	public T get(int index)
	{
		if (index < 0 || index > size)
			return null;
		return array[index];
	}
 
	@Override
	public T set(int index, T element)
	{
		if (index < 0 || index > size)
			return null;
		array[index] = element;
		return element;
	}
	
	@Override
	public int indexOf(T element)
	{
		return find(element);
	}

	@Override
	public boolean remove(T element)
	{
		int location = find(element);
		if (location < 0)
			return false;
		return remove(location) != null;
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
     