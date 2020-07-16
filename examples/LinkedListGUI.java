// CMSC 350 Data Structures and Analysis
// Week 4 Examples
// Dr. Duane J. Jarc
// March 1, 2020

// This class contains the main method that creates the GUI for the
// program that tests the SortedLinkedList class containing pairs of 
// integers that have two comparisons provided, one by extending Comparable
// and a second one with a Comparator. The GUI allows integer pairs to be
// inserted, removed by value, removed by position, a value at a specified
// position to be determined, and provides the ability to display the entire
// list. 

package list;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class LinkedListGUI extends JFrame implements ActionListener
{
	private ListInterface<Pair> pairs;
	private final String[] choices =
		{"", "Insert", "Remove Element", "Remove Position", "Get", 
			"Index Of", "Display"};
	private final JComboBox combo = new JComboBox(choices);
	private final JTextField pairText = new JTextField(10);
	private final JTextField positionText = new JTextField(10);
	private final JButton process = new JButton("Process Request");
	
	public LinkedListGUI()
	{
		super("Sorted Linked List Test");
		setSize(330, 200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new FlowLayout());
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(5, 2, 10, 10));
		panel.add(new JLabel(" Choose Selection: "));
		panel.add(combo);
		panel.add(new JLabel(" Number Pair: "));
		panel.add(pairText);
		panel.add(new JLabel(" Position: "));
		panel.add(positionText);
		panel.add(new JLabel(""));
		panel.add(process);
		add(panel);
		process.addActionListener(this);
		combo.addItemListener(event ->
		{
			String choice = choices[combo.getSelectedIndex()];
			if (choice.equals(""))
				return;
			pairText.setText("");
			positionText.setText("");
			pairText.setEditable(!choice.equals("Get") && 
				!choice.equals("Remove Position") &&!choice.equals("Display"));
			positionText.setEditable(choice.equals("Get") ||
				choice.equals("Remove Position"));
		});
		combo.setSelectedItem("");
		pairText.setEditable(false);
		positionText.setEditable(false);
	}
	
	@Override
	public void actionPerformed(ActionEvent event)
	{
		Pair pair;
		int menuChoice = combo.getSelectedIndex();
		try
		{
			switch (choices[menuChoice])
			{
				case "Insert":
					pair = getPair(pairText.getText());
					pairs.add(pair);
					JOptionPane.showMessageDialog(null,	pair + 
							" inserted into the list");
					break;
				case "Remove Element":
					pair = getPair(pairText.getText());
					if (pairs.remove(pair))
						JOptionPane.showMessageDialog(null,	
							pair + " removed from the list");
					else
						JOptionPane.showMessageDialog(null,	
							pair + " was not in the list");
					break;
				case "Remove Position":
					int position = Integer.parseInt(positionText.getText());
					pair = pairs.remove(position);
					if (pair == null)
						JOptionPane.showMessageDialog(null,	
							"Specified position is outside the array bounds");
					else
						JOptionPane.showMessageDialog(null,	
							pair + " removed from the list");
					break;
				case "Get":
					position = Integer.parseInt(positionText.getText());
					pair = pairs.get(position);
					if (pair == null)
						JOptionPane.showMessageDialog(null,	
							"Specified position is outside the array bounds");
					else
						pairText.setText("" + pair);
					break;
				case "Index Of":
					pair = getPair(pairText.getText());
					position = pairs.indexOf(pair);
					if (position == -1)
						JOptionPane.showMessageDialog(null,	
							"Number pair is not in the list");
					else
						positionText.setText("" + position);
					break;
				case "Display":
					String list = "";
					for (Pair aPair: pairs)
						list += aPair + " ";
					JOptionPane.showMessageDialog(null, list);
					break;
				default:
					JOptionPane.showMessageDialog(null, 
						"Choose selection first ");
			}
		}
		catch (NumberFormatException exception)
		{
			JOptionPane.showMessageDialog(null,	
				"Numeric value required in position and value fields");
		}
		combo.setSelectedItem("");
	}
	
	public Pair getPair(String pairString) throws NumberFormatException
	{
		String[] values = pairString.split(",");
		if (values.length != 2)
			throw new NumberFormatException();
		int first = Integer.parseInt(values[0]);
		int second = Integer.parseInt(values[1]);
		return new Pair(first, second);
	}
	
	public static void main(String[] args)
	{
		LinkedListGUI window = new LinkedListGUI();
		String[] options = {"Sort by First Element", "Sort by Second Element"};
		int choice = JOptionPane.showOptionDialog(null, 
			"Select which element to sort by", "Click a button", 
			JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null,
			options, options[0]);
		if (choice == 0)
			window.pairs = new SortedLinkedList();
		else
			window.pairs = new SortedLinkedList(Pair.getCompare());
		window.setVisible(true);
	}
}
