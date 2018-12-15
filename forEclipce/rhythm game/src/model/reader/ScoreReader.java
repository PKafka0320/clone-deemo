package reader;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import format.ScoreFormat;

public class ScoreReader {
	private Scanner fileScanner;

	public ScoreReader() {
	}

	public ScoreFormat readScore(String musicName) {
		try {
			File scoreFile = new File(System.getProperty("user.dir") + "/asset/score/" + musicName + ".txt");
			fileScanner = new Scanner(scoreFile);
			double scoreValue = 0;
			int maxComboValue = 0;
			int perfectHitValue = 0;
			while (fileScanner.hasNextLine()) {
				String[] values = fileScanner.nextLine().split("#");
				String value = values[1];
				char[] check = values[0].toCharArray();

				if (check[0] == 'S') {
					scoreValue = Double.valueOf(value);
				}
				if (check[0] == 'C') {
					maxComboValue = Integer.valueOf(value);
				}
				if (check[0] == 'P') {
					perfectHitValue = Integer.valueOf(value);
				}
			}

			ScoreFormat score = new ScoreFormat(scoreValue, maxComboValue, perfectHitValue);
			return score;

		} catch (Exception e) {
			System.out.println("exception on settingReader " + e);
			return null;
		}
	}

	public void writeScore(String musicName, ScoreFormat score) throws IOException {
		File settingFile = new File(System.getProperty("user.dir") + "/asset/score/" + musicName + ".txt");
		BufferedWriter bw = new BufferedWriter(new FileWriter(settingFile));

		if (settingFile.isFile() && settingFile.canWrite()) {
			String sValue = String.valueOf((int) score.getScore());
			String cValue = String.valueOf((int) score.getMaxCombo());
			String pValue = String.valueOf((int) score.getPerfectHit());

			bw.write("S#" + sValue);
			bw.newLine();
			bw.write("C#" + cValue);
			bw.newLine();
			bw.write("P#" + pValue);
			bw.close();
		}
	}
}
