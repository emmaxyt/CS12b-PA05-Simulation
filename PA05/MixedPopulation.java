package lesson19;

public class MixedPopulation extends Population {
	int numShelterInPlace;
	int numEssential;
	int numOthers;
	int numFlier;
	int numYoung;

	public MixedPopulation(int numShelterInPlace, int numEssential, int numOthers, int numFlier, int numYoung) {
		super(numShelterInPlace + numEssential + numOthers + numFlier + numYoung);
		this.numShelterInPlace = numShelterInPlace;
		this.numEssential = numEssential;
		this.numOthers = numOthers;
		this.numFlier = numFlier;
		this.numYoung = numYoung;
	}

	public void createPeople() {
		for (int i = 0; i < this.numShelterInPlace; i++) {
			super.addPerson(new StayAtHome());
		}
		for (int i = 0; i < this.numEssential; i++) {
			super.addPerson(new StayAtHomeIfSick());
		}
		for (int i = 0; i < this.numOthers; i++) {
			super.addPerson(new Skeptic());
		}
		for (int i = 0; i < this.numFlier; i++) {
			super.addPerson(new FrequentFlier());
		}
		for (int i = 0; i < this.numYoung; i++) {
			super.addPerson(new YoungPeople());
		}
	}
}
