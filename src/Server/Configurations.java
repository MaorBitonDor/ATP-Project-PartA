package Server;

import java.io.*;
import java.util.Properties;

public class Configurations {
    private  static Configurations instance = null;
    private Properties prop;

    private Configurations(){
        try {
            prop = new Properties();
            File file = new File("resources/config.properties");
            file.createNewFile();
            OutputStream Output = new FileOutputStream(file);

            prop.setProperty("threadPoolSize" , "4");
            prop.setProperty("mazeGeneratingAlgorithm" , "MyMazeGenerator");
            prop.setProperty("mazeSearchingAlgorithm", "BestFirstSearch");

            prop.store(Output,"");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getGeneratorName() {
        return prop.getProperty("mazeGeneratingAlgorithm");
    }

    public void setGeneratorName(String generatorName) {
        try {
            prop.setProperty("mazeGeneratingAlgorithm" , generatorName);
            File file = new File("resources/config.properties");
            OutputStream Output = new FileOutputStream(file);
            prop.store(Output,"");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getSearchName() {
        return prop.getProperty("mazeSearchingAlgorithm");
    }

    public void setSearchName(String searchName) {
        try {
            prop.setProperty("mazeSearchingAlgorithm", searchName);
            File file = new File("resources/config.properties");
            OutputStream Output = new FileOutputStream(file);
            prop.store(Output,"");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getThreadsNum() {
        return Integer.parseInt(prop.getProperty("threadPoolSize"));
    }

    public void setThreadsNum(int threadsNum) {
        try {
            prop.setProperty("threadPoolSize", ""+threadsNum);
            File file = new File("resources/config.properties");
            OutputStream Output = new FileOutputStream(file);
            prop.store(Output,"");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Configurations getInstance(){
        if (instance==null){
            instance = new Configurations();
        }
        return instance;
    }
}
