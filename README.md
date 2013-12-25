AddressListToGoogleMaps
=======================

Turn a list of addresses into a KML file for Google Maps.

Using the Google Geocode API we get the longitude and latitude for each address that can be properly geocoded.

Input: a Comma-Separated Value (CSV) file.
Output: a Comma-Separated Value file that contains
* the values from the original CSV file, and
* the longitude, latitude of the addresses that can be properly geocoded
* the Geocoded address (the address as it is known to Google) 
