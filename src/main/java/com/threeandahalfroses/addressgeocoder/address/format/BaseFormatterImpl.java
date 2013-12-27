package com.threeandahalfroses.addressgeocoder.address.format;

import com.threeandahalfroses.addressgeocoder.address.GeocodedAddress;
import com.threeandahalfroses.addressgeocoder.address.GeocodedAddressFormatter;

import java.io.IOException;
import java.io.Writer;
import java.util.Iterator;

/**
 * Base formatter implementation.  Extend from this one for simple formats that have a header-content-footer
 * structure.  For more complex formatters implement {@see GeocodedAddressFormatter} directly.
 *
 * Created by Patrice Kerremans on 26/12/13.
 */
public abstract class BaseFormatterImpl implements GeocodedAddressFormatter {

    private final Boolean shouldWriteHeader;
    private final Boolean shouldWriteFooter;

    protected BaseFormatterImpl(Boolean shouldWriteHeader, Boolean shouldWriteFooter) {
        this.shouldWriteHeader = shouldWriteHeader;
        this.shouldWriteFooter = shouldWriteFooter;
    }


    @Override
    public void format(Writer writer, Iterator<GeocodedAddress> geocodedAddressIterator) {
        if (this.shouldWriteHeader) {
            try {
                writer.write(buildHeader());
            } catch (IOException IOe) {
                //TODO do something better here pall
                IOe.printStackTrace();
            }
        }
        while (geocodedAddressIterator.hasNext()) {
            GeocodedAddress geocodedAddress = geocodedAddressIterator.next();
            String lineString = buildString(geocodedAddress);
            try {
                writer.write(lineString);
            } catch (IOException IOe) {
                //TODO do something better here pall
                IOe.printStackTrace();
            }
        }
        if (this.shouldWriteFooter) {
            try {
                writer.write(buildFooter());
            } catch (IOException IOe) {
                //TODO do something better here pall
                IOe.printStackTrace();
            }

        }
    }

    protected abstract String buildHeader();

    protected abstract String buildFooter();

    protected abstract String buildString(GeocodedAddress geocodedAddress);
}
