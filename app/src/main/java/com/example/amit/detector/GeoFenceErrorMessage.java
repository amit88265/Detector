package com.example.amit.detector;

import android.content.Context;
import android.content.res.Resources;

import com.google.android.gms.location.GeofenceStatusCodes;

/**
 * Created by AMIT on 07-Dec-17.
 */

public class GeoFenceErrorMessage {

   private GeoFenceErrorMessage(){}

   public static String getErrorString(Context context,int errorCode){
       Resources resources=context.getResources();
       switch (errorCode){
           case GeofenceStatusCodes.GEOFENCE_NOT_AVAILABLE:
               return "GeoFence service is not available now.";
           case GeofenceStatusCodes.GEOFENCE_TOO_MANY_PENDING_INTENTS:
               return "Too many pending request";
           case GeofenceStatusCodes.GEOFENCE_TOO_MANY_GEOFENCES:
                return "Too many GeoFences";
           default:
               return "Unknown Error! GeoFence is not available";
       }
   }

}
