import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class Node implements Comparable<Node> {
	// Id for readability of result purposes
	public int id;
	public int stopID, stopLong, stopLat;

	// Parent in the path
	public Node parent = null;

	public List<Edge> neighbors;

	// Evaluation functions
	public double f = Double.MAX_VALUE;
	public double g = Double.MAX_VALUE;
	// Hardcoded heuristic
	public double h;

	Node(int stopID, int stopLat, int stopLong) {
		this.id = stopID;
		this.neighbors = new ArrayList<>();
		this.stopID = stopID;
		this.stopLong = stopLong;
		this.stopLat = stopLat;
	}

	@Override
	public int compareTo(Node n) {
		return Double.compare(this.f, n.f);
	}

	public static class Edge {
		Edge(int weight, Node node) {
			this.weight = weight;
			this.node = node;
		}

		public int weight;
		public Node node;
	}

	public void addBranch(int weight, Node node) {
		Edge newEdge = new Edge(weight, node);
		neighbors.add(newEdge);
	}

	public double calculateHeuristic(Node target) {
		final int R = 6371; // Radius of the earth
	    double latDistance = Math.toRadians(target.stopLat - stopLat);
	    double lonDistance = Math.toRadians(target.stopLong - stopLong);
	    double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
	            + Math.cos(Math.toRadians(stopLat)) * Math.cos(Math.toRadians(target.stopLat))
	            * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
	    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
	    double distance = R * c * 1000; // convert to meters
	    this.h = distance;
	    return distance;
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

	public static void printPath(Node target) {
		Node n = target;

		if (n == null)
			return;

		List<Integer> ids = new ArrayList<>();

		while (n.parent != null) {
			ids.add(n.id);
			n = n.parent;
		}
		ids.add(n.id);
		Collections.reverse(ids);

		for (int id : ids) {
			System.out.print(id + " ");
		}
		System.out.println("");
	}

	public static void main(String[] args) {
		

		Node res = aStar(head, target);
		printPath(res);
	}
}
