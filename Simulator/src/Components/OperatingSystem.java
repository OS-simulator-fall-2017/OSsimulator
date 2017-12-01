package Components;

import java.io.File;
import java.util.ArrayList;
import GUI.gui;
import ParseText.ParseText;
import java.util.Scanner;
import java.util.Random;
import ParseText.RandomJob;


public class OperatingSystem {

	Random gen = new Random();
	private final int mark = 5;
	private Scheduler scheduler=new Scheduler();
	private CPU cpu = new CPU();
	Scanner scan = new Scanner(System.in);
	//private String input = gui.CommandInput.getText();
	
	
	public void run(){
		
		Clock.tickClock();
		if(gen.nextInt(1)+999999999 == mark) {
			RandomJob.jobGenerator();
		}
			
		
		//Updates the queues first, this should put next process first in ready queue
		scheduler.updateQueues();
		
		
		//If CPU empty and ready queue has process ready, send process to CPU.. else if the cpu has a process then execute
		if(cpu.getCurrentProcess()==null&&Scheduler.getReadyQueue().size()>0){
			cpu.setCurrentProcess(Scheduler.getNextProcess());
			
		}
		
		if(cpu.getCurrentProcess()!=null){
			scheduler.incrementTimer();
			cpu.execute();	
		}
		
		//Goes through ready queue to update IO flags
		for (int i=0;i<Scheduler.getReadyQueue().size();i++){
			if(Scheduler.getReadyQueue().get(i).getIOFlag()!=0){
				Scheduler.getReadyQueue().get(i).decrementIOFlag();
			}
			if(Scheduler.getReadyQueue().get(i).getIOFlag()==0&&Scheduler.getReadyQueue().get(i).getState()==ProcessState.WAIT){
					Scheduler.getReadyQueue().get(i).setState(ProcessState.READY);
				}
		}
		
		
		//Checks if process quantum time in CPU has been reached, if so, send to back of Ready Queue
		if(scheduler.checkQuantumStatus()){
			cpu.getCurrentProcess().setState(ProcessState.READY);
			Scheduler.sendToBack();
			cpu.setCurrentProcess(Scheduler.getNextProcess());
		}
		else{
			return;
		}
		 
	}

}
