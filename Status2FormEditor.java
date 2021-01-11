/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.formautomaton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 *
 * @author
 */
public class Status2FormEditor {

    WebDriver driver;
    WebDriverWait wait;
    Map<String, String> content;
    WebElement element;

    public Status2FormEditor(WebDriver d, WebDriverWait w) {
        driver = d;
        wait = w;
    }

    public void edit(Map<String, String> contentMap) {
        content = contentMap;
//        process("certificateType", "ctl00_mainContents__formFields_RowID0", "radiobutton");
//        process("formPlannedDeclarationPlace", "ctl00_mainContents__formFields_ctl17_CtrlCatFormPlannedDeclarationPlace", "dropdown");
//        process("plannedDeclarationPlace", "ctl00_mainContents__formFields_RowID2", "input");
//        process("plannedDeclarationDate", "ctl00_mainContents__formFields_RowID3", "input");
//        process("coverLetter", "ctl00_mainContents__formFields_RowID4", "textarea");
//        process("senderCustomer", "ctl00_mainContents__formFields_RowID5", "inputSearchSelectVerify", "ctl00_mainContents__formFields_ctl45_CtrlSenderCustomer_txtSearch");
//        process("customerInfo", "ctl00_mainContents__formFields_RowID6", "textarea");
//        process("consignee", "ctl00_mainContents__formFields_RowID10", "textarea");
//        process("destinationCountryName", "ctl00_mainContents__formFields_ctl85_CtrlDestinationCountryISO_Countries", "dropdown");
//        process("destinationCountryCertName", "ctl00_mainContents__formFields_ctl85_CtrlDestinationCountryISO_txtCertName");
//        process("originCountryName", "ctl00_mainContents__formFields_ctl104_CtrlOriginCountryISO_Countries", "dropdown");
//        process("originCountryCertName", "ctl00_mainContents__formFields_ctl104_CtrlOriginCountryISO_txtCertName");
//        process("conveyanceMeans", "ctl00_mainContents__formFields_RowID17", "input");
//        process("entryPoint", "ctl00_mainContents__formFields_RowID18", "input");
//        process("productInfo", "ctl00_mainContents__formFields_RowID19", "textarea");
//        process("productAmount", "ctl00_mainContents__formFields_RowID20", "textarea");
//        process("additionalInfo", "ctl00_mainContents__formFields_RowID21", "textarea");
//        process("phraseReply", "ctl00_mainContents__formFields_RowID22", "selectVerify", "ctl00_mainContents__formFields_ctl160_CtrlPhrase_btnShowAll");
//        process("phraseReplyText", "ctl00_mainContents__formFields_RowID22", "textarea no clear");
//        process("treatment", "ctl00_mainContents__formFields_RowID23", "textarea");
//        process("chemical", "ctl00_mainContents__formFields_RowID24", "input");
//        process("duration", "ctl00_mainContents__formFields_RowID25", "input");
//        process("concentration", "ctl00_mainContents__formFields_RowID26", "input");
//        process("treatmentDate", "ctl00_mainContents__formFields_RowID27", "input");
//        process("treatmentInfo", "ctl00_mainContents__formFields_RowID28", "textarea");
//        process("exportDate", "ctl00_mainContents__formFields_RowID29", "input");
//        process("formInspector", "ctl00_mainContents__formFields_ctl216_CtrlCatFormInspector", "dropdown");
//        process("producer", "ctl00_mainContents__formFields_RowID43", "textarea");
//        process("logisticsInfo", "ctl00_mainContents__formFields_RowID44", "textarea");
//        process("billingCustomer", "ctl00_mainContents__formFields_RowID45", "inputSearchSelectVerify", "ctl00_mainContents__formFields_ctl318_CtrlBillingCustomer_txtSearch");
//        process("billingCustomerDetails", "ctl00_mainContents__formFields_RowID46", "textarea");
//        process("customerContactPerson", "ctl00_mainContents__formFields_RowID47", "input");
//        process("billingReference", "ctl00_mainContents__formFields_RowID48", "input");
//        process("vessel", "ctl00_mainContents__formFields_RowID49", "input");
        process("exportProducts", "", "exportTable");

//        process("additionalInformation", "ctl00_mainContents__formFields_RowID102");
//        process("statusNumber", "ctl00_mainContents__formFields_RowID52");
//        process("status1UserName", "ctl00_mainContents__formFields_RowID55");
//        process("status1Date", "ctl00_mainContents__formFields_ctl378_CtrlStatus1Date_DTLabel", "same level");
//        process("realOwnerSupplierSelection", "ctl00_mainContents__formFields_ctl699_CtrlRealOwnerSupplierSelection_lblSupplier", "same level");
//        process("formID", "ctl00_mainContents__formFields_RowID107");
//        process("createdDate", "ctl00_mainContents__formFields_ctl758_CtrlCreatedDate_DTLabel", "same level");
//        process("formID", "ctl00_mainContents__formFields_RowID115");
//        process("history", "ctl00_mainContents__formFields_ctl864_CtrlHistory_lbHistory", "options");
        ThreadSleep.sleep(5000);
        driver.close();
    }

    private void process(String key, String id) {
        process(key, id, "id");
    }

    private void process(String key, String id, String type) {
        process(key, id, type, null);
    }

    private void process(String key, String id, String type, String id2) {
        if (content.containsKey(key)) {
//            try {
            switch (type) {
                case "input":
                    processText(key, id, type, true);
                    break;
                case "dropdown":
                    processDropdown(key, id);
                    break;
                case "radiobutton":
                    processRadiobutton(key, id);
                    break;
                case "checkbox":
                    processCheckbox(key, id);
                    break;
                case "textarea":
                    processText(key, id, type, true);
                    break;
                case "inputSearchSelectVerify":
                    System.out.println(key + " - " + content.get(key) + " - " + processInputSearchSelectVerify(key, id, id2));
                    break;
                case "id":
                    processInputByID(key, id);
                    break;
                case "selectVerify":
                    System.out.println(key + " - " + content.get(key) + " - " + processClickSelectVerify(key, id, id2));
                    break;
                case "textarea no clear":
                    type = "textarea";
                    processText(key, id, type, false);
                    break;
                case "exportTable":
                    processExportTable(key);
                    break;
            }
//            } catch (NoSuchElementException e) {
//                System.out.println("NoSuchElementException: " + key + ": " + content.get(key));
//            }
        }
    }

    private void processExportTable(String key) {
        String id = "ctl00_mainContents__formFields_RowID50";
        String id2 = "ctl00_mainContents__formFields_ctl352_CtrlCatFormSelExportProduct_tlbInsert";
        String id3 = "ctl00_mainContents__formFields_ctl352_CtrlCatFormSelExportProduct_PanicGrid";
        String id4 = "ctl00_mainContents__formFields_ctl352_CtrlCatFormSelExportProduct_tlbDelete";
        String id5 = "ctl00_mainContents__formFields_ctl352_CtrlCatFormSelExportProduct_LblProduct";
        if (!clearExportTable(id, id3, id4)) {
            return;
        }
        List<Map<String, String>> products = parseExportTableAttributes(key);
        insertExportProducts(id, id2, id3, id5, products);

//        for (Map<String, String> product : products) {
//            element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//tr[@id='" + id + "']//td[text()='" + products.get(0).get("name") + "']")));
//            element.click();
//            element = wait.until(ExpectedConditions.elementToBeClickable(By.id(id2)));
//            element.click();
//        }
    }

    private boolean checkIfContains(String id, String text) {
        int counter = 10;
        while (true) {
            String elementText = wait.until(ExpectedConditions.elementToBeClickable(By.id(id))).getText();
            if (elementText.contains(text)) {
                return true;
            }
            if (counter == 0) {
                return false;
            }
            ThreadSleep.sleep(1000);
            counter--;
        }
    }

    private boolean insertExportProduct(String id, String id2, String id3, String id5, Map<String, String> product, int position) {
        element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//tr[@id='" + id + "']//td[text()='" + product.get("name") + "']")));
        element.click();
        if (!checkIfContains(id5, product.get("name"))) {
            return false;
        }
        element = wait.until(ExpectedConditions.elementToBeClickable(By.id(id2)));
        element.click();
        wait.until(ExpectedConditions.elementToBeClickable(By.id(id3)));
        element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//table[@id='" + id3 + "']//tbody//tr[" + position + "]//td[1]")));
        if (element.getText().equals(product.get("shortName"))) {
            return true;
        }
        return false;
    }

    private boolean insertExportProduct0(String id, String id2, String id3, String id5, Map<String, String> product) {
        element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//tr[@id='" + id + "']//td[text()='" + product.get("name") + "']")));
        element.click();
        if (!checkIfContains(id5, product.get("name"))) {
            return false;
        }
        element = wait.until(ExpectedConditions.elementToBeClickable(By.id(id2)));
        element.click();
        wait.until(ExpectedConditions.elementToBeClickable(By.id(id3)));
        element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//table[@id='" + id3 + "']//tbody//tr[2]//td[1]")));
        if (element.getText().equals(product.get("shortName"))) {
            return true;
        }
        return false;
    }

    private boolean insertExportProducts(String id, String id2, String id3, String id5, List<Map<String, String>> products) {
        if (!insertExportProduct0(id, id2, id3, id5, products.get(0))) {
            return false;
        }
        for (int i = 1; i < products.size(); i++) {
            if (!insertExportProduct(id, id2, id3, id5, products.get(i), i + 2)) {
                return false;
            }
        }
        return true;
    }

    private void clickExportTableRemoveProduct(String id4) {
        wait.until(ExpectedConditions.elementToBeClickable(By.id(id4))).click();
    }

    private boolean clickExportTableGridProduct(String id, String id3) {
        List<WebElement> elements = wait.until(ExpectedConditions.elementToBeClickable(By.id(id))).findElements(By.id(id3));
        if (elements.size() != 0) {
            elements = wait.until(ExpectedConditions.elementToBeClickable(By.id(id3))).findElements(By.tagName("tr"));
            for (WebElement e : elements) {
                if (e.getAttribute("onclick") != null) {
                    e.click();
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkIfExportTableGridProductSelected(String id, String id3) {
        int counter = 5;
        while (counter > 0) {
            List<WebElement> elements = wait.until(ExpectedConditions.elementToBeClickable(By.id(id))).findElements(By.id(id3));
            if (elements.size() != 0) {
                elements = wait.until(ExpectedConditions.elementToBeClickable(By.id(id3))).findElements(By.tagName("tr"));
                for (WebElement ee : elements) {
                    String style = ee.getAttribute("style");
                    if (style != null) {
                        if (style.toLowerCase().contains("grey")) {
                            return true;
                        }
                    }
                }
            }
            counter--;
            ThreadSleep.sleep(1000);
        }
        return false;
    }

    private boolean clearExportTable(String id, String id3, String id4) {
        while (clickExportTableGridProduct(id, id3)) {
            if (checkIfExportTableGridProductSelected(id, id3)) {
                clickExportTableRemoveProduct(id4);
                return true;
            } else {
                return false;
            }
        }
        return true;
    }

    private List<Map<String, String>> parseExportTableAttributes(String key) {
        String[] attributes = content.get(key).split(";");
        int elements = 9;
        int exportProducts = (attributes.length - 2) / elements;
        List<Map<String, String>> maps = new ArrayList<>(exportProducts + 1);
        Map<String, String> map = new HashMap<>(2);
        map.put("name", attributes[0]);
        map.put("shortName", attributes[1]);
        maps.add(map);
        for (int i = 0; i < exportProducts; i++) {
            map = new HashMap<>(elements);
            map.put("name", attributes[elements * i + 2]);
            map.put("shortName", attributes[elements * i + 3]);
            map.put("amount", attributes[elements * i + 4]);
            map.put("unit", attributes[elements * i + 5]);
            map.put("country", attributes[elements * i + 6]);
            map.put("inspection", attributes[elements * i + 7]);
            map.put("billingAmount", attributes[elements * i + 8]);
            map.put("billingUnit", attributes[elements * i + 9]);
            map.put("batch", attributes[elements * i + 10]);
            maps.add(map);
        }
        return maps;
    }

    private boolean processClickSelectVerify(String key, String id, String id2) {
        element = wait.until(ExpectedConditions.elementToBeClickable(By.id(id))).findElement(By.tagName("textarea"));
        element.clear();
        if (content.get(key).equals("")) {
            return true;
        }
        element = wait.until(ExpectedConditions.elementToBeClickable(By.id(id2)));
        element.click();
        int counter = 10;
        while (true) {
            if (counter == 0) {
                return false;
            }
            if (!driver.findElements(By.xpath("//tr[@id='" + id + "']//td[@title='" + content.get(key) + "']")).isEmpty()) {
                break;
            }
            counter--;
            ThreadSleep.sleep(1000);
        }
        element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//tr[@id='" + id + "']//td[@title='" + content.get(key) + "']")));
        String s = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//tr[@id='" + id + "']//td[@title='" + content.get(key) + "']")))
                .findElement(By.xpath("../td[2]")).getAttribute("title");
        element.click();
        counter = 10;
        while (true) {
            if (counter == 0) {
                return false;
            }
            if (wait.until(ExpectedConditions.elementToBeClickable(By.id(id))).findElement(By.tagName("textarea")).getText().equals(s)) {
                break;
            }
            counter--;
            ThreadSleep.sleep(1000);
        }
        return true;
    }

    private void processInputByID(String key, String id) {
        element = wait.until(ExpectedConditions.elementToBeClickable(By.id(id)));
        element.clear();
        element.sendKeys(content.get(key));
    }

    private boolean processInputSearchSelectVerify(String key, String id, String inputID) {
        element = wait.until(ExpectedConditions.elementToBeClickable(By.id(inputID)));
        element.clear();
        element.sendKeys(content.get(key));
        element = wait.until(ExpectedConditions.elementToBeClickable(By.id(id))).findElement(By.className("BlueButton"));
        element.click();
        int counter = 10;
        while (true) {
            if (counter == 0) {
                return false;
            }
            if (!driver.findElement(By.id(id)).findElements(By.tagName("option")).isEmpty()) {
                break;
            }
            counter--;
            ThreadSleep.sleep(1000);
        }
        element = driver.findElement(By.id(id)).findElement(By.tagName("option"));
        String tempText = wait.until(ExpectedConditions.elementToBeClickable(By.id(id))).findElement(By.className("FormFieldReadOnly")).getText();
        element.click();
        counter = 10;
        while (true) {
            if (counter == 0) {
                break;
            }
            if (!wait.until(ExpectedConditions.elementToBeClickable(By.id(id))).findElement(By.className("FormFieldReadOnly")).getText().equals(tempText)) {
                break;
            }
            counter--;
            ThreadSleep.sleep(1000);
        }
        element = wait.until(ExpectedConditions.elementToBeClickable(By.id(id))).findElement(By.className("FormFieldReadOnly"));
        if (element.getText().toLowerCase().contains(content.get(key).toLowerCase())) {
            return true;
        } else {
            return false;
        }
    }

    private void processText(String key, String id, String type, boolean clear) {
        element = wait.until(ExpectedConditions.elementToBeClickable(By.id(id))).findElement(By.tagName(type));
        if (clear) {
            element.clear();
        }
        element.sendKeys(content.get(key));
    }

    private void processDropdown(String key, String id) {
        new Select(wait.until(ExpectedConditions.elementToBeClickable(By.id(id)))).selectByVisibleText(content.get(key));
    }

    private void processRadiobutton(String key, String id) {
        List<WebElement> elements = wait.until(ExpectedConditions.elementToBeClickable(By.id(id))).findElements(By.tagName("input"));
        for (WebElement e : elements) {
            if (e.getAttribute("value").equals(content.get(key))) {
                e.click();
                break;
            }
        }
    }

    private void processCheckbox(String key, String id) {

    }
}
