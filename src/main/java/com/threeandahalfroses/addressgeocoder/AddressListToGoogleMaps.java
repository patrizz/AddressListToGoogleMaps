package com.threeandahalfroses.addressgeocoder;

import com.threeandahalfroses.addressgeocoder.address.FatalException;
import com.threeandahalfroses.addressgeocoder.address.FreeFormAddressSource;
import com.threeandahalfroses.addressgeocoder.address.GeocodedAddress;
import com.threeandahalfroses.addressgeocoder.address.GeocodedAddressFormatter;
import com.threeandahalfroses.addressgeocoder.address.source.csv.FreeFormAddressSourceImpl;
import com.threeandahalfroses.addressgeocoder.geocode.google.GeocodingServiceImpl;
import org.apache.commons.cli.*;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import java.io.*;
import java.text.MessageFormat;
import java.util.Iterator;

/**
 * Main application.  This can be used from the commandline.
 *
 * run
 *
 * Created by Patrice Kerremans on 26/12/13.
 */
public class AddressListToGoogleMaps {

    private static final String OPTION_USAGE = "usage";
    private static final String OPTION_FORCE_OVERWRITE_DESTINATION = "force";
    private static final String OPTION_SOURCE_FILENAME = "in";
    private static final String OPTION_DESTINATION_FILENAME = "out";
    private static final String OPTION_GEOCODING_SERVICE = "geoservice";
    private static final String OPTION_GOOGLE_CLIENT_ID = "google_clientid";
    private static final String OPTION_GOOGLE_CLIENT_KEY = "google_clientkey";
    private static final String OPTION_OUTPUT_FORMAT = "format";

    private static enum OutputFormat {KML, CSV}

    private static final Logger LOGGER = Logger.getLogger(AddressListToGoogleMaps.class);

    /**
     * Default constructor.
     */
    private AddressListToGoogleMaps() {
    }

    public static void main(String[] args) {
        File inputFile;
        File outputFile;

        FileReader fileReader = null;
        FileWriter fileWriter = null;

        boolean overwrite;

        Options options = new Options();
        options.addOption(OPTION_FORCE_OVERWRITE_DESTINATION, false, "force overwriting the destination file.");
        options.addOption(OPTION_SOURCE_FILENAME, true, "the source file.");
        options.addOption(OPTION_DESTINATION_FILENAME, true, "the destination file.");
        options.addOption(OPTION_GEOCODING_SERVICE, true, "the geocoding service to use. Only valid and default value: GOOGLE");
        options.addOption(OPTION_GOOGLE_CLIENT_ID, true, "the google client id to use for the google geocoding service (default: null).");
        options.addOption(OPTION_GOOGLE_CLIENT_KEY, true, "the google client key to use for the google geocoding service (default: null).");
        CommandLineParser parser = new BasicParser();
        CommandLine cmd;

        try {

            try {
                cmd = parser.parse(options, args);

                if (cmd.hasOption(OPTION_USAGE)) {
                    showUsage(options);
                    System.exit(0);
                }
                if (!cmd.hasOption(OPTION_SOURCE_FILENAME)) {
                    throw new FatalException("missing source filename.  Please specify it with the -in option");
                }
                if (!cmd.hasOption(OPTION_DESTINATION_FILENAME)) {
                    throw new FatalException("missing destination filename.  Please specify it with the -out option");
                }
                if (cmd.getOptionValue(OPTION_SOURCE_FILENAME) == null) {
                    throw new FatalException("missing source filename.  Please specify it with the -in option");
                }
                if (cmd.getOptionValue(OPTION_DESTINATION_FILENAME) == null) {
                    throw new FatalException("missing destination filename.  Please specify it with the -out option");
                }
                if (cmd.hasOption(OPTION_GEOCODING_SERVICE) && !"GOOGLE".equals(cmd.getOptionValue(OPTION_GEOCODING_SERVICE))) {
                    throw new FatalException("only currently valid option is GOOGLE.  Either specify that option or remove the option");
                }
            } catch (ParseException e) {
                throw new FatalException(MessageFormat.format("Parsing command failed: {0}", e.getMessage()));
            }
            overwrite = options.hasOption(OPTION_FORCE_OVERWRITE_DESTINATION);
            String googleClientId = null;
            String googleClientKey = null;
            if (cmd.hasOption(OPTION_GOOGLE_CLIENT_ID)) {
                googleClientId = cmd.getOptionValue(OPTION_GOOGLE_CLIENT_ID);
            }
            if (cmd.hasOption(OPTION_GOOGLE_CLIENT_KEY)) {
                googleClientKey = cmd.getOptionValue(OPTION_GOOGLE_CLIENT_KEY);
            }
            OutputFormat format = OutputFormat.KML;
            if (cmd.hasOption(OPTION_OUTPUT_FORMAT)) {
                String formatOptionValue = cmd.getOptionValue(OPTION_OUTPUT_FORMAT);
                if (formatOptionValue.equalsIgnoreCase("KML")) {
                    format = OutputFormat.KML;
                } else if (formatOptionValue.equalsIgnoreCase("CSV")) {
                    format = OutputFormat.CSV;
                } else {
                    throw new FatalException(MessageFormat.format("Unknown format ''{0}''", formatOptionValue));
                }
            }

            inputFile = new File(cmd.getOptionValue(OPTION_SOURCE_FILENAME));
            outputFile = new File(cmd.getOptionValue(OPTION_DESTINATION_FILENAME));
            if (!inputFile.exists()) {
                throw new FatalException(MessageFormat.format("Input file ''{0}'' missing", inputFile.getAbsolutePath()));
            }
            if (outputFile.exists() && !overwrite) {
                throw new FatalException(MessageFormat.format("Output file ''{0}'' already exists.  Specify the option -force to force overwriting.", inputFile.getAbsolutePath()));
            }

            LOGGER.info("OK. everything checks out.");
            LOGGER.info("So here's the deal:");
            LOGGER.info("We're going to read: " + inputFile.getAbsolutePath());
            LOGGER.info("We're going to use the geocoding service of " + "GOOGLE");
            LOGGER.info("And we'll write the result (in '" + format + "' format) here: " + outputFile);

            GeocodingServiceImpl geocodingService;

            if (googleClientId != null) {
                geocodingService = new GeocodingServiceImpl(googleClientId, googleClientKey);
            } else {
                geocodingService = new GeocodingServiceImpl();
            }
            try {
                fileReader = new FileReader(inputFile);
            } catch (FileNotFoundException FNFe) {
                throw new FatalException("input file not found", FNFe);
            }
            FreeFormAddressSource addressService = new FreeFormAddressSourceImpl(fileReader);

            Geocoder geocoder = new Geocoder(
                    geocodingService
            );
            Iterator<GeocodedAddress> geocodedAddressIterator = geocoder.go(addressService.getAddresses());
            GeocodedAddressFormatter geocodedAddressService = new com.threeandahalfroses.addressgeocoder.address.format.kml.GeocodedAddressFormatterImpl();

            if (format == OutputFormat.CSV) {
                geocodedAddressService = new com.threeandahalfroses.addressgeocoder.address.format.csv.GeocodedAddressFormatterImpl(false, false);
            }

            try {
                fileWriter = new FileWriter(outputFile);
            } catch (IOException IOe) {
                throw new FatalException("Error writing output file", IOe);
            }
            geocodedAddressService.format(fileWriter, geocodedAddressIterator);

        } catch (FatalException fe) {
            LOGGER.error(MessageFormat.format("A fatal error occurred: {0}.", fe.getMessage()));
            showUsage(options);
        } finally {
            if (fileReader != null) {
                IOUtils.closeQuietly(fileReader);
            }
            if (fileWriter != null) {
                IOUtils.closeQuietly(fileWriter);
            }
        }
    }

    private static void showUsage(Options options) {
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("AddressListToGoogleMaps", options);
    }


}
