package org.bongiorno.io;


import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * @author chribong
 */
public class ScanningInputStream extends FilterInputStream {

    private final Predicate<Integer> condition;
    private final Consumer<InputStream> callback;

    public ScanningInputStream(Predicate<Integer> condition, Consumer<InputStream> callback, InputStream in) {
        super(in);
        this.callback = callback;
        this.condition = condition;
    }

    public ScanningInputStream(byte[] lookingFor, Consumer<InputStream> callback, InputStream in) {
        super(in);
        this.callback = callback;
        condition = new ScanningPredicate(lookingFor);
    }


    public ScanningInputStream(String lookingFor, Charset charset, Consumer<InputStream> callback, InputStream in) {
        super(in);
        this.callback = callback;
        condition = new ScanningPredicate(lookingFor.getBytes(charset));
    }

    public int read() throws IOException {
        int read = super.read();

        if(condition.test(read))
            callback.accept(super.in);


        return read;
    }

    @Override
    public int read(byte[] b) throws IOException {
        return this.read(b, 0, b.length);
    }

    @Override
    public int read(byte b[], int off, int len) throws IOException {
        int result = super.read(b, off, len);


        for(int i = off; i < result; i++) {
            if(condition.test((int) b[i]))
                callback.accept(super.in);
        }
        return result;
    }
}
