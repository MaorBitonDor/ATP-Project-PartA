package algorithms.mazeGenerators;

public class Position {
    private final int column;
    private final int row;

    /**
     * a constructor for the position
     * @param column - the column index of the position
     * @param row - the row index of the position
     */
    public Position(int column, int row) {
        this.column = column;
        this.row = row;
    }

    /**
     * a getter for the column of the position
     * @return int
     */
    public int getColumnIndex() {
        return column;
    }

    /**
     * a getter for the row of the position
     * @return int
     */
    public int getRowIndex() {
        return row;
    }

    /**
     * return a string of the position in the 2D maze
     * @return String
     */
    @Override
    public String toString() {
        return "{" + row + "," + column + "}";
    }
}
