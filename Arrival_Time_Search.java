import java.io.File;

import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.io.BufferedReader;
import java.io.FileReader;

public class Arrival_Time_Search {

	
	public static void main(String[] args)
	{
		System.out.println("Starting");
		stopTime[] stopTimeArray = new stopTime[1692890]; 
		LocalTime maxTime = LocalTime.parse("23:59:59");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("H:mm:ss");
		try {
			File file = new File("C:/Users/Stephen/stop_times.txt");
			Scanner reader = new Scanner(file);
			int i =0;
			String firstLine = reader.nextLine();
			while (reader.hasNextLine()){
				String data = reader.nextLine();
				String[] stopTimeInfo = data.split(",");
				
				if(stopTimeInfo.length < 9) 
				{
					String[] stopTimeInfoTemp = stopTimeInfo.clone();
					stopTimeInfo = new String[9];
					for(int ii=0; ii<stopTimeInfo.length-2; ii++)
						stopTimeInfo[ii] = stopTimeInfoTemp[ii];
					stopTimeInfo[7] = "0";
					stopTimeInfo[8] = stopTimeInfoTemp[7];
				}
				
				try {
					stopTime entry = new stopTime(Integer.parseInt(stopTimeInfo[0]),
												LocalTime.parse(stopTimeInfo[1].trim(), formatter),
												LocalTime.parse(stopTimeInfo[2].trim(), formatter),
												Integer.parseInt(stopTimeInfo[3]),
												Integer.parseInt(stopTimeInfo[4]),
												Integer.parseInt(stopTimeInfo[6]),
												Integer.parseInt(stopTimeInfo[7]),
												Float.parseFloat(stopTimeInfo[8]));
					stopTimeArray[i] = entry;
					
				} catch (DateTimeParseException d) {i--;}

				
				for(int ii=0; ii<stopTimeInfo.length; ii++) 
					System.out.print(stopTimeInfo[ii] + " ");
				System.out.print("\n");
				i++;
			}	
			
			
			reader.close();	
			System.out.println("Finishing");
			System.out.println(i);
		} catch (IOException e) {}
		
//		for(int i=0; i<stopTimeArray.length; i++)
//			System.out.println(i + ": " +stopTimeArray[i].getArrivalTime());
		
	}
}
