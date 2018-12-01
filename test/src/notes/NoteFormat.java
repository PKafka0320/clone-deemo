package notes;

public class NoteFormat{
	boolean[] line = new boolean[6];
	long timing;
	
	public NoteFormat(boolean[] line, long timing) {
		for(int i=0; i<6; i++) {
			this.line[i] = line[i];
		}
		this.timing = timing;
	}
	public boolean[] getline() {
		return line;
	}
	public long gettiming() {
		return timing;
	}
}