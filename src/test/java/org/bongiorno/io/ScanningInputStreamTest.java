package org.bongiorno.io;

import java.nio.charset.Charset;
import java.util.function.Consumer;
import java.util.function.Predicate;
import org.apache.commons.io.IOUtils;
import org.junit.Ignore;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import static org.apache.commons.io.output.NullOutputStream.NULL_OUTPUT_STREAM;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author chribong
 */
public class ScanningInputStreamTest {

    @Test
    public void testScan() throws Exception {
        final boolean[] pass = {false};
        ByteArrayInputStream bais = new ByteArrayInputStream("Christian".getBytes());

        Predicate<Integer> scan = new ScanningPredicate("rist".getBytes());

        IOUtils.copy(new ScanningInputStream(scan, (c -> pass[0] = true), bais), NULL_OUTPUT_STREAM);
        assertTrue(pass[0]);
    }

    @Test
    public void testScanNotFound() throws Exception {
        final boolean[] fail = {false};
        ByteArrayInputStream bais = new ByteArrayInputStream("Christian".getBytes());

        Predicate<Integer> scan = new ScanningPredicate("foo".getBytes());

        IOUtils.copy(new ScanningInputStream(scan, (c -> fail[0] = true), bais), NULL_OUTPUT_STREAM);
        assertFalse(fail[0]);
    }

    @Test
    public void testScanFile() throws Exception {
        File testFile = new File(this.getClass().getResource("/testscan.dat").getFile());

        final boolean[] pass = {false};

        Predicate<Integer> scan = new ScanningPredicate("broadcasting".getBytes(Charset.forName("UTF-16BE")));

        FileInputStream in = new FileInputStream(testFile);
        IOUtils.copy(new ScanningInputStream(scan, (c -> pass[0] = true), in), NULL_OUTPUT_STREAM);
        in.close();
        assertTrue(pass[0]);

    }
}


