package algorithms.mazeGenerators;

public interface IMazeGenerator {
    Maze generate(int columns,int rows) throws Exception;
    long measureAlgorithmTimeMillis(int columns,int rows) throws Exception;
}
