package test;

import algorithms.maze3D.*;
import algorithms.search.*;

import java.util.ArrayList;

public class RunSearchOnMaze3D {
    public static void main(String[] args) throws Exception {
        IMaze3DGenerator mg = new MyMaze3DGenerator();
        Maze3D maze3D = mg.generate(30,30, 30);
        SearchableMaze3D searchableMaze3D = new SearchableMaze3D(maze3D);
        solveProblem(searchableMaze3D, new BreadthFirstSearch());
        solveProblem(searchableMaze3D, new DepthFirstSearch());
        solveProblem(searchableMaze3D, new BestFirstSearch());
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
