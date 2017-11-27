package Components;
import java.util.ArrayList;

public class Process {

	
	private ProcessState processState;
	private String processName;
	private int calcTime=0;
	private int processMemory;
	private int processTime;
	private int processID;
	private int processPriority;
	private int timeSpent=0;
	private int ioRequestsPerformed=0;
	private int arrivalTime;
	private int IOflag;

	private ArrayList <String> processCommands = new ArrayList<>();
	
	
	//Initializes process with the name, memory size, and array list of operation strings within process
	public Process(String name, int mem, ArrayList<String>operationString){
		this.processName = name;
		this.processMemory = mem;
		this.processCommands.equals(operationString);
		this.processState = ProcessState.NEW;
	}
	

	//Setter methods for all fields of Process
	public void setState(ProcessState state){
		this.processState = state;
	}
	public void setMemory(int mem){
		this.processMemory = mem;
	}
	public void setProcessID(int id){
		this.processID = id;
	}
	public void setTime(int time){
		this.processTime = time;
	}
	public void setPriority(int p){
		this.processPriority = p;
	}
	public void setArrivalTime(int time){
		this.arrivalTime = time;
	}
	public void setCalcTime(int t){
		this.calcTime = t;
	}
	

	//Getter methods for all fields of Process()
	public int getPriority(){
		return this.processPriority;
	}
	public String getState(){
		return this.processState.toString();
	}
	public int getTimeLeft(){ //This will be time of all previous processes + time of process in question
		return this.processTime-this.timeSpent;
	}
	public int getMemory(){
		return this.processMemory;
	}
	public int getProcessID(){
		return this.processID;
	}
	public int getRequestsPerformed(){//returns # of IO requests performed
		return this.ioRequestsPerformed;
	}
	public int getArrivalTime(){
		return arrivalTime;
	}
	public int getWait(){
		return Clock.getClock()-this.arrivalTime;
	}
	public int getCalcTime(){
		return this.calcTime;
	}
	public ArrayList<String> getProcessCommands(){
		return this.processCommands;
	}
	
	
	//Incrementing methods for process
	public void incrementTime(){
		this.timeSpent++;
	}
	public void incrementIORequests(){
		this.ioRequestsPerformed++;
	}
	public void decremementtCalcTime(){
		this.calcTime--;
	}
	
	public void IORequest(int time){
		this.IOflag= Clock.getClock()+time;
		this.processState=ProcessState.WAIT;
	}
	
	public boolean getIOWait(){
		if(this.IOflag<=Clock.getClock()){
			return true;
		}
		else return false;
	}
	
	public void printProcessInfo(){
		System.out.println("PROCESS NAME: " + processName);
		System.out.println("PROCESS STATE: " + processState);
		System.out.println("TIME LEFT FOR THIS PROCESS: " + this.getTimeLeft());
		System.out.println("______________________________________");
	}
	
	
	
	
	
	
}
