package Server;

import java.io.*;
import java.util.Properties;

public class Configurations {
    private  static Configurations instance = null;
    private Properties prop;
//    private String generatorName;
//    private String searchName;
//    private int threadsNum;

    private Configurations(){
        File file = new File("resources/config.properties");
        prop = new Properties();
        try {
            file.createNewFile();
            OutputStream Output = new FileOutputStream(file);

            prop.setProperty("threadsNum" , "4");
            prop.setProperty("generatorName" , "MyMazeGenerator");
            prop.setProperty("searchAlgorithm" , "BestFirstSearch");

            prop.store(Output,"");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getGeneratorName() {
        return prop.getProperty("generatorName");
    }

    public void setGeneratorName(String generatorName) {
        prop.setProperty("generatorName" , generatorName);
    }

    public String getSearchName() {
        return prop.getProperty("searchAlgorithm");
    }

    public void setSearchName(String searchName) {
        prop.setProperty("searchAlgorithm", searchName);
    }

    public int getThreadsNum() {
        return Integer.parseInt(prop.getProperty("threadsNum"));
    }

    public void setThreadsNum(int threadsNum) {
        prop.setProperty("threadsNum", ""+threadsNum);
    }

    public static Configurations getInstance(){
        if (instance==null){
            instance = new Configurations();
        }
        return instance;
    }
}
