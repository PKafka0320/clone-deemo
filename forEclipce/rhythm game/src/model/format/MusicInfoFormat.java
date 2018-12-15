package format;

import java.io.File;
import java.util.Queue;

import javafx.scene.media.Media;
import reader.NoteReader;

public class MusicInfoFormat {
	NoteReader nReader;
	
	private Queue<NoteFormat> notes;
	private ScoreFormat score;
	
	private String musicName;
	private int noteAmount;
	private Media musicSource;
	
	public MusicInfoFormat(String musicName) {
		this.musicName = musicName;
		this.notes = nReader.readNote(musicName);
		score = new ScoreFormat();
		
		this.noteAmount = this.notes.size();
		
		File file = new File(System.getProperty("user.dir") + "/asset/music/soundtrack/" + musicName + ".mp3");
		String filePath = file.toURI().toString();
		musicSource = new Media(filePath);
	}

	public ScoreFormat getScore() {
		return score;
	}

	public String getMusicName() {
		return musicName;
	}

	public int getNoteAmount() {
		return noteAmount;
	}

	public Media getMusicSource() {
		return musicSource;
	}
}
