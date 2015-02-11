package com.sposystems.networking.processNodes;

public class MatrixMultiplier {

	
	public static Double[][] multiply(Double[][]A, Double[][] B){
		int aRows = A.length;
		int aColumns = A[0].length;
		int bRows = B.length;
		int bColumns = B[0].length;
		if (aColumns != bRows){
			throw new IllegalArgumentException("A Rows must be the same size as B columns");
		}
		Double[][] C = new Double[aRows][bColumns];
		for(int x=0;x<aRows;x++){
			for (int y=0; y<bColumns;y++){
				C[x][y] = 0.000000;
			}
		}
		
		for(int x=0; x<aRows;x++){
			for (int y=0;y<bColumns;y++){
				for(int z=0;z<aColumns;z++){
					C[x][y]+=A[x][z]*B[z][y];
				}
			}
		}
		return C;
	}
	
	public static String runMultiplier(){
		String returnString = "";
		MatrixMultiplier m = new MatrixMultiplier();
		Double[][] A = { { 4.00, 3.00, 5.00}, { 2.00, 1.00, -7.00 }, {4.00, 1.5, -3.00} };
		Double[][] B = { { -0.500, 1.500, 7.5 }, { 1.000, -2.0000, 6.5 }, {4.00, 1.5, -3.00} };
		Double[][] answer = m.multiply(A,B);
		for(int i=0;i<3;i++){
			for(int j=0;j<3;j++){
				returnString += answer[i][j]+" ";
			}
		}
		return returnString;
	}
}
