package com.example.amit.detector;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingEvent;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by AMIT on 07-Dec-17.
 */

public class GeoFenceTransitionIntentService extends IntentService {

    public GeoFenceTransitionIntentService() {
        super("GeoFenceIntentService");
    }
    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    protected void onHandleIntent( Intent intent) {
        Log.d("folk","on handle intent");
        GeofencingEvent geofencingEvent=GeofencingEvent.fromIntent(intent);

        if(geofencingEvent.hasError()){
             String errorMsg=GeoFenceErrorMessage.getErrorString(this,geofencingEvent.getErrorCode());
            Log.d("folk",""+errorMsg);
            return;
        }
          int geofenceTransitionType=  geofencingEvent.getGeofenceTransition();
        if (geofenceTransitionType== Geofence.GEOFENCE_TRANSITION_ENTER||geofenceTransitionType==Geofence.GEOFENCE_TRANSITION_EXIT){

            List<Geofence> triggeringGeoFences=geofencingEvent.getTriggeringGeofences();

            String geoFenceTransitionDetails=getGeofenceTransitionDetails(this,geofenceTransitionType,
                    triggeringGeoFences);

            Log.d("folk",geoFenceTransitionDetails);
            sendNotification(geoFenceTransitionDetails);
        }
    }

    private String getGeofenceTransitionDetails(Context context,
                                                int geoFenceTransitionType, List<Geofence> triggeringGeoFences) {
        String geofenceTransitionString =getTransitionString(geoFenceTransitionType);

        ArrayList triggeringGeofencesIDSList=new ArrayList();

        for (Geofence geofence:triggeringGeoFences){
            triggeringGeofencesIDSList.add(geofence.getRequestId());
        }

        String geofencesIdsTriggeringString= TextUtils.join(" ,",triggeringGeofencesIDSList);
        return geofenceTransitionString+ " : "+geofencesIdsTriggeringString;


    }

private void sendNotification(String notification){
    Log.d("folk","send notification");
        Intent notificationIntent=new Intent(getApplicationContext(),MainActivity.class);
    TaskStackBuilder stackBuilder=TaskStackBuilder.create(this);
    stackBuilder.addParentStack(MainActivity.class);
    stackBuilder.addNextIntent(notificationIntent);
    PendingIntent notificationPendingIntent=stackBuilder.getPendingIntent(1,PendingIntent.FLAG_UPDATE_CURRENT);
    NotificationCompat.Builder builder=new NotificationCompat.Builder(this);
    builder.setSmallIcon(R.drawable.common_google_signin_btn_icon_dark)
            .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.common_google_signin_btn_icon_dark))
            .setColor(Color.BLUE)
            .setContentTitle("hello")
            .setContentText(notification)
            .setContentIntent(notificationPendingIntent);

    builder.setAutoCancel(true);
    NotificationManager mNotificationManager= (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
    mNotificationManager.notify(0,builder.build());

}


    private String getTransitionString(int transitionType) {
        switch (transitionType) {
            case Geofence.GEOFENCE_TRANSITION_ENTER:
                return "Entered";
            case Geofence.GEOFENCE_TRANSITION_EXIT:
                return "Exit";
            default:
                return "Unknown";
        }
    }
}
