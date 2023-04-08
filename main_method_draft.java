
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class main_method_draft {
	
	public static void main(String[] args) throws Exception {
	
		// Buffer Reader used to scan the input text file
		
	      File cmd_file = new File(args[0]) ; //  args[0] = "4Kints.txt"
	      BufferedReader input = new BufferedReader(new FileReader(cmd_file)); 
	      String cmd_file_line;
	      
	      if( Integer.parseInt(args[1]) == 0){
          	
	    	  // Arrays.sort() ;
          }
          else if(Integer.parseInt(args[1]) == 1){
          	
        	  // Bubble Sort ;
          }
          else if(Integer.parseInt(args[1]) == 2){
          	
              // Selection Sort
          }
          else if(Integer.parseInt(args[1]) == 3){
          	
              // Insertion Sort
          }
          else if(Integer.parseInt(args[1]) == 4){
        	  
        	  // Mergesort 
          }
          else if(Integer.parseInt(args[1]) == 4){
        	  
        	  // Quicksort
        }
	}
}
