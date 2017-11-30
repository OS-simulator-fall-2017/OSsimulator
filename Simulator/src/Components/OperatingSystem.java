package Components;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class OperatingSystem {

	
	private Scheduler scheduler=new Scheduler();
	private CPU cpu = new CPU();
	Scanner scan = new Scanner(System.in);
	
	
	public void run(){
		
		//Updates the queues first, this should put next process first in ready queue
		scheduler.updateQueues();
		
		//If CPU empty and ready queue has process ready, send process to CPU
		if(cpu.getCurrentProcess().equals(null)&&scheduler.getReadyQueue()!=null){
			cpu.setCurrentProcess(scheduler.getReadyQueue().get(0));
		}
		
	    
}
