// CMSC 350 Data Structures and Analysis
// Week 4 Examples
// Dr. Duane J. Jarc
// March 1, 2020

// This interface defines a sorted list as a list whose components must 
// implement the Comparable interface.

package list;

public interface SortedListInterface<T extends Comparable<T>>
	extends ListInterface<T>
{
}