/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.formautomaton.printer;

import com.mycompany.formautomaton.ThreadSleep;
import com.sun.jna.Native;
import com.sun.jna.platform.win32.BaseTSD;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinUser;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author
 */
public class PrinterWindowController {

    public static final int WM_LBUTTONUP = 514;
    public static final int WM_LBUTTONDOWN = 513;
    private int failCounter = 0;
    private final int failTop = 50;
    private final List<User32.HWND> topWindows = new ArrayList<>();

    protected boolean openPrinterWindowChrome(String windowText, String printer) {
        closePrinterWindows(printer);
        char[] chars = {0x11, 0x10, 'P', 0x10, 0x11};

        User32.HWND hWnd = retrieve(null, windowText);
        if (hWnd == null) {
            return false;
        }
        return sendInput(hWnd, chars);
    }

    protected boolean openPrinterWindow(String windowText, String printer) {
        closePrinterWindows(printer);
        char[] chars = {0x11, 'P', 0x11};
        User32.HWND hWnd = retrieve(null, windowText);
        if (hWnd == null) {
            return false;
        }
        return sendInput(hWnd, chars);
    }

    protected void resetFailCounter() {
        failCounter = 0;
    }

    protected User32.RECT getRect(User32.HWND hWnd) {
        User32.RECT r = new User32.RECT();
        User32.INSTANCE.GetWindowRect(hWnd, r);
        return r;
    }

    protected String getWindowText(User32.HWND hWnd) {
        char[] chars = new char[512];
        User32.INSTANCE.GetWindowText(hWnd, chars, chars.length);
        return Native.toString(chars);
    }

    protected void closeWindow(User32.HWND hWnd) {
        if (hWnd != null) {
            User32.INSTANCE.PostMessage(hWnd, 0x10, null, null); //close window
            ThreadSleep.sleep();
        }
    }

    protected User32.HWND retrieve(User32.HWND hWndParent, String name) {
        return retrieve(hWndParent, name, false);
    }

    protected User32.HWND retrieve(User32.HWND hWndParent, String name, boolean relent) {
        int divider = 1;
        if (relent) {
            divider = 10;
        }

        User32.HWND hWnd = findChild(hWndParent, name);
        while (hWnd == null) {
            System.out.println(name + " " + failCounter);
            failCounter++;
            if (failCounter > failTop / divider) {
                failCounter = 0;
                break;
            }
            ThreadSleep.sleep();
            hWnd = findChild(hWndParent, name);
        }
        failCounter = 0;
        return hWnd;
    }

    protected List<User32.HWND> retrieveAll(User32.HWND hWndParent, String name, int expectedAmount) {
        List<User32.HWND> hWnds = findAll(hWndParent, name);
        while (hWnds.size() < expectedAmount) {
            System.out.println(name + " " + failCounter);
            failCounter++;
            if (failCounter > failTop) {
                failCounter = 0;
                break;
            }
            ThreadSleep.sleep();
            hWnds = findAll(hWndParent, name);
        }
        failCounter = 0;
        return hWnds;
    }

    protected boolean sendInput(User32.HWND hWnd, char[] inputArray) {
        WinUser.INPUT input = new WinUser.INPUT();
        input.type = new WinDef.DWORD(WinUser.INPUT.INPUT_KEYBOARD);
        input.input.setType("ki"); // Because setting INPUT_INPUT_KEYBOARD is not enough: https://groups.google.com/d/msg/jna-users/NDBGwC1VZbU/cjYCQ1CjBwAJ
        input.input.ki.wScan = new WinDef.WORD(0);
        input.input.ki.time = new WinDef.DWORD(0);
        input.input.ki.dwExtraInfo = new BaseTSD.ULONG_PTR(0);

        Set<Character> holdDowns = new HashSet<>();

        for (char c : inputArray) {
            if (c == 0x10 || c == 0x11 || c == 0xA4) { //Shift, Control or Alt
                if (!holdDowns.contains(c)) {
                    keydown(hWnd, input, c);
                    holdDowns.add(c);
                } else {
                    keyup(hWnd, input, c);
                    holdDowns.remove(c);
                }
            } else { //Normal
                if (!activate(hWnd)) {
                    return false;
                }
                keydown(hWnd, input, c);
                keyup(hWnd, input, c);
            }
            ThreadSleep.sleep();
        }
        return true;
    }

    protected List<User32.HWND> getChilds(User32.HWND hWnd) {
        List<User32.HWND> hWnds = new ArrayList<>();
        User32.INSTANCE.EnumChildWindows(hWnd, (hWndChild, dataChild) -> {
            hWnds.add(hWndChild);
            return true;
        }, null);
        return hWnds;
    }

    protected void click(User32.HWND hWnd) {
        ThreadSleep.sleep();
        User32.INSTANCE.PostMessage(hWnd, WM_LBUTTONDOWN, null, null);
        ThreadSleep.sleep();
        User32.INSTANCE.PostMessage(hWnd, WM_LBUTTONUP, null, null);
    }

    public void printChilds(User32.HWND hWnd) {
        User32.INSTANCE.EnumChildWindows(hWnd, (hWndChild, dataChild) -> {
            System.out.println(hWndChild + " - " + getWindowText(hWndChild));
            return true;
        }, null);
    }

    protected boolean activate(User32.HWND hWnd) {
        User32.INSTANCE.ShowWindow(hWnd, 1);
        User32.INSTANCE.SetForegroundWindow(hWnd);

        try { // kerran tuli seuraavalla rivill� null pointer exception, en tied� miksi. siksi nyt try catch
            while (!(User32.INSTANCE.IsWindowVisible(hWnd) && User32.INSTANCE.GetForegroundWindow().equals(hWnd))) {
                System.out.print(".");
                if (failCounter > failTop) {
                    failCounter = 0;
                    return false;
                }
                ThreadSleep.sleep();
                User32.INSTANCE.ShowWindow(hWnd, 1);
                User32.INSTANCE.SetForegroundWindow(hWnd);
                failCounter++;
            }
            failCounter = 0;
            return true;
        } catch (NullPointerException e) {
            System.out.println("activate, " + e);
            return false;
        }
    }

    protected void closePrinterWindows(String printer) {
        for (int i = 0; i < failTop; i++) {
            List<User32.HWND> hWnds;
            hWnds = findAll(null, "Ominaisuudet: " + printer);
            for (User32.HWND h : hWnds) {
                closeWindow(h);
            }
            hWnds = findAll(null, "Tulostusasetukset");
            for (User32.HWND h : hWnds) {
                closeWindow(h);
            }
            hWnds = findAll(null, "Tulosta");
            for (User32.HWND h : hWnds) {
                closeWindow(h);
            }
        }
    }

    private void testClickChilds(User32.HWND hWnd) {
        for (User32.HWND h : getChilds(hWnd)) {
            System.out.println(h + " - " + getWindowText(h));
            ThreadSleep.sleep();
            ThreadSleep.sleep();
            ThreadSleep.sleep();
            ThreadSleep.sleep();
            click(h);
            ThreadSleep.sleep();
            ThreadSleep.sleep();
            ThreadSleep.sleep();
            ThreadSleep.sleep();
        }
    }

    private void keydown(User32.HWND hWnd, WinUser.INPUT input, char c) {
        input.input.ki.wVk = new WinDef.WORD(c);
        input.input.ki.dwFlags = new WinDef.DWORD(0);  // keydown
        User32.INSTANCE.SendInput(new WinDef.DWORD(1), (WinUser.INPUT[]) input.toArray(1), input.size());
    }

    private void keyup(User32.HWND hWnd, WinUser.INPUT input, char c) {
        input.input.ki.wVk = new WinDef.WORD(c);
        input.input.ki.dwFlags = new WinDef.DWORD(2);  // keyup
        User32.INSTANCE.SendInput(new WinDef.DWORD(1), (WinUser.INPUT[]) input.toArray(1), input.size());
    }

    private User32.HWND findChild(User32.HWND hWnd, String target) {
        User32.HWND[] hWnds = new User32.HWND[1];
        User32.INSTANCE.EnumChildWindows(hWnd, (hWndChild, dataChild) -> {
            if (getWindowText(hWndChild).startsWith(target)) {
                hWnds[0] = hWndChild;
                return false; // Found
            }
            return true; // Keep searching
        }, null);
        return hWnds[0];
    }

    private List<User32.HWND> findAll(User32.HWND hWnd, String target) {
        List<User32.HWND> hWnds = new ArrayList<>();
        User32.INSTANCE.EnumChildWindows(hWnd, (hWndChild, dataChild) -> {
            if (getWindowText(hWndChild).startsWith(target)) {
                hWnds.add(hWndChild);
            }
            return true; // Keep searching
        }, null);
        return hWnds;
    }
}
