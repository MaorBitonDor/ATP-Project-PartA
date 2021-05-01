package IO;

import java.io.IOException;
import java.io.OutputStream;

public class MyCompressorOutputStream extends OutputStream {
    OutputStream out;

    public MyCompressorOutputStream(OutputStream out) {
        this.out = out;
    }

    @Override
    public void write(int b) throws IOException {
        out.write(b);
    }

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
}
