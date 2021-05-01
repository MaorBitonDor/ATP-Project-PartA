package IO;

import algorithms.mazeGenerators.Maze;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class SimpleDecompressorInputStream extends InputStream {
    private InputStream in;

    public SimpleDecompressorInputStream(InputStream in) {
        this.in = in;
    }

    @Override
    public int read() throws IOException {
        return in.read();
    }


    @Override
    public int read(byte[] b) throws IOException {
        int byteNum = in.read();
        int counter = 1;
        int curNum = 1;
        for (int i = 0; i < 12; i++) {
            if(byteNum!=-1){
                b[i]= (byte) byteNum;
                byteNum = in.read();
                counter++;
            }
        }
        int curInd=12;
        while(byteNum!=-1){
            for(int i=0;i<byteNum;i++){
                b[curInd] = (byte) (1-curNum);
                curInd++;
            }
            byteNum = in.read();
            counter++;
            curNum = 1-curNum;
        }
        return counter;
    }
}
