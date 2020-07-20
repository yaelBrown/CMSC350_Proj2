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

    /**
     * Polynomial sub class
     */
    public static class Poly {
        public double coef;
        public int exp;
        public Poly next;
        private Poly head;

        public Poly(double c, int e) {
            coef = c;
            exp = e;
            next = null;
        }

        public int getExponent() { return this.exp; }
        public double getCoefficient() { return this.coef; }
        public Poly getNext() { return next; }
        public Poly getHead() { return head; }

        @Override
        public String toString() {
            String t = String.format("", Math.abs(coef));
            switch(exp) {
                case 0:
                    return t;
                case 1:
                    return t + "x";
                default:
                    return t + "x^" + exp;
            }
        }
    }

    Comparator<Polynomial> compare = Polynomial::comparePoly;
    public Poly head = null;

    /**
     * Constructor for Polynomial's
     * @param p
     */
    public Polynomial(String p) {
        Scanner ts = new Scanner(p);
        try {
            while (ts.hasNext()) { addTerm(ts.nextDouble(), ts.nextInt()); }
        } catch (Exception e) {
            e.printStackTrace();
            throw new InvalidPolynomialSyntax("Input error");
        }
    }

    /**
     * Adds terms to Polynomials
     * @param coefficient
     * @param exponent
     */
    public void addTerm(double coefficient, int exponent) {
        if (exponent < 0) throw new InvalidPolynomialSyntax("Cannot process negative polynomials");

        Poly cur = head;

        if (cur == null) {
            head = new Poly(coefficient, exponent);
            head.next = null;
        } else {
            while (cur.next != null) cur = cur.next;
            cur.next = new Poly(coefficient, exponent);
        }
    }

    /**
     * Used to compare Polynomials
     * @param p3
     * @return
     */
    @Override
    public int compareTo(Polynomial p3) {
        Poly thisCurrent = this.head;
        Poly otherCurrent = p3.head;

        while (thisCurrent != null && otherCurrent != null) {
            if (thisCurrent.getExponent() != otherCurrent.getExponent()) {
                return thisCurrent.getExponent() - otherCurrent.getExponent();
            } else if (thisCurrent.getCoefficient() != otherCurrent.getCoefficient()) {
                if (otherCurrent.getCoefficient() > thisCurrent.getCoefficient()) {
                    return -1;
                } else if (otherCurrent.getCoefficient() < thisCurrent.getCoefficient()) {
                    return 1;
                }
            }

            thisCurrent = thisCurrent.getNext();
            otherCurrent = otherCurrent.getNext();
        }

        if (thisCurrent == null && otherCurrent == null) return 0;

        if (thisCurrent == null) {
            return -1;
        } else {
            return 1;
        }
    }

    /**
     * Also used to compare Polynomials
     * @param p2
     * @return int
     */
    public int comparePoly(Polynomial p2) {
        Poly thisPolyTerm = this.head;
        Poly otherPolyTerm = p2.head;

        while (thisPolyTerm != null && otherPolyTerm != null) {
            if (thisPolyTerm.getExponent() != otherPolyTerm.getExponent()) {
                return thisPolyTerm.getExponent() - otherPolyTerm.getExponent();
            } else {
                thisPolyTerm = thisPolyTerm.getNext();
                otherPolyTerm = otherPolyTerm.getNext();
            }
        }

        if (thisPolyTerm == null && otherPolyTerm == null) return 0;
        if (otherPolyTerm == null) return 1;
        return -1;
    }

    /**
     * Overwritten iterator for Poly subclass
     * @return Iterator<Poly>
     */
    @Override
    public Iterator<Poly> iterator() {
        return new Iterator<Poly>() {

            public Poly cur = head.getHead();

            @Override
            public boolean hasNext() {
                return (cur != null) && (cur.getNext() != null);
            }

            @Override
            public Poly next() {
                Poly data = cur;
                cur = cur.next;
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
        StringBuilder exp = new StringBuilder();

        if (head.coef > 0) {
            exp.append(head.toString());
        } else {
            exp.append(" - ").append(head.toString());
        }

        for (Poly temp = head.next; temp != null; temp = temp.next) {

            if ((temp.coef < 0)) {
                exp.append(" - ").append(temp.toString());
            } else {
                exp.append(" + ").append(temp.toString());
            }
        }
        return exp.toString();
    }


}