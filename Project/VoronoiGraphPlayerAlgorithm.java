public abstract class VoronoiGraphPlayerAlgorithm {
    public VoronoiGraph graph;

    public VoronoiGraphPlayerAlgorithm(VoronoiGraph g) {
        graph = g;
    }

    /**
     * Returns a Vertex chosen for the next token to place as in the rules for the
     * Voronoi game.
     * 
     * @param playerIndex       The index of the player choosing the Vertex.
     * @param numRemainingTurns The number of turns remaining for this player after
     *                          this turn.
     * @return a Vertex chosen for the next token to place as in the rules for the
     *         Voronoi game.
     */
    public abstract Vertex chooseVertex(int playerIndex, int numRemainingTurns);
}
