package algorithms.maze3D;

public class Position3D {
    private final int row;
    private final int column;
    private final int depth;

    /**
     * this is the constructor of Position3D.
     * @param depth - the depth we are in the maze3D
     * @param row - the row we are in the maze3D
     * @param column - the column we are in the maze3D
     */
    public Position3D(int depth, int row, int column) {
        this.row = row;
        this.column = column;
        this.depth = depth;
    }

    /**
     * @return the depth of this position.
     */
    public int getDepthIndex(){
        return depth;
    }

    /**
     * @return the row of this position.
     */
    public int getRowIndex(){
        return row;
    }

    /**
     * @return the column of this position.
     */
    public int getColumnIndex(){
        return column;
    }

    /**
     * return a string of the position3D in the maze3D
     * @return String
     */
    @Override
    public String toString() {
        return "{" + depth + "," + row + "," + column + "}";
    }
}
