package infoRetrievalHW2;

import java.util.Arrays;
import java.util.Collections;

public class TargetDistance {
	
	double distance;//euclid distance between current data and query
	int positionInTraining;//the position at training input sample
	
	public TargetDistance(double d, int positionInTraining)
	{//constructor
		this.distance = d;
		//this.actualTarget = actual;
		
		this.positionInTraining = positionInTraining;
	}
	public double getDistance() {
		return distance;
	}
	public void setDistance(double distance) {
		this.distance = distance;
	}
	
	public int getPositionInTraining() {
		return positionInTraining;
	}
	public void setPositionInTraining(int positionInTraining) {
		this.positionInTraining = positionInTraining;
	}

	
}
