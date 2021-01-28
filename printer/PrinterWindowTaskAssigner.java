/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.formautomaton.printer;

import com.mycompany.formautomaton.ThreadSleep;
import com.sun.jna.Native;
import com.sun.jna.platform.win32.User32;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

/**
 *
 * @author
 */
public class PrinterWindowTaskAssigner {

    private final PrinterWindowController pwc = new PrinterWindowController();
    private final List<User32.HWND> topWindows = new ArrayList<>();

    public boolean test() {
        if (!print("asdf", false, "<PRINTER>", 5, 4)) {
            return false;
        }
        return print("asdf", false, "<PRINTER>", 5, 0);

    }

    public boolean print(String targetWindow, boolean chromeBrowser, String printer, int printerMenuPosition, int paperSource) {
        if (chromeBrowser) {
            if (!pwc.openPrinterWindowChrome(targetWindow, printer)) {
                return false;
            }
        } else {
            if (!pwc.openPrinterWindow(targetWindow, printer)) {
                return false;
            }
        }
        topWindows.clear();
        pwc.resetFailCounter();
        User32.HWND hWnd;

        String name = "Tulosta";
        hWnd = pwc.retrieve(null, name);
        if (!processSuccess(hWnd, name)) {
            return false;
        }

        User32.HWND printerMainWindow = hWnd;
        topWindows.add(hWnd);

        name = "FolderView";
        User32.HWND folderView = pwc.retrieve(printerMainWindow, name);
        if (!processSuccess(folderView, name)) {
            return false;
        }

        ThreadSleep.sleep();

        if (!processSuccess(pwc.sendInput(folderView, createPrinterSelectInput(printerMenuPosition)), "PrinterSelectInput")) {
            return false;
        }

        name = "Ominaisuudet: " + printer;
        searchAndCloseAll(name);

        if (!processSuccess(pwc.sendInput(folderView, createOpenPropertiesInput()), "OpenMenuInput")) {
            return false;
        }

        hWnd = pwc.retrieve(null, name);
        if (!processSuccess(hWnd, "Retrieve " + name)) {
            return false;
        }
        pwc.closeWindow(hWnd);

        searchAndCloseAll("Tulostusasetukset");

        pwc.activate(printerMainWindow);

        name = "&M��ritykset";
        hWnd = pwc.retrieve(printerMainWindow, name);
        if (!processSuccess(hWnd, name)) {
            return false;
        }

        pwc.click(hWnd);
        name = "Tulostusasetukset";
        hWnd = pwc.retrieve(null, name);
        if (!processSuccess(hWnd, name)) {
            return false;
        }
        topWindows.add(hWnd);

        ThreadSleep.sleep();

        name = "Paperil�hde:";
        hWnd = analyseCorrectAnonymousHandle(hWnd, name, 1);
        if (!processSuccess(hWnd, name)) {
            return false;
        }

        if (!processSuccess(pwc.sendInput(hWnd, createPaperSourceSelectInput(paperSource)), "PaperSourceSelectInput")) {
            return false;
        }

        name = "&Tulosta";
        hWnd = pwc.retrieve(printerMainWindow, name);
        if (!processSuccess(hWnd, name)) {
            return false;
        }

        //pwc.click(hWnd);
        ThreadSleep.sleep();
        pwc.closePrinterWindows(printer);
        System.out.println("success");
        return true;
    }

    private void searchAndCloseAll(String name) {
        while (true) {
            User32.HWND hWnd = pwc.retrieve(null, name, true);
            if (hWnd == null) {
                break;
            }
            pwc.closeWindow(hWnd);
        }
    }

    private boolean processSuccess(Boolean bool, String name) {
        if (!bool) {
            System.out.println(name + " abort");
            abort();
            return false;
        }
        return true;
    }

    private boolean processSuccess(User32.HWND hWnd, String name) {
        if (hWnd == null) {
            System.out.println(name + " abort");
            abort();
            return false;
        }
        pwc.resetFailCounter();
        return true;
    }

    private boolean processSuccess(List<User32.HWND> hWnds, String name, int expectedSize) {
        if (hWnds.size() != expectedSize) {
            System.out.println(name + " abort. expected: " + expectedSize + ", found: " + hWnds.size());
            abort();
            return false;
        }
        pwc.resetFailCounter();
        return true;
    }

    private User32.HWND analyseCorrectAnonymousHandle(User32.HWND hWnd, String precedingHandleName, int relativeIndexToPrecedingHandle) {
        int sampleSize = 30;
        User32.HWND correctAnonymousHandle = null;
        Map<String, Integer> rects = new HashMap<>();
        List<List<User32.HWND>> hWndsList = new ArrayList<>();
        for (int i = 0; i < sampleSize; i++) {
            List<User32.HWND> hWnds = pwc.getChilds(hWnd);
            hWndsList.add(hWnds);
            for (User32.HWND h : hWnds) {
                ThreadSleep.sleep(1);
                if (pwc.getWindowText(h).equals(precedingHandleName)) {
                    try {
                        User32.HWND target = hWnds.get(hWnds.indexOf(h) + relativeIndexToPrecedingHandle);
                        User32.RECT targetRect = new User32.RECT();
                        User32.INSTANCE.GetWindowRect(target, targetRect);
                        String r = targetRect.toString();
                        if (rects.containsKey(r)) {
                            int counter = rects.get(r);
                            counter++;
                            rects.put(r, counter);
                        } else {
                            rects.put(r, 1);
                        }
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("analyseCorrectAnonymousHandle, " + e);
                    }
                    break;
                }
            }
        }
        String targetString = null;
        if (rects.size() > 1) {
            int highest = 0;
            int secondHighest = 0;
            for (int i : rects.values()) {
                if (i > highest) {
                    highest = i;
                } else if (i > secondHighest) {
                    secondHighest = i;
                }
            }
            if (highest == secondHighest) {
                System.out.println("eka");
                return null;
            }
            for (String s : rects.keySet()) {
                if (rects.get(s) == highest) {
                    targetString = s;
                }
            }
        } else if (rects.size() == 1) {
            targetString = rects.keySet().iterator().next();
        } else {
            System.out.println("toka");
            return null;
        }
        for (List<User32.HWND> hWnds : hWndsList) {
            for (User32.HWND h : hWnds) {
                User32.RECT r = new User32.RECT();
                User32.INSTANCE.GetWindowRect(h, r);
                if (r.toString().equals(targetString)) {
                    correctAnonymousHandle = h;
                }
            }
        }
        return correctAnonymousHandle;
    }

    private int countDistance(User32.HWND h1, User32.HWND h2) {
        User32.RECT r1 = new User32.RECT();
        User32.RECT r2 = new User32.RECT();
        User32.INSTANCE.GetWindowRect(h1, r1);
        User32.INSTANCE.GetWindowRect(h2, r2);
        return r2.top - r1.top;
    }

    private void abort() {
        System.out.println("abort");
        ThreadSleep.sleep();
        ListIterator<User32.HWND> it = topWindows.listIterator(topWindows.size());
        while (it.hasPrevious()) {
            pwc.closeWindow(it.previous());
        }
    }

    private char[] createPrinterSelectInput(int printer) {
        char[] chars = new char[printer + 1];
        chars[0] = 0x24; // home
        for (int i = 1; i < printer + 1; i++) {
            chars[i] = 0x28; // down
        }
        return chars;
    }

    private char[] createPaperSourceSelectInput(int paperSource) {
        char[] chars = new char[paperSource + 2];
        chars[0] = 0x24; // home
        for (int i = 1; i < paperSource + 1; i++) {
            chars[i] = 0x28; // down
        }
        chars[paperSource + 1] = 0x0D; // enter
        return chars;
    }

    private char[] createOpenPropertiesInput() {
        char[] chars = new char[2];
        chars[0] = 0x5D; // menu-key
        chars[1] = 0x4D; // m-key
        return chars;
    }
}
