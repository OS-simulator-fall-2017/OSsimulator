package Components;
import java.util.ArrayList;

public class Scheduler {

	//This sets up a process queue object which holds TWO queues (readyQueue, waitingQueue)
	private static ProcessQueue queue = new ProcessQueue();
	
	private final int quantum = 10;
	private int ticker = 0;
	
	//Inserts process to either ready queue or waiting queue, wherever it can 'fit'
	public void insertProcess(Process p){
		if(queue.getFreeMemory()>p.getMemory()){
			queue.enqueueReadyProcess(p);
		}
		else
			queue.enqueueWaitingProcess(p);
	}
	
	//Removes process from top of readyQueue, can be used to pop to CPU
	public void removeProcess(){
		queue.dequeueReadyProcess();
	}
	
	
	//Returns the arrival time of the process
	public int getArrivalTime(Process p){
		return p.getArrivalTime();
	}
	//Sets the arrival time of the process to the current reading of the clock
	public void setArrivalTime(Process p){
		p.setArrivalTime(Clock.getClock());
	}
	
	//Returns the quantum ticker
	public int getTicker(){
		return ticker;
	
	}
	//Incremements the quantum ticker 1
	public void incremementTicker(){
		this.ticker++;
	}
	
	//Returns readyQueue
	public static ArrayList<Process> getReadyQueue() {
        return queue.getReadyQueue();
    }
	
	//Returns waitingQueue
    public static ArrayList<Process> getWaitingQueue() {
        return queue.getWaitingQueue();
    }
   
    
    //Updates queues based on ProcessQueue.updateQeueus()
    public void updateQueues(){
    	queue.updateQueues();
    }
    
    
    public static int getFreeMemory(){
    	return queue.getFreeMemory();
    }
   
    
    
    
   
}
