package lesson19;

//this subclass represents a person who moves randomly once every 7 days
public class Move7Days extends Person {
	boolean if7days=true;//the person can move at first
	int countDay=0; //the number of days after the last time of moving
	
	public Move7Days(){
		super();
	}
	
	public void tryToMove() {
		if (if7days) { //move once every 7 days
			super.tryToMoveRandomly();
			if7days=false;
		}else{
			countDay++;
		}
		if (countDay==7){
			if7days=true;
			countDay=0;
		}
	}
}
