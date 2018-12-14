package format;

public class NoteFormat {
	int line;
	double timing;
	
	public NoteFormat(int line, double timing) {
		this.line = line;
		this.timing = timing/1000.0;
	}
	
	public NoteFormat(GameInputFormat input) {
		this.convertNoteFormat(input);
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
	
	public NoteFormat convertNoteFormat(GameInputFormat input) {
		return new NoteFormat(input.getLine(), input.getTiming());
	}
}
