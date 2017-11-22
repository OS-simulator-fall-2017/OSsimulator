package round_robin_processing;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Scheduler {

	// Scheduler attributes
	Double totalTime = 0.0;
	List<Process> completedProcesses = new ArrayList<Process>();
	Double timeQuantum = 1.0;

	public Scheduler(Double tq) {
		/*
		 * Constructor method.
		 */

		timeQuantum = tq;
	}

	private void addWaitTime(List<Process> readyQueue, Double waitTime) {
		/*
		 * Add wait time to the processes that are not executing.
		 */

		for (int i = 1; i < readyQueue.size(); i++) {
			Process p = readyQueue.get(i);
			p.setWaitTime(p.getWaitTime() + waitTime);
		}
	}

	public List<Process> getCompletedProcesses() {
		/*
		 * Return the list of completed processes.
		 */

		return this.completedProcesses;
	}

	private int getNextArrival(List<Process> processList) {
		/*
		 * Return the index of the process with the earliest arrival time.
		 */

		int index = 0;
		Double earliest = processList.get(0).getArrivalTime();

		for (int i = 1; i < processList.size(); i++) {
			Double arrivalTime = processList.get(i).getArrivalTime();
			if (arrivalTime < earliest) {
				index = i;
				earliest = arrivalTime;
			}
		}

		return index;
	}

	public Double getTotalTime() {
		/*
		 * Return the total system time.
		 */

		return this.totalTime;
	}

	public void roundRobin(List<Process> processList) {
		/*
		 * Execute the scheduler using Round-Robin Scheduling.
		 */

		// Initialize dispatcher
		Dispatcher dispatcher = new Dispatcher();

		while (!processList.isEmpty()) {

			// Move next process into ready queue
			List<Process> readyQueue = new ArrayList<Process>();
			int nextArrival = getNextArrival(processList);
			Process nextProcess = processList.get(nextArrival);
			readyQueue.add(nextProcess);
			processList.remove(nextArrival);

			// Starve until next process
			Double starveTime = readyQueue.get(0).getArrivalTime() - this.totalTime;
			dispatcher.starve(starveTime);
			this.totalTime = this.totalTime + starveTime;
			System.out.printf("Process PID = %d has arrived", readyQueue.get(0).getPid());

			while (!readyQueue.isEmpty()) {

				// Get next process
				Process p = readyQueue.get(0);
				System.out.printf("\n\n\tLoading process PID = %d\n", p.getPid());
				System.out.printf("\tProcess burst time: %f\n", p.getBurstTime());
				System.out.printf("\tProcess execution time remaining: %f\n", p.getExecutionTime());
				System.out.printf("\tSystem time: %f\n", this.totalTime);

				// If the process execution time is greater or equal to one
				// time quantum, then execute the process for one time quantum.
				// If the execution time is less one time quantum, then execute
				// the remainder of the process.
				if (p.getExecutionTime() >= this.timeQuantum) {
					dispatcher.dispatch(p.getPid(), this.timeQuantum);
					p.setExecutionTime(p.getExecutionTime() - this.timeQuantum);
					this.totalTime = this.totalTime + this.timeQuantum;
					addWaitTime(readyQueue, this.timeQuantum);
					readyQueue.remove(0);
					System.out.printf("\tSystem time: %f\n", this.totalTime);
				} else {
					dispatcher.dispatch(p.getPid(), p.getExecutionTime());
					this.totalTime = this.totalTime + p.getExecutionTime();
					addWaitTime(readyQueue, p.getExecutionTime());
					p.setExecutionTime(0.0);
					readyQueue.remove(0);
					System.out.printf("\tSystem time: %f\n", this.totalTime);
				}

				// Check to see if any new processes have arrived. If so, move
				// them into the ready queue
				for (Iterator<Process> iterator = processList.iterator(); iterator.hasNext();) {
					Process newProc = iterator.next();
					if (newProc.getArrivalTime() <= this.totalTime) {
						System.out.printf("Process PID = %d has arrived\n", newProc.getPid());
						readyQueue.add(newProc);
						iterator.remove();
					}
				}

				// check to see if the current process still has execution time
				// remaining.
				// If it does, then put the process back into the ready queue.
				if (p.getExecutionTime() > 0) {
					readyQueue.add(p);
				} else {
					System.out.printf("Process PID = %d has completed!\n", p.getPid());
					p.turnaroundTime = this.totalTime;
					this.completedProcesses.add(p);
				}
			}

			// Ready Queue is empty, but there are still processes yet to arrive
			// so reschedule remaining processes
			roundRobin(processList);
		}
	}
}