/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.formautomaton;

/**
 *
 * @author
 */
public class RectWithCounter implements Comparable<RectWithCounter> {

    private int counter;
    private String rect;

    public RectWithCounter(String rectString) {
        rect = rectString;
        counter = 0;
    }

    public String getRect() {
        return rect;
    }

    public void incrementCounter() {
        counter++;
    }

    public int getCounter() {
        return counter;
    }

    @Override
    public int compareTo(RectWithCounter other) {
        int i = Integer.compare(counter, other.getCounter());
        if (i != 0) {
            i = -i;
        }
        return i;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof RectWithCounter)) {
            return false;
        }
        RectWithCounter u = (RectWithCounter) o;
        return rect.equals(u.getRect());
    }

    @Override
    public int hashCode() {
        return rect.hashCode();
    }
}
