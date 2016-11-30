/**
 * ConnectCheck.java
 *
 * Uses algorithm based on Breadth First Search
 * to determine if a graph is connected.
 */
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;
public class ConnectCheck {
    private int numConnectedComponents;

    /**
      * Uses Breadth-First Search to find connected components.
      */
    public Integer[] check(ArrayList<Integer>[] edges, int numVertices) {
        Queue<Integer> queue = new LinkedList<Integer>();

        //keeps track of which verticies have been visited
        boolean[] visited = new boolean[numVertices + 1];

        //keeps track of parent for each connected component
        Integer[] parents = new Integer[numVertices + 1];

        numConnectedComponents = 0;

        //initialize visited and parents arrays
        //so that we start with a fresh graph
        //(no verticies have been visited yet)
        for(int y = 0; y < visited.length; y++) {
            visited[y] = false;
            parents[y] = -1;
        }

        //Loop through all vertices
        for(int x = 1; x <= numVertices; x++) { 
            //If we haven't visited the vertex yet
            if(!visited[x]) {
                //start of a new component
                numConnectedComponents++;

                //add the component to the queue  and
                //visit it in order to do a breadth-first searh
                queue.add(x);
                visited[x] = true;

                //This element is the parent of the new component
                parents[x] = x;

                while(!queue.isEmpty()) {
                    Integer currentVertex = queue.remove();

                    //loop through current vertex neighbors
                    for(int i = 0; i < edges[currentVertex].size(); i++) {
                        int neighborVertex = edges[currentVertex].get(i);

                        //If we find one that is unvisited,
                        //add to the queue, set as visited,
                        //and set the component parent
                        if(!visited[neighborVertex]) {
                            queue.add(neighborVertex);
                            visited[neighborVertex] = true;
                            parents[neighborVertex] = x;
                        }
                    }
                }
            }
        }

        return parents;
    }

    //Used in graphstart main in order to get number of
    //connected components
    public int getNumberOfConnectedComponents() {
        return numConnectedComponents;
    }
}
