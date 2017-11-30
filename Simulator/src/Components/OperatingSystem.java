package Components;

import java.io.File;
import java.util.ArrayList;
import GUI.gui;
import ParseText.ParseText;
import java.util.Scanner;

public class OperatingSystem {

	
	private Scheduler scheduler=new Scheduler();
	private CPU cpu = new CPU();
	Scanner scan = new Scanner(System.in);
	private String input = gui.CommandInput.getText();
	
	
	public void run(){
		
		//Updates the queues first, this should put next process first in ready queue
		scheduler.updateQueues();
		
		//If CPU empty and ready queue has process ready, send process to CPU
		if(cpu.getCurrentProcess().equals(null)&&scheduler.getReadyQueue()!=null){
			cpu.setCurrentProcess(scheduler.getReadyQueue().get(0));
		}
		
		if(!cpu.getCurrentProcess().equals(null)){
			cpu.execute();
		}
		
		scheduler.incrementTimer();
		
		//Checks if process quantum time in CPU has been reached, if so, send to back of Ready Queue
		if(scheduler.checkQuantumStatus){
			scheduler.sendToBack()
		}
		else{
			return;
		}
		 
}
