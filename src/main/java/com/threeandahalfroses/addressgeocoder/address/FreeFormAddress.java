package com.threeandahalfroses.addressgeocoder.address;

/**
 * An address as given by a user and that has not yet been processed by a geo-coding service
 * to match a standardized recognizable address layout.
 *
 * Created by Patrice Kerremans on 26/12/13.
 */
public class FreeFormAddress {
    private final String id;
    private final String name;
    private final String description;
    private final String addressAsAString;

    public FreeFormAddress(String id, String name, String description, String addressAsAString) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.addressAsAString = addressAsAString;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getAddressAsAString() {
        return addressAsAString;
    }
}
