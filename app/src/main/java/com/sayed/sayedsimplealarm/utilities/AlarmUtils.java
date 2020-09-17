package com.sayed.sayedsimplealarm.utilities;

import android.util.Log;
import android.util.SparseBooleanArray;

import com.sayed.sayedsimplealarm.model.Alarm;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AlarmUtils {

    private static final SimpleDateFormat TIME_FORMAT = new SimpleDateFormat("h:mm", Locale.getDefault());
    private static final SimpleDateFormat AM_PM_FORMAT = new SimpleDateFormat("a", Locale.getDefault());

    public static String getReadeableTime(long time) {
        return TIME_FORMAT.format(time);
    }

    public static String getReadeableAmPm(long time) {
        return AM_PM_FORMAT.format(time);
    }

    public static void setAlarm(Alarm alarm) {
        final Calendar timeForNextAlarm = getTimeForNextAlarm(alarm);
        alarm.setAlarmTime(timeForNextAlarm.getTimeInMillis());

        //save the current alarm in a bundle and send it to broadcast at AlarmReciever with pending intent



    }

    private static Calendar getTimeForNextAlarm(Alarm alarm) {
        final Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(alarm.getAlarmTime());

        final long currentTime = System.currentTimeMillis();
        final int startIndex = getStartIndexFromTime(calendar);

        int countOfLoop = 0 ;
        boolean isAlarmSetForDay = false ;
        final SparseBooleanArray days = alarm.getAllDays();

        do {
            final int index = (countOfLoop + startIndex) % 7 ;
            isAlarmSetForDay =days.valueAt(index) && (calendar.getTimeInMillis() > currentTime);
            if(!isAlarmSetForDay) {
                calendar.set(Calendar.DAY_OF_MONTH , 1);
                countOfLoop++ ;
            }
        }while (!isAlarmSetForDay && countOfLoop < 7);
        Log.d("final time ", calendar.getTime().toString());
        return calendar ;
    }

    private static int getStartIndexFromTime(Calendar calendar) {
        final int day_of_the_week = calendar.get(Calendar.DAY_OF_WEEK);
        int startIndex = 0;
        switch (day_of_the_week) {
            case Calendar.SUNDAY:
                startIndex = 0;
                break;
            case Calendar.MONDAY:
                startIndex = 1;
                break;
            case Calendar.TUESDAY:
                startIndex = 2;
                break;
            case Calendar.WEDNESDAY:
                startIndex = 3;
                break;
            case Calendar.THURSDAY:
                startIndex = 4;
                break;
            case Calendar.FRIDAY:
                startIndex = 5;
                break;
            case Calendar.SATURDAY:
                startIndex = 6;
                break;

        }

        return startIndex;
    }
}
