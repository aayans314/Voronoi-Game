import java.util.ArrayList;
import java.util.Random;
import java.io.* ;
import java.util.HashMap;
import java.util.Comparator;

public class Graph {
    
    MyHashMap<Vertex, LinkedList<Edge>> vertices = new MyHashMap<>();
    ArrayList<Edge> myEdges;
    
    Random random = new Random();
    
    public Graph(){
        this(0);
    }
    public Graph (int n){
        this(n,0.0);
    }
    @SuppressWarnings("unlikely-arg-type")
    public Graph(int n, double probability){
        
        myEdges  = new ArrayList<>();
        while(n>0){
            Vertex myVertex = new Vertex();        
            vertices.put(myVertex,null);
            //Vertex vertex = new Vertex();
            //vertices.put(vertex, null);
            for(Vertex testVertex:vertices.inputList()){
            if(random.nextDouble()<probability){
                Edge myEdge = new Edge(myVertex, testVertex, 1);
                if(! vertices.values().contains(myEdge)){
                myVertex.addEdge(myEdge);
                //vertex.addEdge(myEdge);
                myEdges.add(myEdge);
                vertices.put(myVertex,myVertex.edges());
                //vertices.put(testVertex,testVertex.edges());
                }
            }
        }
        n--;
    }
    }

    public int size(){
        return vertices.size();
    }

    public Iterable<Vertex> getVertices(){
        return vertices.keySet();
    }

    public Iterable<Edge> getEdges(){
        return myEdges;
    }

    public Vertex addVertex(){
        Vertex vertex = new Vertex();
        vertices.put(vertex, null);
        return vertex;
    }

    public Edge addEdge(Vertex u, Vertex v, double distance){
        Edge edge = new Edge(u, v, distance);
        u.addEdge(edge);
        v.addEdge(edge);
        myEdges.add(edge);
        vertices.put(u, u.edges());
        vertices.put(v,v.edges());
        return edge;
    }

    public Edge getEdge(Vertex u, Vertex v){
        return u.getEdgeTo(v);
    }

    public boolean remove(Vertex vertex){
        if (vertices.containsKey(vertex)){
            
            ArrayList<Vertex> myListofVertices =  vertex.vertices();
            for(Vertex testVertex: myListofVertices){
                testVertex.removeEdge(testVertex.getEdgeTo(vertex));
            }
            vertices.remove(vertex);
            return true;
        }
        return false;
    }

    public boolean remove(Edge edge){
        if(myEdges.contains(edge)){
            edge.vertices()[0].removeEdge(edge);
            edge.vertices()[1].removeEdge(edge);
            myEdges.remove(edge);
            return true;
        }
        else return false;
    }

    public HashMap<Vertex, Double> distanceFrom(Vertex source){
        HashMap<Vertex, Double> hashMap = new HashMap<>();
        Comparator<Vertex> comparator = new Comparator<Vertex>() {
            @Override
            public int compare(Vertex o1, Vertex o2) {
                return hashMap.get(o1).compareTo(hashMap.get(o2));
            }
        };
        PriorityQueue<Vertex> priorityQueue = new Heap<> (comparator,false);
        for (Vertex v : vertices.keySet()) {
            if (!v.equals(source)) {
                hashMap.put(v, Double.POSITIVE_INFINITY);
            } else {
                hashMap.put(source, 0.0);
            }
            priorityQueue.offer(v);
        }
        while (priorityQueue.size() != 0) {
            Vertex u = priorityQueue.poll();
            for (Vertex neighbor : u.vertices()) {
                double alt = hashMap.get(u) + u.getEdgeTo(neighbor).distance();
                if (alt < hashMap.get(neighbor)) {
                    hashMap.put(neighbor, alt);
                    priorityQueue.updatePriority(neighbor);
                }
            }
        }
        return hashMap;
    }

    /* *
   * A graph constructor that takes in a filename and builds
   * the graph with the number of vertices and specific edges 
   * specified.  
   * */
  public Graph( String filename ) {
      try {
          //Setup for reading the file
          FileReader fr = new FileReader(filename);
          BufferedReader br = new BufferedReader(fr);
          //Get the number of vertices from the file and initialize that number of vertices
          ArrayList<Vertex> verticesDraw = new ArrayList<Vertex>() ;
          Integer numVertices = Integer.valueOf( br.readLine().split( ": " )[ 1 ] ) ;
          for ( int i = 0 ; i < numVertices ; i ++ ) {
              verticesDraw.add( new Vertex() );
          }
          //Read in the edges specified by the file and create them
          this.myEdges = new ArrayList<Edge>() ; //If you used a different data structure to store Edges, you'll need to update this line
          br.readLine(); //We don't use the header, but have to read it to skip to the next line
          //Read in all the lines corresponding to edges
          String line = br.readLine();
             while(line != null){
                 //Parse out the index of the start and end vertices of the edge
                  String[] arr = line.split(",");
                  Integer start = Integer.valueOf( arr[ 0 ] ) ;
                  Integer end = Integer.valueOf( arr[ 1 ] ) ;
                  
                  //Make the edge that starts at start and ends at end with weight 1
                  Edge edge = new Edge((Vertex) verticesDraw.get( start ) ,(Vertex) verticesDraw.get( end ) , 1. ) ;
               //Add the edge to the set of edges for each of the vertices
               Vertex startVertex = (Vertex) verticesDraw.get(start);
               Vertex endVertex = (Vertex) verticesDraw.get (end);
              startVertex.addEdge( edge ) ;
              endVertex.addEdge( edge ) ;
              
              myEdges.add(edge);
              
              //Add the edge to the collection of edges in the graph
              vertices.put(startVertex,startVertex.edges());
              vertices.put(endVertex,endVertex.edges());
              
              //Read the next line
              line = br.readLine();
          }
          // call the close method of the BufferedReader:
          br.close();
          System.out.println( vertices.values() );
        }
        catch(FileNotFoundException ex) {
          System.out.println("Graph constructor:: unable to open file " + filename + ": file not found");
        }
        catch(IOException ex) {
          System.out.println("Graph constructor:: error reading file " + filename);
        }



}

public Vertex getVertex(int index){
    
//System.out.println(vertices.keySet().get(index).distanceFromSource());

return vertices.input.get(index);
}
}

