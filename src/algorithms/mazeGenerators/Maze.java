package algorithms.mazeGenerators;

import java.nio.ByteBuffer;

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

    public Maze(byte[] bytes) throws Exception {
        if(bytes==null)
            throw new Exception("Illegal parameter received");
        int row = byteArrayToInt(bytes[0], bytes[1]);
        int column = byteArrayToInt(bytes[2], bytes[3]);
        int startRow = byteArrayToInt(bytes[4], bytes[5]);
        int startCol = byteArrayToInt(bytes[6], bytes[7]);
        int goalRow = byteArrayToInt(bytes[8], bytes[9]);
        int goalCol = byteArrayToInt(bytes[10], bytes[11]);
        Position start = new Position(startRow,startCol);
        Position goal = new Position(goalRow,goalCol);
        int[][] map = new int[row][column];
        int curInd=12;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                map[i][j]=bytes[curInd++];
            }
        }
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

    public byte[] toByteArray(){
        byte[] metaData = new byte[12 + maze.length*maze[0].length];
        byte[][] tempByte = new byte[6][2];
        tempByte[0] = convertIntToByteArray(maze.length);
        tempByte[1] = convertIntToByteArray(maze[0].length);
        tempByte[2] = convertIntToByteArray(start.getRowIndex());
        tempByte[3] = convertIntToByteArray(start.getColumnIndex());
        tempByte[4] = convertIntToByteArray(goal.getRowIndex());
        tempByte[5] = convertIntToByteArray(goal.getColumnIndex());
        int curInd=0;
        for (int i = 0; i < 6; i++) {
            metaData[curInd++] = tempByte[i][2];
            metaData[curInd++] = tempByte[i][3];
        }
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[0].length; j++) {
                metaData[curInd++]=(byte)maze[i][j];
            }
        }
        return metaData;
    }

    private byte[] convertIntToByteArray(int val){
        return ByteBuffer.allocate(4).putInt(val).array();
    }

    /**
     * @param byte1 - the MSB
     * @param byte2 - the LSB
     * @return int that is the concatiate of them both
     */
    public static int byteArrayToInt(byte byte1,byte byte2){
        //int res = (byte2 & 0xFF)+(byte1 & 0xFF)<<8;
        String s1 = String.format("%8s", Integer.toBinaryString(byte1 & 0xFF)).replace(' ', '0');
        String s2 = String.format("%8s", Integer.toBinaryString(byte2 & 0xFF)).replace(' ', '0');
        String s3 = s1 + s2;
        int res = Integer.parseInt(s3, 2);
        return res;
    }



}
