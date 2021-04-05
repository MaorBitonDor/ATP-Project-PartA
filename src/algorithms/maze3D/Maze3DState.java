package algorithms.maze3D;

import algorithms.search.AState;

public class Maze3DState extends AState {
    private Position3D statePosition3D;

    /**
     * this is the constructor of Maze3DState that receive a position3D and create a Maz3DState
     * @param position3D - the current position we are in now.
     */
    public Maze3DState(Position3D position3D) {
        super(position3D.toString());
        statePosition3D = position3D;
    }

    /**
     * @return an int that represent the row that we are in the maze
     */
    public int getPositionRow(){ return statePosition3D.getRowIndex(); }

    /**
     * @return an int that represent the column that we are in the maze
     */
    public int getPositionColumn(){
        return statePosition3D.getColumnIndex();
    }

    /**
     * @return an int that represent the depth that we are in the maze
     */
    public int getPositionDepth(){
        return statePosition3D.getDepthIndex();
    }

    @Override
    public String toString() {
        return statePosition3D.toString();
    }
}
