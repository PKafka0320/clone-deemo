package reader;

import java.util.Scanner;

import format.NoteFormat;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Queue;
import java.util.LinkedList;
import java.nio.file.Path;
import java.nio.file.Paths;


public class NoteReader {
	public Queue<NoteFormat> notes = new LinkedList<NoteFormat>();
	private Scanner fileScan;
	
	private double sync = 530;

	String filePath;

	public NoteReader() {
	}

	public Queue<NoteFormat> readNote(String fileName) {
		try {
			Path currentRelativePath = Paths.get("");
			filePath = currentRelativePath.toAbsolutePath().toString();
//			File noteFile = new File(filePath+"\\asset\\music\\notes\\"+fileName+".txt");
			File noteFile = new File(filePath + "/asset/music/notes/" + fileName + ".txt"); // for mac
//			File noteFile = new File("file:./asset/music/notes/"+fileName+".txt");

			fileScan = new Scanner(noteFile);

			while (fileScan.hasNextLine()) {
				String[] oneNote = fileScan.nextLine().split("#");
				String[] lineString = oneNote[0].split(",");

				for (int i = 0; i < 6; i++) {
					if (Integer.parseInt(lineString[i]) == 1) {
						notes.add( new NoteFormat(i + 1, Double.parseDouble(oneNote[1]) + sync) );
					}
				}
			}
			return notes;
		} catch (FileNotFoundException e) {
			System.out.println(filePath + "\\asset\\music\\notes\\" + fileName + ".txt");
			System.out.println("Fail to loading " + fileName + ".txt");
			return null;
		}
	}
	
	public void setSync(double sync) {
		this.sync = this.sync + (int) (10 * sync);
		System.out.println(this.sync);
	}
}