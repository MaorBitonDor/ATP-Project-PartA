package algorithms.mazeGenerators;

import java.util.Random;

public class SimpleMazeGenerator extends AMazeGenerator{
    /**
     * generate create a simple maze with random walls with at least one solution and returns it.
     * @param columns - number of columns in the maze
     * @param rows - number of rows in the maze
     * @return Maze
     */
    @Override
    public Maze generate(int columns, int rows) {
        Position[] positions = startAndGoalPositions(columns, rows);
        int[][] map = new int[rows][columns];
        Maze simpleMaze = new Maze(map,positions[0],positions[1]);
        Random random = new Random();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if((i != simpleMaze.getStartPosition().getColumnIndex() || j > simpleMaze.getGoalPosition().getRowIndex()) &&
                        (i != simpleMaze.getGoalPosition().getRowIndex() || j < simpleMaze.getStartPosition().getColumnIndex())) {
                    simpleMaze.setMazeCell(i,j,random.nextInt(2));
                }
            }
        }
        return simpleMaze;
    }
}
