public class Edge   {
    private Vertex  [] vertices;
    private double distance;


    public Edge(Vertex   u, Vertex   v, double distance){
        this.vertices = new Vertex[] {u,v};
        this.distance = distance;

    }

    public double distance(){
        return this.distance;
    }
    public Vertex  [] vertices(){
        return vertices;
    }
    public Vertex other(Vertex   vertex){
        if(vertices[0]==vertex) return vertices[1];
        else if (vertices[1]==vertex) return vertices[0];
        else return null;
    }

}
