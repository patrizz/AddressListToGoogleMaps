package com.threeandahalfroses.addressgeocoder;

import com.threeandahalfroses.addressgeocoder.address.FreeFormAddress;
import com.threeandahalfroses.addressgeocoder.address.GeocodedAddress;
import com.threeandahalfroses.addressgeocoder.geocode.GeocodingAddress;
import com.threeandahalfroses.addressgeocoder.geocode.GeocodingService;
import com.threeandahalfroses.addressgeocoder.geocode.google.GeocodingException;
import org.apache.log4j.Logger;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Patrice Kerremans on 26/12/13.
 */
class Geocoder {

    private static final Logger LOGGER = Logger.getLogger(Geocoder.class);

    private final GeocodingService geocodingService;

    public Geocoder(
            GeocodingService geocodingService) {
        this.geocodingService = geocodingService;
    }

    Iterator<GeocodedAddress> go(Iterator<FreeFormAddress> addressIterator) {
        List<GeocodedAddress> geocodedAddresses = new ArrayList<GeocodedAddress>();
        while (addressIterator.hasNext()) {
            FreeFormAddress address = addressIterator.next();
            String name = address.getName();
            String description = address.getDescription();
            try {
                if (address.getAddressAsAString() == null) {
                    LOGGER.warn(MessageFormat.format("Error processing address with id ''{0}''", address.getId()));
                }
                GeocodingAddress geocodingAddress = geocodingService.geocode(address.getAddressAsAString());
                GeocodedAddress geocodedAddress = new GeocodedAddress(
                        name,
                        description,
                        address,
                        geocodingAddress.getLongitude(),
                        geocodingAddress.getLatitude(),
                        geocodingAddress.getFormattedAddress()
                );
                geocodedAddresses.add(geocodedAddress);
            } catch (GeocodingException Ge) {
                LOGGER.error(MessageFormat.format("Error processing ''{0}''", address.getAddressAsAString()), Ge);
            }
        }
        return geocodedAddresses.iterator();
    }


}
