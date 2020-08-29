package com.sayed.sayedsimplealarm.model;

/**
 * this will be the model of an alarm
 */
public class Alarm {
    private String alarmTitle ;
    private Long alarmTime ;

    public Alarm(String alarmTitle, Long alarmTime) {
        this.alarmTitle = alarmTitle;
        this.alarmTime = alarmTime;
    }

    public Alarm setAlarmTitle(String alarmTitle) {
        this.alarmTitle = alarmTitle;
        return this ;
    }

    public Alarm setAlarmTime(Long alarmTime) {
        this.alarmTime = alarmTime;
        return this ;
    }

    public String getAlarmTitle() {
        return alarmTitle;
    }

    public Long getAlarmTime() {
        return alarmTime;
    }
}
