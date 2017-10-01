import java.util.*;

class BFSIterator implements Iterator<Integer> {

  private GraphF1 g;          // The graph to be iterated. 
  private int numVertices;  // The number of vertices in the graph.
  private int count;        // Used to mark the order the 
                            // vertices are visited.
  private int[] mark;       // Keeps track of the order that  
                            // the vertices are visited.
  private int iter;         // Used for the iteration.
  
  public BFSIterator(GraphF1 g) {
    // Creates an iterator for the graph g.
    // Precondition: The graph g is a non-empty graph.
    // Postcondition: Completes the Breadth-first search
    // of graph g, ready for iteration.
    this.g = g;
    numVertices = g.getNumVertices();
    mark = new int[numVertices];
    Arrays.fill(mark,0,numVertices,-1);

    count = 0;
    iter = -1;
    startSearch();
  } // end constructor
  
  public boolean hasNext() {
    // Determines if there is another vertex in the BFS 
    // iteration of the graph.
    // Precondition: None.
    // Postcondition: Returns true if there are more vertices
    // in the BFS iteration, otherwise returns false.
    return (iter >=0) && (iter < numVertices);
  } // end hasNext
  
  public Integer next() throws NoSuchElementException {
    // Returns the next vertex in the BFS iteration
    // of the graph.
    // Precondition: The BFS iteration has more vertices.
    // Postcondition: Returns next element in the BFS 
    // iteration, if none exists, throws an exception.
    if (hasNext()) {
      return mark[iter++];
    } else {
      throw new NoSuchElementException();
    }   // end if
  } // end next
  
  public void remove() {
    // Not implemented, vertices cannot be removed 
    // from the graph using the iterator.
    throw new UnsupportedOperationException();
  } // end remove
  
  protected void startSearch() {
    // Searches each unvisited vertex.
    // Precondition: The vertex exists in the graph.
    // Postcondition: Completes a breadth-first search
    // with each unvisited vertex.
 
    for (int v=0; v < numVertices; v++) {
      if (mark[v] == -1) {
        search(v);
      } // end if
    } // end for
    
    // Breadth-first search completed, initialize
    // iterator.
    iter = 0;
  } // end startSearch

  protected void search(Integer vertex) {
    // Traverse the graph beginning at vertex v by using
    // a breadth-first search. 
    // Precondition: The vertex v is in the graph.
    // Postcondition: Completes a breadth-first search 
    // starting from vertex.

    LinkedList<Integer> q = new LinkedList<Integer>();
    TreeMap<Integer, Integer> m;
    Set<Integer> connectedVertices;
    Integer v;
    
    // This gets it started at vertex v
    q.add(vertex);
    
    while (!q.isEmpty()) {
      v = q.remove();
      if (mark[v] == -1) {
        mark[v] = count++;
        
        m = g.getAdjList(v);
        connectedVertices = m.keySet();
        for (Integer w : connectedVertices) {
          if (mark[w] == -1) {
            q.add(w);
          } // end if
        } // end for 
      } // end if

    } // end while    
  } // end search    

} // end BFS
