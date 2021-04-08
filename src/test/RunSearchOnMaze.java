package test;
import algorithms.mazeGenerators.IMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;
import algorithms.mazeGenerators.Position;
import algorithms.search.*;
import java.util.ArrayList;

public class RunSearchOnMaze {
    public static void main(String[] args) throws Exception {
        IMazeGenerator mg = new MyMazeGenerator();
        Maze maze = mg.generate(29, 27);
//        int[][] map = {{0, 0, 0, 0, 0, 1, 1},
//                        {1, 0, 1, 1, 0, 0, 1},
//                        {1, 0, 0, 1, 1, 0, 0},
//                        {1, 1, 0, 0, 1, 1, 0},
//                        {1, 1, 1, 0, 0, 1, 0},
//                        {1, 1, 1, 1, 0, 0, 0}};
//        Position start = new Position(0,0);
//        Position goal = new Position(4,6);
//        Maze maze = new Maze(start,goal,map);
        maze.print();
        SearchableMaze searchableMaze = new SearchableMaze(maze);
        solveProblem(searchableMaze, new BreadthFirstSearch());
//        solveProblem(searchableMaze, new DepthFirstSearch());
        solveProblem(searchableMaze, new BestFirstSearch());
    }
    private static void solveProblem(ISearchable domain, ISearchingAlgorithm searcher) throws Exception {
        //Solve a searching problem with a searcher
        Solution solution = searcher.solve(domain);
        System.out.println(String.format("'%s' algorithm - nodes evaluated: %s", searcher.getName(), searcher.getNumberOfNodesEvaluated()));
        //Printing Solution Path
        System.out.println("Solution path:");
        ArrayList<AState> solutionPath = solution.getSolutionPath();
        for (int i = 0; i < solutionPath.size(); i++) {
            System.out.println(String.format("%s. %s",i,solutionPath.get(i)));
        }
        System.out.println(solution.getSolutionCost());
    }
}