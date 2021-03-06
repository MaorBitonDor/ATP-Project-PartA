package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private int port;
    private int listeningIntervalMS;
    private IServerStrategy strategy;
    private volatile boolean stop;
    private ExecutorService threadPool; // Thread pool

    /**
     * this is the constructor of the server.
     * @param port - the port that the listen to.
     * @param listeningIntervalMS - the time in mili seconds that the server will listen to a port before throwing an exception
     * @param strategy - the strategy that the server will apply on the client that connect to it.
     */
    public Server(int port, int listeningIntervalMS, IServerStrategy strategy) {
        this.port = port;
        this.listeningIntervalMS = listeningIntervalMS;
        this.strategy = strategy;
        this.stop=false;
        int threadNum = Configurations.getInstance().getThreadsNum();
        this.threadPool = Executors.newFixedThreadPool(threadNum);
    }

    /**
     * this function start the server.
     */
    public void start(){
        Thread r= new Thread(()->run());
        r.start();
    }

    public void run(){
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            serverSocket.setSoTimeout(listeningIntervalMS);
            while (!stop) {
                try {
                    Socket clientSocket = serverSocket.accept();
                    // Insert the new task into the thread pool:
                    threadPool.submit(() -> {handleClient(clientSocket);});
                } catch (SocketTimeoutException e){
                    //e.printStackTrace();
                }
            }
            serverSocket.close();
            threadPool.shutdown(); // do not allow any new tasks into the thread pool (not doing anything to the current tasks and running threads)
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * @param clientSocket  - the client socket that we want to connect to and apply its strategy on.
     */
    private void handleClient(Socket clientSocket) {
        try {
            strategy.ServerStrategy(clientSocket.getInputStream(), clientSocket.getOutputStream());
            clientSocket.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * this function stops the server.
     */
    public void stop(){
        stop = true;
    }
}
