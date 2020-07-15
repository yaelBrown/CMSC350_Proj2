/*
Filename: Project2.java
Author: Yael Brown
Date: 7/14/2020
Brief Purpose of the Program:
*/

public class InvalidPolynomialSyntax extends RuntimeException {

    public InvalidPolynomialSyntax(String msg) {
        super(msg);
    }

    public InvalidPolynomialSyntax(String msg, Throwable t) {
        super(msg);
    }

}
