package Server;

import IO.MyCompressorOutputStream;
import IO.SimpleCompressorOutputStream;
import algorithms.mazeGenerators.IMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;

import java.io.*;

public class ServerStrategyGenerateMaze implements IServerStrategy {
    @Override
    public void applyStrategy(InputStream inFromClient, OutputStream outToClient) {
        try {
            ObjectInputStream fromClient = new ObjectInputStream(inFromClient);
            //TODO change to MyCompressor
            SimpleCompressorOutputStream toClient = new SimpleCompressorOutputStream(outToClient);

            int[] dim = (int[]) fromClient.readObject();
            //TODO change to mazeGenerator by the Config File
            IMazeGenerator mazeGenerator = new MyMazeGenerator();
            Maze maze = mazeGenerator.generate(dim[1],dim[0]);

            toClient.write(maze.toByteArray());
            toClient.flush();

            fromClient.close();
            toClient.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
