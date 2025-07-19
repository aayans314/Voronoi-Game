public class VoronoiBlockerAlgorithm extends VoronoiGraphPlayerAlgorithm {

    public VoronoiBlockerAlgorithm(VoronoiGraph g) {
        super(g);
    }

    public Vertex chooseVertex(int playerIndex, int numRemainingTurns) {

        int opponentIndex = 0;
        if (playerIndex == 0) opponentIndex = 1;
        Vertex bestVertex = null;
        double bestScore = 0;

        // Iterate through all vertices in the graph
        for (Vertex vertex : graph.vertices.input) {

            // Skip the vertex if it is already taken (owned by any player)
            if (graph.hasToken(vertex)) {
                continue;
            }

            // Initialize scores for the current vertex
            double blockingScore = 0;  // Blocking potential score for the vertex
            double valueScore = 0;     // Inherent value of the vertex

            // Value of the vertex (can be adjusted based on turns left)
            valueScore = graph.getValue(vertex); // The inherent value of the vertex
            if (numRemainingTurns < 3) {
                // If the game is nearing its end, prioritize high-value vertices
                valueScore *= 2;
            }

            // Evaluate the blocking score based on proximity to opponent-controlled vertices
            for (Vertex neighbor : vertex.vertices()) {
                int neighborOwner = graph.getCurrentOwner(neighbor);

                // If the neighbor is controlled by the opponent (closer to opponent)
                if (neighborOwner == opponentIndex) {
                    double distance = graph.getDistance(vertex, neighbor);
                    // Block opponent's expansion based on proximity and neighbor's value
                    blockingScore += graph.getValue(neighbor) / distance;
                }
            }

            // Combine the value score and the blocking score
            double totalScore = valueScore + blockingScore;

            // If this vertex has a higher score, update the best vertex
            if (totalScore > bestScore) {
                bestScore = totalScore;
                bestVertex = vertex;
            }
        }

        return bestVertex;
    }
}
