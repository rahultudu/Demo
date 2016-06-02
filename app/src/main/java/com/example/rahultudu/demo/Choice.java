package com.example.rahultudu.demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.google.firebase.analytics.FirebaseAnalytics;

public class Choice extends AppCompatActivity {

    private FirebaseAnalytics mFirebaseAnalytics;
    int Pizza = 0;
    int Burger = 0;
    int IceCream = 0;
    String _CurrentUserName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);

        //setting firebase
        final Bundle extras = getIntent().getExtras();
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        mFirebaseAnalytics.setAnalyticsCollectionEnabled(true);
        mFirebaseAnalytics.setMinimumSessionDuration(1);

        final Bundle Report = new Bundle();

        //for radiogroup
        final RadioGroup _RadioGroup__ = (RadioGroup) findViewById(R.id.RadioGroup);

        //for radio buttons
        final RadioButton _Pizza = (RadioButton) findViewById(R.id.Pizza);
        final RadioButton _Burger = (RadioButton) findViewById(R.id.Burger);
       // RadioButton _IceCream = (RadioButton) findViewById(R.id.IceCream);
        Button submit = (Button) findViewById(R.id.Submit);

        //on click of submit button
        submit.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                if (extras != null) {
                    _CurrentUserName = extras.getString("CurrentUserName");
                }

                Intent myIntent = new Intent(v.getContext(), Logout.class);
                int selectedId = _RadioGroup.getCheckedRadioButtonId();


                // checking which radio button was clicked
                //on specific selection a specific event is logged in with two parameters
                //one is food choice and another is username
                //also each time user property, Food_Choice is being set depending on the selection

                if(selectedId == _Pizza.getId()) {
                    Report.putString("UserName",_CurrentUserName);
                    Report.putString("Food","Pizza");
                    mFirebaseAnalytics.logEvent("PizzaCount", Report);
                    mFirebaseAnalytics.setUserProperty("Food_Choice","Pizza");
                    myIntent.putExtra("Choice", "Pizza");
                }
                else if(selectedId == _Burger.getId()) {
                    Report.putString("UserName",_CurrentUserName);
                    Report.putString("Food","Burger");
                    mFirebaseAnalytics.logEvent("BurgerCount", Report);
                    mFirebaseAnalytics.setUserProperty("Food_Choice","Burger");
                    myIntent.putExtra("Choice", "Burger");

                }
                else {
                    Report.putString("UserName",_CurrentUserName);
                    Report.putString("Food","IceCream");
                    mFirebaseAnalytics.logEvent("IceCreamCount", Report);
                    mFirebaseAnalytics.setUserProperty("Food_Choice","Ice Cream");
                    myIntent.putExtra("Choice", "Ice Cream");

                }


                //on each submit button click
                //an event is loaded which takes username as parameter
                Report.putString("UserName",_CurrentUserName);
                mFirebaseAnalytics.logEvent("Submitted", Report);

                //going to logout page

                myIntent.putExtra("CurrentUserName",_CurrentUserName);
                startActivity(myIntent);
            }
        });
    }
}
