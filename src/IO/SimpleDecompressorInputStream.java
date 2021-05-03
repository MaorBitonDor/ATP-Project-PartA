package IO;

import java.io.IOException;
import java.io.InputStream;

public class SimpleDecompressorInputStream extends InputStream {
    private InputStream in;

    /**
     * this is the constructor of SimpleDecompressorInputStream.
     * @param in - an InputStream that we use to apply the decorator pattern.
     */
    public SimpleDecompressorInputStream(InputStream in) {
        this.in = in;
    }

    /**
     * @return the what the in field returns.
     * @throws IOException - if we cant read we throw an IO exception
     */
    @Override
    public int read() throws IOException {
        return in.read();
    }


    /**
     * this function gets a byte array and fill it with data that we read using the InputStream field in.
     * @param b - byte array that we read into it the data.
     * @return the number of character we read in total.
     * @throws IOException - if we cant read we throw an IO exception.
     */
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
