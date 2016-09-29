//*******************************************
// CIS 265 Fall, 2015 ‐  Section 50     
// Homework #4 – Building a map from each word to its frequency  
// NAME: Mario Muscarella                      
// CSU ID: 2478702       
// DATE & TIME: 11/2 3PM           
//**************************************

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
 
public class FindOccurance {

 
	public static void main(String[] args) throws FileNotFoundException, IOException {
 
		Scanner input = new Scanner(System.in);
	    System.out.print("Enter a Java source file: ");
	    String filename = input.nextLine();

	    File file = new File(filename);
	    if (file.exists()) {
	    	System.out.println("");
	    }
	    else {
	      System.out.println("File " + filename + " does not exist");
	    }  
	    
		BufferedReader bufferedReader = null;
		bufferedReader = new BufferedReader(new FileReader(file));
		String inputLine = null;
		
		// Create a HashMap to hold words as key and count as value
		Map<String, Integer> Map = new HashMap<>();
 
		try {
			while ((inputLine = bufferedReader.readLine()) != null) {
				String[] words = inputLine.split("[ \n\t\r.,;:!?(){}]");
 
				for (int counter = 0; counter < words.length; counter++) {
					String key = words[counter].toLowerCase(); 
					if (key.length() > 0) {
						if (Map.get(key) == null) {
							Map.put(key, 1);
							
						} else {
							int value = Map.get(key).intValue();
							value++;
							Map.put(key, value);
						}
					}
				}
			}
			
			Set<Map.Entry<String, Integer>> entrySet = Map.entrySet();
			System.out.print("hashmap: {");
			for (Map.Entry<String, Integer> entry : entrySet) {
				System.out.print(" " + entry.getKey() + "=" + entry.getValue() +",");
			}
			System.out.println("}");
			System.out.println(" ");
			// Get key and value from each entry
			for (Map.Entry<String, Integer> entry1 : entrySet) {
				System.out.println(entry1.getKey() + "\t\t" + entry1.getValue());
			}
			System.out.println(" ");
			
			// Create a TreeMap to hold words as key and count as value
			Map<String, Integer> treeMap = new TreeMap<String, Integer>(Map);
			System.out.print("hashmap: {");
			for (Entry<String, Integer> entry : treeMap.entrySet()) {
				System.out.print(" " + entry.getKey() + "=" + entry.getValue() +",");
			}
			System.out.println("}");
			System.out.println(" ");
			// Get key and value from each entry
			for (Entry<String, Integer> entry2 : treeMap.entrySet()) {
				System.out.println(entry2.getKey() + "\t\t" + entry2.getValue());
			}
			System.out.println(" ");
			
			Map<String, Integer> sortedMap = sortByComparator(Map);
			System.out.print("LinkedList: {");
			for (Entry<String, Integer> entry : sortedMap.entrySet()) {
				System.out.print(" " + entry.getKey() + "=" + entry.getValue() +",");
			}
			System.out.println("}");
			System.out.println(" ");
			// Get key and value from each entry
			for (Entry<String, Integer> entry2 : sortedMap.entrySet()) {
				System.out.println(entry2.getKey() + "\t\t" + entry2.getValue());
			}
			System.out.println(" ");
		}
		catch (IOException error) {
			System.out.println("Invalid File");
		} finally {
			bufferedReader.close();
		}
	}
 
	private static Map<String, Integer> sortByComparator(Map<String, Integer> unsortMap) {

		// Convert Map to List
		List<Map.Entry<String, Integer>> list = new LinkedList<Map.Entry<String, Integer>>(unsortMap.entrySet());

		// Sort list with comparator, to compare the Map values
		Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
			public int compare(Map.Entry<String, Integer> o1,Map.Entry<String, Integer> o2) {
				return (o2.getValue()).compareTo(o1.getValue());
			}
		});

		// Convert sorted map back to a Map
		Map<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
		for (Iterator<Map.Entry<String, Integer>> it = list.iterator(); it.hasNext();) {
			Map.Entry<String, Integer> entry = it.next();
			sortedMap.put(entry.getKey(), entry.getValue());
		}
		return sortedMap;
	}
	
	
	public static List<String> FindMaxOccurance(Map<String, Integer> map, int n) {
		List<CComparable> l = new ArrayList<>();
		for (Map.Entry<String, Integer> entry : map.entrySet())
			l.add(new CComparable(entry.getKey(), entry.getValue()));
 
		Collections.sort(l);
		List<String> list = new ArrayList<>();
		for (CComparable w : l.subList(0, n))
			list.add(w.wordFromFile + ":" + w.numberOfOccurrence);
		return list;
	}
}
 
class CComparable implements Comparable<CComparable> {
	public String wordFromFile;
	public int numberOfOccurrence;
 
	public CComparable(String wordFromFile, int numberOfOccurrence) {
		super();
		this.wordFromFile = wordFromFile;
		this.numberOfOccurrence = numberOfOccurrence;
	}
 
	@Override
	public int compareTo(CComparable arg0) {
		int cCompare = Integer.compare(arg0.numberOfOccurrence, this.numberOfOccurrence);
		return cCompare != 0 ? cCompare : wordFromFile.compareTo(arg0.wordFromFile);
	}
 
	@Override
	public int hashCode() {
		final int uniqueNumber = 19;
		int cResult = 9;
		cResult = uniqueNumber * cResult + numberOfOccurrence;
		cResult = uniqueNumber * cResult + ((wordFromFile == null) ? 0 : wordFromFile.hashCode());
		return cResult;
	}
 
	@Override
	public boolean equals(Object cObj) {
		if (this == cObj)
			return true;
		if (cObj == null)
			return false;
		if (getClass() != cObj.getClass())
			return false;
		CComparable other = (CComparable) cObj;
		if (numberOfOccurrence != other.numberOfOccurrence)
			return false;
		if (wordFromFile == null) {
			if (other.wordFromFile != null)
				return false;
		} else if (!wordFromFile.equals(other.wordFromFile))
			return false;
		return true;
	}
}