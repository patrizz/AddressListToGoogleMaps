package com.threeandahalfroses.addressgeocoder.geocode.google;

import com.google.code.geocoder.model.GeocodeResponse;
import com.google.code.geocoder.model.GeocoderGeometry;
import com.google.code.geocoder.model.GeocoderResult;
import com.google.code.geocoder.model.LatLng;
import com.threeandahalfroses.addressgeocoder.geocode.GeocodingAddress;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Tests {@see GeocodingServiceImpl}.
 *
 * Created by Patrice Kerremans on 26/12/13.
 */
public class GeocodingServiceImplTest {

    @Test
    public void test_transform_json_simple() throws GeocodingException {
        //example returned by Google
        String geocoded = "{   \"results\" : [      {         \"address_components\" : [            {               \"long_name\" : \"17\",               \"short_name\" : \"17\",               \"types\" : [ \"street_number\" ]            },            {               \"long_name\" : \"Jacob Jordaenslaan\",               \"short_name\" : \"Jacob Jordaenslaan\",               \"types\" : [ \"route\" ]            },            {               \"long_name\" : \"Reet\",               \"short_name\" : \"Reet\",               \"types\" : [ \"sublocality\", \"political\" ]            },            {               \"long_name\" : \"Rumst\",               \"short_name\" : \"Rumst\",               \"types\" : [ \"locality\", \"political\" ]            },            {               \"long_name\" : \"Antwerp\",               \"short_name\" : \"AN\",               \"types\" : [ \"administrative_area_level_2\", \"political\" ]            },            {               \"long_name\" : \"Flanders\",               \"short_name\" : \"Flanders\",               \"types\" : [ \"administrative_area_level_1\", \"political\" ]            },            {               \"long_name\" : \"Belgium\",               \"short_name\" : \"BE\",               \"types\" : [ \"country\", \"political\" ]            },            {               \"long_name\" : \"2840\",               \"short_name\" : \"2840\",               \"types\" : [ \"postal_code\" ]            }         ],         \"formatted_address\" : \"Jacob Jordaenslaan 17, 2840 Rumst, Belgium\",         \"geometry\" : {            \"location\" : {               \"lat\" : 51.1027224,               \"lng\" : 4.4017013            },            \"location_type\" : \"ROOFTOP\",            \"viewport\" : {               \"northeast\" : {                  \"lat\" : 51.1040713802915,                  \"lng\" : 4.403050280291502               },               \"southwest\" : {                  \"lat\" : 51.1013734197085,                  \"lng\" : 4.400352319708498               }            }         },         \"types\" : [ \"street_address\" ]      }   ],   \"status\" : \"OK\" \n" +
                "}";
        GeocodingServiceImpl geocodingService = new GeocodingServiceImpl();
        GeocodingAddress geocodingAddress = geocodingService.transform(geocoded);
        assertNotNull("geocoding address null", geocodingAddress);
        assertNotNull("geocoding address longitude null", geocodingAddress.getLongitude());
        assertNotNull("geocoding address latitude null", geocodingAddress.getLatitude());
        assertNotNull("geocoding address formatted address null", geocodingAddress.getFormattedAddress());

    }

    @Test
    public void test_transform_json_exception1_lng() throws GeocodingException {
        //example returned by Google
        String geocoded = "{   \"results\" : [      {         \"address_components\" : [            {               \"long_name\" : \"17\",               \"short_name\" : \"17\",               \"types\" : [ \"street_number\" ]            },            {               \"long_name\" : \"Jacob Jordaenslaan\",               \"short_name\" : \"Jacob Jordaenslaan\",               \"types\" : [ \"route\" ]            },            {               \"long_name\" : \"Reet\",               \"short_name\" : \"Reet\",               \"types\" : [ \"sublocality\", \"political\" ]            },            {               \"long_name\" : \"Rumst\",               \"short_name\" : \"Rumst\",               \"types\" : [ \"locality\", \"political\" ]            },            {               \"long_name\" : \"Antwerp\",               \"short_name\" : \"AN\",               \"types\" : [ \"administrative_area_level_2\", \"political\" ]            },            {               \"long_name\" : \"Flanders\",               \"short_name\" : \"Flanders\",               \"types\" : [ \"administrative_area_level_1\", \"political\" ]            },            {               \"long_name\" : \"Belgium\",               \"short_name\" : \"BE\",               \"types\" : [ \"country\", \"political\" ]            },            {               \"long_name\" : \"2840\",               \"short_name\" : \"2840\",               \"types\" : [ \"postal_code\" ]            }         ],         \"formatted_address\" : \"Jacob Jordaenslaan 17, 2840 Rumst, Belgium\",         \"geometry\" : {            \"location\" : {               \"lat\" : 51.1027224,               \"loooong\" : 4.4017013            },            \"location_type\" : \"ROOFTOP\",            \"viewport\" : {               \"northeast\" : {                  \"lat\" : 51.1040713802915,                  \"lng\" : 4.403050280291502               },               \"southwest\" : {                  \"lat\" : 51.1013734197085,                  \"lng\" : 4.400352319708498               }            }         },         \"types\" : [ \"street_address\" ]      }   ],   \"status\" : \"OK\" \n" +
                "}";
        GeocodingServiceImpl geocodingService = new GeocodingServiceImpl();
        try {
            geocodingService.transform(geocoded);
            fail("expected exception due to missing 'lng'");
        } catch (GeocodingException Ge) {
            //expected behaviour, "lng" is not present
        }

    }

    @Test
    public void test_transform_json_exception1_lat() throws GeocodingException {
        //example returned by Google
        String geocoded = "{   \"results\" : [      {         \"address_components\" : [            {               \"long_name\" : \"17\",               \"short_name\" : \"17\",               \"types\" : [ \"street_number\" ]            },            {               \"long_name\" : \"Jacob Jordaenslaan\",               \"short_name\" : \"Jacob Jordaenslaan\",               \"types\" : [ \"route\" ]            },            {               \"long_name\" : \"Reet\",               \"short_name\" : \"Reet\",               \"types\" : [ \"sublocality\", \"political\" ]            },            {               \"long_name\" : \"Rumst\",               \"short_name\" : \"Rumst\",               \"types\" : [ \"locality\", \"political\" ]            },            {               \"long_name\" : \"Antwerp\",               \"short_name\" : \"AN\",               \"types\" : [ \"administrative_area_level_2\", \"political\" ]            },            {               \"long_name\" : \"Flanders\",               \"short_name\" : \"Flanders\",               \"types\" : [ \"administrative_area_level_1\", \"political\" ]            },            {               \"long_name\" : \"Belgium\",               \"short_name\" : \"BE\",               \"types\" : [ \"country\", \"political\" ]            },            {               \"long_name\" : \"2840\",               \"short_name\" : \"2840\",               \"types\" : [ \"postal_code\" ]            }         ],         \"formatted_address\" : \"Jacob Jordaenslaan 17, 2840 Rumst, Belgium\",         \"geometry\" : {            \"location\" : {               \"laaaaaat\" : 51.1027224,               \"lng\" : 4.4017013            },            \"location_type\" : \"ROOFTOP\",            \"viewport\" : {               \"northeast\" : {                  \"lat\" : 51.1040713802915,                  \"lng\" : 4.403050280291502               },               \"southwest\" : {                  \"lat\" : 51.1013734197085,                  \"lng\" : 4.400352319708498               }            }         },         \"types\" : [ \"street_address\" ]      }   ],   \"status\" : \"OK\" \n" +
                "}";
        GeocodingServiceImpl geocodingService = new GeocodingServiceImpl();
        try {
            geocodingService.transform(geocoded);
            fail("expected exception due to missing 'lat'");
        } catch (GeocodingException Ge) {
            //expected behaviour, "lat" is not present
        }

    }

    @Test
    public void test_transform_json_exception1_formattedaddress() throws GeocodingException {
        //example returned by Google
        String geocoded = "{   \"results\" : [      {         \"address_components\" : [            {               \"long_name\" : \"17\",               \"short_name\" : \"17\",               \"types\" : [ \"street_number\" ]            },            {               \"long_name\" : \"Jacob Jordaenslaan\",               \"short_name\" : \"Jacob Jordaenslaan\",               \"types\" : [ \"route\" ]            },            {               \"long_name\" : \"Reet\",               \"short_name\" : \"Reet\",               \"types\" : [ \"sublocality\", \"political\" ]            },            {               \"long_name\" : \"Rumst\",               \"short_name\" : \"Rumst\",               \"types\" : [ \"locality\", \"political\" ]            },            {               \"long_name\" : \"Antwerp\",               \"short_name\" : \"AN\",               \"types\" : [ \"administrative_area_level_2\", \"political\" ]            },            {               \"long_name\" : \"Flanders\",               \"short_name\" : \"Flanders\",               \"types\" : [ \"administrative_area_level_1\", \"political\" ]            },            {               \"long_name\" : \"Belgium\",               \"short_name\" : \"BE\",               \"types\" : [ \"country\", \"political\" ]            },            {               \"long_name\" : \"2840\",               \"short_name\" : \"2840\",               \"types\" : [ \"postal_code\" ]            }         ],         \"fffffformatted_address\" : \"Jacob Jordaenslaan 17, 2840 Rumst, Belgium\",         \"geometry\" : {            \"location\" : {               \"lat\" : 51.1027224,               \"lng\" : 4.4017013            },            \"location_type\" : \"ROOFTOP\",            \"viewport\" : {               \"northeast\" : {                  \"lat\" : 51.1040713802915,                  \"lng\" : 4.403050280291502               },               \"southwest\" : {                  \"lat\" : 51.1013734197085,                  \"lng\" : 4.400352319708498               }            }         },         \"types\" : [ \"street_address\" ]      }   ],   \"status\" : \"OK\" \n" +
                "}";
        GeocodingServiceImpl geocodingService = new GeocodingServiceImpl();
        try {
            geocodingService.transform(geocoded);
            fail("expected exception due to missing 'formatted_address'");
        } catch (GeocodingException Ge) {
            //expected behaviour, "formatted_address" is not present
        }

    }

    @Test
    public void test_transform_obj_simple() throws GeocodingException {
        GeocoderResult geocoderResult = new GeocoderResult();
        geocoderResult.setFormattedAddress("Some formatted address");
        GeocoderGeometry geocoderGeometry = new GeocoderGeometry();
        LatLng latLng = new LatLng();
        latLng.setLat(new BigDecimal(1.2345));
        latLng.setLng(new BigDecimal(2.3456));
        geocoderGeometry.setLocation(latLng);
        geocoderResult.setGeometry(geocoderGeometry);

        List<GeocoderResult> geocoderResults = new ArrayList<GeocoderResult>();
        geocoderResults.add(geocoderResult);

        GeocodeResponse geocodeResponse = new GeocodeResponse();
        geocodeResponse.setResults(geocoderResults);

        GeocodingServiceImpl geocodingService = new GeocodingServiceImpl();

        GeocodingAddress geocodingAddress = geocodingService.transform(geocodeResponse);
        assertNotNull("geocoding address null", geocodingAddress);
        assertNotNull("longitude null", geocodingAddress.getLongitude());
        assertNotNull("latitude null", geocodingAddress.getLatitude());
        assertNotNull("formatted address null", geocodingAddress.getFormattedAddress());
    }

    @Test
    public void testGeocode() throws GeocodingException {
        String address = "Groenplaats, Antwerp, Belgium";
        GeocodingServiceImpl geocoder = new GeocodingServiceImpl();
        GeocodingAddress geocodedAddress = geocoder.geocode(address);
        assertNotNull("geocoded address is null", geocodedAddress);
        assertEquals("wrong geocoded address", "Groenplaats, 2000 Antwerp, Belgium", geocodedAddress.getFormattedAddress());
        assertNotNull("latitude is null", geocodedAddress.getLatitude());
        assertNotNull("longitude is null", geocodedAddress.getLongitude());

    }

}
