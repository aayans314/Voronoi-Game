
import java.util.ArrayList;

public class Vertex {
    
    private ArrayList<Edge> edges; // the edges

    /**
     * 
     * Constructs a new vertex.
     */
    public Vertex() {
        edges = new ArrayList<>();
    }
    
    /**
     * method to return the Edge which connects the current vertex and 
     * the given Vertex vertex if such an Edge exists, otherwise return null.
     * 
     * @param vertex a vertex to check with
     * @return the edge that connects the current and the given vertex.
     */
    public Edge getEdgeTo(Vertex vertex) {
        for (Edge edge : edges) {
            if (edge.other(this).equals(vertex)) {
                return edge;
            }
        }
        return null;
    }

    /**
     * adds edge to the collection of edges incident to the current vertex.
     * 
     * @param edge to be added
     */
    public void addEdge(Edge edge){
        edges.add(edge);
    }

    /**
     * removes the given edge from the collection of edges.
     * 
     * @param edge
     * @return true if this Edge was connected to this Vertex, otherwise false.

     */
    public boolean removeEdge(Edge edge) {
        for (Edge otherEdge : edges) {
            if (otherEdge.equals(edge)) {
                edges.remove(otherEdge);
                return true;
            }
        }
        return false;
    }

    /**
     * returns a collection of all the Vertices adjacent to this Vertex.
     * 
     * @return an array list of all the Vertices adjacent to this Vertex.
     * 
     */
    public ArrayList<Vertex> adjacentVertices() {
        ArrayList<Vertex> vertices = new ArrayList<>();
        for (Edge edge : edges) {
            Vertex vertex = edge.other(this);
            vertices.add(vertex);
        }
        return vertices;
    }
    
    /**
     * returns a collection of all the Edges incident to this Vertex 
     * 
     * @return an arraylist of all the Edges incident to this Vertex 
     */
    public ArrayList<Edge> incidentEdges() {
        return edges;
    }

    /**
     * 
     * Returns the degree of this vertex (i.e., the number of edges incident to it).
     * 
     * @return the degree of this vertex
     */
    public int degree() {
        return edges.size();
    }
}   
