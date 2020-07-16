// CMSC 350 Data Structures and Analysis
// Week 4 Examples
// Dr. Duane J. Jarc
// March 1, 2020

// This class contains the main method that creates the GUI for the
// program that tests the UnsortedArrayList class containing integers.
// The GUI allows integers to be appended, inserted, and removed from the
// list. In addition, the value at a given position can be both retrieved
// and the position of a given element  can be determined. Finally all 
// the elements of the list can be displayed.

package list;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ArrayListGUI extends JFrame implements ActionListener
{
	private final ListInterface<Integer> numbers = new UnsortedArrayList();
	private final String[] choices =
		{"", "Append", "Insert", "Remove", "Get", "Set", "Find", "Display"};
	private final JComboBox combo = new JComboBox(choices);
	private final JTextField numberText = new JTextField(10);
	private final JTextField positionText = new JTextField(10);
	private final JButton process = new JButton("Process Request");
	
	public ArrayListGUI()
	{
		super("Unsorted Array List Test");
		setSize(330, 200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new FlowLayout());
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(5, 2, 10, 10));
		panel.add(new JLabel(" Choose Selection: "));
		panel.add(combo);
		panel.add(new JLabel(" Value: "));
		panel.add(numberText);
		panel.add(new JLabel(" Position: "));
		panel.add(positionText);
		panel.add(new JLabel(""));
		panel.add(process);
		add(panel);
		process.addActionListener(this);
		combo.addItemListener(event ->
		{
			String choice = choices[combo.getSelectedIndex()];
			numberText.setText("");
			positionText.setText("");
			numberText.setEditable(!choice.equals("Get") && 
				!choice.equals("Display"));
			positionText.setEditable(!choice.equals("Append") && 
				!choice.equals("Find") && !choice.equals("Display"));
		});
		combo.setSelectedItem("");
		numberText.setEditable(false);
		positionText.setEditable(false);
	}
	
	@Override
	public void actionPerformed(ActionEvent event)
	{
		Integer number, position;
		int menuChoice = combo.getSelectedIndex();
		try
		{
			switch (choices[menuChoice])
			{
				case "Append":
					number = Integer.parseInt(numberText.getText());
					numbers.add(number);
					JOptionPane.showMessageDialog(null,	number + 
							" appended to the list");
					break;
				case "Insert":
					number = Integer.parseInt(numberText.getText());
					position = Integer.parseInt(positionText.getText());
					if (numbers.add(number, position))
						JOptionPane.showMessageDialog(null,	number + 
							" inserted at position " + position);
					else
						JOptionPane.showMessageDialog(null,	
							"Specified position is outside the array bounds");
					break;
				case "Remove":
					position = Integer.parseInt(positionText.getText());
					if (numbers.remove(position))
						JOptionPane.showMessageDialog(null,	
							"Value removed at position " + position);
					else
						JOptionPane.showMessageDialog(null,	
							"Specified position is outside the array bounds");
					break;
				case "Get":
					position = Integer.parseInt(positionText.getText());
					Integer numberValue = numbers.get(position);
					if (numberValue == null)
						JOptionPane.showMessageDialog(null,	
							"Specified position is outside the array bounds");
					else
						numberText.setText("" + numberValue);
					break;
				case "Set":
					position = Integer.parseInt(positionText.getText());
					number = Integer.parseInt(numberText.getText());
					numberValue = numbers.set(position, number);
					if (numberValue != null)
						JOptionPane.showMessageDialog(null,
							"Value at position " + position + "set to " +
							number);
					else
						JOptionPane.showMessageDialog(null,	
							"Specified position is outside the array bounds");
					break;
				case "Find":
					number = Integer.parseInt(numberText.getText());
					position = numbers.indexOf(number);
					if (position == -1)
						JOptionPane.showMessageDialog(null,	
							"Value is not in the list");
					else
						positionText.setText("" + position);
					break;
				case "Display":
					String list = "";
					for (Integer aNumber: numbers)
						list += aNumber + " ";
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
	
	public static void main(String[] args)
	{
		ArrayListGUI window = new ArrayListGUI();
		window.setVisible(true);
	}
}
