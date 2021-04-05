package algorithms.search;

import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;
import java.util.ArrayList;

public class SearchableMaze implements ISearchable{
    private final Maze maze;

    /**
     * this is the constructor of searchableMaze which set the maze to be the given maze
     * @param maze - this is the maze that we want to search on.
     */
    public SearchableMaze(Maze maze) {
        this.maze = maze;
    }

    /**
     * this function get an AState and returns a list of all the possible states that we can go to
     * from the current state in the maze.
     * @param curState - is the state that we are currently in the maze.
     * @return a list of all the neighbors that we can go to from the current state.
     */
    @Override
    public ArrayList<AState> getAllSuccessors(AState curState) {
        ArrayList<AState> possibleStates = new ArrayList<>();
        int[][] map = maze.getMaze();
        int rows = map.length;
        int columns = map[0].length;
        MazeState mazeState = (MazeState)curState;
        int curRow = mazeState.getPositionRow();
        int curColumn = mazeState.getPositionColumn();
        boolean topRight = false, topLeft = false, bottomRight = false, bottomLeft = false;
        //checking the neighbor up from the current cell
        if(curRow-1 >= 0 && map[curRow-1][curColumn] == 0)
        {
            possibleStates.add(createMazeStateFromPosition(curRow-1, curColumn,10, curState));
            //if the up neighbor can be passed through then we can go diagonally to the top right and left neighbors
            //this checks the top right neighbor
            if(curColumn+1<columns && map[curRow-1][curColumn+1] == 0 && !topRight){
                possibleStates.add(createMazeStateFromPosition(curRow-1, curColumn+1,15, curState));
                topRight = true;
            }
            //this checks the top left neighbor
            if(curColumn-1 >= 0 && map[curRow-1][curColumn-1] == 0 && !topLeft){
                possibleStates.add(createMazeStateFromPosition(curRow-1, curColumn-1,15, curState));
                topLeft = true;
            }
        }
        //checking the neighbor right from the current cell
        if(curColumn+1 < columns && map[curRow][curColumn+1] == 0)
        {
            possibleStates.add(createMazeStateFromPosition(curRow, curColumn+1,10, curState));
            //if the right neighbor can be passed through then we can go diagonally to the top and bottom right neighbors
            //this checks the top right neighbor
            if(curRow-1 >= 0 && map[curRow-1][curColumn+1] == 0 && !topRight){
                possibleStates.add(createMazeStateFromPosition(curRow-1, curColumn+1,15, curState));
                topRight = true;
            }
            //this checks the bottom right neighbor
            if(curRow+1 < rows && map[curRow+1][curColumn+1] == 0 && !bottomRight){
                possibleStates.add(createMazeStateFromPosition(curRow+1, curColumn+1,15, curState));
                bottomRight = true;
            }
        }
        //checking the neighbor down from the current cell
        if(curRow+1 < rows && map[curRow+1][curColumn] == 0)
        {
            possibleStates.add(createMazeStateFromPosition(curRow+1, curColumn,10, curState));
            //if the down neighbor can be passed through then we can go diagonally to the bottom right and left neighbors
            //this checks the bottom right neighbor
            if(curColumn+1<columns && map[curRow+1][curColumn+1] == 0 && !bottomRight){
                possibleStates.add(createMazeStateFromPosition(curRow+1, curColumn+1,15, curState));
                bottomRight = true;
            }
            //this checks the bottom left neighbor
            if(curColumn-1 >= 0 && map[curRow+1][curColumn-1] == 0 && !bottomLeft){
                possibleStates.add(createMazeStateFromPosition(curRow+1, curColumn-1,15, curState));
                bottomLeft = true;
            }
        }
        //checking the neighbor left from the current cell
        if(curColumn-1 >= 0 && map[curRow][curColumn-1] == 0)
        {
            possibleStates.add(createMazeStateFromPosition(curRow, curColumn-1,10, curState));
            //if the left neighbor can be passed through then we can go diagonally to the bottom and top left neighbors
            //this checks the bottom left neighbor
            if(curRow+1 < rows && map[curRow+1][curColumn-1] == 0 && !bottomLeft){
                possibleStates.add(createMazeStateFromPosition(curRow+1, curColumn-1,15, curState));
                bottomLeft = true;
            }
            //this checks the top left neighbor
            if(curRow-1 >= 0 && map[curRow-1][curColumn-1] == 0 && !topLeft){
                possibleStates.add(createMazeStateFromPosition(curRow-1, curColumn-1,15, curState));
                topLeft = true;
            }
        }
        return possibleStates;
    }

    /**
     * this is a function to prevent duplication of code that create a maze state and update it according to the
     * arguments we receive in the function
     * @param row - the row that we are in now.
     * @param column - the column that we are in now.
     * @param cost - the cost we want to add to the total cost by now
     * @param prevState - the previous state we want to put to the new state
     * @return a MazeState
     */
    private AState createMazeStateFromPosition(int row, int column, double cost, AState prevState){
        MazeState mazeState = new MazeState(new Position(row,column));
        mazeState.setPrevState(prevState);
        mazeState.setCost(cost);
        return mazeState;
    }
    /**
     * @return a MazeState that represent the goal state in the maze.
     */
    @Override
    public AState getGoalState() {
        return new MazeState(maze.getGoalPosition());
    }

    /**
     * @return a MazeState that represent the starting state in the maze.
     */
    @Override
    public AState getStartState() {
        return new MazeState(maze.getStartPosition());
    }
}
