package com.sposystems.networking.processNodes;

/*---------------------------------------------------------------*
 * Class Name: HTTPDataMiner
 * Version: 1.0
 * Author: Scott O'Connor
 * Date: 2/11/2014
 *
 * Description: Get input from a website
 *
 *---------------------------------------------------------------*/

import java.net.*;
import java.io.*;

public class HttpDataMiner {

    String address, cleanInput;
    boolean recording = false;
    String[] cleanArray, dataCap = new String[100];

    HttpDataMiner(String site) {
        address = site;

        int index = 0;
        if (address.startsWith("http://www.") | address.startsWith("https://www.")) {
            //http://docs.oracle.com/javase/tutorial/networking/urls/readingURL.html
            try {
                URL mySite = new URL(address);
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(mySite.openStream()));
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    cleanInput = inputLine.trim();
                    //if contains td, start recording until finding closing 
                    if (index < 100 && cleanInput.contains("<td")) {
                        recording = true;
                        dataCap[index] = cleanInput;
                        index++;
                        //System.out.println(cleanInput);
                        if (cleanInput.contains("</td>")) {
                                recording = false;
                            }
                    } else {
                        if (recording) {
                            dataCap[index] = cleanInput;
                            index++;
                            //System.out.println(cleanInput);
                            if (cleanInput.contains("</td>")) {
                                recording = false;
                            }
                        }
                    }
                }
                
                in.close();
            } catch (Exception e) {
                System.out.println("Could not connect to that site: " + e);
            }
        } else {
            System.out.println("The website must begin with \"http://www.\"");
        }
    }
    public String[] getNewArray(){
        TableExtractor extract = new TableExtractor(dataCap);
        cleanArray = extract.returner();
        return cleanArray;
    }
}
