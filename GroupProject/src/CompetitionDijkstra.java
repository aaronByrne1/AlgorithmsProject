import java.io.File;
import java.io.FileNotFoundException;
import java. util. *;


public class CompetitionDijkstra {

  
	@SuppressWarnings("unused")
	private DirectedEdge[] edgeTo;
	@SuppressWarnings("unused")
	private double[] distTo;
	@SuppressWarnings("unused")
	private IndexMinPQ<Double> pq;
	@SuppressWarnings("unused")
	public ArrayList<Integer> stop_ids;
	public double cost;
	private Graph G;

	CompetitionDijkstra() {
		try {

			File file = new File("stops.txt");
			stop_ids = new ArrayList<Integer>();
			Scanner scanner = new Scanner(file);
			String infoline = scanner.nextLine();
			while (scanner.hasNextLine()) {
				String[] stopSplit = scanner.nextLine().split(","); 
				stop_ids.add(Integer.parseInt(stopSplit[0]));		
			}
			G = new Graph(50000);
			File stop_times = new File("stop_times.txt");
			Scanner sc = new Scanner(stop_times);
			infoline = sc.nextLine();
			
			while (sc.hasNext()) {
				
				String line = sc.nextLine();
				if(!sc.hasNext())
				{
					break;
				}
				String line2 = sc.nextLine();
				String[] lineSplit = line.split(",");
				String[] lineSplit2 = line2.split(",");
				
				if(lineSplit[0].equals(lineSplit2[0])) // same trip id
				{			
					int v = Integer.parseInt(lineSplit[3]);
					int es = Integer.parseInt(lineSplit2[3]);
					DirectedEdge e = new DirectedEdge(v, es, 1);
					G.addEdge(e);
				}
			}
			File transfer_times = new File("transfers.txt");
			Scanner transferSC = new Scanner(transfer_times);
			infoline = transferSC.nextLine();
			while(transferSC.hasNextLine())
			{
				String line = transferSC.nextLine();
				String[] lineSplit = line.split(",");
				int v = Integer.parseInt(lineSplit[0]);
				int es = Integer.parseInt(lineSplit[1]);
				if (Integer.parseInt(lineSplit[2])==0)
				{
					DirectedEdge e = new DirectedEdge(v, es, 2);
					G.addEdge(e);
				}
				else if (Integer.parseInt(lineSplit[2])==2)
				{
					Double min_trans_time = Double.parseDouble(lineSplit[3]);
					DirectedEdge e = new DirectedEdge(v, es, min_trans_time/100);
					G.addEdge(e);
				}
			}
			transferSC.close();
			sc.close();
			scanner.close();
			
			
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		}
	}



    @SuppressWarnings("unused")
	public String shortestPath(int Start_id, int End_id){
    	
    	DijkstraSP Path = new DijkstraSP(G,Start_id);
    	if(Path.hasPathTo(End_id))
    	{		
    		Path.distTo(End_id);
    		Path.pathTo(End_id);
    		cost = Path.distTo(End_id);
    		Stack<DirectedEdge> ShortestPath  = Path.pathTo(End_id); 
    		String str =ShortestPath.toString();
    		
    		return str;	
    	}
    	return "-1";
    }

}
