/*
Filename: Polynomial.java
Author: Yael Brown
Date: 7/14/2020
Brief Purpose of the Program: To check Polynomials
*/

import java.util.Scanner;
import java.util.Comparator;
import java.util.Iterator;

class Polynomial implements Iterable<Polynomial.Poly>, Comparable<Polynomial> {

    Comparator<Polynomial> compare;
    public Poly head;

    /**
     * Constructor for Polynomial's
     * @param fromFile
     */
    public Polynomial(String fromFile) {
        compare = Polynomial::comparePoly;
        head = null;

        Scanner termScanner = new Scanner(fromFile);
        try {
            while (termScanner.hasNext()) {
                addTerm(termScanner.nextDouble(), termScanner.nextInt());
            }
        } catch (Exception ex) {
            System.out.println(ex.getLocalizedMessage());
            throw new InvalidPolynomialSyntax("Input error");
        }
    }

    /**
     * Adds terms to Polynomials
     * @param coefficient
     * @param exponent
     */
    public void addTerm(double coefficient, int exponent) {
        if (exponent < 0) {
            throw new InvalidPolynomialSyntax("Cannot process negative polynomials");
        }

        Poly current = head;

        if (current == null) {
            head = new Poly(coefficient, exponent);
            head.next = null;
        } else {
            while (current.next != null) {
                current = current.next;
            }
            current.next = new Poly(coefficient, exponent);
        }
    }

    /**
     * Used to compare Polynomials
     * @param poly3
     * @return
     */
    @Override
    public int compareTo(Polynomial poly3) {
        Poly thisCurrent = this.head;
        Poly otherCurrent = poly3.head;

        while (thisCurrent != null && otherCurrent != null) {
            if (thisCurrent.getExponent() != otherCurrent.getExponent()) {
                return thisCurrent.getExponent() - otherCurrent.getExponent();
            } else if (thisCurrent.getCoefficient() != otherCurrent.getCoefficient()) {
                if (otherCurrent.getCoefficient() > thisCurrent.getCoefficient()) {
                    return -1;
                } else if (otherCurrent.getCoefficient() < thisCurrent.getCoefficient()) {
                    return +1;
                }
            }

            thisCurrent = thisCurrent.getNext();
            otherCurrent = otherCurrent.getNext();
        }

        if (thisCurrent == null && otherCurrent == null) {
            return 0;
        }

        if (thisCurrent == null) {
            return -1;
        } else {
            return +1;
        }
    }

    /**
     * Also used to compare Polynomials
     * @param poly2
     * @return int
     */
    public int comparePoly(Polynomial poly2) {
        Poly thisPolyTerm = this.head;
        Poly otherPolyTerm = poly2.head;

        while (thisPolyTerm != null && otherPolyTerm != null) {
            if (thisPolyTerm.getExponent() != otherPolyTerm.getExponent()) {
                return thisPolyTerm.getExponent() - otherPolyTerm.getExponent();
            } else {
                thisPolyTerm = thisPolyTerm.getNext();
                otherPolyTerm = otherPolyTerm.getNext();
            }
        }

        if (thisPolyTerm == null && otherPolyTerm == null) {
            return 0;
        }

        if (otherPolyTerm == null) {
            return +1;
        } else {
            return -1;
        }
    }

    /**
     * Polynomial sub class
     */
    public static class Poly {
        public double coefficient;
        public int exponent;
        public Poly next;
        private Poly head;

        public Poly(double c, int e) {
            coefficient = c;
            exponent = e;
            next = null;
        }

        public int getExponent() {
            return this.exponent;
        }

        public double getCoefficient() {
            return this.coefficient;
        }

        public Poly getNext() {
            return next;
        }

        @Override
        public String toString() {
            String termString = String.format("", Math.abs(coefficient));
            if (exponent == 0) {
                return termString;
            } else if (exponent == 1) {
                return termString + "x";
            } else {
                return termString + "x^" + exponent;
            }
        }

        public Poly getHead() {
            return head;
        }
    }

    /**
     * Overwritten iterator for Poly subclass
     * @return Iterator<Poly>
     */
    @Override
    public Iterator<Poly> iterator() {
        return new Iterator<Poly>() {

            public Poly current = head.getHead();

            @Override
            public boolean hasNext() {
                return current != null && current.getNext() != null;
            }

            @Override
            public Poly next() {
                Poly data = current;
                current = current.next;
                return data;
            }
        };
    }

    /**
     * Overwritten toString method
     * @return String
     */
    @Override
    public String toString() {
        StringBuilder expressionBuilder = new StringBuilder();

        if (head.coefficient > 0) {
            expressionBuilder.append(head.toString());
        } else {
            expressionBuilder.append(" - ").append(head.toString());
        }


        for (Poly tmp = head.next; tmp != null; tmp = tmp.next) {
            if (tmp.coefficient < 0) {
                expressionBuilder.append(" - ").append(tmp.toString());
            } else {
                expressionBuilder.append(" + ").append(tmp.toString());
            }
        }
        return expressionBuilder.toString();
    }


}