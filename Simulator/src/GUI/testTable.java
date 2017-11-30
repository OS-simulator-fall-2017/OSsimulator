package GUI;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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
    
    public ObservableList<testTable> getTestTable(){
        ObservableList<testTable> jobs = FXCollections.observableArrayList();
       jobs.add(new testTable("text", 859.00, 20));
        jobs.add(new testTable("test", 2.49, 198));
        jobs.add(new testTable("idk", 99.00, 74));
        jobs.add(new testTable("paintballs", 19.99, 12));
        jobs.add(new testTable("Wrecked", 1.49, 856));
        return jobs;

}