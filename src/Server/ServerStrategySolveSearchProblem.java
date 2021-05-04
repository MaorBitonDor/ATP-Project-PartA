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
    private static ConcurrentHashMap<String,String> hashMap;
    private static volatile AtomicInteger solNum = new AtomicInteger();

    public ServerStrategySolveSearchProblem() {
        hashMap = new ConcurrentHashMap<>();
        File dir = new File(tempDirectoryPath);
        File[] solFiles = dir.listFiles((dir1,name) -> name.startsWith("MazeSol_"));
        File[] mazeFiles = dir.listFiles((dir1,name) -> name.startsWith("Maze_"));
        Arrays.sort(solFiles);
        Arrays.sort(mazeFiles);
        String curMazeStr;
        byte[] compressedMaze;
        ObjectInputStream mazeFromFile;
        for (int i = 0; i < solFiles.length; i++){
            try {
                mazeFromFile = new ObjectInputStream(new FileInputStream(mazeFiles[i].getPath()));
                compressedMaze = (byte[])mazeFromFile.readObject();
                curMazeStr=convertByteArrayToString(compressedMaze);
                hashMap.put(curMazeStr,solFiles[i].getPath());
                solNum.incrementAndGet();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

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
                String solFileName = tempDirectoryPath + "\\MazeSol_"+solNum;
                String MazeFileName = tempDirectoryPath + "\\Maze_"+solNum;
                solNum.incrementAndGet();
                ObjectOutputStream solToFile = new ObjectOutputStream(new FileOutputStream(solFileName));
                solToFile.writeObject(solution);
                solToFile.flush();
                ObjectOutputStream MazeToFile = new ObjectOutputStream(new FileOutputStream(MazeFileName));
                MazeToFile.writeObject(compressedMaze);
                MazeToFile.flush();
                hashMap.put(mazeStr,solFileName);
                toClient.writeObject(solution);
                toClient.flush();
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
