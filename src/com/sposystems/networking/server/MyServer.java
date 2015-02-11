package com.sposystems.networking.server;
/*---------------------------------------------------------------*
* Class Name: 
* Version: 1.0
* Author: Scott O'Connor
* Date: 1/30/2015
*
* Description: 
*
*---------------------------------------------------------------*/

import com.sposystems.networking.processNodes.*;
import java.io.*;
import java.net.*;



class MyServer {

    public static void main(String args[]) throws Exception {
		System.out.println("Server is now running.");
		System.out.println("Waiting for client commands...");
        ServerSocket ss = new ServerSocket(2250);
        Socket s = ss.accept();
        DataInputStream din = new DataInputStream(s.getInputStream());
        DataOutputStream dout = new DataOutputStream(s.getOutputStream());
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
        String str = "", str2 = "";
        while (!str.equals("stop")) {
            str = din.readUTF();
            System.out.println("client says: " + str);
			switch(str){
				case "ackermann": 
					System.out.println("Ackermann function requested.");
					dout.writeUTF("Enter values m,n like 1,2");
					dout.flush();
					str2 = din.readUTF();
					str2 = doAckermann(str2);
					break;
				case "matrix": 
					System.out.println("Matrix function requested.");
					str2 = MatrixMultiplier.runMultiplier();
					break;
				case "pi": 
					System.out.println("Pi function requested.");
					str2 = "You have selected the Pi Function";
					break;
				case "nhl": 
					System.out.println("Hockey function requested.");
					str2 = nhlDriver.ShowScores();
					break;
					
				default:
					str2 = "\nThe function requested is not a node.\nThe nodes are: ackermann, matrix, pi, nhl.";
					break;
					
			}
            
            dout.writeUTF(str2);
            dout.flush();
			
        }
        din.close();
        s.close();
        ss.close();
    }
	private static String doAckermann(String input){
		//must dissolve input into M and N
		System.out.println("inside the function");
		int M = 1;
		int N = 0;
		try{
			int comma = input.indexOf(","); //find the location of the comma
			String numberString = input.substring(0, comma);
			M = Integer.parseInt(numberString.trim());
			numberString = input.substring((comma+1), input.length());
			N = Integer.parseInt(numberString.trim());
			System.out.println("M and n: " + M + "," + N);
		}catch(Exception e){
			System.out.println("Error farming the inputs: "+e);
			return "Error farming the inputs: "+e;
		}
		return Ackermann.processAckermann(M,N);
	}

}

