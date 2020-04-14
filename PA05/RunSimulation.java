package lesson19;

import java.util.Random;

/**
 * 
 * @author tim
 *
 */

public class RunSimulation {
	private static int MAX_TICKS = 1000;

	private Random random = new Random();

	public static void main(String[] args) {

		int width = Integer.parseInt(args[0]);
		int height = Integer.parseInt(args[1]);
		int numStayHome = Integer.parseInt(args[2]);
		int numEssential = Integer.parseInt(args[3]);
		int numSkeptic = Integer.parseInt(args[4]);
		int numFlier = Integer.parseInt(args[5]);
		int numYoung = Integer.parseInt(args[6]);


		Population population;

		population = new MixedPopulation(numStayHome, numEssential, numSkeptic, numFlier, numYoung);
		population.createPeople();

		Country country = new Country(width, height);
		country.population = population;
		population.placePeople(country);

		System.out.println("Initial State of the Country");
		country.printCountry();

		System.out.println("\nTracking the Infection");
		for (int k = 0; k < MAX_TICKS; k++) {
			country.simulateOneStep();
			country.printState(k);

			if (country.numInfected == 0) {
				break;

			}
		}
		System.out.println("\nFinal State of the Country");
		country.printCountry();

	}

	public static int maxTick() {
		return MAX_TICKS;
	}

}
