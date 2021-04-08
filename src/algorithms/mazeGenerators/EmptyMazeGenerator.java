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
    public Maze generate(int columns, int rows) throws Exception {
        if (columns<2)
            throw new Exception("Illegal number of columns");
        if (rows<2)
            throw new Exception("Illegal number of rows");
        Position[] positions = startAndGoalPositions(columns, rows);
        int[][] map = new int[rows][columns];
        return new Maze(positions[0],positions[1],map);
    }
}
