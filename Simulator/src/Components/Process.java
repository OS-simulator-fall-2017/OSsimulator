package Components;
import GUI.GuiPrompt;
import java.util.ArrayList;
import java.util.Arrays;

public class Process {

	
	private ProcessState processState;
	private String processName;
	private int calcTime=0;
	private int processMemory;
	private int processTime;
	private int processID;
	private int processPriority;
	private int timeSpent=0;
	private int timeLeftToComplete=0;
	private int ioRequestsPerformed=0;
	private int arrivalTime;
	private int IOflag=0;

	private ArrayList <String> processCommands = new ArrayList<>();
	
	
	//Initializes process with the name, memory size, and array list of operation strings within process
	public Process(String processName, int mem, ArrayList<String>operationString){
		this.processName = processName;
		this.processMemory = mem;
		this.processCommands=operationString;
		this.processState = ProcessState.NEW;
		for (int i=0;i<operationString.size();i++){
			if(operationString.get(i).equals("CALCULATE")){
				this.timeLeftToComplete+=Integer.parseInt(operationString.get(i+1));
			}
		}
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
	public void setIOFlag(int t){
		this.IOflag=t;
	}
	

	//Getter methods for all fields of Process()
	public int getPriority(){
		return this.processPriority;
	}
	public ProcessState getState(){
		return this.processState;
	}
	public int getTimeLeft(){ 
		return this.timeLeftToComplete;
	}
	public int getProcessMemory(){
		return this.processMemory;
	}
	public String getProcessName(){
		return this.processName;
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
	public int getTimeSpent(){
		return this.timeSpent;
	}
	public ArrayList<String> getProcessCommands(){
		return this.processCommands;
	}
	public String getNextCommand(){
		if(this.processCommands.size()>0){
		return this.processCommands.remove(0);
		}else return null;
	}
	
	
	//Incrementing methods for process
	public void incrementTime(){
		this.timeSpent++;
	}
	public void incrementIORequests(){
		this.ioRequestsPerformed++;
	}
	public void decrementCalcTime(){
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
		GuiPrompt.println("PROCESS NAME: " + processName);
		GuiPrompt.println("PROCESS STATE: " + processState);
		GuiPrompt.println("CPU TIME LEFT FOR PROCESS COMPLETION: " + this.timeLeftToComplete);
		GuiPrompt.println("Arrival Time: " + this.arrivalTime);
		GuiPrompt.println("PCB: " + this.getProcessCommands().toString());
		GuiPrompt.println("______________________________________");
		
	}
	
	
	
}
