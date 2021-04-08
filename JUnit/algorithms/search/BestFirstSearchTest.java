package algorithms.search;

import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BestFirstSearchTest {
    private BestFirstSearch best;
    private BreadthFirstSearch bfs;
    private Maze maze;

    @Test
    void getName() {
        best = new BestFirstSearch();
        assertEquals(best.getName(),"Best First Search");
    }

    @Test
    void getNumberOfNodesEvaluated() {
        best = new BestFirstSearch();
        assertEquals(best.getNumberOfNodesEvaluated(),0);
        maze = new MyMazeGenerator().generate(10,10);
        SearchableMaze searchableMaze = new SearchableMaze(maze);
        best.solve(searchableMaze);
        assertNotEquals(best.getNumberOfNodesEvaluated(),0);
    }

    @Test
    void incNumberOfNodesEvaluated() {
        best = new BestFirstSearch();
        assertEquals(best.getNumberOfNodesEvaluated(),0);
        best.incNumberOfNodesEvaluated();
        assertEquals(best.getNumberOfNodesEvaluated(),1);
    }

    @Test
    void solve() {
        best = new BestFirstSearch();
        bfs = new BreadthFirstSearch();
        for (int i = 5; i < 35; i++) {
            for (int j = 5; j < 35; j++) {
                maze = new MyMazeGenerator().generate(i,j);
                SearchableMaze searchableMaze = new SearchableMaze(maze);
                Solution bestSol = best.solve(searchableMaze);
                Solution bfsSol = bfs.solve(searchableMaze);
                assertTrue(bestSol.getSolutionCost()<= bfsSol.getSolutionCost());
            }

        }

    }
}