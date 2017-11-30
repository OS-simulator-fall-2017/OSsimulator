package Components;
import java.awt.TextField;
import Components.Process;
import GUI.GuiPrompt;
import ParseText.ParseText;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Scanner;
import javafx.scene.control.*;
import ParseText.ParseText;


public class CommandLine {
		
	//Takes job file and stores each token in our array list 
	 private static final ParseText userInput = new ParseText();
	 private static final ParseText requestFile = new ParseText();

	//Function for determining next action based on input
	  public static boolean storeInputs(String input) {
	        userInput.parseLine(input);
	        boolean valid = Commands(userInput.getCommand());
	        if (valid) {
	            if (!(userInput.getCommand() == null)) {
	                chooseCommand(userInput.getCommand(), userInput.getFile());
	            }
	        }
	        return valid;
	    }

	    
	    public static boolean Commands(String command) {
	    	switch(userInput.getCommand()) {
	    	case "proc": return true;
	    	case "mem": return true;
	    	case "exe": return true;
	    	case "reset": return true;
	    	case "exit": return true;
	    	case "clear": return true;
	    	case "load": return true;
	    	}
	    	return false;
	    	}
	    	
   		
	    
	    private static void chooseCommand(String command, String file) {
	        switch(command) {
	        	case "load": load(file); break;
	            case "proc": proc(); break;
	            case "mem": mem(); break;
	           // case "exe": exe(); break;
<<<<<<< HEAD
	            case "reset": reset(); break;
=======
	           case "reset": reset(); break;
>>>>>>> 5bef4bd7c9047ad4338800120d590574607c455b
	            //case "exit": exit(); break;
	            default: break;
	        } 
	    }
	    
	    //Starts parsing through the actual file defined by user input(Function-Stores input)
	    private static void load(String file) {
	        requestFile.parseFile(file);
	        if(!requestFile.getQueue().isEmpty()) {
	            int size = Integer.valueOf(requestFile.getQueue().remove(0));
	            Scheduler.insertPCB(new Process(file, size, requestFile.getQueue()));
	        }
	        GuiPrompt.print("Load Successful \n");
	        Process paint = Scheduler.getReadyQueue().get(0);
	        GuiPrompt.print(paint.getName() + "\n");
	    }
	    
	    
	    public static void proc(){
  	    	ArrayList<Process> ready = Scheduler.getReadyQueue();
  	    	ArrayList <Process>wait = Scheduler.getWaitingQueue();
  	    	
  	    	if(ready.getSize()==0 && wait.getSize()==0){
  	    		GuiPrompt.println("No process are currently loaded onto system.");
  	    	}else
  	    	{
  	    	for(int i=0;i<ready.size();i++){
  	    		ready.get(i).printProcessInfo();
  	    	}
  	    	for(int k=0;k<wait.size();k++){
  	    		wait.get(k).printProcessInfo();
  	    	}
  	    	}
  	    	
  	    }
  	    
	    public static void mem(){
  	    	int i = Scheduler.getUsedMemory();
  	    	System.out.println("Current memory usage: " + i);
  	    	
  	    }
  	    
	    public static void reset(){
  	    	Clock.resetClock();
  	    	Scheduler.resetScheduler();
  	    	GuiPrompt.clear();
  	    }
  	    
  	   
  	    
	    public void exe(int cycles){
  	    	Simulator.executeSolo=false;
  	    	Simulator.executionCycles=cycles;
  	    }
	    public void exe(){
  	    	Simulator.executeSolo=true;
  	    	Simulator.executionCycles=0;
  	    }
  	    
	    
	    
	
}