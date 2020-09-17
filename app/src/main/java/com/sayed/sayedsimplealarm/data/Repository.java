package com.sayed.sayedsimplealarm.data;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Room;

import com.sayed.sayedsimplealarm.AlarmApp;
import com.sayed.sayedsimplealarm.model.Alarm;
import com.sayed.sayedsimplealarm.utilities.AppExecutor;

import java.util.List;

public class Repository  {
    private final Database dataService ;
    private static Repository repository ;
    private MutableLiveData<Long> newId = new MutableLiveData();
    private Repository (Context context) {
        dataService = Database.getDatabaseInstance(context);
    }

    public static Repository getInstance (Context context) {
        if (repository == null) {
            repository = new Repository(context);
        }
        return repository ;
    }

    public LiveData<List<Alarm>> getAllAlarms () {
        return dataService.alarmDao().getAllAlarms();
    }

    public LiveData<Long> insertAlarm (Alarm alarm) {
        addAlarm(alarm);
        return newId ;

    }

    private void addAlarm(Alarm alarm) {
        AppExecutor.getInstance().IOExecutor.execute(()->{
           newId.postValue(dataService.alarmDao().insertAlarm(alarm));
        });
    }


}

