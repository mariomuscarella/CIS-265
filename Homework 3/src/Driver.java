//*******************************************
// CIS 265 Fall, 2015 ‐  Section 50     
// Homework #3 – Sorting Geometric Objects   
// NAME: Mario Muscarella                      
// CSU ID: 2478702       
// DATE & TIME: 10/14 3PM           
//**************************************
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Driver {

    public static void main(String[] args) throws FileNotFoundException {
    	
        GeometricObject g = null;
        File diskFile = new File("//users//Hobo13//Desktop//Untitled.txt");
        Scanner diskScanner = new Scanner(diskFile);
        List<GeometricObject> geoList = new ArrayList<GeometricObject>();
        while(diskScanner.hasNext()){
            String list = diskScanner.nextLine();
            g = recreateObject(list);
            geoList.add(g);
        }
        diskScanner.close();
        
        int pos = locationLargestArea(geoList);

        System.out.println("BIGGEST OBJECT AT POS "+ pos +" AREA = "+ geoList.get(pos).getArea());
        System.out.println(diskFile.getAbsolutePath() + "@" + System.identityHashCode(pos));
        showObjects(geoList.get(pos));
        System.out.println(" ");
        
    }
    
    // Create ArrayList with each object separated
    private static GeometricObject recreateObject(String list) {

        String[] data = list.split(",");
        String geoObject = data[0];

        if (geoObject.equals("Circle")) {
            String color = data[1];
            boolean filled = Boolean.valueOf(data[2]);
            double radius = Double.valueOf(data[3]);
            return new Circle(color, filled, radius);
        }

        if (geoObject.equals("Rectangle")) {
            String color = data[1];
            boolean filled = Boolean.valueOf(data[2]);
            double length = Double.valueOf(data[3]);
            double width = Double.valueOf(data[4]);
            return new Rectangle(color, filled, length, width);
        }

        return null;
    }
    
    // Allow print of single rows of GeometricObject
    private static void showObjects(GeometricObject geometricObject) {

        System.out.println(geometricObject.toString());
    }
   
    // find location of the object having the largest area
    private static int locationLargestArea(List<GeometricObject> list){
  	  if (list.size() == 0 )
  		  return -1;
  	  
  	  int pos = 0;
  	  double maxArea = list.get(0).getArea();
  	  
  	  for(int i=1; i<list.size(); i++){
  		  GeometricObject g = list.get(i);
  		  if ( g.getArea() >= maxArea){
  			  maxArea = g.getArea();
  			  pos = i;
  		  }
  	  }
    return pos;
    }
      
    private static void sortObjects(List<GeometricObject> list, int low, int high){
    	if (low < high){
    		int minIndex = low;
    		GeometricObject min = list.get(low);
    		for (int i = low + 1; i <= high; i++){ 
    			if (list.get(i).getArea() < min.getArea()){ 
    				min = list.get(i);
    				minIndex = i; 
    			}
    		}
    		
    		//move the original 
    		Collections.swap(list, low, minIndex); 
    		//set the old lowest to the new lowest
    		list.set(low, min); 

    		sortObjects(list, low + 1, high); //call it again
    	}
    }
 } 		