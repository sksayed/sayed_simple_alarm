package com.sayed.sayedsimplealarm.service;

import com.sayed.sayedsimplealarm.model.Alarm;

import java.util.ArrayList;
import java.util.List;

public class AlarmService {

    public static List<Alarm> getTestAlarms () {
        List<Alarm> alarmList = new ArrayList<>();
        for( int i = 1 ; i<=10 ; i++) {
            alarmList.add( new Alarm("alarm title "+i , System.currentTimeMillis()));
        }
        return alarmList ;
    }
}
