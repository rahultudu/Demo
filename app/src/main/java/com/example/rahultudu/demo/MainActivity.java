package com.example.rahultudu.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.analytics.FirebaseAnalytics;

public class MainActivity extends AppCompatActivity {
    private FirebaseAnalytics mFirebaseAnalytics;
    int NumberOfButtonClick = 0;
    TextView Counter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Obtain the FirebaseAnalytics instance.
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        String mFavoriteFood = "everything";

        //setting user property
        mFirebaseAnalytics.setUserProperty("favorite_food", mFavoriteFood);
        Counter = (TextView) findViewById(R.id.click_count);
        Counter.setText(Integer.toString(NumberOfButtonClick));

        //for increasing count

    }

    public void _IncreaseCount(View view)
    {
        NumberOfButtonClick++;
        Counter = (TextView) findViewById(R.id.click_count);
        Counter.setText(Integer.toString(NumberOfButtonClick));
    }

    public void _DecreaseCount(View view)
    {
        NumberOfButtonClick--;
        Counter = (TextView) findViewById(R.id.click_count);
        Counter.setText(Integer.toString(NumberOfButtonClick));
    }
}