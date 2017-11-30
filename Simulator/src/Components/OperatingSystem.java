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
	
	
	/*public void run(){
		
		//Updates the queues first, this should put next process first in ready queue
		scheduler.updateQueues();
		
		//If CPU empty and ready queue has process ready, send process to CPU
		if(cpu.getCurrentProcess().equals(null)&&scheduler.getReadyQueue()!=null){
			cpu.setCurrentProcess(scheduler.getReadyQueue().get(0));
		}
		
		//Takes in input from console
		if(scan.hasNextLine()){
			CommandLine line = new CommandLine();
			ParseText text = new ParseText();
			line.parseLine(scan.nextLine());
			
			switch(line.chooseCommand()){
			case "PROC":
				proc();
				break;
			case "MEM":
				mem();
				break;
			case "LOAD":
				loadProgram(line.getFile());
			case "EXE":
				if(!line.getFile().equals(null)){
					exe(Integer.parseInt(line.getFile()));
				}else exe();
			case "RESET":
				reset();
				break;
			case "EXIT":
			
			}
		}
		
		cpu.tickClock();
		
	}*/
	
	  public void proc(){
	    	ArrayList<Process> ready = Scheduler.getReadyQueue();
	    	ArrayList <Process>wait = Scheduler.getWaitingQueue();
	    	
	    	for(int i=0;i<ready.size();i++){
	    		ready.get(i).printProcessInfo();
	    	}
	    	for(int k=0;k<wait.size();k++){
	    		wait.get(k).printProcessInfo();
	    	}
	    	
	    }
	    
	    public void mem(){
	    	int i = Scheduler.getFreeMemory();
	    	System.out.println("Current memory usage: " + i);
	    	
	    }
	    
	    public void reset(){
	    	Clock.resetClock();
	    	this.scheduler.resetScheduler();
	    }
	    
	    
	   /* public void loadProgram(String program){
	    	CommandLine line = new CommandLine();
	    	line.parseProgramFile(program);
	    	int mem = Integer.parseInt(line.getQueue().remove(0));
	    	Process p = new Process(program,mem,line.getQueue());
	    	scheduler.insertProcess(p);
	    }
	   
	    
	    //Need to add 'EXE' portion of job
	    public void loadJob(String job){
	    	CommandLine line = new CommandLine();
	    	line.parseJobFile(job);
	    	while(!line.getQueue().equals(null)){
	    		String temp = line.getQueue().remove(0);
	    		if(temp=="LOAD"){
	    			loadProgram(line.getQueue().remove(0));
	    		}else if (temp=="EXE"){
	    			
	    		}
	    	}
	    	
	    }
	   */ 
	    public void exe(int cycles){
	    	Simulator.executeSolo=false;
	    	Simulator.executionCycles=cycles;
	    }
	    public void exe(){
	    	Simulator.executeSolo=true;
	    	Simulator.executionCycles=0;
	    }
	    
	    public void start(){
	    	
	    }
	    
	    
}
