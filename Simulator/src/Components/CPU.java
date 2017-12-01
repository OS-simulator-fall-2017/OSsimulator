package Components;

import java.util.ArrayList;
import java.util.Random;

import GUI.GuiPrompt;
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
		if(this.currentProcess != null)
		return this.currentProcess;
		else return null;
	}
	
	
	
	public void execute(){
		
		
		if(currentProcess.getCalcTime()==0){
			text = currentProcess.getNextCommand();
			switch (text){
			case "CALCULATE":
				currentProcess.setState(ProcessState.RUN);
				currentProcess.setCalcTime(Integer.parseInt(currentProcess.getNextCommand()));
				break;
			case "IO":
				currentProcess.incrementIORequests();
				currentProcess.setIOFlag(rand.nextInt(26)+25);
				System.out.println(currentProcess.getIOWait());
				break;
			case "YIELD":
				currentProcess.setState(ProcessState.READY);
				break;
			case "OUT":
				currentProcess.printProcessInfo();
				break;
			
			}
		}
		else{
			currentProcess.decrementCalcTime();
			currentProcess.incrementTime();
		}
		if(currentProcess.getProcessCommands().size()==0&&currentProcess.getCalcTime()==0){
			currentProcess.setState(ProcessState.EXIT);
			System.out.println("FINAL CYCLES TAKEN:" + currentProcess.getTimeSpent());
			this.currentProcess=null;
		}
		

	}
	
	
}
