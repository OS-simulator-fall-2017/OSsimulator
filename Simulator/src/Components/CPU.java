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
		currentProcess.incrementTime();
		text = currentProcess.getNextCommand();
		if(currentProcess.getCalcTime()==0){
			
			switch (text){
			case "CALCULATE":
				currentProcess.setState(ProcessState.RUN);
				currentProcess.setCalcTime(Integer.parseInt(currentProcess.getNextCommand()));
				break;
			case "IO":
				currentProcess.incrementIORequests();
				currentProcess.setState(ProcessState.WAIT);
				currentProcess.setIOFlag(rand.nextInt(26)+25);
				break;
			case "YIELD":
				currentProcess.setState(ProcessState.READY);
				break;
			case "OUT":
				System.out.println("Currently running process:\n___________________________________");
				currentProcess.printProcessInfo();
				break;
			
			}
		}
		else
			currentProcess.decrementCalcTime();
		
		
		if(text==null&&currentProcess.getCalcTime()==0){
			currentProcess.setState(ProcessState.EXIT);
			this.currentProcess=null;
		}
		

	}
	
	
}
