package com.sayed.sayedsimplealarm.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.sayed.sayedsimplealarm.R;

public class AlarmLandingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_landing);
    }


    public static Intent landingPageIntent (Context context) {
        Intent intent = new Intent(context , AlarmLandingActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        return intent ;
    }
}
