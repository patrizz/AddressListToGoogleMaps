package com.threeandahalfroses.addressgeocoder.address;

import java.util.Iterator;

/**
 * Created with IntelliJ IDEA.
 * User: Patrice Kerremans
 * Date: 25/12/13
 * Time: 11:26
 * To change this template use File | Settings | File Templates.
 */
public interface FreeFormAddressSource {

    Iterator<FreeFormAddress> getAddresses();
}
