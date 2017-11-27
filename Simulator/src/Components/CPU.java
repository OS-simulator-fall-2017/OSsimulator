package Components;

public class CPU {

	private Process currentProcess;
	
	
	public void setCurrentProcess(Process p){
		currentProcess = p;
		this.currentProcess.setState(ProcessState.RUN);
	}
	
	
	
	/*
	public Process execute(){
		currentProcess.incrementTime();
		
		if(currentProcess.getCalcTime()==0){
			String command = currentProcess.getProcessCommands().get(0).toUpperCase();
			
			switch(command){
			case ("CALCULATE"):
				currentProcess.setCalcTime(Integer.parseInt(currentProcess.getProcessCommands().get(1)));
				return currentProcess;
			case ("YIELD"):
				currentProcess.setState(ProcessState.READY);
			case ("IO"):
				currentProcess.setState(ProcessState.WAIT);
			case ("OUT"):
				currentProcess.printProcessInfo();
			}
		}
		
	}
	*/
	
	
	
	
	
}
