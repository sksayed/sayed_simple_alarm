package com.sayed.sayedsimplealarm.ui.fragment;

import android.app.PendingIntent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.sayed.sayedsimplealarm.R;
import com.sayed.sayedsimplealarm.customrecycler.AlarmAdapter;
import com.sayed.sayedsimplealarm.customrecycler.EmptyRecyclerView;
import com.sayed.sayedsimplealarm.service.AlarmService;

public class MainFragment extends Fragment {
    private EmptyRecyclerView emptyRecyclerView;
    private TextView empty_view;
    private AlarmAdapter alarmAdapter;
    private FloatingActionButton fab ;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // return super.onCreateView(inflater, container, savedInstanceState);
        final View view = inflater.inflate(R.layout.fragment_main, container, false);
        emptyRecyclerView = view.findViewById(R.id.empty_recycler_view);
        empty_view = view.findViewById(R.id.empty_view);
        emptyRecyclerView.setmEmptyView(empty_view);
        fab = view.findViewById(R.id.fab);
        alarmAdapter = new AlarmAdapter();
        emptyRecyclerView.setAdapter(alarmAdapter);
        emptyRecyclerView.setItemAnimator( new DefaultItemAnimator());
        emptyRecyclerView.setLayoutManager( new LinearLayoutManager(getContext()));
        emptyRecyclerView.addItemDecoration( new DividerItemDecoration(getContext() , DividerItemDecoration.VERTICAL));
        initListeners();
        return view;
    }

    private void initListeners() {
        fab.setOnClickListener( l -> {
            alarmAdapter.setAlarmList(AlarmService.getTestAlarms());
            alarmAdapter.notifyDataSetChanged();
        });
    }

    private void initViews ( ) {

    }
}
