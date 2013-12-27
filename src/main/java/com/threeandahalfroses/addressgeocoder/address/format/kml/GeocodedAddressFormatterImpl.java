package com.threeandahalfroses.addressgeocoder.address.format.kml;

import com.threeandahalfroses.addressgeocoder.address.GeocodedAddress;
import com.threeandahalfroses.addressgeocoder.address.format.BaseFormatterImpl;

import java.text.MessageFormat;

/**
 * Created by Patrice Kerremans on 26/12/13.
 */
public class GeocodedAddressFormatterImpl extends BaseFormatterImpl {

    public GeocodedAddressFormatterImpl() {
        this(true, true);
    }

    public GeocodedAddressFormatterImpl(Boolean shouldWriteHeader, Boolean shouldWriteFooter) {
        super(shouldWriteHeader, shouldWriteFooter);

    }

    @Override
    protected String buildHeader() {
        return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<kml xmlns=\"http://www.opengis.net/kml/2.2\"><Document>";
    }

    @Override
    protected String buildString(GeocodedAddress geocodedAddress) {
        String baseString = "<Placemark>\n" +
                "    <name>{0}</name>\n" +
                "    <description><![CDATA[{1}]]></description>\n" +
                "    <Point>\n" +
                "      <coordinates>{2},{3}</coordinates>\n" +
                "    </Point>\n" +
                "  </Placemark>";
        return MessageFormat.format(
                baseString,
                geocodedAddress.getName(),
                geocodedAddress.getDescription(),
                geocodedAddress.getLongitude(),
                geocodedAddress.getLatitude()
        );
    }

    @Override
    protected String buildFooter() {
        return "</Document></kml>";
    }
}
