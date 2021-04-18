package algorithms.mazeGenerators;

public class Maze {
    private final int[][] maze;
    private final Position start;
    private final Position goal;

    /**
     * a constructor for maze which is protected because every creation of a maze will
     * be executed by a maze generator which is in the same package. the maze is saved as
     * a 2 dimensions array.
     * @param map - 2D int array that represent the maze map
     * @param start - the starting position point
     * @param goal - the finishing position point
     */
    public Maze(Position start, Position goal, int[][] map) throws Exception {
        if(start==null || goal== null || map==null)
            throw new Exception("Illegal parameter received");
        this.maze = map;
        this.start = start;
        this.goal = goal;
    }

    /**
     * a getter for the start position.
     * @return Position
     */
    public Position getStartPosition(){
        return start;
    }

    /**
     * a getter for the goal position.
     * @return Position
     */
    public Position getGoalPosition(){
        return goal;
    }

    /**
     * a getter for the maze map
     * @return int[][]
     */
    public int[][] getMaze() {
        return maze;
    }

    /**
     * setMazeCell change the value of the cell int the wanted row and column to the wanted
     * value.
     * @param row - the row index of the wanted cell
     * @param column - the column index of the wanted cell
     * @param value - the value we want to put in the wanted cell
     */
    public void setMazeCell(int row,int column, int value) throws Exception {
        if(row<0||row> maze.length)
            throw new Exception("row index is out of range");
        if(column<0||column> maze[0].length)
            throw new Exception("column index is out of range");
        if(value!=0 && value !=1)
            throw new Exception("Illegal value");
        maze[row][column]=value;
    }

    /**
     * print is printing the maze, the start position printed as S the goal position printed
     * as E and the rest is printed as 0 or 1, such that 1 is a wall and 0 is a road to pass.
     */
    public void print(){
        for (int i=0;i<maze.length;i++){
            System.out.print("{ ");
            for (int j = 0; j < maze[0].length; j++) {
                if(i==start.getRowIndex() && j==start.getColumnIndex())
                    System.out.print("S ");
                else if(i==goal.getRowIndex() && j==goal.getColumnIndex())
                    System.out.print("E ");
                else
                    System.out.print(maze[i][j] + " ");

            }
            System.out.println("}");
        }
    }
}
