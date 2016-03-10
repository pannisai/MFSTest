package mfs.biller.util;

import java.sql.Timestamp;

public class Timer {
	private final long lgStartTime = System.currentTimeMillis();
	private long lgEndTime;
	private long lgUsedTime;
	private String ID = null;

	public Timer(String ID){this.ID = ID;}
	
	public String getStartTime(){
		return "("+ID+"-Start) "+ new Timestamp(lgStartTime);
	}
	
	public String getStopTime(){
		lgEndTime =  System.currentTimeMillis();
		return "("+ID+"-Stop) Start:"+ new Timestamp(lgStartTime) +" End:"+ new Timestamp(lgEndTime) ;
	}



	// Sample to use Timer

}
