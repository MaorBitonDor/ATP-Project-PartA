package IO;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

public class SimpleCompressorOutputStream extends OutputStream {

    private OutputStream out;

    public SimpleCompressorOutputStream(OutputStream out) {
        this.out = out;
    }

    @Override
    public void write(int b) throws IOException {
        out.write(b);
    }

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
