// CMSC 350 Data Structures and Analysis
// Week 4 Examples
// Dr. Duane J. Jarc
// March 1, 2020

// This class contains the main method that creates the GUI for the
// program that allows the user to enter a multilevel list in parenthesized
// form. It then allows the list to be reversed only at the top level or
// also at all levels.

package list;

import java.awt.*;
import javax.swing.*;

public class MultilevelListGUI extends JFrame
{
	private final JButton show = new JButton("Show Original");
	private final JButton reverseTop = new JButton("Reverse Top");
	private final JButton reverseAll = new JButton("Reverse All");
	private final JTextField listText = new JTextField(25),
		reverseText = new JTextField(25);
	private final JButton process = new JButton("Create List");
	private MultilevelList list = null;
	
	public MultilevelListGUI()
	{
		super("Multilevel List Reversal");
		setSize(500, 200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new FlowLayout());
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(3, 1, 10, 10));
		JPanel topPanel = new JPanel();
		topPanel.setLayout(new FlowLayout());
		topPanel.add(new JLabel("Enter List: "));
		topPanel.add(listText);
		topPanel.add(process);
		panel.add(topPanel);
		JPanel middlePanel = new JPanel();
		middlePanel.add(reverseText);
		reverseText.setEditable(false);
		panel.add(middlePanel);
		JPanel bottomPanel = new JPanel();
		bottomPanel.add(show);
		bottomPanel.add(reverseTop);
		bottomPanel.add(reverseAll);
		panel.add(bottomPanel);
		add(panel);
		process.addActionListener(event -> createList());
		show.addActionListener(event -> 
		{
			if (checkListCreated())
				reverseText.setText(list.toString());
		});
		reverseTop.addActionListener(event -> 
		{
			if (checkListCreated())
				reverseText.setText(list.reverseTop());
		});
		reverseAll.addActionListener(event -> 
		{
			if (checkListCreated())
				reverseText.setText(list.reverseAll());
		});
	}
	
	private void createList()
	{
		try
		{
			list = new MultilevelList(listText.getText());
		}
		catch (InvalidListSyntax exception)
		{
			JOptionPane.showMessageDialog(null, "Invalid List Syntax");
		}
	}
	
	private boolean checkListCreated()
	{
		if (list != null)
			return true;
		JOptionPane.showMessageDialog(null, "List Not Yet Created");
		return false;
	}
	
	public static void main(String[] args)
	{
		MultilevelListGUI window = new MultilevelListGUI();
		window.setVisible(true);
	}
}
