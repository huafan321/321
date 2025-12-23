package lab11.graphs;

import edu.princeton.cs.algs4.Stack;

/**
 *  @author Josh Hug
 */
public class MazeCycles extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */

    private static final int INFINITY = Integer.MAX_VALUE;
    public MazeCycles(Maze m) {
        super(m);
    }

    @Override
    public void solve() {
        // TODO: Your code here!
        Stack<Integer> dfs = new Stack<>();
        for(int v = 0; v < maze.V(); v++){
            edgeTo[v] = INFINITY;
            distTo[v] = INFINITY;
        }
        distTo[0] = 0;
        edgeTo[0] = 0;
        dfs.push(0);

        while(!dfs.isEmpty()){
           int x = dfs.pop();
           marked[x] = true;
           for(int n : maze.adj(x)){
               if (marked[n] && !isParent(n,x)){
                   announce();
                   return;
               }
               if (!marked[n]){
                   edgeTo[n] = x;
                   distTo[n] = distTo[x] + 1;
                   dfs.push(n);
               }
           }
        }
    }

    // Helper methods go here
    private boolean isParent(int a, int b){
        return edgeTo[b] == a;
    }
}

