
class Edge {

  private Integer v, w;    // The vertices of the edge.
  private int weight;      // The weight of the edge.
   
  public Edge(Integer first, Integer second, int edgeWeight) {
    // Constructor. Creates an edge from v to w with weight 
    // edgeWeight.
    // Precondition: None.
    // Postcondition: The edge is created.
    v = first;
    w = second;
    weight = edgeWeight;
  } // end constructor
  
  public int getWeight() {
    // Returns the edge weight
    return weight;
  } // end getWeight
  
  public Integer getV() {
    // Returns the first vertex of the edge
    return v;
  } // end getV

  public Integer getW() {
    // Returns the second vertex of the edge
    return w;
  } // end getW
} // end Edge
