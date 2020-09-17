package com.sayed.sayedsimplealarm.service.broadcastreciever;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.os.Build;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

import com.sayed.sayedsimplealarm.R;
import com.sayed.sayedsimplealarm.model.Alarm;
import com.sayed.sayedsimplealarm.ui.activity.AlarmLandingActivity;
import com.sayed.sayedsimplealarm.utilities.AlarmUtils;
import com.sayed.sayedsimplealarm.utilities.StringUtilities;

public class AlarmReciever extends BroadcastReceiver {
    private static final String TAG = AlarmReciever.class.getSimpleName();
    public static final String CHANNEL_ID = "sayed_alarm";

    @Override
    public void onReceive(Context context, Intent intent) {
        final Alarm alarm = intent.getBundleExtra(StringUtilities.BUNDLE_EXTRA).getParcelable(StringUtilities.ALARM_PARCELEBLE_EXTRA);
        if (alarm == null) {
            Log.e(TAG, "alarm sent is null", new NullPointerException());
            return;
        }
        //TODO:create Notification for the alarm

        final NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        createNotificationChannel(context);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context , CHANNEL_ID);
        builder.setSmallIcon(R.drawable.ic_baseline_alarm_on_24);
        builder.setColor(ContextCompat.getColor(context , R.color.colorPrimary));
        builder.setContentTitle("Alarm By Sayed");
        builder.setContentText(alarm.getAlarmTitle());
        builder.setVibrate( new long[]{1000l,500l,200l,100l,9000l});
        builder.setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM));
        builder.setContentIntent(AlarmUtils.launchALarmPendingPage(context , alarm));
        builder.setAutoCancel(true);
        builder.setPriority(NotificationManager.IMPORTANCE_HIGH);

        manager.notify((int)alarm.getId() ,builder.build());
        //now set the alarm manually
        AlarmUtils.setAlarm(context , alarm);
    }

    private void createNotificationChannel(Context context) {
        // check for build version Oreo
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.O) return;
        final NotificationManager manager = context.getSystemService(NotificationManager.class);
        if (manager == null) return;

        final String name = context.getString(R.string.app_name);
        if (manager.getNotificationChannel(name) == null) {
            final NotificationChannel channel =
                    new NotificationChannel(CHANNEL_ID, name, NotificationManager.IMPORTANCE_HIGH);
            channel.enableLights(true);
            channel.enableVibration(true);
            channel.setVibrationPattern(new long[]{1000, 500, 1000, 500, 200, 9000, 12});
            channel.setBypassDnd(true);
            manager.createNotificationChannel(channel);
        }

    }
}
