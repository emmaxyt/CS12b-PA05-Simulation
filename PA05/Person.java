package lesson19;

import java.util.Random;

/**
 * The Person class models infected people in a 2d grid a person has a unique
 * id, an (x,y) location in a country, and an infection status. The could be
 * normal, infected, exposed, or recovered.
 * 
 * Each person has an individual probablity of being infected when coming in
 * contact with an infected person and they have an individual recovery time
 * after they are infected, at which point they are no longer infectious. They
 * can move one unit horizontally, vertically, or diagonally in the grid.
 * 
 * The tick() method simulates them moving one step in the grid in some random
 * direction as long as no one else is there.
 * 
 */

public abstract class Person {
	private Random random = new Random();

	private static int counter = 1;
	int id = 0;

	int x;
	int y;
	Country country;

	boolean infected = false;
	boolean exposed = false;
	boolean recovered = false;
	double infectionProb = 0.5;
	int age = 0;
	int infectionTime = -1;
	int recoveryTime = 5;

	/**
	*/
	public Person(int x, int y, Country country) {
		this.x = x;
		this.y = y;
		this.id = Person.counter++;
		this.country = country;
	}

	public Person() {
		this.x = -1;
		this.y = -1;
		this.id = Person.counter++;
		this.country = null;
	}

	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * print a character for the Person representing their infection status
	 */
	public String toString() {
		String r = " ";
		if (this.recovered) {
			r = "r";
		} else if (this.infected) {
			r = "X";
		} else if (this.exposed) {
			r = "E";
		} else {
			r = "O";
		}
		return r;
	}

	/**
	 * tick() simulates the persons movement in one unit of time. it will change
	 * this.x and this.y We'll first just randomly try to move one step
	 */
	public void tick() {
		this.tryToMove();
		this.checkForInfection();

	}

	public abstract void tryToMove();

	/**
	 * try to move one step in a random direction. if they way is blocked then don't
	 * move.
	 */
	public void tryToMoveRandomly() {
		int dx = random.nextInt(3) - 1; // -1,0,1
		int dy = random.nextInt(3) - 1; // -1,0,1
		if (isOK(this.x + dx, this.y + dy, this.country)) {
			this.moveTo(this.x + dx, this.y + dy);
		}
	}

	/**
	 * update their infection status. They move from normal to infected or from
	 * infected to recovered.
	 */
	public void checkForInfection() {
		if (this.exposed && !this.infected) {
			this.infected = true;
			this.infectionTime = this.age;
		}
		this.age++;
		if (infected && !this.recovered && (this.age - this.infectionTime > this.recoveryTime)) {
			this.recovered = true;
		}
	}

	/**
	 * this is called if someone is near an infected person a random number is
	 * generated to see if they have become exposed to the virus. In the next step
	 * they will become infected if they were exposed.
	 */
	public void infect(Person p) {
		if (Math.random() <= this.infectionProb) {
			if (!this.infected) {
				this.exposed = true;
			}
		}
	}

	/**
	 * this is called by an infected person and it visits all neighbors and infects
	 * them
	 */
	public void infectNeighbors() {

		if (this.infected && (this.age - this.infectionTime < this.recoveryTime)) {
			for (int i = this.x - 1; i <= this.x + 1; i++) {
				for (int j = this.y - 1; j <= this.y + 1; j++) {
					if (inRange(i, j, country)) {
						Person p = country.places[i][j];
						if (p != null && this.infected) {
							p.infect(this);
						}
					}
				}
			}
		}
	}

	public boolean inRange(int i, int j, Country country) {
		return (i >= 0 && i < country.places.length && j >= 0 && j < country.places[i].length);
	}

	/**
	 * this moves the person from one place to another
	 */
	public void moveTo(int a, int b) {
		this.country.places[this.x][this.y] = null;
		if (this.country.places[a][b] != null) {
			throw new RuntimeException("a Person can't move to an occupied position.");
		}
		this.country.places[a][b] = this;
		this.x = a;
		this.y = b;
	}

	/**
	 * this tests to see if the position (a,b) is on the grid for the country and if
	 * it has the value null, so that a Person could move there.
	 */
	public boolean isOK(int a, int b, Country country) {
		if (a < 0 || a >= country.places.length || b < 0 || b >= country.places[0].length) {
			return false;
		} else if (country.places[a][b] != null) {
			return false;
		} else {
			return true;
		}

	}

}
