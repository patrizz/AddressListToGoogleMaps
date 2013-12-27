package com.threeandahalfroses.addressgeocoder.geocode.google;

import com.google.code.geocoder.Geocoder;
import com.google.code.geocoder.GeocoderRequestBuilder;
import com.google.code.geocoder.model.GeocodeResponse;
import com.google.code.geocoder.model.GeocoderRequest;
import com.google.code.geocoder.model.GeocoderResult;
import com.threeandahalfroses.addressgeocoder.geocode.GeocodingAddress;
import com.threeandahalfroses.addressgeocoder.geocode.GeocodingService;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.security.InvalidKeyException;

/**
 * Created by Patrice Kerremans on 26/12/13.
 */
public class GeocodingServiceImpl implements GeocodingService {

    private Geocoder googleGeocoder;

    public GeocodingServiceImpl() {
        this(null, null);
    }

    public GeocodingServiceImpl(String googlePremierClientId, String googlePremierClientKey) {
        if (googlePremierClientId != null) {
            try {
                googleGeocoder = new Geocoder(googlePremierClientId, googlePremierClientKey);
            } catch (InvalidKeyException e) {
                e.printStackTrace();
            }
        } else {
            googleGeocoder = new Geocoder();
        }
    }

    @Override
    public GeocodingAddress geocode(String addressString) throws GeocodingException {
        try {
            GeocoderRequest geocoderRequest = new GeocoderRequestBuilder().setAddress(addressString).setLanguage("en").getGeocoderRequest();
            GeocodeResponse geocoderResponse = googleGeocoder.geocode(geocoderRequest);
            return transform(geocoderResponse);
        } catch(Exception e) {
            throw new GeocodingException("Error occurred during geoding: " + addressString, e);
        }
    }

    GeocodingAddress transform(GeocodeResponse geocoderResponse) {
        GeocoderResult geocoderResult = geocoderResponse.getResults().get(0);
        String longitude = geocoderResult.getGeometry().getLocation().getLng().toString();
        String latitude = geocoderResult.getGeometry().getLocation().getLat().toString();
        String formattedAddress = geocoderResult.getFormattedAddress();
        return new GeocodingAddress(longitude, latitude, formattedAddress);
    }


    GeocodingAddress transform(String jsonString) throws GeocodingException {
        try {
            JSONTokener jsonTokener = new JSONTokener(jsonString);
            JSONObject jsonObject = new JSONObject(jsonTokener);
            String longitude = String.valueOf(jsonObject.getJSONArray("results").getJSONObject(0).getJSONObject("geometry").getJSONObject("location").getDouble("lng"));
            String latitude = String.valueOf(jsonObject.getJSONArray("results").getJSONObject(0).getJSONObject("geometry").getJSONObject("location").getDouble("lat"));
            String formattedAddress = jsonObject.getJSONArray("results").getJSONObject(0).getString("formatted_address");
            return new GeocodingAddress(longitude, latitude, formattedAddress);
        } catch (JSONException Je) {
            throw new GeocodingException("Parsing exception", Je);
        }
    }


}
