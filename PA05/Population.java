package lesson19;

import java.util.Random;

/**
 * A population is a list of people whose types are Person or some subclass of
 * Person.
 * 
 * The Population class can have subclasses of different kinds of populations!
 * 
 */
public abstract class Population {
	public Person[] people;
	private int numPeople = 0;
	private Random random = new Random();

	public Population(int n) {
		this.people = new Person[n];
	}
	
	public abstract void createPeople();

	public void addPerson(Person p) {
		people[numPeople++] = p;
	}

	public int getSize() {
		return numPeople;
	}

	/** this randomly places the people on the grid */
	public void placePeople(Country country) {
		for (int k = 0; k < numPeople; k++) {
			Person p = people[k];
			p.country = country;

			int i = random.nextInt(country.places.length);
			int j = random.nextInt(country.places[i].length);
			while (country.places[i][j] != null) {
				i = random.nextInt(country.places.length);
				j = random.nextInt(country.places[i].length);
			}

			p.setPosition(i, j);
			country.places[i][j] = p;
		}
		people[0].infected = true;
	}

}
