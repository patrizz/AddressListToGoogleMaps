package com.threeandahalfroses.addressgeocoder.address;

/**
 * Address that has been processed by a geo-coding service.
 *
 * Created by Patrice Kerremans on 26/12/13.
 */
public class GeocodedAddress {
    private final String name;
    private final String description;
    private final FreeFormAddress originalAddress;
    private final String longitude;
    private final String latitude;
    private final String geocodedAddress;

    public GeocodedAddress(
            String name,
            String description,
            FreeFormAddress originalAddress,
            String longitude,
            String latitude,
            String geocodedAddress) {
        this.name = name;
        this.description = description;
        this.originalAddress = originalAddress;
        this.longitude = longitude;
        this.latitude = latitude;
        this.geocodedAddress = geocodedAddress;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public FreeFormAddress getOriginalAddress() {
        return originalAddress;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getGeocodedAddress() {
        return geocodedAddress;
    }
}
