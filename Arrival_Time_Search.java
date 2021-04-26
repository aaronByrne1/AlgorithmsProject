import java.io.File;
import java.io.IOException;
import java.util.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.io.BufferedReader;
import java.io.FileReader;

public class Arrival_Time_Search {

	
	public static void main(String[] args)
	{
		System.out.println("Starting Now");
		stopTime[] stopTimeArray = new stopTime[2000000]; 
		LocalTime maxTime = LocalTime.parse("23:59:59");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("H:mm:ss");
		try {
			BufferedReader reader = new BufferedReader(new FileReader("C:/Users/Stephen/stop_times.txt"));
			int i =0;
			reader.readLine();
			String line1=null;
			
			while ((line1 = reader.readLine()) != null){
				String data = reader.readLine();
				String[] stopTimeInfo = data.split(",");
				stopTime entry = new stopTime(Integer.parseInt(stopTimeInfo[0]),
											LocalTime.parse(stopTimeInfo[1].trim(), formatter),
											LocalTime.parse(stopTimeInfo[2].trim(), formatter),
											Integer.parseInt(stopTimeInfo[3]),
											Integer.parseInt(stopTimeInfo[4]),
											Integer.parseInt(stopTimeInfo[6]),
											Integer.parseInt(stopTimeInfo[7]),
											Float.parseFloat(stopTimeInfo[8]));
				if(entry.getArrivalTime().isBefore(maxTime)) {
					stopTimeArray[i] = entry;
					i++;
				}
				
				for(int ii=0; ii<stopTimeInfo.length; ii++) 
					System.out.print(stopTimeInfo[ii] + " ");
				System.out.println("");
			}	
			
			
			reader.close();	
			System.out.println("Finishing");
		} catch (IOException e) {}
		
	}
}
