package com.threeandahalfroses.addressgeocoder.address.source.csv;

import au.com.bytecode.opencsv.CSVReader;
import com.threeandahalfroses.addressgeocoder.address.FreeFormAddress;
import com.threeandahalfroses.addressgeocoder.address.FreeFormAddressSource;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Patrice Kerremans
 * Date: 25/12/13
 * Time: 11:27
 * To change this template use File | Settings | File Templates.
 */
public class FreeFormAddressSourceImpl implements FreeFormAddressSource {

    private static final Logger LOGGER = Logger.getLogger(FreeFormAddressSourceImpl.class);

    private final Reader reader;

    public FreeFormAddressSourceImpl(Reader reader) {
        this.reader = reader;
    }

    @Override
    public Iterator<FreeFormAddress> getAddresses() {
        CSVReader reader = null;
        List<FreeFormAddress> freeFormAddresses = new ArrayList<FreeFormAddress>();
        try {
            reader = new CSVReader(this.reader);
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                FreeFormAddress freeFormAddress = new FreeFormAddress(nextLine[0].trim(), nextLine[1].trim(), nextLine[2].trim(), nextLine[3].trim());
                freeFormAddresses.add(freeFormAddress);
            }
        } catch (IOException IOe) {
            LOGGER.error("Error parsing the CSV file", IOe);
            //TODO throw an exception here instead of returning null
            return null;
        } finally {
            IOUtils.closeQuietly(reader);
        }

        return freeFormAddresses.iterator();
    }
}
