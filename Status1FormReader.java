/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.formautomaton;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 *
 * @author
 */
public class Status1FormReader {

    WebDriver driver;
    WebDriverWait wait;

    public Status1FormReader(WebDriver d, WebDriverWait w) {
        driver = d;
        wait = w;
    }

    public Map<String, String> read() {
        Map<String, String> content = new HashMap<>();
        content.put("certificateType", getText("ctl00_mainContents__formFields_RowID0"));
        content.put("formPlannedDeclarationPlace", getText("ctl00_mainContents__formFields_ctl17_CtrlCatFormPlannedDeclarationPlace", "dropdown", "ctl00_mainContents__formFields_RowID1"));
        content.put("plannedDeclarationPlace", getText("ctl00_mainContents__formFields_RowID2"));
        content.put("plannedDeclarationDate", getText("ctl00_mainContents__formFields_RowID3"));
        content.put("coverLetter", getText("ctl00_mainContents__formFields_RowID4"));
        content.put("senderCustomer", getText("ctl00_mainContents__formFields_RowID5"));
        content.put("customerInfo", getText("ctl00_mainContents__formFields_RowID6"));
        content.put("consignee", getText("ctl00_mainContents__formFields_RowID10"));
        content.put("destinationCountryName", getText("ctl00_mainContents__formFields_ctl85_CtrlDestinationCountryISO_lblCountries", "same level"));
        content.put("destinationCountryCertName", getText("ctl00_mainContents__formFields_ctl85_CtrlDestinationCountryISO_lblCertName", "same level"));
        content.put("originCountryName", getText("ctl00_mainContents__formFields_ctl104_CtrlOriginCountryISO_lblCountries", "same level"));
        content.put("originCountryCertName", getText("ctl00_mainContents__formFields_ctl104_CtrlOriginCountryISO_lblCertName", "same level"));
        content.put("conveyanceMeans", getText("ctl00_mainContents__formFields_RowID17"));
        content.put("entryPoint", getText("ctl00_mainContents__formFields_RowID18"));
        content.put("productInfo", getText("ctl00_mainContents__formFields_RowID19"));
        content.put("productAmount", getText("ctl00_mainContents__formFields_RowID20"));
        content.put("additionalInfo", getText("ctl00_mainContents__formFields_RowID21"));
        content.put("phraseReply", getText("ctl00_mainContents__formFields_ctl160_CtrlPhrase_ReplyExternalLB", "title wait", "ctl00_mainContents__formFields_ctl160_CtrlPhrase_TransLabel1"));
        content.put("treatment", getText("ctl00_mainContents__formFields_RowID23"));
        content.put("chemical", getText("ctl00_mainContents__formFields_RowID24"));
        content.put("duration", getText("ctl00_mainContents__formFields_RowID25"));
        content.put("concentration", getText("ctl00_mainContents__formFields_RowID26"));
        content.put("treatmentDate", getText("ctl00_mainContents__formFields_RowID27"));
        content.put("treatmentInfo", getText("ctl00_mainContents__formFields_RowID28"));
        content.put("exportDate", getText("ctl00_mainContents__formFields_ctl209_CtrlExportDate_DTLabel", "title wait", "ctl00_mainContents__formFields_RowID29"));
        content.put("formInspector", getText("ctl00_mainContents__formFields_ctl216_CtrlCatFormInspector", "dropdown", "ctl00_mainContents__formFields_RowID30"));
        content.put("producer", getText("ctl00_mainContents__formFields_RowID43"));
        content.put("logisticsInfo", getText("ctl00_mainContents__formFields_RowID44"));
        content.put("billingCustomer", getText("ctl00_mainContents__formFields_RowID45"));
        content.put("billingCustomerDetails", getText("ctl00_mainContents__formFields_RowID46"));
        content.put("customerContactPerson", getText("ctl00_mainContents__formFields_RowID47"));
        content.put("billingReference", getText("ctl00_mainContents__formFields_RowID48"));
        content.put("vessel", getText("ctl00_mainContents__formFields_RowID49"));
        content.put("additionalInformation", getText("ctl00_mainContents__formFields_RowID102"));
        content.put("statusNumber", getText("ctl00_mainContents__formFields_RowID52"));
        content.put("status1UserName", getText("ctl00_mainContents__formFields_RowID55"));
        content.put("status1Date", getText("ctl00_mainContents__formFields_ctl378_CtrlStatus1Date_DTLabel", "same level"));
        content.put("realOwnerSupplierSelection", getText("ctl00_mainContents__formFields_ctl699_CtrlRealOwnerSupplierSelection_lblSupplier", "same level"));
        content.put("formID", getText("ctl00_mainContents__formFields_RowID107"));
        content.put("createdDate", getText("ctl00_mainContents__formFields_ctl758_CtrlCreatedDate_DTLabel", "same level"));
        content.put("formID", getText("ctl00_mainContents__formFields_RowID115"));
        content.put("history", getText("ctl00_mainContents__formFields_ctl864_CtrlHistory_lbHistory", "options"));
        content.put("status1", getText("ctl00_mainContents__formFields_RowID53", "input"));
        content.put("status2", getText("ctl00_mainContents__formFields_RowID58", "input"));
        content.put("status8", getText("ctl00_mainContents__formFields_RowID84", "input"));
        retrieveExportProducts(content, "ctl00_mainContents__formFields_RowID50");

        for (String s : content.keySet()) {
            System.out.println(s);
            String value = content.get(s);
            if (value.equals("")) {
                value = "----------empty------------";
            }
            System.out.println(value);
            System.out.println("");
        }
        driver.close();
        return content;
    }

    private void retrieveExportProducts(Map<String, String> content, String id) {
        wait.until(ExpectedConditions.elementToBeClickable(By.id(id)));
        int rows = driver.findElements(By.xpath("//*[@id='ctl00_mainContents__formFields_ctl352_CtrlCatFormSelExportProduct_PanicGrid']//tr")).size();
        int columns = driver.findElements(By.xpath("//*[@id='ctl00_mainContents__formFields_ctl352_CtrlCatFormSelExportProduct_PanicGrid']//tr/th")).size();
        List<WebElement> titles = driver.findElements(By.xpath("//*[@id='ctl00_mainContents__formFields_ctl352_CtrlCatFormSelExportProduct_PanicGrid']//tr/th"));
        for (int i = 1; i < rows; i++) {
            for (int ii = 0; ii < columns; ii++) {
                int row = i + 1;
                int column = ii + 1;
                String key = "exportProduct " + i + ": " + titles.get(ii).getText();
                String value;
                String path = "//*[@id='ctl00_mainContents__formFields_ctl352_CtrlCatFormSelExportProduct_PanicGrid']//tr[" + row + "]/td[" + column + "]";
                if (titles.get(ii).getText().equals("Tarkastus") || titles.get(ii).getText().equals("Laskutettu")) {
                    value = driver.findElement(By.xpath(path + "/span/input")).getAttribute("checked");
                } else {
                    value = driver.findElement(By.xpath(path)).getText();
                }
                content.put(key, NullToFalse.doIt(value).trim());
            }
        }
    }

    private String getText(String id, String type, String id2) {
        wait.until(ExpectedConditions.elementToBeClickable(By.id(id2)));
        switch (type) {
            case "title wait":
                return getText(id, "same level no wait");

            case "dropdown":
                return new Select(driver.findElement(By.id(id))).getFirstSelectedOption().getAttribute("textContent").trim();
        }
        return null;
    }

    private String getText(String id, String type) {
        switch (type) {
            case "same level":
                return wait.until(ExpectedConditions.elementToBeClickable(By.id(id))).getText().trim();
            case "same level no wait":
                return driver.findElement(By.id(id)).getText().trim();
            case "options": {
                List<WebElement> elements = wait.until(ExpectedConditions.elementToBeClickable(By.id(id))).findElements(By.tagName("option"));
                StringBuilder sb = new StringBuilder();
                for (WebElement e : elements) {
                    sb.append(e.getAttribute("value"));
                    sb.append(" ");
                    sb.append(e.getText());
                    sb.append(System.lineSeparator());
                }
                return sb.toString();
            }
            case "input":
                wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='" + id + "']")));
                String value = driver.findElement(By.xpath("//*[@id='" + id + "']//input")).getAttribute("selected");
                return NullToFalse.doIt(value);
        }
        return null;
    }

    private String getText(String id) {
        return wait.until(ExpectedConditions.elementToBeClickable(By.id(id))).findElement(By.className("FormFieldReadOnly")).getText().trim();
    }
}
