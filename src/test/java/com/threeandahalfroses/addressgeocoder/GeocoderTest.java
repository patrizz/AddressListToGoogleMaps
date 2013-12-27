package com.threeandahalfroses.addressgeocoder;

import com.threeandahalfroses.addressgeocoder.address.FreeFormAddress;
import com.threeandahalfroses.addressgeocoder.address.GeocodedAddress;
import com.threeandahalfroses.addressgeocoder.geocode.GeocodingAddress;
import com.threeandahalfroses.addressgeocoder.geocode.GeocodingService;
import com.threeandahalfroses.addressgeocoder.geocode.google.GeocodingException;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Tests {@see Geocoder}.
 *
 * Created by Patrice Kerremans on 27/12/13.
 */
public class GeocoderTest {
    
    @Test
    public void test_simplecase() {

        GeocodingService geocodingService = new GeocodingService() {

            private final String[][] RESULTS = new String[][] {
                    new String[] {"1", "11", "pretty format 1"},
                    new String[] {"2", "22", "pretty format 2"},
                    new String[] {"3", "33", "pretty format 3"}
            };

            @Override
            public GeocodingAddress geocode(String addressString) throws GeocodingException {
                int index = 0;
                if (addressString.startsWith("Groenplaats")) {
                    index = 0;
                }
                if (addressString.startsWith("Rome")) {
                    index = 1;
                }
                if (addressString.endsWith("Spain")) {
                    index = 2;
                }
                return new GeocodingAddress(
                        RESULTS[index][0],RESULTS[index][1],RESULTS[index][2]
                );
            }
        };
        Geocoder geocoder = new Geocoder(geocodingService);
        List<FreeFormAddress> freeFormAddresses = new ArrayList<FreeFormAddress>();
        freeFormAddresses.add(new FreeFormAddress("1", "name1", "description1", "Groenplaats 1, Antwerp, Belgium"));
        freeFormAddresses.add(new FreeFormAddress("1", "name2", "description2", "Rome, Italy"));
        freeFormAddresses.add(new FreeFormAddress("1", "name3", "description3", "Granada, Spain"));
        Iterator<GeocodedAddress> geocodedAddressIterator = geocoder.go(freeFormAddresses.iterator());
        assertNotNull("iterator null", geocodedAddressIterator);
        int count = 0;
        while (geocodedAddressIterator.hasNext()) {
            GeocodedAddress geocodedAddress = geocodedAddressIterator.next();
            assertNotNull("geocoded address is null for " + geocodedAddress.getOriginalAddress().getId(), geocodedAddress.getGeocodedAddress());
            count++;
        }
        assertEquals("wrong count", 3, count);
        
    }
    
}
