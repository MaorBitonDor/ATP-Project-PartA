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
    public long measureAlgorithmTimeMillis(int depth, int columns,int rows) throws Exception {
        if (columns<2)
            throw new Exception("Illegal number of columns");
        if (rows<2)
            throw new Exception("Illegal number of rows");
        if (depth<2)
            throw new Exception("Illegal number of depth");
        long beforeTime = System.currentTimeMillis();
        generate(depth,rows,columns);
        long afterTime = System.currentTimeMillis();
        return afterTime-beforeTime;
    }


}
