package sudoku.model;

import java.io.*;
import java.util.Scanner;


public class ImportarFicheros {
	
	private int [][] casillas= new int [9][9];
	
	
   public static void main(String [] arg) {
     
	   File f =new File("Sudokus.txt");
     
	   
	   try {
		Scanner sc = new Scanner(f);
		
		
	   int num;
	   
	   
	   while(sc.hasNext()) {
		   
	  
		   num=sc.nextInt();
		   System.out.println(num);
	   }
	     
	   } 
	    catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
   }
}