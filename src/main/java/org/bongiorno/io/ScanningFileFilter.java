package org.bongiorno.io;


import java.io.*;
import java.nio.charset.Charset;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * @author chribong
 */
public class ScanningFileFilter implements FileFilter {


    private Predicate<Integer> predicate;

    public ScanningFileFilter(Predicate<Integer> predicate) {
        this.predicate = predicate;
    }

    public ScanningFileFilter(String lookingFor) {
        this.predicate = new ScanningPredicate(lookingFor);
    }

    public ScanningFileFilter(String lookingFor, Charset charset) {
        this.predicate = new ScanningPredicate(lookingFor, charset);
    }

    public ScanningFileFilter(byte[] lookingFor) {
        this.predicate = new ScanningPredicate(lookingFor);
    }

    @Override
    public boolean accept(File pathname) {
        final boolean[] accept = {true};
        try {
            ScanningInputStream input = new ScanningInputStream(predicate, new FoundCosumer(accept), new FileInputStream(pathname));

            read(input);
            input.close();

        } catch (IOException e) {
            accept[0] = false;
        }
        return accept[0];
    }

    public static void read(InputStream input) throws IOException {

        byte[] buffer = new byte[2048];
        for (int n = 0; n != -1; n = input.read(buffer)) {
            // do nothing. this is just to drive scanning the whole file
        }
    }

    private static class FoundCosumer implements Consumer<InputStream> {
        private final boolean[] accept;

        public FoundCosumer(boolean[] accept) {
            this.accept = accept;
        }

        @Override
        public void accept(InputStream input) {
            accept[0] = true;
        }
    }

}