package test;


import algorithms.maze3D.IMaze3DGenerator;
import algorithms.maze3D.Maze3D;
import algorithms.maze3D.MyMaze3DGenerator;
import algorithms.maze3D.Position3D;

public class RunMaze3DGenerator {
    public static void main(String[] args) throws Exception {
        testMazeGenerator(new MyMaze3DGenerator());
    }
    private static void testMazeGenerator(IMaze3DGenerator maze3DGenerator) throws Exception {
        // prints the time it takes the algorithm to run
        System.out.println(String.format("Maze generation time(ms): %s", maze3DGenerator.measureAlgorithmTimeMillis(100/*depth*/,100/*rows*/,100/*columns*/)));
        // generate another maze
        Maze3D maze3D = maze3DGenerator.generate(100/*depth*/,100/*rows*/, 100/*columns*/);
        // prints the maze
//        maze3D.print();
        // get the maze entrance
        Position3D startPosition = maze3D.getStartPosition();
        // print the start position
        System.out.println(String.format("Start Position: %s", startPosition)); // format "{depth,row,column}"
        // prints the maze exit position
        System.out.println(String.format("Goal Position: %s", maze3D.getGoalPosition()));
    }
}
