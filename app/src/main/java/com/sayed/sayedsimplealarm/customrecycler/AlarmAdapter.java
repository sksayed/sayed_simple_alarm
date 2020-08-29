package com.sayed.sayedsimplealarm.customrecycler;

import android.app.Application;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sayed.sayedsimplealarm.Allintefaces.IAlarmListAdder;
import com.sayed.sayedsimplealarm.R;
import com.sayed.sayedsimplealarm.model.Alarm;

import java.util.List;

public class AlarmAdapter extends RecyclerView.Adapter<AlarmAdapter.AlarmViewHolder> implements IAlarmListAdder {

    private List<Alarm> alarmList ;

    @NonNull
    @Override
    public AlarmViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.alarm_item , parent , false);
        return new AlarmViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AlarmViewHolder holder, int position) {
        Alarm alarm = alarmList.get(position);
        holder.alarm_time.setText(alarm.getAlarmTitle());
        holder.title.setText(alarm.getAlarmTitle());
    }


    @Override
    public int getItemCount() {
       if (alarmList != null) {
           return alarmList.size() ;
       }else {
           return 0 ;
       }
    }

   @Override
    public void setAlarmList(List<Alarm> alarmList) {
        this.alarmList = alarmList ;
    }

    class AlarmViewHolder extends RecyclerView.ViewHolder {
        TextView title ;
        TextView alarm_time ;

        public AlarmViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            alarm_time = itemView.findViewById(R.id.alarm_time);
        }
    }


}
