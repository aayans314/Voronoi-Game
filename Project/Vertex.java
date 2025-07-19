import java.util.ArrayList;

public class Vertex  {
    
    private LinkedList<Edge  > incidentEdges;
    private ArrayList<Vertex  > adjacentVertices;
    private double distanceFromSource;
    private Vertex predecessor;

    

    public Vertex(){
        this.incidentEdges = new LinkedList<>();
        this.adjacentVertices = new ArrayList<>();
    }

    
    public Edge getEdgeTo(Vertex   vertex){

        for (Edge edge: incidentEdges){
            if((edge.other(this)==vertex)){
                return edge;
            }
        }
        return null;
    }

    public void addEdge(Edge   edge){
        incidentEdges.add(edge);
    }

    public boolean removeEdge(Edge   edge){
        if(incidentEdges.contains(edge)){
            for(int i=0;i<incidentEdges.size();i++){
                if(incidentEdges.get(i)==edge){
                    incidentEdges.remove(i);
                }
            }
            return true;
        }
        else return false;
    }

    public ArrayList<Vertex  > vertices(){
        return this.adjacentVertices;
    }

    public LinkedList<Edge> edges(){
        return this.incidentEdges;
    }



    @SuppressWarnings({ "rawtypes", "unchecked" })
    public Iterable<Vertex > adjacentVertices(){
        return (Iterable) this.adjacentVertices;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public Iterable<Edge  > incidentEdges(){
        return (Iterable) this.incidentEdges;
    }
   
    public double distanceFromSource(){
        return this.distanceFromSource;
    }

    public void setDistance(double distance){
        this.distanceFromSource = distance;
    }
    public Vertex getPredecessor(){
        return this.predecessor;
    }

    public void setPredecessor(Vertex pred){
        this.predecessor = pred;
    }
    
    
}