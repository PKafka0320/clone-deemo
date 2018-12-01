package notes;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Note {
	public ArrayList<NoteFormat> notes = new ArrayList<NoteFormat>();
	private Scanner fileScan;
	
	public Note(String fileName){
		try {
			Path currentRelativePath = Paths.get("");
			String filePath = currentRelativePath.toAbsolutePath().toString();
			File noteFile = new File(filePath+"\\asset\\music\\notes\\"+fileName+".txt");
//			File noteFile = new File("file:./asset/music/notes/"+fileName+".txt");
			
			fileScan = new Scanner(noteFile);
			
			while(fileScan.hasNextLine()) {
				String[] oneNote = fileScan.nextLine().split("#");
				String[] lineString = oneNote[0].split(",");
				boolean[] lines = new boolean[6];
				
				for(int i=0; i<6; i++) {
					if(Integer.parseInt(lineString[i]) == 1) lines[i] = true;
					else lines[i] = false;
				}
				
				long timing = Long.parseLong(oneNote[1]);
				
				notes.add(new NoteFormat(lines, timing));
				
			}
			
		}catch(FileNotFoundException e) {
			System.out.println("Fail to loading "+fileName+".txt");
		}
	}
	
	public ArrayList<NoteFormat> getNotes() {
		return notes;
	}
}