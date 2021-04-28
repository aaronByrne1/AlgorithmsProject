import java.io.File;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;


public class AStarSP {
	public List<Node> stops;
	
	AStarSP(Node head, Node target)	
	{
		Scanner sc = new Scanner ("stops.txt");
		Scanner sc2 = new Scanner ("stop-times.txt");

		while (sc.hasNextLine())
		{
			String line = sc.nextLine();
			String[] lineSplit = line.split(",");
			Node lineSplit[0] = new Node(Integer.parseInt(lineSplit[0]), Integer.parseInt(lineSplit[5]), Integer.parseInt(lineSplit[6]));		
			//add its branches using aarons searching
		}
		while(sc2.hasNext())
		{
			//add branches
			String line = sc.nextLine();
			//first time has to get line 1 and 2 but then has to be 2 and 3, 3 and 4 and so on
			String line2 = sc.nextLine();
			String[] lineSplit = line.split(",");
			String[] lineSplit2 = line2.split(",");
			if(lineSplit[0].equals(lineSplit2[0]))	//Same trip_id
			{
				String time1 = lineSplit2[2].substring(1);
				String time2 = lineSplit2[3].substring(1);	//Delete space

				SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
				Date date1 = format.parse(time1);
				Date date2 = format.parse(time2);
				int difference = date2.getTime() - date1.getTime(); 
				
				//search for those nodes
				lineSplit[4].addBranch(difference , lineSplit2[4]);	
			}
			
		}
		sc.close();
		Node head = new Node(heuristic (start.);
	    head.g = 0;

	    Node n1 = new Node(2);
	    Node n2 = new Node(2);
	    Node n3 = new Node(2);

	    head.addBranch(1, n1);
	    head.addBranch(5, n2);
	    head.addBranch(2, n3);
	    n3.addBranch(1, n2);

	    Node n4 = new Node(1);
	    Node n5 = new Node(1);
	    Node target = new Node(0);

	    n1.addBranch(7, n4);
	    n2.addBranch(4, n5);
	    n3.addBranch(6, n4);

	    n4.addBranch(3, target);
	    n5.addBranch(1, n4);
	    n5.addBranch(3, target);

	    Node res = aStar(head, target);
	    printPath(res);
	}
	public static Node aStar(Node start, Node target) {
		PriorityQueue<Node> closedList = new PriorityQueue<>();
		PriorityQueue<Node> openList = new PriorityQueue<>();

		start.f = start.g + start.calculateHeuristic(target);
		openList.add(start);

		while (!openList.isEmpty()) {
			Node n = openList.peek();
			if (n == target) {
				return n;
			}

			for (Node.Edge edge : n.neighbors) {
				Node m = edge.node;
				double totalWeight = n.g + edge.weight;

				if (!openList.contains(m) && !closedList.contains(m)) {
					m.parent = n;
					m.g = totalWeight;
					m.f = m.g + m.calculateHeuristic(target);
					openList.add(m);
				} else {
					if (totalWeight < m.g) {
						m.parent = n;
						m.g = totalWeight;
						m.f = m.g + m.calculateHeuristic(target);

						if (closedList.contains(m)) {
							closedList.remove(m);
							openList.add(m);
						}
					}
				}
			}

			openList.remove(n);
			closedList.add(n);
		}
		return null;
	}
	public double heuristic(double lat1, double lat2, double lon1, double lon2)
	{
		final int R = 6371; // Radius of the earth

	    double latDistance = Math.toRadians(lat2 - lat1);
	    double lonDistance = Math.toRadians(lon2 - lon1);
	    double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
	            + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
	            * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
	    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
	    double distance = R * c * 1000; // convert to meters

	    return distance;
		
	
	}
	
	
}
