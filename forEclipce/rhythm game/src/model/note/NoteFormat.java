package note;

public class NoteFormat {
	int line;
	double timing;
	
	public NoteFormat(int line, double timing) {
		this.line = line;
		this.timing = timing/1000.0;
	}
	
	public int getLine() {
		return this.line;
	}
	
	public double getTiming() {
		return this.timing;
	}
	
	public String toString() {
		return "("+this.line+", "+this.timing+")";
	}
}
