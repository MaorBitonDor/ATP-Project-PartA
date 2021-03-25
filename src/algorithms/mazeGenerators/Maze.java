package algorithms.mazeGenerators;

public class Maze {
    private final int[][] maze;
    private final Position start;
    private final Position goal;

    /**
     * a constructor for maze which is protected because every creation of a maze will
     * be executed by a maze generator which is in the same package. the maze is saved as
     * a 2 dimensions array.
     * @param columns - number of columns in the maze
     * @param rows - number of rows in the maze
     * @param start - the starting position point
     * @param goal - the finishing position point
     */
    protected Maze(int columns, int rows, Position start, Position goal) {
        this.maze = new int[rows][columns];
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
    public void setMazeCell(int row,int column, int value){
        maze[row][column]=value;
    }

    /**
     * print is printing the maze, the start position printed as S the goal position printed
     * as E and the rest is printed as 0 or 1, such that 1 is a wall and 0 is a road to pass.
     */
    public void print(){
        for (int i=0;i<maze.length;i++){
            for (int j = 0; j < maze[0].length; j++) {
                if(i==start.getRowIndex() && j==start.getColumnIndex())
                    System.out.print("S ");
                else if(i==goal.getRowIndex() && j==goal.getColumnIndex())
                    System.out.print("E ");
                else
                    System.out.print(maze[i][j] + " ");

            }
            System.out.println();
        }
    }
}
