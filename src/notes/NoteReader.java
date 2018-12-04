package notes;

import java.util.Scanner;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Queue;
import java.util.LinkedList;
import java.nio.file.Path;
import java.nio.file.Paths;

public class NoteReader {
	public Queue<NoteFormat> notes = new LinkedList<NoteFormat>();
	private Scanner fileScan;
	
	public NoteReader(String fileName){
		try {
			Path currentRelativePath = Paths.get("");
			String filePath = currentRelativePath.toAbsolutePath().toString();
			File noteFile = new File(filePath+"\\asset\\music\\notes\\"+fileName+".txt");
//			File noteFile = new File("file:./asset/music/notes/"+fileName+".txt");
			
			fileScan = new Scanner(noteFile);
			
			while(fileScan.hasNextLine()) {
				String[] oneNote = fileScan.nextLine().split("#");
				String[] lineString = oneNote[0].split(",");
				
				for(int i=0; i<6; i++) {
					if(Integer.parseInt(lineString[i]) == 1) {
						notes.add(new NoteFormat(i+1, Double.parseDouble(oneNote[1])));
					}
				}
				
				
			}
			
		}catch(FileNotFoundException e) {
			System.out.println("Fail to loading "+fileName+".txt");
		}
	}
	
	public Queue<NoteFormat> getNotes() {
		return notes;
	}
}