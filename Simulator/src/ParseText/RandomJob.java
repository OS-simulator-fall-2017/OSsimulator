package ParseText;

import java.io.File;

import Components.Process;
import Components.Scheduler;
import Components.Clock;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Random;
public class RandomJob {
	
	 private ArrayList<Process> genPrograms = new ArrayList<>();

	 public static void jobGenerator() {      
	      File f = null;
	      File[] paths;
	      ParseText job = new ParseText();
	  	  Process genProgram = null;
	      try {  
	         // create new file
	         f = new File("programs/");
	         
	         // returns pathnames for files and directory
	         paths = f.listFiles();
	         
	         // for each pathname in pathname array
	         for(File path:paths) {
	         
	            // prints file and directory paths
	        	 String name = String.valueOf(path);
	        	 name = name.replace("programs/","");
	        	 job.parseProgramFile(path);
	        	 int size = Integer.valueOf(job.getQueue().remove(0));
	        	 Process temp = new Process(name, size, job.getQueue());
	        	 Scheduler.insertProcess(temp);
	         }
	        
	         
	      } catch(Exception e) {
	         
	         // if any error occurs
	         e.printStackTrace();
	      }
	   }
	
}
