package bearmaps.hw4;
import edu.princeton.cs.algs4.Stopwatch;
import java.util.List;
import bearmaps.proj2ab.DoubleMapPQ;
import bearmaps.proj2ab.ExtrinsicMinPQ;
import java.util.Map;
import java.util.HashMap;
import java.util.LinkedList;


public class AStarSolver<Vertex> implements ShortestPathsSolver<Vertex>{
    private Map<Vertex, Double> distTo ;
    private Map<Vertex, Vertex> edgeTo ;
    private SolverOutcome outcome;
    private LinkedList<Vertex> solution;
    private double solutionW;
    private int numState = 0;
    private ExtrinsicMinPQ<Vertex> pq;
    private boolean isEmpty;
    private double timeSpent;

    public AStarSolver(AStarGraph<Vertex> input, Vertex start, Vertex end, double timeout){
        Stopwatch sw = new Stopwatch();
        distTo = new HashMap<>();
        edgeTo = new HashMap<>();
        pq = new DoubleMapPQ<>();
        solution = new LinkedList<>();

        distTo.put(start, 0.0);
        pq.add(start, distTo.get(start) + input.estimatedDistanceToGoal(start, end) );

        while(pq.size() > 0 && !pq.getSmallest().equals(end)&& sw.elapsedTime() < timeout){
            Vertex v = pq.removeSmallest();
            numState += 1;
            for(WeightedEdge<Vertex> e: input.neighbors(v)){
                if(!distTo.containsKey(e.to())) {
                    distTo.put(e.to(), Double.POSITIVE_INFINITY);
                }
                relax(e, input, end, v);
            }
        }
        if(pq.size() == 0){
            outcome = SolverOutcome.UNSOLVABLE;
        }else if (pq.getSmallest().equals(end)){
            outcome = SolverOutcome.SOLVED;
            Vertex v = pq.getSmallest();
            solutionW = distTo.get(v);
            while(v != null){
                solution.addFirst(v);
                v = edgeTo.get(v);
            }
        }else{
            outcome = SolverOutcome.TIMEOUT;
        }
        timeSpent = sw.elapsedTime();
    }

    private void relax(WeightedEdge<Vertex> e, AStarGraph<Vertex> input, Vertex end, Vertex original){
        Vertex w = e.to(), v = e.from();

        if(distTo.get(v) + e.weight() < distTo.get(w) || !distTo.containsKey(w) ){
            distTo.put(w, distTo.get(v) + e.weight());
            edgeTo.put(w, original);
            if(pq.contains(w)) {
                pq.changePriority(w, distTo.get(w) + input.estimatedDistanceToGoal(w, end)  );
            }else{
                pq.add(w, distTo.get(w) + input.estimatedDistanceToGoal(w, end));
            }
        }

    }

    public SolverOutcome outcome(){
        return outcome;
    }
    public List<Vertex> solution(){
        if(outcome == SolverOutcome.SOLVED){
            return solution;
        }
        return null;

    }
    public double solutionWeight(){
        if(outcome ==SolverOutcome.SOLVED){
            return solutionW;
        }
        return 0;
    }
    public int numStatesExplored(){
        return numState;
    }
    public double explorationTime(){
        return timeSpent;
    }
}
