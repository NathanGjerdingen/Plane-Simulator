# Plane-Simulator

Write a simulation program for a small airport that has only one runway. There will be a queue of planes waiting to land and a queue 
of planes waiting to take off. However, only one plane can use the runway at a time, so there can be only one takeoff or one landing 
in progress at any one time. Assume that all takeoffs take the same amount of time. Assume that all landings take the same amount of 
time, but this need not be the same as the takeoff time. Assume that planes arrive for landing at random times but with a specified 
probability of a plane arriving during any given minute. Similarly, assume that planes arrive at the takeoff queue at random times but 
with a (possibly different) specified probability of a departure. (Despite the fact that takeoffs and landings are scheduled, delays 
make this a reasonable assumption.) Since it is more expensive and more dangerous to keep a plane waiting to land than it is to keep a 
plane waiting to take off, landings will have priority over takeoffs. Thus, as long as some plane is waiting to land, no plane can take 
off. Use a clock that is an integer variable that counts the number of minutes simulated. Use a random number generator to simulate 
arrival and departure times of airplanes.

This simulation can be used, among other things, for deciding when the air traffic has become so heavy that a second runway must be 
built. Hence, the simulation will simulate disaster conditions in which planes crash because they run out of fuel while waiting too 
long in the landing queue. By examining the simulated situation, the airport authority hopes to avoid real tragedy. Assume all planes 
can remain in the queue for the same amount of time before they run out of fuel. If a plane runs out of fuel, your simulation will not 
discover this until the simulated plane is removed from the queue; at that point, the fact that the plane crashed is recorded, that 
plane is discarded, and the next plane is processed. A crashed plane is not considered in the calculation of waiting time. At the end 
of the simulated time, the landing queue is examined to see whether any of the planes in the simulated queue have crashed. You can 
disregard the planes left in the queue at the end of the simulation, other than those that crashed for lack of sufficient fuel. Use the 
following input and output specifications:

Input: (1) The amount of time needed for one plane to land; (2) the amount of time needed for one plane to take off; (3) the average 
amount of time between arrival of planes to the landing queue; (4) the average amount of time between arrival of planes to the takeoff 
queue; (5) the maximum amount of time that a plane can stay in the landing queue without running out of fuel and crashing; and (6) the 
total length of time to be simulated.

Output: (1) The number of planes that took off in the simulated time; (2) the number of planes that landed in the simulated time; (3) 
the number of planes that crashed because they ran out of fuel before they could land; (4) the average time that a plane spent in the 
takeoff queue; and (5) the average time that a plane spent in the landing queue.
