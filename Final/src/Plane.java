public class Plane {
	
	/********************************************************************************
	 *	Description:	Initialize needed variables for Plane Class.				* 
	 ********************************************************************************/
	
	private int landingTime; 		// The amount of time it takes for the plane to land...
	private int takeOffTime; 		// The amount of time it takes for a plane to take off...
	private int maxFlightTime; 		// The amount of time a plane can fly before it runs out of fuel...
	private int startTime; 			// Records start of Flight time...
	public int arrivalWaitTime; 	// Times how long a plane was in the air...
	public int takeOffWaitTime; 	// Times how long a plane was on the ground...
	private int flightTimePlane; 	// Variable for average flight time after simulation...
	private int groundTime;			// Variable for average ground time after simulation...
	
	/********************************************************************************
	 *	Description:	Constructor for class.										* 
	 ********************************************************************************/

	public Plane(int landingTime, int takeOffTime, int maxFlightTime, int time) {
		this.landingTime = Simulator.LANDING_TIME;
		this.takeOffTime = takeOffTime;
		this.maxFlightTime = maxFlightTime;
		this.startTime = time;
	}
	
	/********************************************************************************
	 *	Description:	Accessors and Mutators...									* 
	 ********************************************************************************/
	
	public int getGroundTime() {
		return groundTime;
	}

	public void setGroundTime(int groundTime) {
		this.groundTime = groundTime;
	}

	public int getFlightTime() {
		return flightTimePlane;
	}

	public void setFlightTime(int flightTime) {
		this.flightTimePlane = flightTime;
	}

	public int getStartTime() {
		return startTime;
	}

	public int getArrivalWaitTime() {
		return arrivalWaitTime;
	}

	public void setArrivalWaitTime(int arrivalWaitTime) {
		this.arrivalWaitTime = arrivalWaitTime;
	}

	public void setStartTime(int startTime) {
		this.startTime = startTime;
	}

	public int getLandingTime() {
		return landingTime;
	}

	public void setLandingTime(int landingTime) {
		this.landingTime = landingTime;
	}

	public int getTakeOffTime() {
		return takeOffTime;
	}

	public void setTakeOffTime(int takeOffTime) {
		this.takeOffTime = takeOffTime;
	}

	public int getMaxFlightTime() {
		return maxFlightTime;
	}

	public void setMaxFlightTime(int maxFlightTime) {
		this.maxFlightTime = maxFlightTime;
	}
	
	/********************************************************************************
	 *	Description: Calculates the total flight time a plane has been in the air.	* 
	 ********************************************************************************/
	
	public int endFlight() {	
		flightTimePlane = (Simulator.CURRENT_TIME- startTime) + Runway.runwayWaitTime + arrivalWaitTime;
		return flightTimePlane;
	}
	
	/********************************************************************************
	 *	Description: Calculates the total time a plane spend waiting to takeOff.	* 
	 ********************************************************************************/
	
	public int startFlight() {	// Calculates the total flight duration
		groundTime = ((Simulator.CURRENT_TIME- startTime)+ Runway.runwayWaitTime + takeOffWaitTime);
		return groundTime;
	}
	    
}
