package Components;
import java.util.ArrayList;
//First come First Serve Scheduler
public class FCFS {
	
	private static ProcessQueue queue = new ProcessQueue();
	
	public void insertProcess(Process p){
		if(queue.getFreeMemory()>p.getProcessMemory()){
			queue.enqueueReadyProcess(p);
		}
		else
			queue.enqueueWaitingProcess(p);
	}
	
	

}
