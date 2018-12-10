package scoreController;

import java.util.ArrayList;

import etc.CollisionTypeFormat;
import etc.ScoreFormat;

//������ bad�̻��϶� ����
//�޺��� perfect, great�϶��� ������, bad���ϰ� ������ �޺��� ����
public class ScoreController {
	ScoreFormat score = new ScoreFormat();
	ScoreCalc sCalc = new ScoreCalc();
	
	ArrayList<String> availableTypeForCombo = new ArrayList<String>( CollisionTypeFormat.getCollisionTypeList().subList(2, 4) );
	ArrayList<String> availableTypeForScore = new ArrayList<String>( CollisionTypeFormat.getCollisionTypeList().subList(1, 4) );
	
	public ScoreController() {
	}
	
	public void updateCombo( CollisionTypeFormat collisionType ) {
		if( availableTypeForCombo.contains( collisionType.getCollisionType() ) ) {
			score.addCombo(1);
		}
		else {
			score.resetCombo();
		}
	}
	
	public void updateScore( CollisionTypeFormat collisionType ) {
		if( availableTypeForScore.contains( collisionType.getCollisionType() ) ) {
			double addedScore = sCalc.getaddedScore(score, collisionType);
			score.addScore(addedScore);
		}
	}

	public boolean update( CollisionTypeFormat collisionType ) {
		try {
			updateCombo(collisionType);
			updateScore(collisionType);
			return true;
		}
		catch (Exception e) {
			System.out.println("Error Type "+e);
			return false;
		}
	}
	
	public ScoreFormat getScoreFormat() {
		return this.score;
	}
}
