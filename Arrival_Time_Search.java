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
		StopTimeBST<LocalTime, stopTime> stopTime;
		stopTime = parseStopTimeInfo();
		
//		StopTimeBST<LocalTime, stopTime> stopTimeBst = new StopTimeBST<LocalTime, stopTime>();
//		
//		System.out.println("finished creating");
//		
//		stopTimeBst.put(stopTime[100].getArrivalTime(), stopTime[100]);
//		stopTimeBst.put(stopTime[200].getArrivalTime(), stopTime[200]);
//		stopTimeBst.put(stopTime[300].getArrivalTime(), stopTime[300]);
//		
//		System.out.println("finished putting");
//		
//		System.out.println(stopTimeBst.prettyPrintKeys());
//		
//		System.out.println("finished pretty printing");
//		
//		List<stopTime> entry = stopTimeBst.get(LocalTime.parse("07:16:35"));
//		for(int i=0;i<entry.size(); i++)
//			entry.get(i).print();
		
	}
	
	public static StopTimeBST<LocalTime, stopTime> parseStopTimeInfo() {
		System.out.println("Starting");
		StopTimeBST<LocalTime, stopTime> stopTimeBst = new StopTimeBST<LocalTime, stopTime>(); 
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("H:mm:ss");
		try {
			File file = new File("C:/Users/Stephen/stop_times.txt");
			Scanner reader = new Scanner(file);
			reader.nextLine();
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
					stopTimeBst.put(entry.getArrivalTime(), entry);
					
				} catch (DateTimeParseException d) {}

				
				for(int ii=0; ii<stopTimeInfo.length; ii++) 
					System.out.print(stopTimeInfo[ii] + " ");
				System.out.print("\n");
			}	
			
			
			reader.close();	
			System.out.println("Finishing");
		} catch (IOException e) {}
		return stopTimeBst;
	}
}
