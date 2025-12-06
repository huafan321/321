package hw4.puzzle;
import edu.princeton.cs.algs4.MinPQ;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Solver {
    private MinPQ<node> pq;
    private node last;
    private Map<WorldState,Integer> bestGCost = new HashMap<>();

    public Solver(WorldState initial){
        pq = new MinPQ<node>();
        node k = new node(initial, 0, null,0);
        pq.insert(k);
        bestGCost.put(initial,0);

        astarSearch();
    }

    public int moves(){
        if (last == null) return -1;
        return last.moves;
    }

    public Iterable<WorldState> solution(){
        LinkedList<WorldState> path = new LinkedList<>();
        node current = last;

        while(current != null){
            path.add(0,current.current);
            current = current.previous;
        }
        return path;
    }

    private class node implements Comparable<node>{
        private WorldState current;
        private int moves;
        private node previous;
        private final int priority;

        public node(WorldState initial, int moves,node previous, int h){
            this.current = initial;
            this.moves = moves;
            this.previous = previous;

            this.priority = moves + h;
        }

        @Override
        public int compareTo(node o) {
            return priority - o.priority;
        }
    }
    private void astarSearch() {
        Map<WorldState, Integer> hCache = new HashMap<>();

        while (!pq.isEmpty()) {
            node x = pq.delMin();

            if (x.moves != bestGCost.get(x.current)) continue;

            if (x.current.isGoal()) {
                last = x;
                return;
            }

            for (WorldState n : x.current.neighbors()) {
                if (x.previous != null && n.equals(x.previous.current)) continue;

                int newG = x.moves + 1;

                if (!bestGCost.containsKey(n) || newG < bestGCost.get(n)) {
                    bestGCost.put(n, newG);

                    int h = hCache.computeIfAbsent(n, k -> k.estimatedDistanceToGoal());

                    pq.insert(new node(n, newG, x, h));
                }
            }
        }
    }

}
