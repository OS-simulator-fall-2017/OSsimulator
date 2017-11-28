package Components;
import java.awt.TextField;
import GUI.GuiPrompt;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Scanner;


public class CommandLine {

	private String inputFile;
	private String command, value;
	private ArrayList<String>tokens = new ArrayList<>();
	private Scanner scan;
	
	

	//Takes job file and stores each token in our array list 
	public void parseJobFile(String jobFile){
		tokens.clear();
		File file = new File(jobFile);
        try {
			scan = new Scanner(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
            
        while (scan.hasNext()) {
                tokens.add(scan.next());
            }
       
        scan.close();
        
	}
	
	//Takes 'String command' as input (ie each line of job file)
	 public void parseLine(String command) {
	        command = command.toUpperCase();
	        scan = new Scanner(command);
	        this.command = scan.next();
	        scan = null;
	        if (scan.hasNext())
	            value = scan.next();
	        scan.close();
	    }
	 
	 
	 
	//Getter methods for the few fields of this class
	 public ArrayList<String> getQueue() {
	        return tokens;
	    }

	    public String getCommand() {
	        return command;
	    }

	    public String getValue() {
	        return value;
	    }
	  
	   
	    public static void proc(){
	    	ArrayList<Process> ready = Scheduler.getReadyQueue();
	    	ArrayList <Process>wait = Scheduler.getWaitingQueue();
	    	
	    	for(int i=0;i<ready.size();i++){
	    		ready.get(i).printProcessInfo();
	    	}
	    	for(int k=0;k<wait.size();k++){
	    		wait.get(k).printProcessInfo();
	    	}
	    	
	    }
	    
	    public static void mem(){
	    	int i = Scheduler.getFreeMemory();
	    	System.out.println("Current memory usage: " + i);
	    	
	    }
	    
	    public static boolean Commands(javafx.scene.control.TextField commandInput) {
   		 if(commandInput.getText().equals("proc")){
   			 GuiPrompt.println("Testing");
   	         return true;
   		 }
   		 if(commandInput.getText().equals("mem"))
   			 return true;
   		 if(commandInput.getText().equals("load"))
   			 return true;    
   		 if(commandInput.getText().equals("exe"))
   			 return true;
   		 if(commandInput.getText().equals("reset"))
   			 return true;
   		 if(commandInput.getText().equals("exit"))
   			 return true;
   	System.out.println("Error: not a proper command");
   	return false;
   }
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	
}