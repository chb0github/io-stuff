package org.bongiorno.io;

import org.junit.Ignore;
import org.junit.Test;

import java.io.File;
import java.io.FileFilter;
import java.nio.charset.Charset;

import static org.junit.Assert.assertTrue;

/**
 * @author chribong
 */
public class ScanningFileFilterTest {


    @Test
    public void testAcceptFile() throws Exception {
        FileFilter filter = new ScanningFileFilter("broadcasting", Charset.forName("UTF-16BE"));
        File pathname = new File(this.getClass().getResource("/testscan.dat").getFile());

        boolean accept = filter.accept(pathname);
        assertTrue(accept);
    }

    @Test
    public void testRejectFile() throws Exception {
        FileFilter filter = new ScanningFileFilter("foo");
        assertTrue(filter.accept(new File(this.getClass().getResource("/testscan.dat").getFile())));
    }
}
