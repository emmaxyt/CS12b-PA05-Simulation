package lesson19;

public class AnalyzeSimulation {
	private static int MAX_TICKS = RunSimulation.maxTick();

	public static void main(String[] args) {
		int width = Integer.parseInt(args[0]);
		int height = Integer.parseInt(args[1]);
		int numStayHome = Integer.parseInt(args[2]);
		int numEssential = Integer.parseInt(args[3]);
		int numSkeptic = Integer.parseInt(args[4]);
		int numFlier = Integer.parseInt(args[5]);
		int numYoung = Integer.parseInt(args[6]);
		int numRepetition = Integer.parseInt(args[7]);
		int numOfDay = 0;
		int peakInfectedLevel = 0;
		int count = 0;
		int numOfInfected;
		int infectedBefore;
		int infectedLevel;
		int ttlDay = 0;
		int ttlInfected = 0;
		int ttlPeak = 0;
		double averageDay;
		double averageInfected;
		double averagePeak;

		for (int i = 0; i < numRepetition; i++) {
			count++;
			peakInfectedLevel = 0;
			Population population;
			population = new MixedPopulation(numStayHome, numEssential, numSkeptic, numFlier, numYoung);
			population.createPeople();

			Country country = new Country(width, height);
			country.population = population;
			population.placePeople(country);

			numOfInfected = country.numInfected + country.numRecovered;

			for (int k = 0; k < MAX_TICKS; k++) {
				infectedBefore = country.numInfected + country.numRecovered;
				country.simulateOneStep();
				infectedLevel = country.numInfected + country.numRecovered - infectedBefore;
				numOfInfected += infectedLevel;
				if (infectedLevel > peakInfectedLevel) {
					peakInfectedLevel = infectedLevel;
				}

				if (country.numInfected == 0) {
					numOfDay = k + 1;
					break;

				}
			}
			ttlDay += numOfDay;
			ttlInfected += numOfInfected;
			ttlPeak += peakInfectedLevel;
			System.out.printf("%-11s %-3d %-2s", "Simulation ", count, ":");
			System.out.printf("%-16s %-3d", "number of days:", numOfDay);
			System.out.printf("%-20s %-3d", "number of infected:", numOfInfected);
			System.out.printf("%-21s %-3d %n", "peak infected level:", peakInfectedLevel);
		}
		averageDay = ttlDay / numRepetition;
		averageInfected = ttlInfected / numRepetition;
		averagePeak = ttlPeak / numRepetition;
		System.out.println("the average number of days it takes until there are no new infections: " + averageDay);
		System.out.println("the average number of people infected (and eventually recovered): " + averageInfected);
		System.out.println("the maximum number of people infected at any one time: " + averagePeak);
	}
}
