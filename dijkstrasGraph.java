import java.util.*; // needed for priority queue
import java.util.List;
public class dijkstrasGraph {

    // variables
    private int numberOfVertexs;
    private List<List<Vertex>> adjacencyListToVertex;
    private int[] distancesOfVertexs;
    private Set<Integer> settledVertexs;
    private PriorityQueue<Vertex> tempPriorityQueue;

    // constructor
    public dijkstrasGraph(int numberOfVertexs){
        this.numberOfVertexs = numberOfVertexs;
        distancesOfVertexs = new int[numberOfVertexs];
        settledVertexs = new HashSet<Integer>();
        tempPriorityQueue = new PriorityQueue<Vertex>(numberOfVertexs);
    }

    public void dijkstrasAlgorithm(List<List<Vertex>> adjacencyListToVertex, int source){
        this.adjacencyListToVertex = adjacencyListToVertex;
        for(int i = 0; i < numberOfVertexs; i++){
            distancesOfVertexs[i] = Integer.MAX_VALUE;
        }
        tempPriorityQueue.add(new Vertex(source, 0));
        distancesOfVertexs[source] = 0;
        while(settledVertexs.size() != numberOfVertexs){
            if(tempPriorityQueue.isEmpty()){
                return; // exit if pq is empty
            }
            int tempVertex = (tempPriorityQueue.remove()).lastVertexInPath;
            if(settledVertexs.contains(tempVertex)){ // known vertex
                continue; // does not execute rest of code
            }
            settledVertexs.add(tempVertex);
            getVertexPath(tempVertex);
        }
    }

    public void getVertexPath(int vertex){
        int x = -1;
        int y = -1;
        for(int i = 0; i < adjacencyListToVertex.get(vertex).size(); i++){
            Vertex adjacentVertex = adjacencyListToVertex.get(vertex).get(i);
            if(!settledVertexs.contains(adjacentVertex.lastVertexInPath)){
                x = adjacentVertex.weightOfPath;
                y = distancesOfVertexs[vertex] + x;
                if(y<distancesOfVertexs[adjacentVertex.lastVertexInPath]){
                    distancesOfVertexs[adjacentVertex.lastVertexInPath] = y;
                }
                tempPriorityQueue.add(new Vertex(adjacentVertex.lastVertexInPath, distancesOfVertexs[adjacentVertex.lastVertexInPath]));
            }
        }
    }

    // tester method
    public static void main (String arg[]){
        int numberOfVertexs = 5;
        List<List<Vertex>> adjacencyListToVertex = new ArrayList<List<Vertex>>();
        System.out.println("X");
        for(int i =0; i< numberOfVertexs; i++){
            List<Vertex> addItem = new ArrayList<Vertex>();
            adjacencyListToVertex.add(addItem);
        }
        adjacencyListToVertex.get(0).add(new Vertex(1,3));
        adjacencyListToVertex.get(0).add(new Vertex(2,7));
        adjacencyListToVertex.get(0).add(new Vertex(3,2));
        adjacencyListToVertex.get(1).add(new Vertex(4,8));
        adjacencyListToVertex.get(1).add(new Vertex(5,6));
        adjacencyListToVertex.get(2).add(new Vertex(1,4));
        dijkstrasGraph runner = new dijkstrasGraph(numberOfVertexs);
        int source = 0;
        runner.dijkstrasAlgorithm(adjacencyListToVertex,source);
        for(int i = 0; i < runner.distancesOfVertexs.length; i++){
            System.out.println(runner.distancesOfVertexs[i]);
        }
    }
}
