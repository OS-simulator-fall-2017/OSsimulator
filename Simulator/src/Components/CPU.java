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
		if(currentProcess.getCalcTime()==0){
			String comm = currentProcess.getNextCommand();
			System.out.println("COMMAND: " + comm);
			switch (comm){
			case "CALCULATE":
				currentProcess.setState(ProcessState.RUN);
				currentProcess.setCalcTime(Integer.parseInt(currentProcess.getNextCommand()));
				System.out.println("CALC TIME:" + currentProcess.getCalcTime());
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
			default:break;
			}
		}
		else
			currentProcess.decrementCalcTime();
		
		if(currentProcess.getCalcTime()==0&&currentProcess.getNextCommand()==null){
			currentProcess.setState(ProcessState.EXIT);
		}
		

	}
	
	
}
