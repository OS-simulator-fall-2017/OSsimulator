package Components;

import java.util.ArrayList;

public class ProcessQueue {

	 private ArrayList<Process> readyQueue = new ArrayList<>();
	 private ArrayList<Process> waitingQueue = new ArrayList<>();
	 private int freeMemory = 4096;
	 
	  public ProcessQueue(){
		 
	 }
	 //Adds a process to the ready queue if there is enough space, if there isn't enough space its added to the waiting queue
	 public void enqueueReadyProcess(Process p){
		 if(p.getMemory()<this.freeMemory){
		 this.readyQueue.add(p);
		 this.freeMemory = this.freeMemory - p.getMemory();
		 }else
			 enqueueWaitingProcess(p);
	 }
	 public void dequeueReadyProcess(){
		 Process temp;
		 temp = this.readyQueue.remove(0);
		 this.freeMemory+=this.freeMemory+temp.getMemory();
		 
	 }
	 
	
	 public void enqueueWaitingProcess(Process p){
		 this.waitingQueue.add(p);
	 }
	 public void dequeueWaiting(){
		 this.waitingQueue.remove(0);
	 }
	 
	 
	 //Pops process from front of array list, sends it to the back
	 public void preemptedProcess(Process p){
		 Process temp = this.readyQueue.get(0);
		 this.readyQueue.remove(0);
		 this.readyQueue.add(temp);
	 }
	 
	  
}
