package scoreController;

import etc.CollisionTypeFormat;
import etc.ScoreFormat;

//점수는 각 노트의 판정에 따라 차등적으로 올라감
public class ScoreCalc {
	
	public ScoreCalc() {
	}
	
	public double getaddedScore(ScoreFormat score, CollisionTypeFormat collisionType) {
		return 10;
	}
}
