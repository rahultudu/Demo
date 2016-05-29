package com.example.rahultudu.demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.common.server.converter.StringToIntConverter;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    private FirebaseAnalytics mFirebaseAnalytics;
    int NumberOfLogins = 0;
    Long _Total = 0L;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference Cred = database.getReference("UserCredentials");
        final DatabaseReference Total = database.getReference("Total");

        Button Login = (Button) findViewById(R.id.button);

        Total.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                _Total = dataSnapshot.getValue(Long.class);
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });

        Login.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {

                //getting username and password on login
                EditText _UserName = (EditText) findViewById(R.id.UserName);
                EditText _Password = (EditText) findViewById(R.id.Password);

                String UserName = _UserName.getEditableText().toString();
                String Password = _Password.getEditableText().toString();

                //made a new child
                Cred.child(Long.toString(_Total+1)).push();
                Credentials C = new Credentials();
                C.UserName = UserName;
                C.Password = Password;

                //details of new child pushed
                Cred.child(Long.toString(_Total+1)).setValue(C);
                Total.setValue(_Total+1);

                //sending username to next activity
                Intent myIntent = new Intent(v.getContext(), Choice.class);
                myIntent.putExtra("CurrentUserName",UserName);
                startActivity(myIntent);


            }
        });
    }

}