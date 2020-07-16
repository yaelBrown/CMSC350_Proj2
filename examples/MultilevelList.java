// CMSC 350 Data Structures and Analysis
// Week 4 Examples
// Dr. Duane J. Jarc
// March 1, 2020

// This class defines a multilevel linked list that has a constructor that
// allows a list to be built from a parenthesized string and contains methods
// to reverse the list at either only the top level or at all levels..

package list;

import java.util.*;

public class MultilevelList
{
	private class Node
	{
		private String data;
		private Node first;
		private Node rest;
	}

	private Node head;
	
	public MultilevelList(String list)
	{
		StringTokenizer tokenizer = new StringTokenizer(list, "() ", true);
		if (!tokenizer.hasMoreTokens() || !tokenizer.nextToken().equals("("))
			throw new InvalidListSyntax();
		head = makeList(tokenizer);
	}
		
	private Node makeList(StringTokenizer tokenizer)
	{		
		Node subHead = null, previous = null, current;
		while (tokenizer.hasMoreTokens())
		{
			String token = tokenizer.nextToken();
			switch (token)
			{
				case ")":
					return subHead;
				case " ":
					continue;
				default:
					current = new Node();
					if (previous == null)
						subHead = current;
					else
						previous.rest = current;
					previous = current;
					if (token.equals("("))
						current.first = makeList(tokenizer);
					else
						current.data = token;
			}
		}
		throw new InvalidListSyntax();
	}
		
	public String reverseTop()
	{
		Node copy = copy(head);
		Node reversal = reverseTop(copy);
		return toString(reversal);
	}
	
	public String reverseAll()
	{
		Node copy = copy(head);
		Node reversal = reverseAll(copy);
		return toString(reversal);
	}
	
	private Node copy(Node node)
	{
		if (node == null)
			return null;
		Node copy = new Node();
		copy.data = node.data;
		copy.first = copy(node.first);
		copy.rest = copy(node.rest);
		return copy;
	}
	
	private Node reverseTop(Node node)
	{
		if (node == null)
			return null;
		if (node.rest == null)
			return node;
		Node second = node.rest;
		node.rest = null;
		Node reverseRest = reverseTop(second);
		second.rest = node;
		return reverseRest;
	}

		private Node reverseAll(Node node)
	{
		if (node == null)
			return null;
		if (node.rest == null)
			return node;
		Node second = node.rest;
		node.rest = null;
		Node reverseRest = reverseAll(second);
		second.first = reverseAll(second.first);
		second.rest = node;
		return reverseRest;
	}

	@Override
	public String toString()
	{
		return toString(head);
	}
	
	private String toString(Node current)
	{
		String result = "";
		if (current != null)
		{
			result += "(";
			while (current != null)
			{
				if (current.data != null)
					result += " " + current.data;
				else
					result += " " + toString(current.first);
				current = current.rest;
			}
			result += " )";
		}
		return result;
	}
}

