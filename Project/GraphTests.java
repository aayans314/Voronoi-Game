public class GraphTests {
    public static void testGraphFromFile() {
        Graph graph = new Graph("graph1.txt");
        System.out.println("Test Graph From File:");
        System.out.println("Number of vertices: " + graph.size());
        System.out.println("Number of edges: " + graph.myEdges.size());
        
    }

    public static void testRandomGraphConstruction(int n, double p) {
        Graph graph = new Graph(n, p);
        int possibleEdges = n * (n - 1) / 2;
        int actualEdges = graph.myEdges.size();
        double observedP = (double) actualEdges / possibleEdges;
    
        System.out.println("Test Random Graph Construction:");
        System.out.println("Number of vertices: " + graph.size());
        System.out.println("Number of edges: " + actualEdges);
        System.out.println("Expected edges: " + (int)(possibleEdges * p));
        System.out.println("Observed probability: " + observedP);
        System.out.println("Within expected range: " + (Math.abs(observedP - p) < 0.05 ? "Yes" : "No"));
    }

    public static void main(String[] args) {
        testGraphFromFile(); 
        testRandomGraphConstruction(10, 0.3); 
}
}
