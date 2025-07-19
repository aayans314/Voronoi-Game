
public class GraphTests {
    public static void testGraphFromFile() {
        Graph graph = new Graph("graph1.txt");
        System.out.println("Number of vertices: " + graph.size());
        System.out.println("Number of edges: " + graph.getEdges().size());
        for (Edge edge : graph.getEdges()) {
            System.out.println("Edge between " + edge.vertices()[0] + " and " + edge.vertices()[1] + " with distance " + edge.distance());
        }
    }

    public static void testRandomGraphConstruction(int n, double p) {
        Graph graph = new Graph(n, p);
        int maxEdges = n * (n - 1) / 2;
        int actualEdges = graph.getEdges().size();
        double observedP = (double) actualEdges /maxEdges;
    
        System.out.println("Number of vertices: " + graph.size());
        System.out.println("Number of edges: " + actualEdges);
        System.out.println("Expected edges: " + (int)(maxEdges * p));
        System.out.println("Observed probability: " + observedP);
    
    }

    public static void main(String[] args) {
        testGraphFromFile(); 
        testRandomGraphConstruction(500, 0.3); 
    }
}

