package com.sayed.sayedsimplealarm.ui.fragment;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.sayed.sayedsimplealarm.data.Repository;
import com.sayed.sayedsimplealarm.model.Alarm;

import java.util.List;

public class MainFragmentViewModel extends AndroidViewModel {
    private final Repository repository ;
    public MainFragmentViewModel(@NonNull Application application) {
        super(application);
        repository = Repository.getInstance(application);
    }

    public LiveData<List<Alarm>> getAllAlarms () {
       return repository.getAllAlarms() ;
    }
}
