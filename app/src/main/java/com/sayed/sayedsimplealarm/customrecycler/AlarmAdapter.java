package com.sayed.sayedsimplealarm.customrecycler;

import android.content.Context;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sayed.sayedsimplealarm.allintefaces.IAlarmListAdder;
import com.sayed.sayedsimplealarm.R;
import com.sayed.sayedsimplealarm.model.Alarm;
import com.sayed.sayedsimplealarm.utilities.AlarmUtils;

import java.util.List;

public class AlarmAdapter extends RecyclerView.Adapter<AlarmAdapter.AlarmViewHolder> implements IAlarmListAdder {

    private List<Alarm> alarmList;
    private String[] mDays = null;
    private int mColor  ;

    @NonNull
    @Override
    public AlarmViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.alarm_row, parent, false);
        return new AlarmViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AlarmViewHolder holder, int position) {
        Context context = holder.itemView.getContext();

        if (mDays == null) {
            mDays = context.getResources().getStringArray(R.array.day_array);
        }

        if (mColor == 0) {
            mColor = context.getResources().getColor(R.color.colorAccent);
        }

        Alarm alarm = alarmList.get(position);
        holder.time.setText(AlarmUtils.getReadeableTime(alarm.getAlarmTime()));
        holder.amPm.setText(AlarmUtils.getReadeableAmPm(alarm.getAlarmTime()));
        holder.label.setText(alarm.getAlarmTitle());
        holder.days.setText(buildSelectedDays(alarm));

    }

    private Spannable buildSelectedDays(Alarm alarm) {
        int daysCount = 7;
        SparseBooleanArray array = alarm.getAllDays();
        int startIndex, endIndex;
        SpannableStringBuilder builder = new SpannableStringBuilder();
        for (int i = 0; i < daysCount; i++) {
            startIndex = builder.length();
            final String day = mDays[i];
            builder.append(day);
            builder.append(" ");
            endIndex = startIndex + day.length();
            boolean isSelected = array.valueAt(i);

            if (isSelected) {
                ForegroundColorSpan span = new ForegroundColorSpan(mColor);
                builder.setSpan(span , startIndex , endIndex , SpannableStringBuilder.SPAN_EXCLUSIVE_EXCLUSIVE);
            }

        }
        return builder ;

    }


    @Override
    public int getItemCount() {
        if (alarmList != null) {
            return alarmList.size();
        } else {
            return 0;
        }
    }


    @Override
    public void setAlarmList(List<Alarm> alarmList) {
        this.alarmList = alarmList;
    }

    class AlarmViewHolder extends RecyclerView.ViewHolder {
        final TextView time, amPm, label, days;

        public AlarmViewHolder(@NonNull View itemView) {
            super(itemView);

            time = itemView.findViewById(R.id.ar_time);
            amPm = itemView.findViewById(R.id.ar_am_pm);
            label = itemView.findViewById(R.id.ar_label);
            days = itemView.findViewById(R.id.ar_days);
        }
    }
}



