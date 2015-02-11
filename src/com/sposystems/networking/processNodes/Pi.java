package com.sposystems.networking.processNodes;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi;
import java.math.BigDecimal;
import java.util.Scanner;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.BrokenBarrierException;
/**
 *
 * @author onnetz
 */
public class Pi
{

final BigDecimal FOUR = new BigDecimal("4");
BigDecimal arctan1_5, arctan1_239, pi;

final int roundingMode = BigDecimal.ROUND_HALF_EVEN;
final int digits;
Thread[] threads;
int numProcessors;
CyclicBarrier cb;

     public Pi(int digits) 
     {
         this.digits = digits;
         numProcessors = Runtime.getRuntime().availableProcessors();
        // numProcessors = 2;
     }

     public void startPi() 
     {
         threads = new Thread[numProcessors];
         cb = new CyclicBarrier(numProcessors+1, new Runnable() 
         {
             public void run() 
             {
                 finishUp();
             }
         });

         for(int a = 0; a < numProcessors; a++) {
             threads[a] = new pi.Pi.MyThreads();
             threads[a].start();
         }
         try {
             cb.await();
         } catch(InterruptedException ie) {

         } catch(BrokenBarrierException be) {

         }
     }

    public void computePi() {
        try {
        arctan1_5 = arctan(5, digits+5);
        arctan1_239 = arctan(239, digits+5);

                     cb.await();
         } catch(InterruptedException ie) {
             ie.printStackTrace();
         } catch(BrokenBarrierException be) {
             be.printStackTrace();
         }

        
    }

    public void finishUp() {
        pi = arctan1_5.multiply(FOUR).subtract(
                                  arctan1_239).multiply(FOUR);
        pi.setScale(digits,
                           BigDecimal.ROUND_HALF_UP);
    }

    public BigDecimal arctan(int inverseX,
                                    int scale)
    {
        BigDecimal result, numer, term;
        BigDecimal invX = BigDecimal.valueOf(inverseX);
        BigDecimal invX2 =
            BigDecimal.valueOf(inverseX * inverseX);

        numer = BigDecimal.ONE.divide(invX,
                                      scale, roundingMode);

        result = numer;
        int i = 1;
        do {
            numer =
                numer.divide(invX2, scale, roundingMode);
            int denom = 2 * i + 1;
            term =
                numer.divide(BigDecimal.valueOf(denom),
                             scale, roundingMode);
            if ((i % 2) != 0) {
                result = result.subtract(term);
            } else {
                result = result.add(term);
            }
            i++;
        } while (term.compareTo(BigDecimal.ZERO) != 0);
        return result;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Enter the number of digit you wish to calculate: ");
        int digits = keyboard.nextInt();
        Pi m = new Pi(digits);
        long stime = System.currentTimeMillis();
        m.startPi();
        long etime = System.currentTimeMillis();
        System.out.println((double)(etime - stime)/1000 + 
                " secs for " + digits + " digits.");
    }
	public static String runPi(){
		String returnString = "";
		
	}
    class MyThreads extends Thread {
        public void run() {
            computePi();
        }
    }

}