package com.deeepsense.smuggler.ds_nfcbeam.feature;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create Fragments
        BeamReaderFragment readerFragment = new BeamReaderFragment();
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.constaintLayout, readerFragment,
                readerFragment.getTag()).commit();

    }
}
