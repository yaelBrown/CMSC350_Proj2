/*
Filename: Project2.java
Author: Yael Brown
Date: 7/14/2020
Brief Purpose of the Program: To check Polynomials
*/

import java.util.*;
import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;

public class Project2 {
    public static List<Polynomial> polyList = new ArrayList<>();

    /**
     * Allows you to choose file with Polynomials
     * @return ArrayList of polynomials
     */
    public static ArrayList<String> readFile() {
        ArrayList<String> polyList = new ArrayList<>();
        JFileChooser chooser = new JFileChooser();

        chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        chooser.setCurrentDirectory(new File(System.getProperty("user.dir")));

        // Read the file
        int response = chooser.showOpenDialog(null);

        if (response == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();

            try {
                Scanner fileIn = new Scanner(file);
                if  (file.isFile()){
                    while (fileIn.hasNextLine()){
                        String expression = fileIn.nextLine();
                        polyList.add(expression);
                    }
                }
            } catch (NoSuchElementException nse) {
                JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),"This file is empty!");
            } catch(FileNotFoundException fne) {
                JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),"The file could not be found!");
            }
        }

        return polyList;
    }

    /**
     * Checks the order of the Polynomials
     * @param polyList
     * @return
     */
    public static boolean checkOrder(List<Polynomial> polyList) {
        boolean orderCheck = true;
        Polynomial previous = polyList.get(polyList.size() - 1);
        for (int i = polyList.size()-2; i > 0; i--) {
            if (previous.comparePoly(polyList.get(i)) < 0) {
                orderCheck = false;
            }
        }
        return orderCheck;
    }

    /**
     * Method to run that processes the logic of application.
     */
    public static void polyListProcess() {
        try {
            ArrayList<String> temp = readFile();
            for (String e : temp) {
                Polynomial p = new Polynomial(e);
                System.out.println("p = " + p);
                polyList.add(p);
            }
        } catch (InvalidPolynomialSyntax ex) {
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), ex.getMessage());
        }

        System.out.println("The Strong Ordered Polynomial: " + trueFalseYesNoConversion(OrderedList.checkSorted(polyList)));
        System.out.println("The Weak Ordered Polynomial: " + trueFalseYesNoConversion(checkOrder(polyList)));
    }

    /**
     * Converts true and false to string of yes and no
     * @param tf
     * @return
     */
    public static String trueFalseYesNoConversion(boolean tf) {
        if (tf == true) {
            return "Yes";
        }
        return "No";
    }

    public static void main(String[] args) {
        polyListProcess();
    }
}