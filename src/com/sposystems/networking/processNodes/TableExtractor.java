package com.sposystems.networking.processNodes;
/*---------------------------------------------------------------*
 * Class Name: TableExtractor
 * Version: 1.0
 * Author: Scott O'Connor
 * Date: 2/12/2014
 *
 * Description: Given the table content from HttpDataMiner,
 * the extractor will remove the excess scripting. 
 *
 *---------------------------------------------------------------*/

public class TableExtractor {

    String[] inputString;
    String[] cleanArray = new String[100];
    String lineAdjust, garbage;

    TableExtractor(String[] in) {
        inputString = in;
        for (int x = 0; x < 100; x++) {
            if (inputString[x] == null) {
                break;
            }
            if (!inputString.equals("")) {
                cleanArray[x] = (lineCleaner(inputString[x]));
                //System.out.println(x + ": "+cleanArray[x]);
            }
        }
    }

    private String lineCleaner(String toBeCleaned) {
        lineAdjust = toBeCleaned;
        while (lineAdjust.contains("<")) {
            int x = lineAdjust.indexOf("<");
            int y = lineAdjust.indexOf(">");
            garbage = lineAdjust.substring(x, y + 1);
            if (garbage.startsWith("<tr")) {
                lineAdjust = lineAdjust.replace(garbage, "\n");
            } else if (garbage.startsWith("<td")) {
                //System.out.println(garbage);
                lineAdjust = lineAdjust.replace(garbage, ",");
            } else {
                lineAdjust = lineAdjust.replace(garbage, "");
            }
        }
        return lineAdjust;
    }

    public String[] returner() {
        return cleanArray;
    } 
}
