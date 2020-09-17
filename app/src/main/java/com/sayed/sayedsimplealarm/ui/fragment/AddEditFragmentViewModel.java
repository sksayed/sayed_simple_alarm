package com.sayed.sayedsimplealarm.ui.fragment;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.sayed.sayedsimplealarm.data.Repository;
import com.sayed.sayedsimplealarm.model.Alarm;

public class AddEditFragmentViewModel extends AndroidViewModel {
    private Repository repository ;

    public AddEditFragmentViewModel(@NonNull Application application) {
        super(application);
        repository = Repository.getInstance(application);
    }

    public LiveData<Long> insertAlarm (Alarm alarm) {
        return repository.insertAlarm(alarm);
    }
}
