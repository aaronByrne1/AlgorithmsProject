import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.time.LocalTime;

public class projectInterface {
	public static void main(String[] args) {
		StopTimeBST<LocalTime, stopTime> stopTime;
		stopTime = Arrival_Time_Search.parseStopTimeInfo();
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
		boolean stopId = false;
		int depart=0;
		int arrival = 0;
		int answer =0;
		CompetitionDijkstra bruh = new CompetitionDijkstra();
		while(quit==false) 
		{
			error = false;
			System.out.println(intro);
			try {
				answer = input.nextInt();
			}
			catch(InputMismatchException ex){
				System.out.println("Try again. (" +
	                    "Incorrect input: an integer is required)");
	            answer =-1;
			}
			input.nextLine();
			if(answer ==1) 
			{
				System.out.println("Enter departure bus stop:");
				try {
					depart = input.nextInt();
				}
				catch(InputMismatchException ex){
					System.out.println("Try again. (" +
		                    "Incorrect input: an integer is required)");
		            depart =0;
		            stopId=false;
				}
				input.nextLine();
				for(int i =0;i<CompetitionDijkstra.stop_ids.size();i++) 
				{
					if(depart==CompetitionDijkstra.stop_ids.get(i)) {
						stopId=true;
					}
					
				}
				if(stopId == true) 
				{
					stopId=false;
					System.out.println("Enter arrival bus stop:");
					try {
						arrival = input.nextInt();
					}
					catch(InputMismatchException ex){
						System.out.println("Try again. (" +
			                    "Incorrect input: an integer is required)");
			            arrival =0;
			            stopId=false;
					}
					input.nextLine();
					for(int i =0;i<CompetitionDijkstra.stop_ids.size();i++){
						if(arrival==CompetitionDijkstra.stop_ids.get(i)){
							stopId=true;
						}
					}
				}
				if(stopId == true) 
				{
					if(CompetitionDijkstra.cost!=-1) {
						String path = bruh.shortestPath(depart,arrival);
						System.out.println("Cost:");
						System.out.println(CompetitionDijkstra.cost);
						System.out.println("Path:");
						System.out.println(path);
					}
					else {
						System.out.println("Error: Path not possible\n");
					}
				}
				if(stopId==false) {
					System.out.println("Error: invalid stop number\n");
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
					System.out.println("ERROR: Enter a valid Time\n");
					error = true;
				}
				if(chars.get(0)=='2'&&chars.get(1)>='4'){
					System.out.println("ERROR: Enter a valid Time\n");
					error = true;
				}
				else if(chars.get(2)==colon&&chars.get(5)==colon&&chars.get(0)<='2'&&chars.get(6)<='5'&&chars.get(3)<='5'&&error!=true) 
				{
					System.out.println("Loading...");
					Arrival_Time_Search.search(stopTime, SearchTime);
					System.out.println("\n");
				}
				else 
				{
					System.out.println("ERROR: Enter a valid Time\n");
				}
			}
			else if(answer ==4) 
			{
				quit = true;
			}
			else 
			{
				System.out.println("ERROR: Enter a valid number\n");
			}
		}
	}
}
