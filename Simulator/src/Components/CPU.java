package Components;

import java.util.ArrayList;
import java.util.Random;
import GUI.gui;

public class CPU {

	private String text;
	private Process currentProcess;
	Random rand = new Random();
	
	
	 public static int tickClock(){

	        return Clock.tickClock();
	    }
	 
	 
	public void setCurrentProcess(Process p){
		currentProcess = p;
		this.currentProcess.setState(ProcessState.RUN);
	}
	public Process getCurrentProcess(){
		return this.currentProcess;
	}
	
	
	
	
	public void execute(){
		
		String command = currentProcess.getNextCommand().toUpperCase();
		switch(command){
		case ("CALCULATE"):
			currentProcess.setState(ProcessState.RUN);
			currentProcess.setCalcTime(Integer.parseInt(currentProcess.getNextCommand()));
		case ("IO"):
			currentProcess.incrementIORequests();
			currentProcess.IORequest(rand.nextInt(26)+25);
		case ("YIELD"):
			currentProcess.setState(ProcessState.WAIT);
		case ("OUT"):
			System.out.println("Current Process Information:");
			currentProcess.printProcessInfo();
		}
		

		
	}
	
	
	
	
	
	
}
