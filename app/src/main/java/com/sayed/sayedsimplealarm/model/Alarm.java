package com.sayed.sayedsimplealarm.model;

import android.util.SparseBooleanArray;

import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * this will be the model of an alarm
 */
public class Alarm {
    private static final String TAG = Alarm.class.getSimpleName();
    private long id;
    private String alarmTitle;
    private Long alarmTime;
    /*
     * we will use Sparse Boolean Array ,SparseBooleanArrays map integers to booleans.
     * for more info visit https://developer.android.com/reference/android/util/SparseBooleanArray
     * it will map the days to be selected to ring as an alarm
     * */
    private SparseBooleanArray allDays;
    private boolean isEnabled;


    @Retention(RetentionPolicy.RUNTIME)
    @IntDef({SUN, MON, TUE, WED, THRUS, FRI, SAT})
            /**
             *the days interface has been used as Enum type , which was helped by @IntDef annotation
             * we can still make the same thing with an Enum of days , and in all the methods this enum will
             * be used as a concrete type
             */
    @interface Days {
    }

    //list of days when to fire
    public static final int SUN = 0;
    public static final int MON = 1;
    public static final int TUE = 2;
    public static final int WED = 3;
    public static final int THRUS = 4;
    public static final int FRI = 5;
    public static final int SAT = 6;

    private static final long NO_ID = -1;

    //here we will make a constructor chaning
    public Alarm() {
        this(NO_ID);
    }

    public Alarm(long id) {
        this(id, System.currentTimeMillis());
        this.id = id;
    }

    public Alarm(long id, long alarmTime) {
        this.id = id;
        this.alarmTime = alarmTime;
    }


    public Alarm(long id, String alarmTitle, Long alarmTime, @Days int... days) {
        this.id = id;
        this.alarmTitle = alarmTitle;
        this.alarmTime = alarmTime;
        this.allDays = buildAllDays(days);
    }


    public Alarm setAlarmTitle(String alarmTitle) {
        this.alarmTitle = alarmTitle;
        return this;
    }

    public Alarm setAlarmTime(Long alarmTime) {
        this.alarmTime = alarmTime;
        return this;
    }

    public String getAlarmTitle() {
        return alarmTitle;
    }

    public Long getAlarmTime() {
        return alarmTime;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public SparseBooleanArray getAllDays() {
        return allDays;
    }

    public void setAllDays(SparseBooleanArray allDays) {
        this.allDays = allDays;
    }

    public void setDay(@Days int day, boolean isAlarmed) {
        this.allDays.append(day, isAlarmed);
    }

    public boolean getDay(@Days int day) {
        return this.allDays.get(day);
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    @Override
    public String toString() {
        return "Alarm{" +
                "id=" + id +
                ", alarmTitle='" + alarmTitle + '\'' +
                ", alarmTime=" + alarmTime +
                ", allDays=" + allDays +
                ", isEnabled=" + isEnabled +
                '}';
    }

    @Override
    public int hashCode() {
        int hashcode = (int) id;
        hashcode = (int) ((alarmTime << 5) + alarmTime * 32);
        for (int i = 0; i < allDays.size(); i++) {
            hashcode = 31 * hashcode + (allDays.valueAt(i) ? 1 : 0);
        }
        return hashcode;
    }

    private static SparseBooleanArray buildAllDays(@Days int[] days) {

        final SparseBooleanArray sparseBooleanArray = buildBaseDays();
        for (int i = 0; i < days.length; i++) {
            sparseBooleanArray.append(days[i], true);
        }
        return sparseBooleanArray;
    }

    private static SparseBooleanArray buildBaseDays() {
        final int numberOfDays = 7;
        final SparseBooleanArray array = new SparseBooleanArray(numberOfDays);
        array.append(Alarm.SUN, false);
        array.append(Alarm.MON, false);
        array.append(Alarm.TUE, false);
        array.append(Alarm.WED, false);
        array.append(Alarm.THRUS, false);
        array.append(Alarm.FRI, false);
        array.append(Alarm.SAT, false);
        return array;
    }

}
