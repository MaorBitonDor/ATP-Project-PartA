package algorithms.maze3D;

import algorithms.mazeGenerators.Position;
import algorithms.search.AState;
import algorithms.search.ISearchable;
import algorithms.search.MazeState;

import java.util.ArrayList;

public class SearchableMaze3D implements ISearchable {
    private final Maze3D maze3D;

    /**
     * this is the constructor of searchableMaze3D which set the maze3D to be the given maze3D
     * @param maze3D - the maze3D we want to search on
     */
    public SearchableMaze3D(Maze3D maze3D) throws Exception {
        if(maze3D == null)
            throw new Exception("Illegal parameter maze3D");
        this.maze3D = maze3D;
    }

    /**
     * this function get an AState and returns a list of all the possible states that we can go to
     * from the current state in the maze3D.
     * @param curState - is the state that we are currently in the maze.
     * @return a list of all the neighbors that we can go to from the current state.
     */
    @Override
    public ArrayList<AState> getAllSuccessors(AState curState) throws Exception {
        ArrayList<AState> possibleStates = new ArrayList<>();
        if(curState == null)
            return possibleStates;
        int[][][] map = maze3D.getMap();
        int depth = map.length;
        int rows = map[0].length;
        int columns = map[0][0].length;
        Maze3DState mazeState3D = (Maze3DState)curState;
        int curRow = mazeState3D.getPositionRow();
        int curColumn = mazeState3D.getPositionColumn();
        int curDepth = mazeState3D.getPositionDepth();
        //checking the right cell
        if(columns > curColumn+1 && map[curDepth][curRow][curColumn+1] == 0)
            possibleStates.add(createMaze3DStateFromPosition3D(curDepth,curRow,curColumn+1));
        //check the left cell
        if(curColumn-1>=0 && map[curDepth][curRow][curColumn-1] == 0)
            possibleStates.add(createMaze3DStateFromPosition3D(curDepth,curRow,curColumn-1));
        //checking the backward cell
        if(rows > curRow+1 && map[curDepth][curRow+1][curColumn] == 0)
            possibleStates.add(createMaze3DStateFromPosition3D(curDepth,curRow+1,curColumn));
        //checking the forward cell
        if(curRow-1>=0 && map[curDepth][curRow-1][curColumn] == 0)
            possibleStates.add(createMaze3DStateFromPosition3D(curDepth,curRow-1,curColumn));
        //checking the up cell
        if(depth > curDepth+1 && map[curDepth+1][curRow][curColumn] == 0)
            possibleStates.add(createMaze3DStateFromPosition3D(curDepth+1,curRow,curColumn));
        //checking the down cell
        if(curDepth-1>=0 && map[curDepth-1][curRow][curColumn] == 0)
            possibleStates.add(createMaze3DStateFromPosition3D(curDepth-1,curRow,curColumn));
        return possibleStates;
    }

    /**
     * this is a function to prevent duplication of code that create a maze3Dstate and update it according to the
     * arguments we receive in the function
     * @param depth - the depth that we are in now.
     * @param row - the row that we are in now.
     * @param column - the column that we are in now.
     * @return a Maze3DState
     */
    private AState createMaze3DStateFromPosition3D(int depth, int row, int column) throws Exception {
        Maze3DState maze3DState = new Maze3DState(new Position3D(depth,row,column));
        maze3DState.setCost(10);
        return maze3DState;
    }

    /**
     * @return the goal Maze3DState of the maze3D
     */
    @Override
    public AState getGoalState() {
        return new Maze3DState(maze3D.getGoalPosition());
    }

    /**
     * @return the start Maze3DState of the maze3D
     */
    @Override
    public AState getStartState() {
        return new Maze3DState(maze3D.getStartPosition());
    }
}
