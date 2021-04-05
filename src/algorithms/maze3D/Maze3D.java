package algorithms.maze3D;

public class Maze3D {
    private final int[][][] map;
    private final Position3D start;
    private final Position3D goal;

    /**
     * this is the constructor of maze3D
     * @param start - the starting position3D in the maze3D.
     * @param goal - the goal position3D in the maze3D.
     * @param map - 3D array representing the maze3D when 1 is a wall and 0 is a way to pass
     */
    public Maze3D(Position3D start, Position3D goal, int[][][] map) {
        this.map = map;
        this.start = start;
        this.goal = goal;
    }

    /**
     * @return the 3D array of the maze - the map.
     */
    public int[][][] getMap(){
        return map;
    }

    /**
     * @return the start Position3D of the maze3D
     */
    public Position3D getStartPosition(){
        return start;
    }

    /**
     * @return the goal Position3D of the maze3D
     */
    public Position3D getGoalPosition(){
        return goal;
    }

    /**
     * print is printing the maze3D, the start position printed as S the goal position printed
     * as E and the rest is printed as 0 or 1, such that 1 is a wall and 0 is a road to pass.
     */
    public void print(){
        System.out.println("{");
        for (int i=0;i<map.length;i++){
            for (int j = 0; j < map[0].length; j++) {
                System.out.print("{ ");
                for (int k = 0; k < map[0][0].length; k++) {
                    if(i== start.getDepthIndex() && j==start.getRowIndex() && k==start.getColumnIndex())
                        System.out.print("S ");
                    else if(i== goal.getDepthIndex() && j==goal.getRowIndex() && k==goal.getColumnIndex())
                        System.out.print("E ");
                    else
                        System.out.print(map[i][j][k] + " ");
                }
                System.out.println("}");
            }
            if(i != map.length-1){
                System.out.print("---");
                for (int k = 0; k < map[0][0].length; k++)
                    System.out.print("--");
                System.out.println();
            }
        }
        System.out.println("}");
    }
}
