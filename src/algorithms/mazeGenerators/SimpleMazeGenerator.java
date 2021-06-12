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
    public Maze generate(int columns, int rows) throws Exception {
        if (columns<2)
            throw new Exception("Illegal number of columns");
        if (rows<2)
            throw new Exception("Illegal number of rows");
        Position[] positions = startAndGoalPositions(columns, rows);
        int[][] map = new int[rows][columns];
        Maze simpleMaze = new Maze(positions[0],positions[1],map);
        Random random = new Random();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if((i != simpleMaze.getStartPosition().getColumnIndex() || j > simpleMaze.getGoalPosition().getRowIndex()) &&
                        (i != simpleMaze.getGoalPosition().getRowIndex() || j < simpleMaze.getStartPosition().getColumnIndex())) {
                    simpleMaze.setMazeCell(i,j,random.nextInt(2));
                }
            }
        }
        for(int col = simpleMaze.getStartPosition().getColumnIndex();col<columns;col++){
            simpleMaze.setMazeCell(0,col,0);
        }
        for(int row = 0;row<simpleMaze.getGoalPosition().getRowIndex();row++){
            simpleMaze.setMazeCell(row,columns-1,0);
        }
        return simpleMaze;
    }
}
