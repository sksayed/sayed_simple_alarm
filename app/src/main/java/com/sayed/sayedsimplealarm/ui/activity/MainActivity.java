package com.sayed.sayedsimplealarm.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.sayed.sayedsimplealarm.R;
import com.sayed.sayedsimplealarm.ui.fragment.MainFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (findViewById(R.id.main_fragment_container) !=  null) {
            MainFragment mainFragment = new MainFragment();
             getSupportFragmentManager()
                     .beginTransaction()
                     .add(R.id.main_fragment_container , mainFragment)
                     .addToBackStack(null)
                     .commit();
        }
    }
}