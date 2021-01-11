/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.formautomaton;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author 
 */
public class Form {
    String certificateType;
    String formPlannedDeclarationPlace;
    String plannedDeclarationPlace;
    String plannedDeclarationDate;
    String coverLetter;
    String senderCustomer;
    String customerInfo;
    String consignee;
    String destinationCountryName;
    String destinationCountryCertName;
    String originCountryName;
    String originCountryCertName;
    String conveyanceMeans;
    String entryPoint;
    String productInfo;
    String productAmount;
    String additionalInfo;
    String phrasePhrases;
    String phraseReply;
    String treatment;
    String chemical;
    String duration;
    String concentration;
    String treatmentDate;
    String treatmentInfo;
    String exportDate;
    String formInspector;
    String producer;
    String logisticsInfo;
    String billingCustomer;
    String billingCustomerDetails;
    String customerContactPerson;
    String billingReference;
    String vessel;
    String exportProduct;
    String additionalInformation;
    int status;
    Map<String, String> stringVariables;
    
    public Form(){
        stringVariables = new HashMap<>();
    }
    
    public void insert(String variable, String value){
        
    }
    
}


