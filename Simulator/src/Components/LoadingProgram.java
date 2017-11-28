package Components;

import java.util.ArrayList;
import java.util.Scanner;

public class LoadingProgram {

	private int arrivalTime = Clock.getClock();
	ArrayList<String> commands = new ArrayList<>();
	String name;
	int memory;
	

	
	public LoadingProgram(ArrayList<String>commands,int time){
		this.arrivalTime=time;
		this.commands.equals(commands);
		this.memory=Integer.parseInt(commands.remove(0));
	}
	
	
	public void createProcess(){
		Process p = new Process(this.name,this.memory,this.commands);
		Scheduler.insertProcess(p);
	}
	
	
	
	
	
}
