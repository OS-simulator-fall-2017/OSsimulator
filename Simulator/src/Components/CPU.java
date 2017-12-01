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
		if(this.currentProcess != null)
		return this.currentProcess;
		else return null;
	}
	
	
	
	public void execute(){
		currentProcess.incrementTime();
		if(currentProcess.getCalcTime()==0&&currentProcess.getNextCommand()!=null){
			String comm = currentProcess.getNextCommand();
			switch (comm){
			case "CALCULATE":
				currentProcess.setState(ProcessState.RUN);
				currentProcess.setCalcTime(Integer.parseInt(currentProcess.getNextCommand()));
			case "IO":
				currentProcess.incrementIORequests();
				currentProcess.setState(ProcessState.WAIT);
				currentProcess.setIOFlag(rand.nextInt(26)+25);
			case "YIELD":
				currentProcess.setState(ProcessState.READY);
			case "OUT":
				System.out.println("Currently running process:\n___________________________________");
				currentProcess.printProcessInfo();
			case (""):
				currentProcess.setState(ProcessState.EXIT);
				Scheduler.removeProcess();
			}
		}
		else{
			currentProcess.decrementCalcTime();
			
		}

	}
	
	
}
