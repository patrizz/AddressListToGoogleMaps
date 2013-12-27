package com.threeandahalfroses.addressgeocoder.geocode;

/**
 * Created by Patrice Kerremans on 26/12/13.
 */
public class GeocodingAddress {

    private final String longitude;
    private final String latitude;
    private final String formattedAddress;

    public GeocodingAddress(String longitude, String latitude, String formattedAddress) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.formattedAddress = formattedAddress;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getFormattedAddress() {
        return formattedAddress;
    }
}
