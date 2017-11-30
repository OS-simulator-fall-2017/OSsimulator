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
		currentProcess.incrementTime();
		
		if(currentProcess.getCalcTime==0){
			String comm = currentProcess.getNextCommand();
			switch (comm){
			case "CALCULATE":
				currentProcess.setState(ProcessState.RUN);
				currentProcess.setCalcTime = currentProcess.getNextCommand();
			case "IO":
				currrentProcess.incrementIORequests();
				currentProcess.setState(ProcessState.WAIT);
				currentProcess.IOFlag=currentProcess.getNextCommand();
			case "YIELD":
				currentProcess.setState(ProcessState.READY);
			case "OUT":
				System.out.println("Currently running process:\n___________________________________")
				currentProcess.printProcessInfo();
			case null:
				currentProcess.setState(ProcessState.EXIT);
				Scheduler.removeProcess(currentProcess);
			}
		}
		else{
			currentProcess.decrementCalcTime();
		}

	}
	
	
}
