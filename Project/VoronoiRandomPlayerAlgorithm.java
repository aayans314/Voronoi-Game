import java.util.Random;

/**
 * This algorithm picks a random Vertex that doesn't have a token on it.
 */
public class VoronoiRandomPlayerAlgorithm extends VoronoiGraphPlayerAlgorithm {

    public VoronoiRandomPlayerAlgorithm(VoronoiGraph g) {
        super(g);
    }

    public Vertex chooseVertex(int playerIndex, int numRemainingTurns) {
        Random rand = new Random();

        int i;
        while (graph.hasToken(graph.getVertex((i = rand.nextInt(graph.size()))))){}

        return graph.getVertex(i);
    }

}
