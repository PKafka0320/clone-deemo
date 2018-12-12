package scoreController;

import java.util.ArrayList;

import etc.CollisionTypeFormat;
import etc.ScoreFormat;
import gamedrawer.ScoreDrawer;

//점수는 bad이상일때 오름
//콤보는 perfect, great일때만 오르고, bad이하가 나오면 콤보가 끊김
public class ScoreController {
	ScoreFormat score = new ScoreFormat();
	ScoreCalc sCalc;
	ScoreDrawer scoreDrawer;
	
	ArrayList<String> availableTypeForCombo = new ArrayList<String>( CollisionTypeFormat.getCollisionTypeList().subList(2, 4) );
	ArrayList<String> availableTypeForScore = new ArrayList<String>( CollisionTypeFormat.getCollisionTypeList().subList(1, 4) );
	
	public ScoreController(ScoreDrawer scoreDrawer) {
		this.scoreDrawer = scoreDrawer;
	}
	
	public void updateCombo( CollisionTypeFormat collisionType, double currentTime ) {
		if( availableTypeForCombo.contains( collisionType.getCollisionType() ) ) {
			score.addCombo(1);
			scoreDrawer.drawCombo(score, currentTime, collisionType);
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

	public boolean update( CollisionTypeFormat collisionType, double currentTime ) {
		try {
			updateCombo(collisionType, currentTime);
			updateScore(collisionType);
			return true;
		}
		catch (Exception e) {
			System.out.println("Error Type "+e+" in ScoreController");
			return false;
		}
	}
	
	public ScoreFormat getScoreFormat() {
		return this.score;
	}
	
	public void setScoreCalc(ScoreCalc sCalc) {
		this.sCalc = sCalc;
	}
}
