import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.time.LocalTime;

public class projectInterface {
	public static void main(String[] args) {
		try {
			searchByStopName.createTST();
		} catch (FileNotFoundException e) {
			System.out.println("ERROR: File not found");
		}
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		String intro ="Enter 1-4 to select options:"
				+ "\n1: Find shortest paths between two bus stops"
				+ "\n2: Search for bus stop"
				+ "\n3: Search by arrival time"
				+ "\n4: Exit";
		boolean quit = false;
		boolean error = false;
		boolean stopId = true;
		int depart=0;
		int arrival = 0;
		CompetitionDijkstra bruh = new CompetitionDijkstra();
		while(quit==false) 
		{
			error = false;
			System.out.println(intro);
			int answer = input.nextInt();
			input.nextLine();
			if(answer ==1) 
			{
				System.out.println("Enter departure bus stop:");
				depart = input.nextInt();
				for(int i =0;i<CompetitionDijkstra.stop_ids.length;i++) 
				{
					if(depart==CompetitionDijkstra.stop_ids[i]) 
					{
						stopId=true;
					}
					else 
					{ 
						stopId=false;
					}
				}

				if(stopId == true) 
				{
					System.out.println("Enter arrival bus stop:");
					arrival = input.nextInt();
					for(int i =0;i<CompetitionDijkstra.stop_ids.length;i++) 
					{
						if(arrival==CompetitionDijkstra.stop_ids[i]) 
						{
							stopId=true;
						}
						else 
						{ 
							stopId=false;
						}
					}
				}
				if(stopId == true) 
				{
					if(CompetitionDijkstra.cost!=-1) {
						System.out.println("Cost:");
						System.out.println(CompetitionDijkstra.cost);
						System.out.println("Path:");
						String what = bruh.shortestPath(depart,arrival);
						System.out.println(what);
					}
					else {
						System.out.println("Error: Path not possible");
					}
				}
			}
			else if(answer ==2) 
			{
				System.out.println("Search by bus stop name by providing full or partial name and pressing enter:");
				String name = input.nextLine();
				searchByStopName.functionality(name);
			}
			else if(answer ==3) 
			{
				System.out.println("Enter arrival time in format hh:mm:ss" );
				String SearchTime = input.nextLine(); 
				char colon='\u003A';
				ArrayList<Character> chars = new ArrayList<Character>();
				for (char c : SearchTime.toCharArray()) {
					chars.add(c);
				}
				if(chars.size()!=8){
					System.out.println("ERROR: Enter a valid Time");
					error = true;
				}
				else if(chars.get(7)=='6'&&chars.get(8)!='0') 
				{
					System.out.println("ERROR: Enter a valid Time");
					error = true;
				}
				else if (chars.get(4)=='6'&&chars.get(5)!='0') 
				{
					System.out.println("ERROR: Enter a valid Time");
					error = true;
				}
				else if(chars.get(3)!= colon&&chars.get(6)!= colon&&chars.get(1)<='2'&&chars.get(7)<='6'&&chars.get(4)<='6'&&error!=true) 
				{

					StopTimeBST<LocalTime, stopTime> stopTime;
					stopTime = Arrival_Time_Search.parseStopTimeInfo();
					Arrival_Time_Search.search(stopTime, SearchTime);

				}
				else 
				{
					System.out.println("ERROR: Enter a valid Time");
				}
			}
			else if(answer ==4) 
			{
				quit = true;
			}
			else 
			{
				System.out.println("ERROR: Enter a valid number");
			}
		}
	}
}