package round_robin_processing;

public class Process {

	private Integer pid;
	private Double arrivalTime;
	private Double burstTime;
	private Double executionTime;
	private Double waitTime;
	Double turnaroundTime;

	public Process() {
		/*
		 * Constructor method.
		 */

		pid = 1;
		arrivalTime = 0.0;
		burstTime = 0.0;
		executionTime = 0.0;
		waitTime = 0.0;
		turnaroundTime = 0.0;
	}

    /**
     * @return the pid
     */
    public Integer getPid() {
        return pid;
    }

    /**
     * @return the arrivalTime
     */
    public Double getArrivalTime() {
        return arrivalTime;
    }

    /**
     * @return the burstTime
     */
    public Double getBurstTime() {
        return burstTime;
    }

    /**
     * @return the executionTime
     */
    public Double getExecutionTime() {
        return executionTime;
    }

    /**
     * @return the waitTime
     */
    public Double getWaitTime() {
        return waitTime;
    }

    /**
     * @param pid the pid to set
     */
    public void setPid(Integer pid) {
        this.pid = pid;
    }

    /**
     * @param arrivalTime the arrivalTime to set
     */
    public void setArrivalTime(Double arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    /**
     * @param burstTime the burstTime to set
     */
    public void setBurstTime(Double burstTime) {
        this.burstTime = burstTime;
    }

    /**
     * @param executionTime the executionTime to set
     */
    public void setExecutionTime(Double executionTime) {
        this.executionTime = executionTime;
    }

    /**
     * @param waitTime the waitTime to set
     */
    public void setWaitTime(Double waitTime) {
        this.waitTime = waitTime;
    }
        
        
}