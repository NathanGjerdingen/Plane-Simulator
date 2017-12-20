import java.util.ArrayList;

public class Runway {

	/********************************************************************************
	 *	Description:	Initialize needed variables for Runway class...				* 
	 ********************************************************************************/
	
	//Queues...
	public GenericLLQueue<Plane> arrivalQueue = new GenericLLQueue<Plane>();
	public GenericLLQueue<Plane> departureQueue = new GenericLLQueue<Plane>();
	public GenericLLQueue<Plane> runway = new GenericLLQueue<Plane>();
	
	//ArrayList...
	public ArrayList<Plane> flightLog = new ArrayList<Plane>();
	
	//Instance Variables...
	public static int runwayWaitTime;
	public int planesLanded;
	public int planesDeparted;
	public int planesCrashed;
	
	/********************************************************************************
	 *	Description:	Constructor for the class.									* 
	 ********************************************************************************/
	
	public Runway() {
		runwayWaitTime = 0;
	}
	
	/********************************************************************************
	 *	Description:	Boolean statements that return the state of the Runway...	* 
	 ********************************************************************************/
	
	public boolean isBusy() {
		if (runwayWaitTime == 0) {
			return false;
		}
		return true;
	}
	
	public boolean isEmpty() {
		if (runway.isEmpty()) {
			return true;
		} 
		return false;
	}
	
	/********************************************************************************
	 * 	Description:	Removes a plane from the arriavlQueue and moves it to       *
	 * 					the Runway for landing. Then checks to see if the plane 	*
	 * 					'crashed' or not. If not, increments planesLanded and 		*
	 * 					removes that plane from the Runway and to the flightLog.	*
	 ********************************************************************************/
	
	public void landPlane() {
		
		//Remove the plane from arrival queue and rename it 'currentlyLanding'...
		Plane currentlyLanding = arrivalQueue.remove();
		
		//Add the plane taken from arrivalQueue to the Runway...
		runway.add(currentlyLanding);
		
		//Store the passed waiting time for landing in 'runwayWaitTime'...
		runwayWaitTime = currentlyLanding.getLandingTime();
		
		// Check for plane crash
		if(currentlyLanding.endFlight() >= Simulator.MAX_FLY_TIME) {
			
			//Increment and remove from Runway...
			planesCrashed++;
			runway.remove(); 
			
		} else {
			
			//Increment and end current planes flight...
			planesLanded++;
			currentlyLanding.endFlight();
			
			//Remove from Runway and add Plane to flightLog for metrics...
			flightLog.add(runway.remove()); 
			
		}	 

	}
	
	/********************************************************************************
	 * 	Description:	Methods for looping through arrivalQueue and departureQueue *
	 * 					to increment the wait time counters for each Plane left in 	*
	 * 					their respective Queue.										*
	 * 																				*
	 * 	Precondition:	Queue size must be greater than 0.							*
	 ********************************************************************************/
	
	public void incrementArrivalWaitTime() {
		if (arrivalQueue.size() > 0) {
			for (int i = 0; i < arrivalQueue.size(); i++) {
				arrivalQueue.atPossition(i).getData().arrivalWaitTime++;
			}
		}
	}
	
	public void incrementTakeOffWaitTime() {
		if (departureQueue.size() > 0) {
			for (int i = 0; i < departureQueue.size(); i++) {
				departureQueue.atPossition(i).getData().takeOffWaitTime++;
			}
		}
	}
	
	/********************************************************************************
	 * 	Description:	Removs a plane from the Departing queue and then stores the *
	 * 					plane in the flightLog.									   	*
	 ********************************************************************************/
	
	public void departPlane() {
		
		//Remove the plane from departureQueue queue and rename it 'currentlyDeparting'...
		Plane currentlyDeparting = departureQueue.remove();
		
		//Add the plane taken from departureQueue to the Runway...
		runway.add(currentlyDeparting);
		
		//Store the passed plane takeOffTime for departing in 'runwayWaitTime'...
		runwayWaitTime = currentlyDeparting.getTakeOffTime();
		
		//Increment the number of successful landings...
		planesDeparted++;
		
		//StartFlight
		currentlyDeparting.startFlight();
		
		//Remove plane from Runway and move it to FlightLog for data tracking
		flightLog.add(runway.remove());	
	
	}

}
