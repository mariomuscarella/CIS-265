//*******************************************
// CIS 265 Fall, 2015 ‐  Section 50     
// Homework #1 – Merge Two sorted List    
// NAME: Mario Muscarella                     
// CSU ID: 2478702             
// DATE & TIME: 9/9 3am
//**************************************

import java.util.Scanner;
import java.util.Arrays;

public class merge {

	public static int[] merge(int[] list1, int[] list2) {
		
		// Define null list of length of list1 and list 2
		int l3 = list1.length +  list2.length;
		
		// Create list3 length of of combined lists
	    int[] list3 = new int [l3];
	    
	    // Loop to add each integer of list1 to list3
	    int i = 0;
	    // Starts at first cell of list3
	    while (i < list1.length){
	            list3[i] = list1[i];
	            i++;
	        }
	    
	    // Loop to add each integer of list 2 to list3
	    int j = 0;
	    // Starts at cell after final list1 integer in list3
	    while (j < list2.length){
	            list3[j+list1.length] = list2[j];
	            j++;
	        }
	    
	    // Sort list3 numerically
	    Arrays.sort(list3);
	    
	    // Returns list3 in full for use below
	    return list3;
	    
	}
	


	public static void main(String[] args) {
		java.util.Scanner input = new java.util.Scanner(System.in);
	
		// Enter values for list1
		System.out.print("Enter list1: ");
		int size1 = input.nextInt();
		// First integer defines size of list1
		int[] list1 = new int[size1];
		
		// Loop to continue creating list1
		for (int i = 0; i < list1.length; i++) {
			list1[i] = input.nextInt(); 
		}
	
		// Enter values for list2
		System.out.print("Enter list2: ");
		int size2 = input.nextInt();
		// First integer defines size of list2
		int[] list2 = new int[size2];
		
		// Loop to continue creating list2
		for (int i = 0; i < list2.length; i++) {
			list2[i] = input.nextInt();
		}
		
		// Combine list1 and list2 and print
		int[] list3 = merge(list1, list2);
		// Print each variable of list3
		for (int i=0; i<list3.length; i++){
		    System.out.print(list3[i] + " ");
		}
	}
}