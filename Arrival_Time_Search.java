import java.io.File;
import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.List;
import java.util.Collections;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Arrival_Time_Search {

	
//	public static void main(String[] args)
//	{
//		StopTimeBST<LocalTime, stopTime> stopTime;
//		stopTime = parseStopTimeInfo();
//		
//		search(stopTime, "14:30:00");
//
//	}
	
	public static List<stopTime> search(StopTimeBST<LocalTime, stopTime> stopTimes, String arrivalTime)
	{
		List<stopTime> listOfTripIds = stopTimes.get(LocalTime.parse(arrivalTime));
		if(listOfTripIds.isEmpty())
			System.out.print("no trips matching this arrival time");
		else
		{
			for(int i=0; i<listOfTripIds.size(); i++)
			{
				listOfTripIds.get(i).print();
			}
		}
		return listOfTripIds;
	}
	
	public static StopTimeBST<LocalTime, stopTime> parseStopTimeInfo() {
		System.out.println("Starting");
		StopTimeBST<LocalTime, stopTime> stopTimeBst = new StopTimeBST<LocalTime, stopTime>(); 
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("H:mm:ss");
		try {
			File file = new File("C:/Users/Stephen/AlgorithmsProject/stop_times.txt");
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
												stopTimeInfo[5],
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
		
		sortLists(stopTimeBst);
		return stopTimeBst;
	}
	
	public static void sortLists(StopTimeBST<LocalTime, stopTime> stopTimes) 
	{
		
		if(stopTimes.isEmpty()) return;
		sortLists(stopTimes.getRoot());
		
	}
	
	private static void sortLists(StopTimeBST.Node x)
	{
		if(x.getLeft() == null && x.getRight() == null)
		{
			quickSort(x.getVals());
			return;
		}
		
		if(x.getLeft() != null)
		{
			sortLists(x.getLeft());
			quickSort(x.getVals());
		}
		
		if(x.getRight() != null)
		{
			sortLists(x.getRight());
			quickSort(x.getVals());
		}
		
		return;
	}

    static List<stopTime> quickSort (List<stopTime> stopTime){
	
    	quickSort(stopTime, 0, stopTime.size() -1 );
    	return stopTime;
    }
    
    static void quickSort(List<stopTime> stopTime, int begin, int end) {
        if (begin < end) {
            int partitionIndex = partition(stopTime, begin, end);

            quickSort(stopTime, begin, partitionIndex-1);
            quickSort(stopTime, partitionIndex+1, end);
        }
    }
    
    static int partition(List<stopTime> stopTime, int begin, int end) {
        stopTime pivot = stopTime.get(end);
        int i = (begin-1);

        for (int j = begin; j < end; j++) {
            if (stopTime.get(j).getTripId() <= pivot.getTripId()) {
                i++;
                Collections.swap(stopTime, i, j);
            }
        }

        Collections.swap(stopTime, i+1, end);
        return i+1;
    }
}
