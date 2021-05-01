package IO;

import java.io.IOException;
import java.io.InputStream;

public class MyDecompressorInputStream extends InputStream {
    InputStream in;

    public MyDecompressorInputStream(InputStream in) {
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
            }
            byteNum = in.read();
            counter++;
        }
        return counter;
    }
}
