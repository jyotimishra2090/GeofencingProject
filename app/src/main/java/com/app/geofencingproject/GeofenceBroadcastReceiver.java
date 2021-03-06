package com.app.geofencingproject;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingEvent;
import java.util.List;

public class GeofenceBroadcastReceiver extends BroadcastReceiver {
    private static final String TAG = "GeofenceingBroadcast";

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.

        NotificationHelper notificationHelper = new NotificationHelper(context);
        GeofencingEvent geofencingEvent = GeofencingEvent.fromIntent(intent);
        if(geofencingEvent.hasError()){
            Log.d(TAG,"OnReceive:Error ");
            return;
        }

        List<Geofence> geofenceList = geofencingEvent.getTriggeringGeofences();
        for(Geofence geofence:geofenceList){
            Log.d(TAG,"OnReceive:"+geofence.getRequestId());
        }
        int transitionType = geofencingEvent.getGeofenceTransition();
        switch (transitionType){
            case Geofence.GEOFENCE_TRANSITION_ENTER:
                Toast.makeText(context,"GEOFENCE_TRANSITION_ENTER",Toast.LENGTH_LONG).show();
                notificationHelper.sendHighPriorityNotification("GEOFENCE_TRANSITION_ENTER","",MapsActivity.class);
                break;
            case Geofence.GEOFENCE_TRANSITION_DWELL:
                Toast.makeText(context,"GEOFENCE_TRANSITION_DWELL",Toast.LENGTH_LONG).show();
                notificationHelper.sendHighPriorityNotification("GEOFENCE_TRANSITION_DWELL","",MapsActivity.class);
                break;
            case Geofence.GEOFENCE_TRANSITION_EXIT:
                Toast.makeText(context,"GEOFENCE_TRANSITION_EXIT",Toast.LENGTH_LONG).show();
                notificationHelper.sendHighPriorityNotification("GEOFENCE_TRANSITION_EXIT","",MapsActivity.class);
                break;
        }
    }
}
