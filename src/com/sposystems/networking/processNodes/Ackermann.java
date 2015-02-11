package com.sposystems.networking.processNodes;
 
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Mark
 */
public class Ackermann {
    
       public static long ackermann(long m, long n) {
	  
      if (m == 0) {
          return n + 1;
      }
      if (n == 0) {
          return ackermann(m - 1, 1);
      }
      return ackermann(m - 1, ackermann(m, n - 1));
   }

   public static void main(String[] args) {
      long M = 2;
      long N = 3;
      System.out.println(ackermann(M, N));
   }
    public static String processAckermann(int M, int N){
		String results = "Ackermann (" + M + ", "+ N +   ") = " + ackermann((long)M, (long)N);
		return results;
	}
}
