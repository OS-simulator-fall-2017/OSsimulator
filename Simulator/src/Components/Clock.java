package Components;

public class Clock {
    
	private static int time = 0;
	
    //Each tick of the clock will be one 'looped-cycle'
    public static int getClock(){
    	return time;
    }
    
    public static int tickClock() {
        return time++;
    }

    public static void resetClock() {
        time = 0;
    }
    
    public static void setClock(int t){
    	time = t;
    }
}