package application.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;

public class Data {

	public Integer getGrade(String login, String nomImage)
	{
		try {
			Integer noteRes = null;
			FileReader fileReader = new FileReader("grade.txt");
			BufferedReader reader = new BufferedReader(fileReader);
			while (reader.ready()) {
				String[] line = reader.readLine().split("\n");
				for (String s : line) {					
					String[] values = s.split(";");					
					String loginRes = values[0].replace(";", "");
					String nomImageRes = values[1].replace(";", "");					
					if(loginRes.equals(login) && nomImageRes.equals(nomImage))	{
						noteRes = Integer.parseInt(values[2].replace(";", ""));
					}
				}
			}
			reader.close();
			fileReader.close();			
			return noteRes;			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void writeGradeLine(String historyLine)
	{
		try {
			File history = new File("grade.txt");
			FileOutputStream flux = new FileOutputStream(history, false);
			
			for (int i = 0; i < historyLine.length(); i++) {
				flux.write(historyLine.charAt(i));
			}				
			flux.close();		
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
