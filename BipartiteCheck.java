/**
 * BipartiteCheck.java
 *
 * Uses algorithm based on Breath First Search
 * to determine if a graph is bipartite (or in different words 2 colorable).
 */
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;
public class BipartiteCheck {
    /**
      * Uses Breadth-First Search to test for bicolorability.
      */
    public boolean check(ArrayList<Integer>[] edges, int numVertices) {
        Queue<Integer> queue = new LinkedList<Integer>();

        //keeps track of color of each vertex
        int[] color = new int[numVertices + 1];

        //initialize color array
        // -1: no color has been set yet
        //  0: first color
        //  1: second color
        for(int y = 0; y < color.length; y++) {
            color[y] = -1;
        }

        //Loop through all vertices
        for(int x = 1; x <= numVertices; x++) { 
            if(color[x] == -1) {
                queue.add(x);

                //start the coloring
                color[x] = 0;
            }

            //bfs
            while(!queue.isEmpty()) {
                Integer currentVertex = queue.remove();

                //loop through current vertex neighbors
                for(int i = 0; i < edges[currentVertex].size(); i++) {
                    int neighborVertex = edges[currentVertex].get(i);

                    //vertex hasn't been visited yet
                    if(color[neighborVertex] == -1) {
                        color[neighborVertex] = 1 - color[currentVertex];
                        queue.add(neighborVertex);
                    //if we run into a neighbor with the same color as
                    //currentVertex, return false: not bicolorable
                    } else if(color[currentVertex] == color[neighborVertex]) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
