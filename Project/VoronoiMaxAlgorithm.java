/**
 * Filename: VoronoiMaxAlgorithm.java
 * Author: Aayan Shah
 * Last Modified: 12/9/2024
 * Purpose: A smarter point picking algorithm
 */

import java.util.ArrayList;

public class VoronoiMaxAlgorithm extends VoronoiGraphPlayerAlgorithm {

    public VoronoiMaxAlgorithm(VoronoiGraph g) {
        super(g);
    }

    @Override
    public Vertex chooseVertex(int playerIndex, int numRemainingTurns) {


        Vertex bestVertex = null;
        //This is essentially all the vertices in the graph
        ArrayList<Vertex> vertices = graph.vertices.input;

        int oppIndex = 0;
        if (playerIndex == 0) {
            oppIndex = 1;
        }
        double bestValue = 0;


        for (Vertex myVertex : vertices) {

            //No need to analyze if the vertex is already taken
            if(graph.hasToken(myVertex)){
                continue;
            }
            //testvalue, the overall value of the vertex
            double testValue = 0;
            //valuefactor, the factor contributed by the vertex self value
            double valueFactor = 0;
            //edgefactor, the factor contributed by how advantageous its edges are
            double edgeFactor = 0;
            //leftneighbor, the factor showing the number of neighbors it can still win over
            double leftNeighbors = 0;

            for (Vertex testVertex : vertices) {
                //skips analyzing self vertex
                if(testVertex==myVertex) continue;
                // calculate the edge factor as value of connected vertex / distance between them
                if (graph.getCurrentOwner(testVertex) != null) {
                    if(graph.getCurrentOwner(testVertex)!=oppIndex){
                    edgeFactor += (double) graph.getValue(testVertex) / Math.pow(graph.getDistance(myVertex, testVertex),0.5);
                    //increases leftneighbors as we can win this in the future
                    leftNeighbors++;
                    }
                }
            }

            //initially multiplying by 2 to get a higher factor
            valueFactor = graph.getValue(myVertex) * 2;
            if (numRemainingTurns < 3) {
                //when the game is very close to ending, prioritize the value of vertex to be taken
                valueFactor *= 2;
            }

            //sums up all of the factors
            testValue = valueFactor+leftNeighbors*50+edgeFactor;

            //swaps bestvertex if newer one has higher value
            if (testValue > bestValue) {
                bestValue = testValue;
                bestVertex = myVertex;
            }


        }
        //return the best vertex with highest value
        return bestVertex;
    }
}
