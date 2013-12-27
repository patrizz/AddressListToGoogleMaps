package com.threeandahalfroses.addressgeocoder;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Tests {@see AddressListToGoogleMaps}.
 *
 * Created by Patrice Kerremans on 27/12/13.
 */
public class AddressListToGoogleMapsTest {

    @Test
    public void test_normalcase_file1_kmlout() throws IOException {
        URL url = AddressListToGoogleMapsTest.class.getResource("/file1.csv");
        assertNotNull("file1.csv resource not found", url);
        File inFile = new File(url.getFile());
        File outFile = File.createTempFile("test", ".kml");
        AddressListToGoogleMaps.main(new String[]{
                "-in", inFile.getAbsolutePath(),
                "-out", outFile.getAbsolutePath()
        });
        assertTrue("output not found", outFile.exists());
    }

}
