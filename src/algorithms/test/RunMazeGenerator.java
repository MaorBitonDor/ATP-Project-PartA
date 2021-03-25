package algorithms.test;

import algorithms.mazeGenerators.EmptyMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;

public class RunMazeGenerator {
    public static void main(String[] args) {
        EmptyMazeGenerator mazeGen = new EmptyMazeGenerator();
        Maze maze = mazeGen.generate(10,10);
        maze.print();

    }
}
