package Server;

import IO.MyDecompressorInputStream;
import IO.SimpleDecompressorInputStream;
import algorithms.mazeGenerators.Maze;
import algorithms.search.BestFirstSearch;
import algorithms.search.ISearchingAlgorithm;
import algorithms.search.SearchableMaze;

import java.io.*;
import java.util.Arrays;

public class ServerStrategySolveSearchProblem implements IServerStrategy{
    private String tempDirectoryPath = System.getProperty("java.io.tmpdir");

    @Override
    public void applyStrategy(InputStream inFromClient, OutputStream outToClient) {
        try {
            ObjectInputStream fromClient = new ObjectInputStream(inFromClient);
            ObjectOutputStream toClient = new ObjectOutputStream(outToClient);
            File folder = new File(tempDirectoryPath);
            Maze maze = (Maze)fromClient.readObject();
            InputStream mazeInputStream;
            byte[] fileMazeBytes,mazeByte = maze.toByteArray();
            for (final File curFile : folder.listFiles()) {
                mazeInputStream = new MyDecompressorInputStream(new FileInputStream(curFile.getName()));
                fileMazeBytes = new byte[mazeByte.length];
                mazeInputStream.read(fileMazeBytes);
                if(Arrays.equals(mazeByte,fileMazeBytes)){
                    //TODO return memorized solution
                }
            }
            //TODO add the current maze to the folder
            //TODO change the searchAlgo by the ConfigFile
            ISearchingAlgorithm search = new BestFirstSearch();
            SearchableMaze Smaze = new SearchableMaze(maze);
            toClient.writeObject(search.solve(Smaze));
            toClient.flush();

            fromClient.close();
            toClient.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
