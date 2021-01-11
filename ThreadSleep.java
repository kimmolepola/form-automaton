/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.formautomaton;

import com.mycompany.formautomaton.printer.PrinterWindowController;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author
 */
public class ThreadSleep {

    public static void sleep() {
        try {
            Thread.sleep(40);
        } catch (InterruptedException ex) {
            Logger.getLogger(PrinterWindowController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void sleep(int i) {
        try {
            Thread.sleep(i);
        } catch (InterruptedException ex) {
            Logger.getLogger(PrinterWindowController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
