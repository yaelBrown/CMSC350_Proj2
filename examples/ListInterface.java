// CMSC 350 Data Structures and Analysis
// Week 4 Examples
// Dr. Duane J. Jarc
// March 1, 2020

// This interface defines the operations that are required for a list in
// addition to those that are inherited from the CollectionInterface and
// the Iterable interfaces.

package list;

import collection.*;

public interface ListInterface<T> extends Iterable<T>, CollectionInterface<T>
{
	boolean add(T element, int index);
	T set(int index, T element);
	T get(int index);
	int indexOf(T element);
	T remove(int index);
	int size();
}
