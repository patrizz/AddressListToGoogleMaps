AddressListToGoogleMaps
=======================

Turn a list of addresses into a CSV or KML file for Google Maps.

Using the Google Geocode API we get the longitude and latitude for each address that can be properly geocoded.

Input: a Comma-Separated Value (CSV) file.
Output: a Comma-Separated Value file or a KML file that contains
* the values from the original CSV file, and
* the longitude, latitude of those addresses that could be properly geocoded
* the Geocoded address (the address as it is known to Google)

If you chose KML as the output format (default), then you can use the result immediately to view the place holders on a map.
Using a site like http://display-kml.appspot.com/, for instance, you get the following result: http://screencast.com/t/en3og60OfLHp

Enjoy!

Example
=======

Input:
------
```
"1", "Center of the universe", "The place everything revolves around; first real stock exchange building,first real newspaper, world's diamond centre, etc etc", "Groenplaats, Antwerp, Belgium"
"2", "The eternal city", "First really big city ever.  Had at the peak of the roman empire 1.6 million inhabitants (98 AD - 117 AD Under emperor Trajan) or about 1 in 200 people alive, lived in Rome.  Compare that to 1 in 388 for Shanghai today.  Today Rome is still mesmerizing", "Rome, Italy"
"3", "Fun little street", "Nice to live as a student.  Fantastic city.  Fantastic people.", "calle santo domingo, granada, spain"
```

Output:
-------
```xml
    <?xml version="1.0" encoding="UTF-8"?>
    <kml xmlns="http://www.opengis.net/kml/2.2">
        <Document>
            <Placemark>
                <name>Center of the universe</name>
                <description><![CDATA[The place everything revolves around; first real stock exchange building,first real newspaper, world's diamond centre, etc etc]]></description>
                <Point><coordinates>4.401068,51.2190994</coordinates></Point>
            </Placemark>
            <Placemark>
                <name>The eternal city</name>
                <description><![CDATA[First really big city ever.  Had at the peak of the roman empire 1.6 million inhabitants (98 AD - 117 AD Under emperor Trajan) or about 1 in 200 people alive, lived in Rome.  Compare that to 1 in 388 for Shanghai today.  Today Rome is still mesmerizing]]></description>
                <Point><coordinates>12.4825199,41.8929163</coordinates></Point>
            </Placemark>
            <Placemark>
                <name>Fun little street</name>
                <description><![CDATA[Nice to live as a student.  Fantastic city.  Fantastic people.]]></description>
                <Point><coordinates>-3.6240811,37.1388708</coordinates></Point>
            </Placemark>
        </Document>
    </kml>
```
