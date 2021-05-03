package Server;

import IO.MyCompressorOutputStream;
import algorithms.mazeGenerators.IMazeGenerator;
import algorithms.mazeGenerators.Maze;

import java.io.*;

public class ServerStrategyGenerateMaze implements IServerStrategy {

    /**
     * this function is the maze generator server.
     * @param inFromClient - input stream to receive data from the client.
     * @param outToClient - output stream to send data back to the client.
     */
    @Override
    public void applyStrategy(InputStream inFromClient, OutputStream outToClient) {
        try {
            ObjectInputStream fromClient = new ObjectInputStream(inFromClient);
            ObjectOutputStream toClient = new ObjectOutputStream(outToClient);
            //OutputStream toClient = new ObjectOutputStream(outToClient);
            int[] dim = (int[]) fromClient.readObject();
            IMazeGenerator mazeGenerator = (IMazeGenerator) Class.forName("algorithms.mazeGenerators."+Configurations.getInstance().getGeneratorName()).getConstructor().newInstance();
            Maze maze = mazeGenerator.generate(dim[1],dim[0]);
            byte[] mazeByteArray = maze.toByteArray();

            MyCompressorOutputStream toClient2 = new MyCompressorOutputStream(new ByteArrayOutputStream());
            toClient2.write(mazeByteArray);
            toClient2.flush();

            toClient.writeObject(((ByteArrayOutputStream)toClient2.getOut()).toByteArray());
            toClient.flush();

            fromClient.close();
            toClient.close();
            toClient2.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
