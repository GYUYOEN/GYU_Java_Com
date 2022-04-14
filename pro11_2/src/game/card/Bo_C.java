package game.card;

public class Bo_C extends Hand_C {

	@Override
	public int compare(Hand_C otherHand) {
		int result = 0;
		
		if(otherHand instanceof Bo_C) {
			result = 0;
		} else if(otherHand instanceof Gawi_C) {
			result = -1;
		} else if(otherHand instanceof Bawi_C) {
			result = 1;
		}
		
		return result;
	}

	@Override
	public Hand_C cheat(Hand_C otherHand) {
		Hand_C newHand = null;
		
		if(otherHand instanceof Gawi_C) {
			newHand = new Bawi_C();
		} else if(otherHand instanceof Bawi_C) {
			newHand = new Bo_C();
		} else if(otherHand instanceof Bo_C) {
			newHand = new Gawi_C();
		}
		
		return newHand;
	}

}
