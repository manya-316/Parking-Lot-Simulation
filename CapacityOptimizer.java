public class CapacityOptimizer {
	private static final int NUM_RUNS = 10;

	private static final double THRESHOLD = 5.0d;

	public static int getOptimalNumberOfSpots(int hourlyRate) {
		int lotSize = 1;
		
		while(true){
			System.out.println("==== SETTING LOT CAPACITY TO: " + lotSize + " ====");
			double totalOF = 0;


			for (int i = 1; i < NUM_RUNS+1; i++){
				ParkingLot newLot = new ParkingLot(lotSize);
				long startTime = System.currentTimeMillis();
				Simulator lotSim = new Simulator(newLot, hourlyRate, Simulator.SIMULATION_DURATION);
				lotSim.simulate();
				int queueLength = lotSim.getIncomingQueueSize();
				totalOF += queueLength;
				long endTime = System.currentTimeMillis();

				System.out.println("Simulation run " + i + " (" + (endTime-startTime) + "ms); Queue length at the end of simulation run: " + queueLength);
			}
			
			double avOf = totalOF / NUM_RUNS;
			System.out.println("Average overflow count: " + avOf);

			if(avOf <= THRESHOLD){
				System.out.println("Average overflow count: " + avOf);
				return lotSize;
			}else{
				lotSize++;
			}
		}
		
	}

	
	public static void main(String args[]) {
	
		StudentInfo.display();

		long mainStart = System.currentTimeMillis();
		
		if (args.length < 1) {
			System.out.println("Usage: java CapacityOptimizer <hourly rate of arrival>");
			System.out.println("Example: java CapacityOptimizer 11");
			return;
		}

		if (!args[0].matches("\\d+")) {
			System.out.println("The hourly rate of arrival should be a positive integer!");
			return;
		}
		
		int hourlyRate = Integer.parseInt(args[0]);
		
		int lotSize = getOptimalNumberOfSpots(hourlyRate);

		System.out.println();
		System.out.println("SIMULATION IS COMPLETE!");
		System.out.println("The smallest number of parking spots required: " + lotSize);

		long mainEnd = System.currentTimeMillis();

		System.out.println("Total execution time: " + ((mainEnd - mainStart) / 1000f) + " seconds");

	}
}