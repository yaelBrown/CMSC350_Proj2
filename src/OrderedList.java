/*
Filename: OrderedList.java
Author: Yael Brown
Date: 7/14/2020
Brief Purpose of the Program: To check Polynomials
*/

import java.util.List;

public class OrderedList {
    /**
     * Checks if List of Polynomials is sorted
     * @param list
     * @param <T>
     * @return
     */
    public static <T extends Comparable<? super T> > boolean checkSorted(List<T> list) {
        boolean isSorted = true;
        for (int i = list.size()-1; i > 0 ; i--) {
            T current = list.get(i);
            if (!checkSorted(list, current)) { isSorted = false; }
        }
        return isSorted;
    }

    /**
     * Checks if polynomial is sorted against previous list
     * @param list
     * @param current
     * @param <T>
     * @return
     */
    private static <T extends Comparable<? super T> > boolean checkSorted(List<T> list, T current) {
        T curValue = list.get(list.indexOf(current));
        T nextValue = list.get(list.indexOf(current) - 1);

        if (nextValue != null) { return curValue.compareTo(nextValue) >= 0; }

        return true;
    }

}