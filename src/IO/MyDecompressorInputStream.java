package IO;

import java.io.IOException;
import java.io.InputStream;

public class MyDecompressorInputStream extends InputStream {
    InputStream in;

    /**
     * this is the constructor of MyDecompressorInputStream.
     * @param in - an InputStream that we use to apply the decorator pattern.
     */
    public MyDecompressorInputStream(InputStream in) {
        this.in = in;
    }

    /**
     * @return the what the in field returns.
     * @throws IOException - if we cant read we throw an IO exception
     */
    @Override
    public int read() throws IOException {
        return 0;
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
        for (int i = 0; i < 12; i++) {
            if(byteNum!=-1){
                b[i]= (byte) byteNum;
                byteNum = in.read();
                counter++;
            }
        }
        int curInd=12;
        String byteStr;
        while(byteNum!=-1){
            byteStr = String.format("%8s", Integer.toBinaryString(byteNum & 0xFF)).replace(' ', '0');
            for (char numChar:byteStr.toCharArray()) {
                if(curInd<b.length)
                    b[curInd++]= (byte) Integer.parseInt(String.valueOf(numChar));
                else
                    return counter;
            }
            byteNum = in.read();
            counter++;
        }
        return counter;
    }
}
