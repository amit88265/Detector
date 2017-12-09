package com.example.amit.detector;


import com.google.android.gms.maps.model.LatLng;

import java.util.HashMap;

/**
 * Created by AMIT on 07-Dec-17.
 */

public final class Constants {

    private Constants(){}

    public static final long GEOFENCE_EXPIRATION_IN_HOURS = 12;


    public static final long GEOFENCE_EXPIRATION_IN_MILLISECONDS =
            GEOFENCE_EXPIRATION_IN_HOURS * 60 * 60 * 1000;

    public static final float GEOFENCE_RADIUS_IN_METERS = 100;


    public static final HashMap<String, LatLng> AREA_LANDMARKS = new HashMap<>();
    static {
        // My Home Location
        AREA_LANDMARKS.put("HOME", new LatLng(28.6253974, 77.2934852));

    }

}
