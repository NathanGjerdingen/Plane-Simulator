import java.util.*;

public class Simulator {

	/********************************************************************************
	 *	Description:	Initialize needed variables for Simulator class...			* 
	 ********************************************************************************/
	
	public static int DEPART_PROB=0;
	public static int ARRIVE_PROB=0;
	public static int LANDING_TIME=0;
	public static int CURRENT_TIME=0;
	public static int MAX_FLY_TIME=0;
	public static int TAKEOFF_TIME=0;
	
	Runway runway = new Runway();
	
	/********************************************************************************
	 *	Description:	Random number generator methods for a plane based on the 	*
	 *					users input of probability of 0-100%. Used in 				*
	 *					startSimulation.									        * 
	 ********************************************************************************/
	
	public boolean planeWantsToLand() {	
		int random = (int) (Math.random() * 100);
		if(random <= ARRIVE_PROB) {
			return true;
		}
		return false;
	}
	
	public boolean planeWantsToTakeOff() {
		int random = (int) (Math.random() * 100);
		if(random <= DEPART_PROB) {
			return true;
		}
		return false;
	}

	/********************************************************************************
	 *	Description:	Methods that calculate the Average time of all planes in 	*
	 *					the flightLog spent in the air and on the ground.			*
	 * 																				*
	 * 	Precondition:	FlightLog.size is not 0.									*
	 ********************************************************************************/
																						
	public int avgFlightTime() {
		
		//Check if flightLog is greater than zero...
		if(runway.flightLog.size() > 0) {
			
			//Initialize accumulator for loop... 
			int accumulator = 0;
			
			//Loop through flightLog and accumulate flightTime...
			for (Plane plane : runway.flightLog) {
				accumulator += plane.getFlightTime();
			}
			
			//Calculate and return average...
			return (accumulator / runway.flightLog.size());
			
		} else { 
		
			//Return Zero. Should only occur if flightLog is (or less than) 0...
			return 0;
		
		}
		
	}
	
	public int avgGroundTime() {
		
		//Check if flightLog is greater than zero...
		if(runway.flightLog.size() > 0) {
			
			//Initialize accumulator for loop... 
			int accumulator = 0;
			
			//Loop through flightLog and accumulate groundTime...
			for (Plane plane : runway.flightLog) {
				accumulator += plane.getGroundTime();
			}
			
			//Calculate and return average...
			return (accumulator / runway.flightLog.size());
		
		} else {
			
			//Return Zero. Should only occur if flightLog is (or less than) 0...
			return 0;
		
		}
		
	}

	
	/********************************************************************************
	 *	Description:	The startSimulation method is the main driver for this 		*
	 *					program. It queries random percentage generators 			*
	 *					(planeWantsToLand and planeWantsToTakeOff) to generate 		*
	 *					planes, and then queries the Runway to see if a plane can 	*
	 *					land or take off.						 					*
	 ********************************************************************************/
	
	public void startSimulation(int runTime) {	
		
		//Loop for simulation...
		for (CURRENT_TIME = 0; CURRENT_TIME < runTime; CURRENT_TIME++) {
	
			//Conditional statement to check if plane wants to land. 
			if (planeWantsToLand()) {	
				
				//Create newPlane and add it to the arrivalQueue...
				Plane newPlane = new Plane(LANDING_TIME, TAKEOFF_TIME, MAX_FLY_TIME, CURRENT_TIME);
				runway.arrivalQueue.add(newPlane);	
			
			}
			
			//Conditional statement to check if plane wants to depart...
			if (planeWantsToTakeOff()) {	
				
				//Create newPlane and add it to the departureQueue...
				Plane newPlane = new Plane(LANDING_TIME, TAKEOFF_TIME, MAX_FLY_TIME, CURRENT_TIME);
				runway.departureQueue.add(newPlane);
			
			}
			
			//Conditional statement to if Runway is available for use...
			if (!runway.isBusy() && runway.isEmpty()) {
				
				//Conditional statement that determines land, depart, or do nothing...
				if (!runway.arrivalQueue.isEmpty()) {
					
					//Land the plane currently in the arrivalQueue...
					runway.landPlane();
					
					//Increment any waiting plane's ArrivalWaitTime's...
					if (!runway.arrivalQueue.isEmpty()) {
						runway.incrementArrivalWaitTime();
					}
					
				} else if (!runway.departureQueue.isEmpty()) {
					
					//Depart the plan the plane currently in the departureQueue...
					runway.departPlane();
					
					//Increment any waiting plane's takeOffWaitTime's...
					if (!runway.arrivalQueue.isEmpty()) {
						runway.incrementTakeOffWaitTime();
					}
					
				} else {
					
					//This is where the Runway isn't busy and also has no planes in either Queues...
					
				}
				
			}
			
			//Decrement wait time for Runway...
			if (runway.runwayWaitTime != 0) {
				runway.runwayWaitTime--;	
			}	
			
		}	
		
	}

	
	/********************************************************************************
	 *	Description:	toString prints out the report of the simulator. This is to *
	 *					see how many planes are left in the queues, and how many 	*
	 *					landed or departed after the simulation finished.			*
	 ********************************************************************************/
	
	@Override
	public String toString() {
		
		String accumulator = "\n*****************Report******************";
		accumulator += "\nArrival Queue size after simulation: " + runway.arrivalQueue.size();
		accumulator += "\nDeparture Queue size after simulation: " + runway.departureQueue.size();
		accumulator += "\n\nPlanes Landed: " + runway.planesLanded;
		accumulator += "\nPlanes Departed: " + runway.planesDeparted;
		accumulator += "\nFlightLog Size: " + runway.flightLog.size();
		accumulator += "\nPlanes Crashed: " + runway.planesCrashed;
		accumulator+="\n\n Average Flight Time in Air: " + avgFlightTime();
		accumulator+="\n Average Time waiting to takeOff: " + avgGroundTime();
		return accumulator;
	
	}
	
	/********************************************************************************
	 * 	Description:	Main method for input and testing.							*
	 ********************************************************************************/
	
	public static void main(String[] args) {
		
		System.out.println("********** 1 Runway Airport Simulation **************************");
		System.out.println("-----------------------------------------------------------------");
		Scanner newInput = new Scanner(System.in);
		
		System.out.println("Please enter the landing time for planes...");
		LANDING_TIME = newInput.nextInt();
	
		System.out.println("Please enter the takeoff time for planes...");
		TAKEOFF_TIME = newInput.nextInt();
			
		System.out.println("Please enter probablitiy of a plane requesting to land (0-100%)");
		ARRIVE_PROB = newInput.nextInt();
		
		System.out.println("Please enter probablitiy of a plane requesting to take off (0-100%)");
		DEPART_PROB = newInput.nextInt();
		
		System.out.println("Please enter the maximum flight time for planes...");
		MAX_FLY_TIME = newInput.nextInt();
		
		System.out.println("Please enter the total simular time...");
		int totalSimTime = newInput.nextInt();
		
		//Start simulation
		Simulator sim = new Simulator();
		sim.startSimulation(totalSimTime);
		System.out.println("\n" + sim.toString());
		
	}
	
}
