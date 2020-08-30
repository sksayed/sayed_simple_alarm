package com.sayed.sayedsimplealarm.ui.fragment;

import android.Manifest;
import android.app.PendingIntent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.sayed.sayedsimplealarm.R;
import com.sayed.sayedsimplealarm.customrecycler.AlarmAdapter;
import com.sayed.sayedsimplealarm.customrecycler.EmptyRecyclerView;
import com.sayed.sayedsimplealarm.service.AlarmService;
import com.sayed.sayedsimplealarm.utilities.StringUtilities;

public class MainFragment extends Fragment {
    private EmptyRecyclerView emptyRecyclerView;
    private TextView empty_view;
    private AlarmAdapter alarmAdapter;
    private FloatingActionButton fab;
    private Toolbar toolbar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // return super.onCreateView(inflater, container, savedInstanceState);
        final View view = inflater.inflate(R.layout.fragment_main, container, false);
        initViews(view);
        loadContents();
        initListeners();
        return view;
    }

    private void initListeners() {
        fab.setOnClickListener(l -> {
            AddEditAlarmFragment addEditAlarmFragment = new AddEditAlarmFragment();
            Bundle bundle = new Bundle();
            bundle.putString(StringUtilities.TOOLBAR_EXTRA, StringUtilities.ADD_ALARM);
            addEditAlarmFragment.setArguments(bundle);
           /* alarmAdapter.setAlarmList(AlarmService.getTestAlarms());
            alarmAdapter.notifyDataSetChanged();*/
           /* getActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.main_fragment_container, addEditAlarmFragment)
                    .addToBackStack(null)
                    .commit();*/
            NavHostFragment.findNavController(this).navigate(R.id.action_mainFragment_to_addEditAlarmFragment);
        });
    }

    private void initViews(View view) {
        emptyRecyclerView = view.findViewById(R.id.empty_recycler_view);
        empty_view = view.findViewById(R.id.empty_view);
        fab = view.findViewById(R.id.fab);
        toolbar = view.findViewById(R.id.toolbar);
        emptyRecyclerView.setmEmptyView(empty_view);
    }

    private void loadContents() {
        alarmAdapter = new AlarmAdapter();
        emptyRecyclerView.setAdapter(alarmAdapter);
        emptyRecyclerView.setItemAnimator(new DefaultItemAnimator());
        emptyRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        emptyRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

        //set up ToolBar
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setTitle("sayeds alarm");
    }

}
