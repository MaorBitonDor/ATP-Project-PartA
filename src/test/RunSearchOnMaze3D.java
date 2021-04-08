package test;

import algorithms.maze3D.*;
import algorithms.search.*;

import java.util.ArrayList;

public class RunSearchOnMaze3D {
    public static void main(String[] args) throws Exception {
        IMaze3DGenerator mg = new MyMaze3DGenerator();
        Maze3D maze3D = mg.generate(100,100, 100);
//        int[][][] map = {
//                        {{0,0,0,0,0},
//                        {0,0,1,1,0},
//                        {1,0,0,1,0},
//                        {1,1,0,0,0}} ,
//                        {{1,1,1,1,1},
//                        {1,1,1,1,1},
//                        {1,1,1,1,0},
//                        {1,1,1,1,1}},
//                        {{0,0,0,0,0},
//                        {0,0,1,1,0},
//                        {1,0,0,1,0},
//                        {1,1,0,0,0}}
//
//        };
//        Position3D start = new Position3D(0,0,0);
//        Position3D end = new Position3D(2,0,0);
//        Maze3D maze3D = new Maze3D(start,end,map);
//        maze3D.print();
        SearchableMaze3D searchableMaze3D = new SearchableMaze3D(maze3D);
        solveProblem(searchableMaze3D, new BreadthFirstSearch());
        solveProblem(searchableMaze3D, new DepthFirstSearch());
        solveProblem(searchableMaze3D, new BestFirstSearch());
    }
    private static void solveProblem(ISearchable domain, ISearchingAlgorithm searcher) throws Exception {
        //Solve a searching problem with a searcher
        long beforeTime = System.currentTimeMillis();
        Solution solution = searcher.solve(domain);
        long afterTime = System.currentTimeMillis();
        System.out.println("--------------------");
        System.out.println(afterTime-beforeTime);
        System.out.println("--------------------");
        System.out.println(String.format("'%s' algorithm - nodes evaluated: %s", searcher.getName(), searcher.getNumberOfNodesEvaluated()));
        //Printing Solution Path
        System.out.println("Solution path:");
        ArrayList<AState> solutionPath = solution.getSolutionPath();
//        for (int i = 0; i < solutionPath.size(); i++) {
//            System.out.println(String.format("%s. %s",i,solutionPath.get(i)));
//        }
        System.out.println(solution.getSolutionCost());
    }
}
