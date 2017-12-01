package Components;
import java.util.ArrayList;
import java.util.Random;

public class Scheduler {

	//This sets up a process queue object which holds TWO queues (readyQueue, waitingQueue)
	private static ProcessQueue queue = new ProcessQueue();
	
	private final int quantum = 10;
	private static int timer = 0;
	Random mim = new Random();
	Random max = new Random();
	
	//Inserts process to either ready queue or waiting queue, wherever it can 'fit'
	public static void insertProcess(Process p){
		if(queue.getFreeMemory()>p.getProcessMemory()){
			queue.enqueueReadyProcess(p);
		}
		else
			queue.enqueueWaitingProcess(p);
	}
	
	
	//Removes process from top of readyQueue, can be used to pop to CPU
	public static Process removeProcess(){
		return queue.dequeueReadyProcess();
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
	public int getTimer(){
		return this.timer;
	
	}
	//Increments the quantum ticker 1
	public void incrementTimer(){
		this.timer++;
	}
	public static void resetTimer(){
		timer=0;
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
    public static int getUsedMemory(){
    	return queue.getUsedMemory();
    }
    
    public static void resetScheduler(){
    	timer=0;
    	queue.resetQueues();
    }
   
    public boolean checkQuantumStatus(){
    	if (timer==quantum){
    		return true;
    	}else
    		return false;
    }
    
    public static void sendToBack(){
    	Process temp = queue.getReadyQueue().remove(0);
    	//temp.setState(ProcessState.READY);
    	queue.addReadyProcess(temp);
    	timer=0;
    }
    
    public static Process getNextProcess(){
    	for(int i=0;i<queue.getReadyQueue().size();i++){
    		if(queue.getReadyQueue().get(0).getState()==ProcessState.READY){
    			return queue.getReadyQueue().get(0);
    		}else
    			sendToBack();
    	}
    	return null;
    }
}
