import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;
import java.util.TreeMap;

class GraphF1 {
	private int numVertices; // number of vertices in the graph
	private int numEdges;    // number of edges in the graph
	private boolean undirected; // tells whether the graph is undirected

	// For each vertex, we need to keep track of the edges,
	// so for each edge, we need to store the second vertex and 
	// the edge weight. This can be done as a <key, value> pair, 
	// with the second vertex as the key, and the weight as the 
	// value. The TreeMap data structure is used to store a list 
	// these (key, value) pairs for each vertex, accessible as 
	// adjList.get(v).  
	private Vector<TreeMap<Integer, Integer>> adjList;

	public GraphF1(int n, boolean undirected) {
		// Constructor for weighted graph.
		// Precondition: The number of vertices n should be
		// greater than zero.
		// Postcondition: Initializes the graph with n vertices.
		numVertices = n;
		this.undirected = undirected;
		numEdges = 0;
		adjList = new Vector<TreeMap<Integer, Integer>>();
		for (int i=0; i<numVertices; i++) {
			adjList.add(new TreeMap<Integer, Integer>());
		} // end for
	} // end constructor

	public int getNumVertices() {
		// Determines the number of vertices in the graph.
		// Precondition: None.
		// Postcondition: Returns the number of vertices in
		// the graph.
		return numVertices;
	} // end getNumVertices

	public int getNumEdges() {
		// Determines the number of edges in the graph.
		// Precondition: None.
		// Postcondition: Returns the number of edges in
		// the graph.
		return numEdges;
	} // end getNumEdges

	public int getEdgeWeight(Integer v, Integer w) {
		// Determines the weight of the edge between vertices
		// v and w.
		// Precondition: 
		// Postcondition: Returns the weight of the edge or most positive int if not there.
		if (adjList.get(v).get(w) == null) {
			return Integer.MAX_VALUE;
		}
		else {
			return adjList.get(v).get(w);
		}
	} // end getWeight

	public void addEdge(Integer v, Integer w, int wgt) {
		// Adds an edge from v to w with weight wgt to the graph.
		// Precondition: The vertices contained within
		// edge e exist in the graph.
		// Postcondition: An edge from v to w is part of the 
		// graph.

		// Add the edge to both v's and w's adjacency list
		adjList.get(v).put(w, wgt);
		if(undirected)
			adjList.get(w).put(v, wgt); // needed for undirected graphs
		numEdges++;
	} // end addEdge

	public void addEdge(Edge e) {
		// Adds an edge to the graph.
		// Precondition: The vertices contained within
		// edge e exist in the graph.
		// Postcondition: Edge e is part of the graph.

		// Extract the vertices and weight from the edge e
		Integer v = e.getV(); 
		Integer w = e.getW();
		int weight = e.getWeight();

		addEdge(v, w, weight);
	} // end addEdge

	public void removeEdge(Edge e) {
		// Removes an edge from the graph.
		// Precondition: The vertices contained in the edge e
		// exist in the graph.
		// Postcondition: Edge e is no longer part of the graph.

		// Extract the vertices from the edge e
		Integer v = e.getV(); 
		Integer w = e.getW();

		// Remove the edge from v's and w's adjacency list 
		adjList.get(v).remove(w);
		adjList.get(w).remove(v);    
		numEdges--;
	} // end remove

	public Edge findEdge(Integer v, Integer w) {
		// Finds the edge connecting v and w.
		// Precondition: The edge exists.
		// Postcondition: Returns the edge with the weight.
		int wgt = adjList.get(v).get(w);
		return new Edge(v, w, wgt);
	} // end findEdge

	// package access
	TreeMap<Integer,Integer> getAdjList(Integer v) {
		// Returns the adjacency list for given vertex
		// Precondition: The vertex exists in the graph
		// Postcondition: Returns the associated adjacency
		// list.
		return adjList.get(v);
	} // end getAdjList

	// check for no loops
	public boolean checkSimple(){

		for(int i = 0; i < numVertices; i++){
			if(adjList.get(i).get(i) != null){
				return false;
			}
		}

		return true;

	}

	// check for multiple edges
	public boolean checkMultiple(){

		Integer numEdges = 0;

		for(int i = 0; i < numVertices; i++){
			numEdges += adjList.get(i).size();
		}		

		if(numEdges > numVertices)
			return true;
		else
			return false;
	}


	public Integer graphType(){

		// value to return
		Integer type = 0;

		// check for simple, undirected
		if(undirected){
			if(checkSimple() == true)
				type = 1;
		}

		// check for simple, directed
		if(!undirected){
			if(checkSimple() == true && checkMultiple() == false)
				type = 2;
			else
				type = 3;
		}




		return type;
	}

	public Integer numSinks(){
		Integer numSinks = 0;
		for(int i = 0; i < numVertices; i++){
			TreeMap<Integer,Integer> vertList = getAdjList(i);
			if(vertList.isEmpty())
				numSinks = numSinks +1;			
		}

		return numSinks;
	}

	// to string method for graph
	public String toString(){

		String s = "";

		for(int i = 0; i < numVertices; i++){

			s += i;

			TreeMap<Integer, Integer> treeMap = getAdjList(i);

			for(int j = 0; j < numVertices; j++){

				Integer weight = treeMap.get(j);
				if(weight != null){
					s += "=>" + j + "[" + weight + "]"; 
				}
			}

			s += "\n";
		}

		return s;
	}

	public static void main(String[] args) {
		// create a graph... figure 14-24a in Prichard
		GraphF1 graph1 = new GraphF1(5, true);
		graph1.addEdge(0, 1, 8);
		graph1.addEdge(0, 4, 4);
		graph1.addEdge(0, 3, 9);
		graph1.addEdge(1, 2, 1);
		graph1.addEdge(2, 1, 2);
		graph1.addEdge(2, 3, 3);
		graph1.addEdge(3, 2, 2);
		graph1.addEdge(3, 4, 7);
		graph1.addEdge(4, 2, 1);

		GraphF1 graph2 = new GraphF1(3, true);
		//graph2.addEdge(0,0,3);
		graph2.addEdge(0,1,4);
		graph2.addEdge(1,2,6);
		graph2.addEdge(2,0,7);
		graph2.addEdge(2,0,6);
		//graph2.addEdge(2,2,8);
		//graph2.addEdge(0,2,8);
		
		
		System.out.println(graph2.graphType());

	}

} // end Graph
