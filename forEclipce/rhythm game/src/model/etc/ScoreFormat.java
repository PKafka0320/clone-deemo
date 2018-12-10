package etc;

public class ScoreFormat {
	private double score;
	private int combo;
	private int maxCombo;
	
	public ScoreFormat() {
		score = 0;
		combo = 0;
	}
	
	public boolean[] compair(ScoreFormat other) {
		boolean[] ans = {true, true};
		if(this.score != other.score) ans[0] = false;
		if(this.combo != other.combo) ans[1] = false;
		return ans;
	}
	
	public void setScore(double score) {
		this.score = score;
	}
	
	public void setCombo(int combo) {
		this.combo = combo;
	}
	
	public double getScore() {
		return this.score;
	}
	
	public int getCombo() {
		return this.combo;
	}
	
	public int getMaxCombo() {
		return this.maxCombo;
	}
	
	public void addScore(double addedScoreVal) {
		this.score += addedScoreVal;
	}
	
	public void addCombo(int addedComboVal) {
		this.combo += addedComboVal;
		if(this.combo > this.maxCombo) {
			this.maxCombo = this.combo;
		}
	}
	
	public void resetCombo() {
		this.combo = 0;
	}
	
	public String toString() {
		return this.score+", "+this.combo+", "+this.maxCombo;
	}
}
