package algorithms.maze3D;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class MyMaze3DGenerator extends AMaze3DGenerator{

    @Override
    public Maze3D generate(int depth, int row, int column) throws Exception {
        if (column<2)
            throw new Exception("Illegal number of columns");
        if (row<2)
            throw new Exception("Illegal number of rows");
        if (depth<2)
            throw new Exception("Illegal number of depth");
        Position3D start = new Position3D(0,0,0);
        Position3D goal = new Position3D(depth-1, row-1, column-1);
        int[][][] map = new int[depth][row][column];
        Stack<int[]> stack = new Stack<>();
        for (int i = 0; i < depth; i++) {
            for (int j = 0; j < row; j++) {
                for (int k = 0; k < column; k++) {
                    map[i][j][k] = 1;
                }
            }
        }
        int[] nextCell, curCell = {start.getDepthIndex(), start.getRowIndex(), start.getColumnIndex()};
        stack.push(curCell);
        ArrayList<int[]> neighbors;
        Random random = new Random();
        int posNum;
        while(!stack.empty()){
            curCell = stack.pop();
            map[curCell[0]][curCell[1]][curCell[2]] = 0;
            neighbors = getNeighbors(map,depth,row,column,curCell);
            if(neighbors.size()!=0){
                stack.push(curCell);
                posNum = random.nextInt(neighbors.size());
                nextCell = neighbors.get(posNum);
                //we stay at the same row and column so we moved to up or down
                if(nextCell[1] == curCell[1] && nextCell[2] == curCell[2])
                {
                    //we moved up
                    if(nextCell[0] > curCell[0]){
                        map[curCell[0]+1][curCell[1]][curCell[2]] = 0;
                    }
                    //we moved down
                    else{ // nextCell[0] < curCell[0]
                        map[curCell[0]-1][curCell[1]][curCell[2]] = 0;
                    }
                }
                //we stayed at the same row and depth so we moved left or right
                else if(nextCell[0] == curCell[0] && nextCell[1] == curCell[1])
                {
                    //we moved right
                    if(nextCell[2] > curCell[2]){
                        map[curCell[0]][curCell[1]][curCell[2]+1] = 0;
                    }
                    //we moved left
                    else { // nextCell[2] < curCell[2]
                        map[curCell[0]][curCell[1]][curCell[2]-1] = 0;
                    }
                }
                //we stayed at the same column and depth so we moved forward or backward
                else if(nextCell[0] == curCell[0] && nextCell[2] == curCell[2])
                {
                    //we moved forward
                    if(nextCell[1] > curCell[1]){
                        map[curCell[0]][curCell[1]+1][curCell[2]] = 0;
                    }
                    //we moved backward
                    else { // nextCell[1] < curCell[1]
                        map[curCell[0]][curCell[1]-1][curCell[2]] = 0;
                    }
                }
                curCell = nextCell;
                stack.push(curCell);
            }
        }
        // if we want to make the maze harder we can create more dead ends with random cells turning them to 0
        int randDepth, randRow, randColumn;
        for (int i = 0; i < ((int)Math.sqrt(depth*row*column)); i++) {
            randDepth = random.nextInt(depth);
            randRow = random.nextInt(row);
            randColumn = random.nextInt(column);
            if(map[randDepth][randRow][randColumn] == 1) {
                map[randDepth][randRow][randColumn] = random.nextInt(2);
            }
        }
        int goalDepth = goal.getDepthIndex(), goalRow =goal.getRowIndex(), goalColumn = goal.getColumnIndex();
        map[goalDepth][goalRow][goalColumn] = 0;
        if(map[goalDepth][goalRow][goalColumn-1] == 1 && map[goalDepth][goalRow-1][goalColumn] == 1
                && map[goalDepth-1][goalRow][goalColumn] == 1){
            map[goalDepth-1][goalRow][goalColumn] = 0;
            map[goalDepth][goalRow-1][goalColumn] = 0;
            map[goalDepth][goalRow][goalColumn-1] = 0;
        }
        return new Maze3D(start,goal,map);
    }

    private ArrayList<int[]> getNeighbors(int[][][] map, int depth, int rows, int columns,int[] curCell) throws Exception {
        if (map == null || curCell == null)
            throw new Exception("Illegal map or curCell params");
        ArrayList<int[]> neighbors = new ArrayList<>();
        int[] tempPosition = new int[3];
        //checking the right cell
        if(columns > curCell[2]+2 && map[curCell[0]][curCell[1]][curCell[2]+2] == 1)
        {
            tempPosition[0] = curCell[0];
            tempPosition[1] = curCell[1];
            tempPosition[2] = curCell[2]+2;
            neighbors.add(tempPosition.clone());
        }
        //check the left cell
        if(curCell[2]-2>=0 && map[curCell[0]][curCell[1]][curCell[2]-2] == 1)
        {
            tempPosition[0] = curCell[0];
            tempPosition[1] = curCell[1];
            tempPosition[2] = curCell[2]-2;
            neighbors.add(tempPosition.clone());
        }
        //checking the backward cell
        if(rows > curCell[1]+2 && map[curCell[0]][curCell[1]+2][curCell[2]] == 1)
        {
            tempPosition[0] = curCell[0];
            tempPosition[1] = curCell[1]+2;
            tempPosition[2] = curCell[2];
            neighbors.add(tempPosition.clone());
        }
        //checking the forward cell
        if(curCell[1]-2>=0 && map[curCell[0]][curCell[1]-2][curCell[2]] == 1)
        {
            tempPosition[0] = curCell[0];
            tempPosition[1] = curCell[1]-2;
            tempPosition[2] = curCell[2];
            neighbors.add(tempPosition.clone());
        }
        //checking the up cell
        if(depth > curCell[0]+1 && map[curCell[0]+1][curCell[1]][curCell[2]] == 1)
        {
            tempPosition[0] = curCell[0]+1;
            tempPosition[1] = curCell[1];
            tempPosition[2] = curCell[2];
            neighbors.add(tempPosition.clone());
        }
        //checking the down cell
        if(curCell[0]-1>=0 && map[curCell[0]-1][curCell[1]][curCell[2]] == 1)
        {
            tempPosition[0] = curCell[0]-1;
            tempPosition[1] = curCell[1];
            tempPosition[2] = curCell[2];
            neighbors.add(tempPosition.clone());
        }
        return neighbors;
    }
}
