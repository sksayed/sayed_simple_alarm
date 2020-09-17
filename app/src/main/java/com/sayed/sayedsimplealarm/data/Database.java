package com.sayed.sayedsimplealarm.data;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import com.sayed.sayedsimplealarm.AlarmApp;
import com.sayed.sayedsimplealarm.model.Alarm;

@androidx.room.Database(entities = {Alarm.class}, version = DatabaseHelper.VERSION, exportSchema = false)
@TypeConverters({DaysConverter.class})
public abstract class Database extends RoomDatabase {
    public abstract AlarmDao alarmDao();

    private static volatile Database databaseInstance = null;

    public static Database getDatabaseInstance(Context context) {
        if (databaseInstance == null) {
            synchronized (Database.class) {
                if (databaseInstance == null) {
                    databaseInstance = Room.databaseBuilder(context, Database.class, DatabaseHelper.DATABASE_NAME)
                            .build();
                }
            }
        }
        return databaseInstance;
    }

}
