package com.example.rahultudu.demo;

import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Logout extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logout);

        String _CurrentUserName = "";
        String _Choice = "";

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            _CurrentUserName = extras.getString("CurrentUserName");
            _Choice = extras.getString("Choice");
        }

        TextView LoggedOut = (TextView) findViewById(R.id.LoggedOut);
        LoggedOut.setText(_CurrentUserName + " selected " + _Choice);


        Button _Logout = (Button) findViewById(R.id.Logout);
        _Logout.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                System.exit(0);
            }
        });



    }
}
