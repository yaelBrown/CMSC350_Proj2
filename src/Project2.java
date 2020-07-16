/*
Filename: Project2.java
Author: Yael Brown
Date: 7/14/2020
Brief Purpose of the Program:
*/

import com.sun.javafx.geom.BaseBounds;

import javax.swing.*;
import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Project2 {
    public static ArrayList<String> fPolys = new ArrayList<>();

    public static ArrayList<String> polysFromFile() {
        String fileName = "sampleFile.txt";
        String path = "src" + "/" + fileName;
        ArrayList<String> out = new ArrayList<>();

        // Open File in directory
        // This is only here for project requirements.
        JFileChooser fc = new JFileChooser(new File(fileName));

        // Read the actual file
        try (BufferedReader f = new BufferedReader(new FileReader(path))) {
            StringBuffer sb = new StringBuffer();
            while (f.ready()) {
                char c = (char) f.read();
                if (c == '\n') {
                    out.add(sb.toString());
                    sb = new StringBuffer();
                } else {
                    sb.append(c);
                }
            }
            if (sb.length() > 0) {
                out.add(sb.toString());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return out;
    }

    public static void main(String[] args) {

        // Print Polynomials in text file
        for (String p : fPolys) {
            System.out.println(p);
        }

    }

}