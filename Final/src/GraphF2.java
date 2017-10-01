import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;
import java.util.TreeMap;

class GraphF2 {
  private int numVertices; // number of vertices in the graph
  private int numEdges;    // number of edges in the graph
  
  // For each vertex, we need to keep track of the edges,
  // so for each edge, we need to store the second vertex and 
  // the edge weight. This can be done as a <key, value> pair, 
  // with the second vertex as the key, and the weight as the 
  // value. The TreeMap data structure is used to store a list 
  // these (key, value) pairs for each vertex, accessible as 
  // adjList.get(v).  
  private Vector<TreeMap<Integer, Integer>> adjList;
  
  public GraphF2(int n) {
    // Constructor for weighted graph.
    // Precondition: The number of vertices n should be
    // greater than zero.
    // Postcondition: Initializes the graph with n vertices.
    numVertices = n;
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
    //adjList.get(w).put(v, wgt); // needed for undirected graphs
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
  
  /*
   * Dijkstra's shortest path algorithm
   */
  public static int[] shortestPath (GraphF2 theGraph) {
	  // finds the minimum cost paths between 0 vertex and all other vertices in a 
	  // weighted directed graph.
	  int n = theGraph.numVertices;
	  ArrayList<Integer> vertexSet = new ArrayList<Integer>();
	  vertexSet.add(0);
	  
	  int[] weights = new int[n];
	  weights[0] = 0;
	  for (int v=1; v<n; v++) {
		  weights[v] = theGraph.getEdgeWeight(0, v);
	  }
	  for (int step=2; step<n; step++) {
		  int smallVertex = 0;
		  int smallWeight = Integer.MAX_VALUE;
		  for (int v=1; v<n; v++) {
			  if (weights[v] < smallWeight) {
				  if (!vertexSet.contains(v)) {
						 smallVertex = v;
						 smallWeight = weights[v];
					  }				  
			  }
		  }
		  vertexSet.add(smallVertex);
		  for (int u=1; u<n; u++) {
			  if (!vertexSet.contains(u) && theGraph.getEdgeWeight(smallVertex, u) < Integer.MAX_VALUE) {
				  if (weights[u] > (weights[smallVertex] + theGraph.getEdgeWeight(smallVertex, u))) {
					  weights[u] = weights[smallVertex] + theGraph.getEdgeWeight(smallVertex, u);
				  }
			  }
		  }
	  }
	  return weights;
  }

  public static void main(String[] args) {
	  // create a graph... figure 14-24a in Prichard
	  GraphF2 graph1 = new GraphF2(5);
	  graph1.addEdge(0, 1, 8);
	  graph1.addEdge(0, 4, 4);
	  graph1.addEdge(0, 3, 9);
	  graph1.addEdge(1, 2, 1);
	  graph1.addEdge(2, 1, 2);
	  graph1.addEdge(2, 3, 3);
	  graph1.addEdge(3, 2, 2);
	  graph1.addEdge(3, 4, 7);
	  graph1.addEdge(4, 2, 1);

	  int[] shortest = new int[graph1.numVertices]; 
	  shortest = shortestPath(graph1);
	  for (int i=0; i< graph1.numVertices; i++){
		  System.out.println(i + ": " + shortest[i]);
	  }
  }
  
} // end Graph