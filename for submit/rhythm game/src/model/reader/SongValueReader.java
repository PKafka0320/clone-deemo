package reader;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class SongValueReader {

	private Scanner fileScanner;

	public SongValueReader() {
	}

	public String[] readSongValue() {
		try {
			File scoreFile = new File(System.getProperty("user.dir") + "/asset/temp/" + "currentSongValue" + ".txt");
			fileScanner = new Scanner(scoreFile);
			String[] SongValue = new String[2];

			while (fileScanner.hasNextLine()) {
				String[] values = fileScanner.nextLine().split("#");
				String value = values[1];
				char[] check = values[0].toCharArray();

				if (check[0] == 'N') {
					SongValue[0] = value;
				}
				if (check[0] == 'A') {
					SongValue[1] = value;
				}
			}

			return SongValue;

		} catch (Exception e) {
			System.out.println("exception on settingReader " + e);
			return null;
		}
	}

	public void writeSongValue(String musicName, int noteAmount) throws IOException {
		File settingFile = new File(System.getProperty("user.dir") + "/asset/temp/" + "currentSongValue" + ".txt");
		BufferedWriter bw = new BufferedWriter(new FileWriter(settingFile));

		if (settingFile.isFile() && settingFile.canWrite()) {
			String nValue = musicName;
			String aValue = String.valueOf(noteAmount);

			bw.write("N#" + nValue);
			bw.newLine();
			bw.write("A#" + aValue);
			bw.close();
		}
	}
}
