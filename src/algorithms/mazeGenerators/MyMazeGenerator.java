package algorithms.mazeGenerators;

public class MyMazeGenerator extends AMazeGenerator{
    /**
     * generate create a maze with our chosen algorithm and creates walls accordingly.
     * @param columns - number of columns in the maze
     * @param rows - number of rows in the maze
     * @return Maze
     */
    @Override
    public Maze generate(int columns, int rows) {
        Position[] positions = startAndGoalPositions(columns, rows);
        int[][] map = new int[rows][columns];
        Maze myMaze = new Maze(map,positions[0],positions[1]);
        //TODO need to choose an algorithm to build the maze and build it
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                //todo fill
            }
        }
        return myMaze;
    }
}
