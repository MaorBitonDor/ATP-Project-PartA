package test;
import algorithms.mazeGenerators.*;
import algorithms.search.*;
import java.util.ArrayList;

//todo copy paste the test from the pdf!!!
public class RunSearchOnMaze {
    public static void main(String[] args) {
        IMazeGenerator mg = new MyMazeGenerator();
        Maze maze = mg.generate(10, 10);
//        int[][] map ={{ 1, 0, 0, 0, 0, 0, 1, 0, 0, 0 },
//                      { 1, 0, 1, 1, 1, 0, 1, 1, 1, 0 },
//                      { 0, 0, 0, 0, 0, 0, 1, 0, 0, 0 },
//                      { 1, 0, 1, 0, 1, 0, 1, 0, 1, 1 },
//                      { 1, 0, 0, 0, 0, 0, 1, 0, 0, 0 },
//                      { 1, 0, 1, 1, 1, 1, 1, 1, 1, 0 },
//                      { 1, 0, 1, 0, 0, 0, 0, 0, 1, 0 },
//                      { 1, 0, 1, 0, 1, 0, 1, 1, 1, 0 },
//                      { 1, 0, 0, 0, 1, 0, 0, 0, 0, 0 },
//                      { 1, 1, 1, 1, 1, 1, 1, 1, 1, 0 }};
//        maze.setMaze(map);
        maze.print();
        SearchableMaze searchableMaze = new SearchableMaze(maze);
        MazeState ms = new MazeState(new Position(3,3));
        ArrayList<AState> possibleStates = searchableMaze.getAllPossibleStates(ms);
//        solveProblem(searchableMaze, new BreadthFirstSearch());
        solveProblem(searchableMaze, new DepthFirstSearch());
//        solveProblem(searchableMaze, new BestFirstSearch());
    }
    private static void solveProblem(ISearchable domain, ISearchingAlgorithm searcher) {
        //Solve a searching problem with a searcher
        Solution solution = searcher.solve(domain);
        System.out.println(String.format("'%s' algorithm - nodes evaluated: %s", searcher.getName(), searcher.getNumberOfNodesEvaluated()));
        //Printing Solution Path
        System.out.println("Solution path:");
        ArrayList<AState> solutionPath = solution.getSolutionPath();
        for (int i = 0; i < solutionPath.size(); i++) {
            System.out.println(String.format("%s. %s",i,solutionPath.get(i)));
        }
    }
}
