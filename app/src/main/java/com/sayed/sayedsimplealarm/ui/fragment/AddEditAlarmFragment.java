package com.sayed.sayedsimplealarm.ui.fragment;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.sayed.sayedsimplealarm.R;
import com.sayed.sayedsimplealarm.enums.AddEditView;
import com.sayed.sayedsimplealarm.model.Alarm;
import com.sayed.sayedsimplealarm.utilities.StringUtilities;
import com.sayed.sayedsimplealarm.utilities.ValidationUtility;
import com.sayed.sayedsimplealarm.utilities.ViewUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AddEditAlarmFragment extends Fragment {
    private Toolbar toolbar;
    private TimePicker mTimePicker;
    private EditText mLabel;
    private CheckBox mMon, mTues, mWed, mThurs, mFri, mSat, mSun;
    private ActionBar actionBar;
    private AddEditFragmentViewModel mEditViewModel;
    private ArrayList<CheckBox> mCheckBoxes ;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = LayoutInflater.from(container.getContext()).inflate(R.layout.fragment_add_edit_alarm, container, false);
        initViews(view);
        loadContents();
        setHasOptionsMenu(true);
        initListeners();
        return view;
    }

    private void loadContents() {
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
        toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24);
        toolbar.setTitle("yoo");
    }

    private void initViews(View v) {
        toolbar = v.findViewById(R.id.xyz);
        mTimePicker = (TimePicker) v.findViewById(R.id.edit_alarm_time_picker);
        mLabel = (EditText) v.findViewById(R.id.edit_alarm_label);
        mMon = (CheckBox) v.findViewById(R.id.edit_alarm_mon);
        mTues = (CheckBox) v.findViewById(R.id.edit_alarm_tues);
        mWed = (CheckBox) v.findViewById(R.id.edit_alarm_wed);
        mThurs = (CheckBox) v.findViewById(R.id.edit_alarm_thurs);
        mFri = (CheckBox) v.findViewById(R.id.edit_alarm_fri);
        mSat = (CheckBox) v.findViewById(R.id.edit_alarm_sat);
        mSun = (CheckBox) v.findViewById(R.id.edit_alarm_sun);
        mEditViewModel = new ViewModelProvider(getActivity()).get(AddEditFragmentViewModel.class);
        mCheckBoxes  = (ArrayList<CheckBox>)
        ViewUtils.addAllchekcBoxes( mSun , mMon , mTues , mWed , mThurs, mFri , mSat);
    }

    private void initListeners() {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(AddEditAlarmFragment.this).popBackStack();
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(getMenuRes() , menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.save:
                save();
                return true;
            case R.id.delete:
                delete();
                return true;
            case R.id.update:
                update();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    private int getMenuRes() {
        int result ;
       String toolbar_title = getArguments().getString(StringUtilities.TOOLBAR_EXTRA , " ");
       switch (toolbar_title) {
           case StringUtilities.ADD_ALARM:
               result = R.menu.save ;
               break;
           case StringUtilities.Edit_ALARM:
               result = R.menu.update;
               break;
           case StringUtilities.NULL:
           default:
               throw new IllegalStateException("The state is illigal "+MainFragment.class.getSimpleName()+"" +
                       " should sent proper data in bundle with key" +StringUtilities.TOOLBAR_EXTRA);
       }
       return result ;
    }


    private void update() {
    }

    private void delete() {
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void save() {
       final Alarm alarm = new Alarm();
       //we will store time with respect to a calander object
        final Calendar time = Calendar.getInstance();
        time.set(Calendar.MINUTE , ViewUtils.getTimePickerInMinutes(mTimePicker));
        time.set(Calendar.HOUR , ViewUtils.getTimePickerInHour(mTimePicker));
        //setting the time in alarm object
        alarm.setAlarmTime(time.getTimeInMillis());
        alarm.setAlarmTitle(mLabel.getText().toString());
        //setting the days clicked by the user
        alarm.setDay(Alarm.MON, mMon.isChecked());
        alarm.setDay(Alarm.TUE, mTues.isChecked());
        alarm.setDay(Alarm.WED, mWed.isChecked());
        alarm.setDay(Alarm.THRUS, mThurs.isChecked());
        alarm.setDay(Alarm.FRI, mFri.isChecked());
        alarm.setDay(Alarm.SAT, mSat.isChecked());
        alarm.setDay(Alarm.SUN, mSun.isChecked());

        if(ValidationUtility.isAllCheckBoxesFalse(mCheckBoxes)){
            mEditViewModel.insertAlarm(alarm);
        }else {
            //show an alert that user should try again
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity() , R.style.NoDaysSelectedTheme);
            builder.setMessage("No Days were Selected");
            builder.setIcon(R.drawable.ic_baseline_cancel_24);
            builder.setPositiveButton("Yes" , null);
            builder.show();
        }

    }

    



}
