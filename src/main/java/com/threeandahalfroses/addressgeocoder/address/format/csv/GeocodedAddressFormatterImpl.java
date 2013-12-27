package com.threeandahalfroses.addressgeocoder.address.format.csv;

import com.threeandahalfroses.addressgeocoder.address.GeocodedAddress;
import com.threeandahalfroses.addressgeocoder.address.format.BaseFormatterImpl;

/**
 * Created by Patrice Kerremans on 26/12/13.
 */
public class GeocodedAddressFormatterImpl extends BaseFormatterImpl {


    public GeocodedAddressFormatterImpl(Boolean shouldWriteHeader, Boolean shouldWriteFooter) {
        super(shouldWriteHeader, shouldWriteFooter);

    }

    @Override
    protected String buildHeader() {
        return "ID, ADDRESS_STRING, NAME, DESCRIPTION, LONGITUDE, LATITUDE, GEOCODED_ADDRESS";
    }

    String escapeAndQuoteString(String string) {
        return "\"" + string.replaceAll("\"", "\\\"") + "\"";
    }

    public String buildString(GeocodedAddress geocodedAddress) {
        String line = "";

        line += escapeAndQuoteString(geocodedAddress.getOriginalAddress().getId()) + ",";
        line += escapeAndQuoteString(geocodedAddress.getOriginalAddress().getAddressAsAString()) + ",";
        line += escapeAndQuoteString(geocodedAddress.getName()) + ",";
        line += escapeAndQuoteString(geocodedAddress.getDescription()) + ",";
        line += escapeAndQuoteString(geocodedAddress.getLongitude()) + ",";
        line += escapeAndQuoteString(geocodedAddress.getLatitude()) + ",";
        line += escapeAndQuoteString(geocodedAddress.getGeocodedAddress());

        return line;
    }

    @Override
    protected String buildFooter() {
        //Nothing to do
        return "";
    }
}
