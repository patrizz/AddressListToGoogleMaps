package com.threeandahalfroses.addressgeocoder.address;

/**
 * Thrown when the application cannot possibly continue sensibly.
 * Example: when a mandatory option is missing.
 *
 * Created by Patrice Kerremans on 26/12/13.
 */
public class FatalException extends Exception {

    public FatalException(String message) {
        super(message);
    }

    public FatalException(String message, Throwable throwable) {
        super(message, throwable);
    }

}
