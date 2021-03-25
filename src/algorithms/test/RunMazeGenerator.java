package algorithms.test;

import algorithms.mazeGenerators.EmptyMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;
import algorithms.mazeGenerators.SimpleMazeGenerator;

public class RunMazeGenerator {
    public static void main(String[] args) {
        EmptyMazeGenerator mazeGen = new EmptyMazeGenerator();
        Maze maze = mazeGen.generate(10,10);
        //maze.print();
        SimpleMazeGenerator simpleMazeGenerator = new SimpleMazeGenerator();
        Maze maze1 = simpleMazeGenerator.generate(10,10);
        maze1.print();
    }
}
