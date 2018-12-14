package calculater;

import format.CollisionTypeFormat;
import format.ScoreFormat;

//점수는 각 노트의 판정에 따라 차등적으로 올라감
public class ScoreCalc {
	int noteAmount;
	static double maxScore = 1000000.0;

	double sWeight;
	double cWeight;

	public ScoreCalc(int noteAmount) {
		this.noteAmount = noteAmount;

		this.cWeight = (maxScore/10*2) / ((noteAmount + 1) * noteAmount / 2.0);
		this.sWeight = (maxScore/10*8) / noteAmount;
	}

	public double getaddedScore(ScoreFormat score, CollisionTypeFormat collisionType) {
		double i = 0;
		if (collisionType.getCollisionType() == "perfect")
			i = 1;
		else if (collisionType.getCollisionType() == "great")
			i = 0.66;
		else if (collisionType.getCollisionType() == "bad")
			i = 0.33;
		return score.getCombo()*cWeight + sWeight*i;
	}
}
