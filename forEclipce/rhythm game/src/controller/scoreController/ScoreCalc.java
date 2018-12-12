package scoreController;

import etc.CollisionTypeFormat;
import etc.ScoreFormat;

//점수는 각 노트의 판정에 따라 차등적으로 올라감
public class ScoreCalc {
	int noteAmount;
	static double maxScore = 1000000.0;

	double weight;
	
	public ScoreCalc(int noteAmount) {
		this.noteAmount = noteAmount;
		
		this.weight = maxScore/( (noteAmount + 1)*noteAmount/2.0 );
	}
	
	public double getaddedScore(ScoreFormat score, CollisionTypeFormat collisionType) {
		double i = 0;
		if(collisionType.getCollisionType() == "perfect") i=1;
		else if(collisionType.getCollisionType() == "great") i=0.66;
		else if(collisionType.getCollisionType() == "bad") i = 0.33;
		return score.getCombo()*( this.weight*i  );
	}
}
