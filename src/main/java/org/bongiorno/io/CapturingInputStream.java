package org.bongiorno.io;

import java.io.*;

public class CapturingInputStream extends FilterInputStream {

    private ByteArrayOutputStream captureStream;


    public CapturingInputStream(InputStream delegate) {
        super(delegate);
        this.captureStream = new ByteArrayOutputStream();
    }

    @Override
    public int read() throws IOException {
        int readByte = super.read();
        if(readByte != -1)
            captureStream.write(readByte);
        return readByte;
    }

    @Override
    public int read(byte[] b) throws IOException {
        return read(b,0,b.length);
    }

    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        int numRead = super.read(b,off,len);
        if(numRead != -1)
            captureStream.write(b,off,numRead);
        return numRead;
    }

    public InputStream getCapturedStream() {
        return new ByteArrayInputStream(captureStream.toByteArray());
    }
}
