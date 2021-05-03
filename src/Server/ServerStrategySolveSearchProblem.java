package Server;

import IO.MyCompressorOutputStream;
import IO.MyDecompressorInputStream;
import IO.SimpleDecompressorInputStream;
import algorithms.mazeGenerators.Maze;
import algorithms.search.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class ServerStrategySolveSearchProblem implements IServerStrategy{
    private String tempDirectoryPath = System.getProperty("java.io.tmpdir");
    private static ConcurrentHashMap<String,String> hashMap = new ConcurrentHashMap<>();
    private static volatile AtomicInteger solNum = new AtomicInteger();

    /**
     * this function is the maze solve server.
     * @param inFromClient - input stream to receive data from the client.
     * @param outToClient - output stream to send data back to the client.
     */
    @Override
    public void applyStrategy(InputStream inFromClient, OutputStream outToClient) {
        try {
            ObjectInputStream fromClient = new ObjectInputStream(inFromClient);
            ObjectOutputStream toClient = new ObjectOutputStream(outToClient);
            toClient.flush();
            File folder = new File(tempDirectoryPath);
            Maze maze = (Maze)fromClient.readObject();
            MyCompressorOutputStream myCompressor = new MyCompressorOutputStream(new ByteArrayOutputStream());
            myCompressor.write(maze.toByteArray());
            byte[] compressedMaze = ((ByteArrayOutputStream)myCompressor.getOut()).toByteArray();
            String mazeStr = convertByteArrayToString(compressedMaze);
            //TODO check if we need to check the search algorithm before returning
            if(hashMap.containsKey(mazeStr)){
                ObjectInputStream solFromFile = new ObjectInputStream(new FileInputStream(hashMap.get(mazeStr)));
                Solution solution = (Solution) solFromFile.readObject();
                toClient.writeObject(solution);
                toClient.flush();
            }
            else {
                ISearchingAlgorithm search = (ISearchingAlgorithm) Class.forName("algorithms.search."+Configurations.getInstance().getSearchName()).getConstructor().newInstance();
                SearchableMaze Smaze = new SearchableMaze(maze);
                Solution solution = search.solve(Smaze);
                String solFileName = "MazeSol_"+solNum;
                solNum.incrementAndGet();
                ObjectOutputStream solToFile = new ObjectOutputStream(new FileOutputStream(solFileName));
                solToFile.writeObject(solution);
                hashMap.put(mazeStr,solFileName);
                toClient.writeObject(solution);
                toClient.flush();
//                ObjectInputStream solFromFile1 = new ObjectInputStream(new FileInputStream(hashMap.get(mazeStr)));
//                Solution solution1 = (Solution) solFromFile1.readObject();
//                ArrayList<AState> mazeSolutionSteps = solution1.getSolutionPath();
//                for (int i = 0; i < mazeSolutionSteps.size(); i++) {
//                    System.out.println(String.format("%s. %s", i, mazeSolutionSteps.get(i).toString()));
//                }
            }

            fromClient.close();
            toClient.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String convertByteArrayToString(byte[] comperssedMaze) {
        String res="";
        for (int i = 0; i < comperssedMaze.length; i++) {
            res+=comperssedMaze[i];
        }
        return res;
    }
}
