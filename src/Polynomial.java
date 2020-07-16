/*
Filename: Project2.java
Author: Yael Brown
Date: 7/14/2020
Brief Purpose of the Program:
*/

import java.util.Comparator;
import java.util.Iterator;

public class Polynomial implements Iterable, Comparable {
    private String polyStr = null;
    Comparator<Polynomial> compare;

    public Polynomial(String pS) throws InvalidPolynomialSyntax {
        this.polyStr = pS;

    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }

    @Override
    public Iterator iterator() {
        return null;
    }

    @Override
    public String toString() {
        return this.polyStr;
    }

}
