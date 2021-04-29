import java.io.File;
import java.io.FileNotFoundException;
import java. util. *;
/*
 * A Contest to Meet (ACM) is a reality TV contest that sets three contestants at three random
 * city intersections. In order to win, the three contestants need all to meet at any intersection
 * of the city as fast as possible.
 * It should be clear that the contestants may arrive at the intersections at different times, in
 * which case, the first to arrive can wait until the others arrive.
 * From an estimated walking speed for each one of the three contestants, ACM wants to determine the
 * minimum time that a live TV broadcast should last to cover their journey regardless of the contestantsâ€™
 * initial positions and the intersection they finally meet. You are hired to help ACM answer this question.
 * You may assume the following:
 *    ï‚· Each contestant walks at a given estimated speed.
 *    ï‚· The city is a collection of intersections in which some pairs are connected by one-way
 * streets that the contestants can use to traverse the city.
 *
 * This class implements the competition using Dijkstra's algorithm
 */

public class CompetitionDijkstra {

    /**
     * @param filename: A filename containing the details of the city road network
     * @param sA, sB, sC: speeds for 3 contestants
    */
	@SuppressWarnings("unused")
	private DirectedEdge[] edgeTo;
	@SuppressWarnings("unused")
	private double[] distTo;
	@SuppressWarnings("unused")
	private IndexMinPQ<Double> pq;
	@SuppressWarnings("unused")
	public double cost;
	private Graph G;

	CompetitionDijkstra() {
		try {

			File file = new File("stops.txt");
			Scanner scanner = new Scanner(file);
			int i = 0;
			String infoline = scanner.nextLine();
			while (scanner.hasNextLine()) {
				String[] stopSplit = scanner.nextLine().split(","); 
				stop_ids[i] = Integer.parseInt(stopSplit[i]);
				i++;
				
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
