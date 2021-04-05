package algorithms.maze3D;

import algorithms.mazeGenerators.Position;

import java.util.Random;

public abstract class AMaze3DGenerator implements IMaze3DGenerator{

    /**
     * measureAlgorithmTimeMillis measures the time in milli seconds that takes
     * to generate a maze using the generate method, it doing it by subtracting
     * the finish time from the start time.
     * @param columns - number of columns in the maze
     * @param rows - number of rows in the maze
     * @return long
     */
    public long measureAlgorithmTimeMillis(int depth, int columns,int rows){
        long beforeTime = System.currentTimeMillis();
        generate(depth,rows,columns);
        long afterTime = System.currentTimeMillis();
        return afterTime-beforeTime;
    }

    /**
     * startAndGoalPositions is a function that choose the start and goal
     * positions of the maze and returns it in a Position array in size of 2
     * which the start position is in the first place in the array and the goal
     * position is in the second place in the array.
     * @param columns - number of columns in the maze
     * @param rows - number of rows in the maze
     * @return Position[]
     */
    public Position3D[] startAndGoalPositions(int depth, int columns, int rows){
        Position3D[] pos3D = new Position3D[2];
        Random rand = new Random();
        int rowGoal = rand.nextInt(rows);
        int colStart = rand.nextInt(columns);
        while(colStart == columns-1 || rowGoal == 0 || rowGoal == rows-1)
        {
            colStart = rand.nextInt(columns);
            rowGoal = rand.nextInt(rows);
        }
//        pos3D[0] = new Position(0,colStart);
//        pos3D[1] = new Position(rowGoal,columns-1);
        return pos3D;
    }
}
