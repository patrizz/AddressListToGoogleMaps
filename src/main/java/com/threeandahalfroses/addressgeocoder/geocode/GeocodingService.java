package com.threeandahalfroses.addressgeocoder.geocode;

import com.threeandahalfroses.addressgeocoder.geocode.google.GeocodingException;

/**
 * Created by Patrice Kerremans on 26/12/13.
 */
public interface GeocodingService {
    GeocodingAddress geocode(String addressString) throws GeocodingException;
}
