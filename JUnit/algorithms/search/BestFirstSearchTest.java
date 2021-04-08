package algorithms.search;

import algorithms.maze3D.Maze3D;
import algorithms.maze3D.MyMaze3DGenerator;
import algorithms.maze3D.SearchableMaze3D;
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
    void getNumberOfNodesEvaluated() throws Exception {
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
    void solve() throws Exception {
        best = new BestFirstSearch();
        bfs = new BreadthFirstSearch();
        for (int i = 5; i < 35; i++) {
            for (int j = 5; j < 35; j++) {
                maze = new MyMazeGenerator().generate(i,j);
                SearchableMaze searchableMaze = new SearchableMaze(maze);
                Solution bestSol = best.solve(searchableMaze);
                Solution bfsSol = bfs.solve(searchableMaze);
                assertTrue(bestSol.getSolutionCost()<= bfsSol.getSolutionCost());
                assertTrue(bestSol.getSolutionPath().size()>0);
            }
        }
        //testing the time to create a big 2D maze
        long beforeTime = System.currentTimeMillis();
        maze = new MyMazeGenerator().generate(1000,1000);
        long afterTime = System.currentTimeMillis();
        assertTrue(afterTime-beforeTime<=60000);
        //testing the solving of a big 2D maze
        SearchableMaze searchableMaze = new SearchableMaze(maze);
        beforeTime = System.currentTimeMillis();
        Solution bestSol = best.solve(searchableMaze);
        afterTime = System.currentTimeMillis();
        assertTrue(afterTime-beforeTime<=60000);
        //testing the time to create a big 3D maze
        beforeTime = System.currentTimeMillis();
        Maze3D maze3D = new MyMaze3DGenerator().generate(100,100,100);
        afterTime = System.currentTimeMillis();
        assertTrue(afterTime-beforeTime<=60000);
        //testing the solving of a big 3D maze
        SearchableMaze3D searchableMaze3D = new SearchableMaze3D(maze3D);
        beforeTime = System.currentTimeMillis();
        best.solve(searchableMaze3D);
        afterTime = System.currentTimeMillis();
        assertTrue(afterTime-beforeTime<=60000);

    }
}