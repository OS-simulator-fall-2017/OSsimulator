package Components;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Scanner;

import GUI.GuiPrompt;
import javafx.scene.control.TextField;


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
	 
	 
	 public static boolean Commands(TextField input) {
		 if(input.getText().equals("proc")) {
			 GuiPrompt.println("No processes are current running.");
	         return true; 
	         }
		 
		 if(input.getText().equals("mem")) {
			 return true;
		 }
		 
		 if(input.getText().equals("load")) {
			 return true;  
		 }
		 
		 if(input.getText().equals("exe")) {
			 return true;
		 }
		 
		 if(input.getText().equals("reset")) {
			 return true;
		 }
		 
		 if(input.getText().equals("exit")) {
			 return true;
		 }
		 
	System.out.println("Error: not a proper command");
	return false;
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
	
}