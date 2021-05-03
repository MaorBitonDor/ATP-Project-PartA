package IO;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

public class SimpleCompressorOutputStream extends OutputStream {

    private OutputStream out;

    /**
     * this is the constructor of SimpleCompressorOutputStream.
     * @param out - an OutputStream that we need for the decorator pattern
     */
    public SimpleCompressorOutputStream(OutputStream out) {
        this.out = out;
    }

    /**
     * @param b - an int that we want to write, we do it using the field out.
     * @throws IOException - if we cant write the data then we throw an IO exception.
     */
    @Override
    public void write(int b) throws IOException {
        out.write(b);
    }

    /**
     * this function takes the ones and zeros of the byte array and creates byte array that contains the number of zeros
     * in a row and then ones in a row and so on until we finish all the bytes in the byte array.
     * @param b - byte array that we receive and need to write it using the other write method after compressing the data.
     * @throws IOException - if we cant write the data then we throw an IO exception.
     */
    @Override
    public void write(byte[] b) throws IOException {
        int counter=0;
        int curNum=0;
        for (int i = 0; i < 12; i++) {
            out.write(b[i]);
        }
        for(int i=12; i<b.length; i++){
            if (b[i]==curNum){
                counter++;
                if(counter==256) {
                    out.write(counter-1);
                    out.write(0);
                    counter = 1;
                }
            }
            else{
                out.write(counter);
                counter=1;
                curNum = 1 - curNum;
            }
            if(i==b.length-1){
                out.write(counter);
            }
        }

    }
}
