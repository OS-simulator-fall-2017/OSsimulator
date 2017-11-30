package Components;

import java.util.ArrayList;

public class ProcessQueue {

	 private ArrayList<Process> readyQueue = new ArrayList<>();
	 private ArrayList<Process> waitingQueue = new ArrayList<>();
	 private int freeMemory = 4096;
	 
	 
	 //Adds a process to the ready queue if there is enough space, if there isn't enough space its added to the waiting queue
	 public void enqueueReadyProcess(Process p){
		 if(p.getMemory()<this.freeMemory){
		 this.readyQueue.add(p);
		 p.setState(ProcessState.READY);
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
	 
	 public void removeProcess(Process process) {
	        if (readyQueue.remove(process))
	            return;
	        else
	            waitingQueue.remove(process);
	    }
	 
	 
	 public int getFreeMemory(){
		 return this.freeMemory;
	 }
	 public int getUsedMemory(){
		 return 4096-this.freeMemory;
	 }
	 
	 //Pops process from front of array list, sends it to the back
	 public void preemptedProcess(Process p){
		 Process temp = this.readyQueue.get(0);
		 this.readyQueue.remove(0);
		 this.readyQueue.add(temp);
	 }
	 
	 public ArrayList<Process> getReadyQueue(){
		 return this.readyQueue;
	 }
	 public ArrayList<Process> getWaitingQueue(){
		 return this.waitingQueue;
	 }
	 
	 
	public void setWait(Process p){
		p.setState(ProcessState.WAIT);
	}
	
	
	public void updateQueues(){
		for (int i=0;i<this.readyQueue.size();i++){
			if(this.readyQueue.get(i).getState().equals(ProcessState.EXIT));{
				this.freeMemory+=this.readyQueue.get(i).getMemory();
				this.readyQueue.remove(i);
			}
		}
		if(this.freeMemory>0 && this.waitingQueue.size()>0){
			for(int i = 0;i<this.waitingQueue.size();i++){
				if(this.waitingQueue.get(i).getMemory()<this.freeMemory){
					this.readyQueue.add(this.waitingQueue.get(i));
					this.freeMemory-=this.waitingQueue.get(i).getMemory();
				}
			}
		}
	}
	
	public void resetQueues(){
		this.readyQueue.clear();
		this.waitingQueue.clear();
	}
	
	
	  
}
