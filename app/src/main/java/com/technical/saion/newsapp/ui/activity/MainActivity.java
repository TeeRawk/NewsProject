package com.technical.saion.newsapp.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.technical.saion.newsapp.R;
import com.technical.saion.newsapp.ui.fragment.MainFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MainFragment fragment=new MainFragment();


        getSupportFragmentManager().beginTransaction().replace(R.id.activity_main_container,fragment).commit();
    }
}
