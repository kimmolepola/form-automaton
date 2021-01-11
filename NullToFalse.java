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
public class NullToFalse {

    public static String doIt(String s) {
        if (s == null) {
            s = "false";
        }
        return s;
    }
}
