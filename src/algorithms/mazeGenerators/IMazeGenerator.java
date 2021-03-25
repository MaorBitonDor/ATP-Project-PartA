package algorithms.mazeGenerators;

public interface IMazeGenerator {
    Maze generate(int columns,int rows);
    long measureAlgorithmTimeMillis(int columns,int rows);
}
