package com.threeandahalfroses.addressgeocoder.address.source.csv;

import com.threeandahalfroses.addressgeocoder.address.FreeFormAddress;
import org.junit.Test;

import java.io.StringReader;
import java.util.Iterator;

import static org.junit.Assert.*;

/**
 * Tests {@see FreeFormAddressSourceImpl}.
 *
 * Created by Patrice Kerremans on 26/12/13.
 */
public class FreeFormAddressSourceImplTest {

    @Test
    public void testSimple() {
        String inputString = "1, \"groenplaats, antwerp, belgium\"";
        StringReader stringReader = new StringReader(inputString);
        FreeFormAddressSourceImpl freeFormAddressSourceImpl = new FreeFormAddressSourceImpl(stringReader);
        Iterator<FreeFormAddress> freeFormAddressIterator = freeFormAddressSourceImpl.getAddresses();
        assertNotNull("free form address iterator is null", freeFormAddressIterator);
        assertTrue("free form address iterator is empty", freeFormAddressIterator.hasNext());
        FreeFormAddress freeFormAddress = freeFormAddressIterator.next();
        assertNotNull("address string is null", freeFormAddress.getAddressAsAString());
        assertEquals("wrong id", "1", freeFormAddress.getId());
        assertEquals("wrong address string", "groenplaats, antwerp, belgium", freeFormAddress.getAddressAsAString());
    }

}
