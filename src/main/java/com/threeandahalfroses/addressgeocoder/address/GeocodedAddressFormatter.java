package com.threeandahalfroses.addressgeocoder.address;

import java.io.Writer;
import java.util.Iterator;

/**
 * Formats an iterator of geo-coded addresses and writes the result to a {@see Writer}.
 *
 * Created by Patrice Kerremans on 26/12/13.
 */
public interface GeocodedAddressFormatter {

    void format(Writer writer, Iterator<GeocodedAddress> geocodedAddressIterator);

}
