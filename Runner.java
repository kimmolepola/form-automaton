/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.formautomaton;

import com.mycompany.formautomaton.printer.PrinterWindowController;
import com.mycompany.formautomaton.printer.PrinterWindowTaskAssigner;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 *
 * @author
 */
public class Runner {

    public static boolean run() {

//        System.setProperty("webdriver.chrome.driver", "U:\\Tiedostot\\NetBeansProjects\\formautomaton\\chromedriver.exe");
        System.setProperty("webdriver.chrome.driver", "C:\\Omat\\Ohjelmat\\formautomaton\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, 30);
        driver.manage().window().maximize();

        driver.get("t�h�n nettisivu");

        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.name("ctl00$mainContents$LoginConsole$ctl03")));
        element.sendKeys("t�h�n k�ytt�j�tunnus");

        element = wait.until(ExpectedConditions.elementToBeClickable(By.name("ctl00$mainContents$LoginConsole$ctl05")));
        element.sendKeys("t�h�n salasana");

        element = wait.until(ExpectedConditions.elementToBeClickable(By.name("ctl00$mainContents$LoginConsole$LoginBtn")));
        element.click();

        element = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Vienti-ilmoitukset")));
        element.click();

        element = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("2 Keskener�iset")));
        element.click();

        element = wait.until(ExpectedConditions.elementToBeClickable(By.id("ctl00_mainContents_QueryResults1_resultsGrid_ctl00_ctl04_solu0")));
        element.click();

        element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@value='Muokkaa lomaketta']")));
        element.click();

//        Status1FormReader status1FormReader = new Status1FormReader(driver, wait);
//        Map<String, String> content = status1FormReader.read();
        Map<String, String> newContent = new HashMap<>();
        newContent.put("certificateType", "ExportCertificate_FR");
        newContent.put("formPlannedDeclarationPlace", "Ecker�");
        newContent.put("plannedDeclarationPlace", "tarkenna l�ht�- ja tarkastuspaikka");
        newContent.put("plannedDeclarationDate", "suunniteltu l�ht�aika");
        newContent.put("coverLetter", "saate eviralle asdf");
        newContent.put("senderCustomer", "ahmed");
        newContent.put("customerInfo", "viej�n nimi- ja osoitetiedot");
        newContent.put("consignee", "vastaanottajan nimi- ja osoitetiedot");
        newContent.put("destinationCountryName", "Ven�j�");
        newContent.put("destinationCountryCertName", "vastaanottajamaa");
        newContent.put("originCountryName", "Ruotsi");
        newContent.put("originCountryCertName", "alkuper�maa");
        newContent.put("conveyanceMeans", "l�hetystapa");
        newContent.put("entryPoint", "maahantulopaikka");
        newContent.put("productInfo", "vientituotteen tiedot");
        newContent.put("productAmount", "m��r�");
        newContent.put("additionalInfo", "lis�tietoja");
        newContent.put("phraseReply", "ISRAEL, turve");
        newContent.put("phraseReplyText", "lis�lausekkeet terveystodistukselle");
        newContent.put("treatment", "tuotteen k�sittely");
        newContent.put("chemical", "kemikaali");
        newContent.put("duration", "k�sittelyn kesto");
        newContent.put("concentration", "k�ytt�v�kevyys");
        newContent.put("treatmentDate", "2.4.2018");
        newContent.put("treatmentInfo", "k�sittelyn lis�tieto");
        newContent.put("exportDate", "12.4.2018");
        newContent.put("formInspector", "Kimmo Lepola");
        newContent.put("producer", "valmistajan nimi");
        newContent.put("logisticsInfo", "huolintaliikkeen nimi");
        newContent.put("billingCustomer", "evira");
        newContent.put("billingCustomerDetails", "laskutustieto");
        newContent.put("customerContactPerson", "tilaajan nimi");
        newContent.put("billingReference", "viite laskulle");
        newContent.put("vessel", "laiva tai muu tieto");
        newContent.put("exportProducts",
                "9 Terveystodistukset, Terveystodistus muu;"
                + "Terveystodistus muu;"
                + "1 Kasvit- ja kasvinosat, Siemenet;"
                + "Siemenet;"
                + "123;"
                + "kpl;"
                + "Guinea;"
                + "true;"
                + "345;"
                + ";"
                + "qwer;"
                + "7 Puu- ja sahatavara, Rakennusmateriaali;"
                + "Rakennusmateriaali;"
                + "234;"
                + "m;"
                + "Vietnam;"
                + "true;"
                + ";"
                + ";"
                + "asdf");

        Status2FormEditor status2FormEditor = new Status2FormEditor(driver, wait);
        status2FormEditor.edit(newContent);

        // return Print(driver, wait);
        return true;
    }

    private static boolean Print(WebDriver driver, WebDriverWait wait) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.name("ctl00$ctl06$ctl04")));
        element.click();

        Set<String> allWindows = driver.getWindowHandles();
        for (String s : allWindows) {
            driver.switchTo().window(s);
            if (driver.getCurrentUrl().contains("PrintForm")) {
                element = wait.until(ExpectedConditions.elementToBeClickable(By.tagName("body")));
                element.sendKeys();

                String title = driver.getTitle();
                if (title.equals("")) {
                    title = driver.getCurrentUrl();
                    if (title.startsWith("http://")) {
                        title = title.substring(7);
                    }
                }

                PrinterWindowTaskAssigner pwta = new PrinterWindowTaskAssigner();
                return pwta.print(title, true, "t�h�n printteri", 5, 4);
            }
        }
        return false;
    }
}
