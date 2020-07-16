// CMSC 350 Data Structures and Analysis
// Week 4 Examples
// Dr. Duane J. Jarc
// March 1, 2020

// This class defines integer pair objects that can be compared in two ways.
// The first comparison provided by the compareTo method considers the first
// element of the pair to be most significant. It also provides a getCompare
// method that returns a Comparator that considers the second element to be
// the most significant. In addition, a toString method is included that forms
// a string separating the integers by a comma.

package list;

import java.util.*;

public class Pair implements Comparable<Pair>
{
	private int first, second;
	
	public Pair(int first, int second)
	{
		this.first = first;
		this.second = second;
	}
	
	@Override
	public int compareTo(Pair other)
	{
		if (first != other.first)
			return first - other.first;
		return second - other.second;
	}
	
	@Override
	public String toString()
	{
		return "(" + first + ", " + second + ")"; 
	}
	
	public static Comparator<Pair> getCompare()
	{
		return (Pair left, Pair right) -> 
		{	
			if (left.second != right.second)
				return left.second - right.second;
			return left.first - right.first;
		};
	}
}
