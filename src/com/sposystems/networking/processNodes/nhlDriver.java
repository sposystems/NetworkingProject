package com.sposystems.networking.processNodes;
/*---------------------------------------------------------------*
* Driver Name: nhlDriver
* Version: 1.0
* Author: Scott O'Connor
* Date: 2/11/2014
*
* Description: Use the NHL Stats to make predictions 
*   about upcomming hockey games.
*
*---------------------------------------------------------------*/
import com.sposystems.networking.processNodes.*;
public class nhlDriver {
    public static String ShowScores() {
        String[] myArray;
        HttpDataMiner myInput = new HttpDataMiner("http://www.nhl.com/ice/standings.htm");
        myArray = myInput.getNewArray();
        
        //System.out.println(myArray[7]);
		//Please note the owners of this page tend to move contents around
		//If you aren't getting the scores, loop through the first few elements to 
		// find them again.
		return (myArray[7]);
    }
}
