package com.sayed.sayedsimplealarm;

import android.app.Application;
import android.widget.Toast;

import androidx.room.Room;

import com.facebook.stetho.Stetho;
import com.sayed.sayedsimplealarm.data.Database;
import com.sayed.sayedsimplealarm.data.DatabaseHelper;
import com.sayed.sayedsimplealarm.model.Alarm;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class AlarmApp extends Application {
    private static AlarmApp instance ;

    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
        instance = this ;
    }

    public static AlarmApp getInstance () {
        return instance ;
    }
}
