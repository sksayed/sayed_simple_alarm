package com.sayed.sayedsimplealarm.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.sayed.sayedsimplealarm.model.Alarm;

import java.util.List;

@Dao
public interface AlarmDao {

    @Insert(entity = Alarm.class , onConflict = OnConflictStrategy.IGNORE)
    long insertAlarm (Alarm alarm);

    @Update(entity = Alarm.class , onConflict = OnConflictStrategy.IGNORE)
    int updateAlam (Alarm alarm);

    @Delete(entity = Alarm.class )
    void deleteAlarm(Alarm alarm);

    @Query(value = "Select * from "+DatabaseHelper.TABLE_NAME+" ")
    LiveData<List<Alarm>> getAllAlarms ();

    @Query(value = "DELETE from "+DatabaseHelper.TABLE_NAME)
    void deleteAll ();
}
