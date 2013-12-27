package com.threeandahalfroses.addressgeocoder.address.format.csv;

import com.threeandahalfroses.addressgeocoder.address.FreeFormAddress;
import com.threeandahalfroses.addressgeocoder.address.GeocodedAddress;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Tests {@see GeocodedAddressFormatterImpl}.
 *
 * Created by Patrice Kerremans on 26/12/13.
 */
public class GeocodedAddressFormatterImplTest {

    @Test
    public void test_buildString_Simple() {
        GeocodedAddressFormatterImpl geocodedAddressFormatter = new GeocodedAddressFormatterImpl(false, false);
        FreeFormAddress freeFormAddress = new FreeFormAddress("1", "Ze name", "Ze description", "Johnny ville, uk, europe");
        GeocodedAddress geocodedAddress = new GeocodedAddress("Ze name", "Ze description", freeFormAddress, "long", "lat", "Johnny Ville, UK, Europe");
        String geocodedAddressString = geocodedAddressFormatter.buildString(geocodedAddress);
        assertNotNull("geocoded address string is null", geocodedAddressString);
        assertEquals("geocoded address is wrong", "\"1\",\"Johnny ville, uk, europe\",\"Ze name\",\"Ze description\",\"long\",\"lat\",\"Johnny Ville, UK, Europe\"", geocodedAddressString);
    }

}
