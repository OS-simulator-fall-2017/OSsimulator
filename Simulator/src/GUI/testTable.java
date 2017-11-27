package GUI;

public class testTable {

    private String name;
    private double arrival;
    private int run;

    public testTable(){
        this.name = "";
        this.arrival = 0;
        this.run = 0;
    }

    public testTable(String name, double arrival, int run){
        this.name = name;
        this.arrival = arrival;
        this.run = run;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getArrival() {
        return arrival;
    }

    public void setArrival(double arrival) {
        this.arrival = arrival;
    }

    public int getRun() {
        return run;
    }

    public void setRun(int run) {
        this.run = run;
    }

}