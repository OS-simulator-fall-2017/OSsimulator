package Components;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Scanner;


public class CommandLine {

	private String inputFile;
	private String command;
	private String value;
	private ArrayList<String>tokens = new ArrayList<>();
	private Scanner scan;
	
	
	
	//Takes job file and stores each token in our array list 
	public void parseJobFile(String jobFile){
		this.tokens.clear();
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
	
	public void parseProgramFile(String programFile){
		this.tokens.clear();
		File file = new File(programFile);
        try {
			scan = new Scanner(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
            
        while (scan.hasNext()) {
                this.tokens.add(scan.next());
            }
       
        scan.close();
       // LoadingProgram l = new LoadingProgram(tokens,time);
	}
	
	//Takes 'String command' as input (ie each line of job file)
	 public void parseLine(String command) {
	        command = command.toUpperCase();
	        scan = new Scanner(command);
	        this.command = scan.next();
	        value = null;
	        if (scan.hasNext())
	            value = scan.next();
	        scan.close();
	    }
	 
	 
	//Getter methods for the few fields of this class
	 public ArrayList<String> getQueue() {
	        return this.tokens;
	    }

	    public String getCommand() {
	        return this.command;
	    }

	    public String getValue() {
	        return this.value;
	    }
	  
	 
	    
	    
	    
	    
	    
	    
	    
	
}