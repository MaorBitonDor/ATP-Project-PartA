package algorithms.search;

import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;

import java.util.ArrayList;

public class SearchableMaze implements ISearchable{
    private Maze maze;

    public SearchableMaze(Maze maze) {
        this.maze = maze;
    }

    @Override
    public ArrayList<AState> getAllPossibleStates(AState curState) {
        ArrayList<AState> possibleStates = new ArrayList<>();
        int[][] map = maze.getMaze();
        int rows = map.length;
        int columns = map[0].length;
        MazeState mazeState = (MazeState)curState;
        int curRow = mazeState.getPositionRow();
        int curColumn = mazeState.getPositionColumn();
        boolean topRight = false, topLeft = false, bottomRight = false, bottomLeft = false;
        //todo add explains
        if(curRow-1 >= 0 && map[curRow-1][curColumn] == 0)
        {
            possibleStates.add(new MazeState(new Position(curRow-1, curColumn)));
            if(curColumn+1<columns && map[curRow-1][curColumn+1] == 0 && !topRight){
                possibleStates.add(new MazeState(new Position(curRow-1, curColumn+1)));
                topRight = true;
            }
            if(curColumn-1 >= 0 && map[curRow-1][curColumn-1] == 0 && !topLeft){
                possibleStates.add(new MazeState(new Position(curRow-1, curColumn-1)));
                topLeft = true;
            }
        }
        if(curColumn+1 < columns && map[curRow][curColumn+1] == 0)
        {
            possibleStates.add(new MazeState(new Position(curRow, curColumn+1)));
            if(curColumn+1<columns && map[curRow-1][curColumn+1] == 0 && !topRight){
                possibleStates.add(new MazeState(new Position(curRow-1, curColumn+1)));
                topRight = true;
            }
            if(curRow+1 < rows && map[curRow+1][curColumn+1] == 0 && !bottomRight){
                possibleStates.add(new MazeState(new Position(curRow+1, curColumn+1)));
                bottomRight = true;
            }
        }
        if(curRow+1 < rows && map[curRow+1][curColumn] == 0)
        {
            possibleStates.add(new MazeState(new Position(curRow+1, curColumn)));
            if(curColumn+1<columns && map[curRow+1][curColumn+1] == 0 && !bottomRight){
                possibleStates.add(new MazeState(new Position(curRow+1, curColumn+1)));
                bottomRight = true;
            }
            if(curColumn-1 >= 0 && map[curRow+1][curColumn-1] == 0 && !bottomLeft){
                possibleStates.add(new MazeState(new Position(curRow+1, curColumn-1)));
                bottomLeft = true;
            }
        }
        if(curColumn-1 >= 0 && map[curRow][curColumn-1] == 0)
        {
            possibleStates.add(new MazeState(new Position(curRow, curColumn-1)));
            if(curRow+1 < rows && map[curRow+1][curColumn-1] == 0 && !bottomLeft){
                possibleStates.add(new MazeState(new Position(curRow+1, curColumn-1)));
                bottomLeft = true;
            }
            if(curRow-1 >= 0 && map[curRow-1][curColumn-1] == 0 && !topLeft){
                possibleStates.add(new MazeState(new Position(curRow-1, curColumn-1)));
                topLeft = true;
            }
        }
        return possibleStates;
    }

    @Override
    public AState getGoalState() {
        return new MazeState(maze.getGoalPosition());
    }

    @Override
    public AState getStartState() {
        return new MazeState(maze.getStartPosition());
    }
}
