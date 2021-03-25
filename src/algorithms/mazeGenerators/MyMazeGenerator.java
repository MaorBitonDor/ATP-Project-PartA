package algorithms.mazeGenerators;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

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
        Stack<int[]> stack = new Stack<>();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                map[i][j] = 1;
            }
        }
        //position of the starting cell in tha maze
        int[] curCell = {positions[0].getRowIndex(),positions[0].getColumnIndex()};
        stack.push(curCell);
        ArrayList<int[]> neighbors = new ArrayList<>();
        int[] tempPosition = new int[2];
        Random random = new Random();
        int posNum;
        //Todo check if works
        while(!stack.empty())
        {
            if(columns > curCell[1]+2 && map[curCell[0]][curCell[1]+2] == 1)
            {
                tempPosition[0] = curCell[0];
                tempPosition[1] = curCell[1]+2;
                neighbors.add(tempPosition);
            }
            if(curCell[1]-2>=0 && map[curCell[0]][curCell[1]-2] == 1)
            {
                tempPosition[0] = curCell[0];
                tempPosition[1] = curCell[1]-2;
                neighbors.add(tempPosition);
            }
            if(rows > curCell[0]+2 && map[curCell[0]+2][curCell[1]] == 1)
            {
                tempPosition[0] = curCell[0]+2;
                tempPosition[1] = curCell[1];
                neighbors.add(tempPosition);
            }
            if(curCell[0]-2>=0 && map[curCell[0]-2][curCell[1]] == 1)
            {
                tempPosition[0] = curCell[0]-2;
                tempPosition[1] = curCell[1];
                neighbors.add(tempPosition);
            }
            if(neighbors.size()!=0) {
                posNum = random.nextInt(neighbors.size());
                curCell =neighbors.get(posNum);
                stack.push(curCell);
            }
            else
                curCell = stack.pop();
        }
        Maze myMaze = new Maze(map,positions[0],positions[1]);
        return myMaze;
    }

}
