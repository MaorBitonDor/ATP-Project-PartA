package algorithms.search;

import algorithms.mazeGenerators.Position;

public class MazeState extends AState {
    private Position StatePosition;
    public MazeState(Position position) {
        super(position.toString());
        StatePosition = position;
    }

    public int getPositionRow(){ return StatePosition.getRowIndex(); }

    public int getPositionColumn(){
        return StatePosition.getColumnIndex();
    }
}
