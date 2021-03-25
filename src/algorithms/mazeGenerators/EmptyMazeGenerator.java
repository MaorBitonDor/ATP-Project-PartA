package algorithms.mazeGenerators;

public class EmptyMazeGenerator extends AMazeGenerator{

    /**
     * generate creates a maze without any walls - 2 dimensions array filled
     * with zeros.
     * @param columns - number of columns in the maze
     * @param rows - number of rows in the maze
     * @return Maze
     */
    @Override
    public Maze generate(int columns, int rows) {
        Position[] positions = startAndGoalPositions(columns, rows);
        int[][] map = new int[rows][columns];
        return new Maze(map,positions[0],positions[1]);
    }
}
