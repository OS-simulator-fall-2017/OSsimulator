package Components;
import java.util.ArrayList;

public class FCFS {
	
	private static ProcessQueue queue = new ProcessQueue();
	
	public void insertProcess(Process p){
		if(queue.getFreeMemory()>p.getMemory()){
			queue.enqueueReadyProcess(p);
		}
		else
			queue.enqueueWaitingProcess(p);
	}
	
	

}