package lesson19;

import java.util.Random;
// This subclass represents young people who has lower probability to be infected and needs less time to get recovered once they are infected.
public class YoungPeople extends Person {
	private Random random = new Random();
	double infectionProb = 0.3;
	int infectionTime = -1;
	int recoveryTime = 4;

	public YoungPeople() {
		super();
	}

	public void tryToMove() {
		if (!this.infected) {
			tryToMoveRandomly();
		}
	}

	public void tryToMoveRandomly() {
		int dx = (random.nextInt(3) - 1) * 2; // -2,0,2
		int dy = (random.nextInt(3) - 1) * 2; // -2,0,2
		if (isOK(this.x + dx, this.y + dy, this.country)) {
			this.moveTo(this.x + dx, this.y + dy);
		}
	}

}
