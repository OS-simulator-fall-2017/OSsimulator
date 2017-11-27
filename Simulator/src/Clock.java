package Components;

public class Clock {
    
	private static int time = 0;
	
    
    public static int getClock(){
    	return time;
    }
    
    public static int tickClock() {
        return time++;
    }

    public static void resetClock() {
        time = 0;
    }
}