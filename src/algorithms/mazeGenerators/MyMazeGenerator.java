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
        int[] nextCell, curCell = {positions[0].getRowIndex(),positions[0].getColumnIndex()};
        stack.push(curCell);
        ArrayList<int[]> neighbors;
        Random random = new Random();
        int posNum;
        while(!stack.empty())
        {
            curCell = stack.pop();
            map[curCell[0]][curCell[1]] = 0;
            neighbors = getNeighbors(map,rows,columns,curCell);
            if(neighbors.size()!=0) {
                stack.push(curCell);
                posNum = random.nextInt(neighbors.size());
                nextCell = neighbors.get(posNum);
                //we stay at the same row so we moved to the left or to the right
                if(nextCell[0] == curCell[0])
                {
                    //we moved to the right
                    if(nextCell[1] > curCell[1]){
                        map[curCell[0]][curCell[1]+1] = 0;
                    }
                    //we moved to the left
                    else{ // nextCell[1] < curCell[1]
                        map[curCell[0]][curCell[1]-1] = 0;
                    }
                }
                //we stayed at the same column so we moved up or down
                else if(nextCell[1] == curCell[1])
                {
                    //we moved down
                    if(nextCell[0] > curCell[0]){
                        map[curCell[0]+1][curCell[1]] = 0;
                    }
                    //we moved up
                    else { // nextCell[0] < curCell[0]
                        map[curCell[0]-1][curCell[1]] = 0;
                    }
                }
                curCell = nextCell;
                stack.push(curCell);
            }
        }
        // if we want to make the maze harder we can create more dead ends with random cells turning them to 0
//        for (int i = 0; i < rows; i++) {
//            for (int j = 0; j < columns; j++) {
//                if(map[i][j] == 1) {
//                    map[i][j] = random.nextInt(2);
//                }
//            }
//        }
        return new Maze(map,positions[0],positions[1]);
    }

    private ArrayList<int[]> getNeighbors(int[][] map, int rows, int columns, int[] curCell){
        ArrayList<int[]> neighbors = new ArrayList<>();
        int[] tempPosition = new int[2];
        if(columns > curCell[1]+2 && map[curCell[0]][curCell[1]+2] == 1)
        {
            tempPosition[0] = curCell[0];
            tempPosition[1] = curCell[1]+2;
            neighbors.add(tempPosition.clone());
        }
        if(curCell[1]-2>=0 && map[curCell[0]][curCell[1]-2] == 1)
        {
            tempPosition[0] = curCell[0];
            tempPosition[1] = curCell[1]-2;
            neighbors.add(tempPosition.clone());
        }
        if(rows > curCell[0]+2 && map[curCell[0]+2][curCell[1]] == 1)
        {
            tempPosition[0] = curCell[0]+2;
            tempPosition[1] = curCell[1];
            neighbors.add(tempPosition.clone());
        }
        if(curCell[0]-2>=0 && map[curCell[0]-2][curCell[1]] == 1)
        {
            tempPosition[0] = curCell[0]-2;
            tempPosition[1] = curCell[1];
            neighbors.add(tempPosition.clone());
        }
        return neighbors;
    }

}
