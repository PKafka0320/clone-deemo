package reader;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class SettingReader {
	private String settingFilePath = System.getProperty("user.dir") + "/asset/option/setting.txt";
	private Scanner fileScanner;
	
	 public SettingReader() {
		 
	 }
	 
	 public double[] readSetting() {
		 try {
			 File settingFile = new File(settingFilePath);
			 fileScanner = new Scanner(settingFile);
			 double[] settingValues = new double[3];
			 
			 while (fileScanner.hasNextLine()) {
					String[] settingName = fileScanner.nextLine().split("#");
					String value = settingName[1];
					char[] check = settingName[0].toCharArray();
					
					if(check[0] == 'V') {
						settingValues[0] =  Double.valueOf(value);
					}
					if(check[0] == 'Y') {
						settingValues[1] =  Double.valueOf(value);
					}
					if(check[0] == 'S') {
						settingValues[2] =  Double.valueOf(value);
					}
			 }
			 
			 return settingValues;
		 } catch (Exception e) {
			System.out.println("exception on settingReader "+e);
			return null;
		 }
	 }
	 
	 public void writeSetting(double[] settingValues) throws IOException {
		 File settingFile = new File(System.getProperty("user.dir") + "/asset/option/setting.txt");
		 BufferedWriter bw = new BufferedWriter(new FileWriter(settingFile));
		 
		 if(settingFile.isFile() && settingFile.canWrite()) {
			 String vValue = String.valueOf((int) settingValues[0]);
			 String yValue = String.valueOf((int) settingValues[1]);
			 String sValue = String.valueOf((int) settingValues[2]);
			 
			 bw.write("V#" + vValue);
			 bw.newLine();
			 bw.write("Y#" + yValue);
			 bw.newLine();
			 bw.write("S#" + sValue);
			 bw.close();
		 }
	 }
}
