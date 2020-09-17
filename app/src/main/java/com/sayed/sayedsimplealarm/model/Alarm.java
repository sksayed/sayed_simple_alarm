package com.sayed.sayedsimplealarm.model;


import android.os.Parcel;
import android.os.Parcelable;
import android.util.SparseBooleanArray;

import androidx.annotation.IntDef;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverter;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import com.sayed.sayedsimplealarm.data.DatabaseHelper;
import com.sayed.sayedsimplealarm.data.DatabaseHelper.*;

/**
 * this will be the model of an alarm
 * this class has to be parcelable so that we can move it around
 * now we will implement the model as an entity
 */
@Entity(tableName = DatabaseHelper.TABLE_NAME)
public class Alarm implements Parcelable {
    private static final String TAG = Alarm.class.getSimpleName();
    @PrimaryKey(autoGenerate = true)
    private long id;
    @ColumnInfo(name = DatabaseHelper.COL_ALARM_TITLE)
    private String alarmTitle;

    @ColumnInfo(name = DatabaseHelper.COL_TIME)
    private Long alarmTime;
    /*
     * we will use Sparse Boolean Array ,SparseBooleanArrays map integers to booleans.
     * for more info visit https://developer.android.com/reference/android/util/SparseBooleanArray
     * it will map the days to be selected to ring as an alarm
     * */
    @ColumnInfo(name = DatabaseHelper.COL_ALL_DAYS)
    private SparseBooleanArray allDays;
    @ColumnInfo(name = DatabaseHelper.COL_IS_ENABLED)
    private boolean isEnabled;

    @Ignore
    protected Alarm(Parcel in) {
        id = in.readLong();
        alarmTitle = in.readString();
        if (in.readByte() == 0) {
            alarmTime = null;
        } else {
            alarmTime = in.readLong();
        }
        allDays = in.readSparseBooleanArray();
        isEnabled = in.readByte() != 0;
    }

    @Ignore
    public static final Creator<Alarm> CREATOR = new Creator<Alarm>() {
        @Override
        public Alarm createFromParcel(Parcel in) {
            return new Alarm(in);
        }

        @Override
        public Alarm[] newArray(int size) {
            return new Alarm[size];
        }
    };

    /**
     * Describe the kinds of special objects contained in this Parcelable
     * instance's marshaled representation. For example, if the object will
     * include a file descriptor in the output of {@link #writeToParcel(Parcel, int)},
     * the return value of this method must include the
     * {@link #CONTENTS_FILE_DESCRIPTOR} bit.
     *
     * @return a bitmask indicating the set of special object types marshaled
     * by this Parcelable object instance.
     */
    @Ignore
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * Flatten this object in to a Parcel.
     *
     * @param dest  The Parcel in which the object should be written.
     * @param flags Additional flags about how the object should be written.
     *              May be 0 or {@link #PARCELABLE_WRITE_RETURN_VALUE}.
     */
    @Ignore
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(alarmTitle);
        if (alarmTime == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(alarmTime);
        }
        dest.writeSparseBooleanArray(allDays);
        dest.writeByte((byte) (isEnabled ? 1 : 0));
    }


    @Retention(RetentionPolicy.RUNTIME)
    @IntDef({SUN, MON, TUE, WED, THRUS, FRI, SAT})
            /*
             the days interface has been used as Enum type , which was helped by @IntDef annotation
              we can still make the same thing with an Enum of days , and in all the methods this enum will
              be used as a concrete type
             */
    @interface Days {
    }

    //list of days when to fire
    public static final int SUN = 1;
    public static final int MON = 2;
    public static final int TUE = 3;
    public static final int WED = 4;
    public static final int THRUS = 5;
    public static final int FRI = 6;
    public static final int SAT = 7;


    //here we will make a constructor chaning

    public Alarm() {
        this(System.currentTimeMillis());
        this.allDays = buildBaseDays();
    }

    @Ignore
    public Alarm(long alarmTime) {
        this.alarmTime = alarmTime;
    }

    public Alarm(@Nullable String alarmTitle, @NonNull Long alarmTime, @Days int... days) {
        this.alarmTitle = alarmTitle;
        this.alarmTime = alarmTime;
        this.allDays = buildAllDays(days);
    }

    public void setAlarmTitle(String alarmTitle) {
        this.alarmTitle = alarmTitle;
    }

    public void setAlarmTime(Long alarmTime) {
        this.alarmTime = alarmTime;
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
