//*******************************************
// CIS 265 Fall, 2015 ‐  Section 50     
// Homework #2 – Geometric Objects   
// NAME: Mario Muscarella                      
// CSU ID: 2478702       
// DATE & TIME: 9/28 3PM           
//**************************************
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
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
        
        for (int a = 0; a < geoList.size(); a++) {
        	System.out.println("POS " + a + " => " + diskFile.getAbsolutePath() + "@" + System.identityHashCode(a));
        	System.out.println(geoList.get(a));
        	System.out.println(" ");
        }
        
        int maxIndex = findPositionLargestObject(geoList);

        System.out.println("BIGGEST OBJECT AT POS "+ maxIndex +" AREA = "+ geoList.get(maxIndex).getArea());
        System.out.println(diskFile.getAbsolutePath() + "@" + System.identityHashCode(maxIndex));
        showObjects(geoList.get(maxIndex));
        System.out.println(" ");
        
        int color = findPositionBiggestColor(geoList, null);
        
        System.out.println("BIGGEST COLOR RED AT POS" + color + " AREA = " + geoList.get(color).getArea());
        System.out.println(diskFile.getAbsolutePath() + "@" + System.identityHashCode(color));
        showObjects(geoList.get(color));
        System.out.println(" ");
        
        int small = findPositionSmallestCircle(geoList);
        
        System.out.println("SMALLEST CIRCLE AT POS " + small + " AREA = " + geoList.get(small).getArea());
        System.out.println(diskFile.getAbsolutePath() + "@" + System.identityHashCode(small));
        showObjects(geoList.get(small));
       
     
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
   
    // Find largest object
      private static int findPositionLargestObject(List<GeometricObject> geoList) {
    	
    	// Start with a maxIndex of zero and run loop to find largest object and POS
        int maxIndex = 0;
        for (int i = 1; i < geoList.size(); i++) {
            // Area of i compares max area
            if (geoList.get(i).getArea() > geoList.get(maxIndex).getArea()) {
                maxIndex = i;
            }
        }
        return maxIndex;
    }
      
      private static int findPositionBiggestColor(List<GeometricObject> geoList,String searchColor) {
    	  
    	  // Start with POS 0 and loop to find POS
    	  int color = 0; 
    	  for (GeometricObject g : geoList){
    		  // Only compare objects with color red
    		  if ( g.getColor().equals("red")) {
    			  // Loop to find largest object
    			  for (int i = 1; i < geoList.size(); i++) {
    				  if (geoList.get(i).getArea() > geoList.get(color).getArea()) {
		                color = i;
    				  }
    			  }
    		  }
    	  }
    	  return color;
      }
      
      private static int findPositionSmallestCircle(List<GeometricObject> geoList) {
    	  
    	  // Loop to read POS and find smallest circle
    	  int small = 0;
          for (int i = 1; i < 4; i++) {
        	// Area of i compares smallest area
        	if (geoList.get(i).getArea() < geoList.get(small).getArea()) {
        		small = i;
              }
          }
          return small;
    	  
      }
 
 } 		