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
        public Poly next;
        private Poly head;
        public double coef;
        public int exp;

        public Poly(double c, int e) {
            coef = c;
            exp = e;
            next = null;
        }

        public int getExponent() {
            return this.exp;
        }
        public double getCoefficient() {
            return this.coef;
        }
        public Poly getNext() {
            return next;
        }
        public Poly getHead() { return head; }

        @Override
        public String toString() {
            String termStr = String.format("", Math.abs(coef));
            switch(exp) {
                case 0:
                    return termStr;
                case 1:
                    return termStr + "x";
                default:
                    return termStr + "x^" + exp;
            }
        }
    }

    Comparator<Polynomial> compare = Polynomial::comparePoly;
    public Poly head = null;

    /**
     * Constructor for Polynomial's
     * @param pStr
     */
    public Polynomial(String pStr) {
        Scanner termScanner = new Scanner(pStr);
        try {
            while (termScanner.hasNext()) { addTerm(termScanner.nextDouble(), termScanner.nextInt()); }
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
        if (exponent < 0) { throw new InvalidPolynomialSyntax("Cannot process negative polynomials"); }
        Poly cur = null;
        head = new Poly(coefficient, exponent);
        head.next = null;

        if (cur != null) {
            while (cur.next != null) { cur = cur.next; }
            cur.next = new Poly(coefficient, exponent);
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

        if (thisCurrent.equals(null) && otherCurrent.equals(null)) return 0;
        if (thisCurrent.equals(null)) return -1;

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

        return 1;
    }

    /**
     * Also used to compare Polynomials
     * @param poly2
     * @return int
     */
    public int comparePoly(Polynomial poly2) {
        Poly thisPolyTerm = this.head;
        Poly otherPolyTerm = poly2.head;

        while(thisPolyTerm != null && otherPolyTerm != null) {
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
}