package algorithms.search;

import algorithms.mazeGenerators.Position;

public class MazeState extends AState {
    private Position StatePosition;

    /**
     * this is the constructor of the MazeState that gets a position and call the super class
     * with the toString of the class Position
     * @param position - the position in the maze that we currently in.
     */
    public MazeState(Position position) {
        super(position.toString());
        StatePosition = position;
    }

    /**
     * @return an int that represent the row that we are in the maze
     */
    public int getPositionRow(){ return StatePosition.getRowIndex(); }

    /**
     * @return an int that represent the column that we are in the maze
     */
    public int getPositionColumn(){
        return StatePosition.getColumnIndex();
    }

    /**
     * @return a String representing the MazeState
     */
    @Override
    public String toString() {
        return "MazeState{" +
                "StatePosition=" + StatePosition +
                '}';
    }
}
