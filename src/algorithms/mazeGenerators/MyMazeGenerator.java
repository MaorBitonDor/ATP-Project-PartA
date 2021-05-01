package algorithms.mazeGenerators;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class MyMazeGenerator extends AMazeGenerator{
    /**
     * generate create a maze with randomized DFS algorithm and creates walls accordingly.
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
        Position[] positions = new Position[2];
        if(rows==2){
            positions[0] = new Position(0,0);
            positions[1] = new Position(0,columns-1);
        }
        else
            positions = startAndGoalPositions(columns, rows);
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
        int randRow, randColumn;
        for (int i = 0; i < ((int)Math.sqrt(rows*columns)); i++) {
            randRow = random.nextInt(rows);
            randColumn = random.nextInt(columns);
            if(map[randRow][randColumn] == 1) {
                map[randRow][randColumn] = random.nextInt(2);
            }
        }
        int goalRow = positions[1].getRowIndex(), goalColumn = positions[1].getColumnIndex();
        map[positions[0].getRowIndex()][positions[0].getColumnIndex()] = 0;
        map[goalRow][goalColumn] = 0;
        if(map[goalRow+1][goalColumn]==1 && map[goalRow][goalColumn-1]==1 && map[goalRow-1][goalColumn]==1){
            map[goalRow+1][goalColumn] = 0;
            map[goalRow][goalColumn-1] = 0;
        }
        return new Maze(positions[0],positions[1],map);
    }

    /**
     * this function returns a list of arrays each array in size of 2 that state the position of the all
     * the neighbors of the current cell that the algorithm did not visit in. We define that a neighbor is a
     * cell that is 2 cells away (up, down, left or right) from the current cell.
     * @param map - the map that represent the maze.
     * @param rows - the number of rows in the map.
     * @param columns - the number of columns in the map.
     * @param curCell - the current cell that we want to get its neighbors.
     * @return ArrayList<int[]> called neighbors that contains all the neighbors of the current cell.
     */
    private ArrayList<int[]> getNeighbors(int[][] map, int rows, int columns, int[] curCell) throws Exception {
        if (map == null || curCell == null)
            throw new Exception("Illegal map or curCell params");
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
