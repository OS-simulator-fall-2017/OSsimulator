package Components;
import java.util.ArrayList;

public class Scheduler {

	//This sets up a process queue object which holds TWO queues (readyQueue, waitingQueue)
	private static ProcessQueue queue = new ProcessQueue();
	
	private final int quantum = 10;
	private int ticker = 0;
	
	
	public void insertProcess(Process p){
		if(queue.getFreeMemory()>p.getMemory()){
			queue.enqueueReadyProcess(p);
		}
		else
			queue.enqueueWaitingProcess(p);
	}
	
	public void removeProcess(){
		queue.dequeueReadyProcess();
	}
	
	
	public int getArrivalTime(Process p){
		return p.getArrivalTime();
	}
	public void setArrivalTime(Process p){
		p.setArrivalTime(Clock.getClock());
	}
	
	
	public int getTicker(){
		return ticker;
	}
	public void incremementTicker(){
		this.ticker++;
	}
	
	
	public ArrayList<Process> getReadyQueue() {
        return queue.getReadyQueue();
    }

    public ArrayList<Process> getWaitingQueue() {
        return queue.getWaitingQueue();
    }
    
    public void updateQueues(){
    	queue.updateQueues();
    }
    

	
}
