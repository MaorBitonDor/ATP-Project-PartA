package IO;

import java.io.IOException;
import java.io.OutputStream;

public class MyCompressorOutputStream extends OutputStream {
    private OutputStream out;

    /**
     * this is the constructor of MyCompressorOutputStream.
     * @param out - an OutputStream that we need for the decorator pattern
     */
    public MyCompressorOutputStream(OutputStream out) {
        this.out = out;
    }

    /**
     * @param b - an int that we want to write, we do it using the field out.
     * @throws IOException - if we cant write the data then we throw an IO exception.
     */
    @Override
    public void write(int b) throws IOException {
        //out.write(b);
    }

    /**
     * this function takes the ones and zeros of the byte array and create one byte out of 8 cell in the byte array that
     * can be ones or zeros.
     * @param b - byte array that we receive and need to write it using the other write method after compressing the data.
     * @throws IOException - if we cant write the data then we throw an IO exception.
     */
    @Override
    public void write(byte[] b) throws IOException {
        for (int i = 0; i < 12; i++) {
            out.write(b[i]);
        }
        String res;
        for(int i=12; i<b.length; i+=8){
            res="";
            for (int j = 0; j < 8; j++) {
                if(i+j<b.length)
                    if((int)b[i+j]==0)
                        res+="0";
                    else
                        res+="1";
                else
                    break;
            }
            if(res.length()<8){
                while(res.length()<8){
                    res+="0";
                }
            }
            out.write(Integer.parseInt(res, 2));
        }
    }

    public OutputStream getOut() {
        return out;
    }
}
