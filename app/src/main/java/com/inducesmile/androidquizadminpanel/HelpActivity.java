package com.inducesmile.androidquizadminpanel;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.inducesmile.androidquizadminpanel.lightquiz.AppConstant;
import com.inducesmile.androidquizadminpanel.lightquiz.MainActivity;

public class HelpActivity extends AppCompatActivity {
    private String category;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            category = extras.getString(AppConstant.CATEGORY);
        }
        if(HomeActivity.userEmail.equalsIgnoreCase("")){
            (findViewById(R.id.text3)).setVisibility(View.GONE);
            (findViewById(R.id.text4)).setVisibility(View.GONE);
        }

        Button startButton = (Button)findViewById(R.id.start_button);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent startButtonIntent = new Intent(HelpActivity.this, QuizHomeActivity.class);
               /* if(category.equalsIgnoreCase("General Knowledge")){
                    HomeActivity.generalKnowledge = false;
                }
                //Entertainment
                if(category.equalsIgnoreCase("Entertainment")){
                    HomeActivity.entertainment = false;
                }
                //History
                if(category.equalsIgnoreCase("History")){
                    HomeActivity.history = false;
                }
                //Sports
                if(category.equalsIgnoreCase("Sports")){
                    HomeActivity.sports = false;
                }
                //Business
                if(category.equalsIgnoreCase("Business")){
                    HomeActivity.businessts = false;
                }
                //Computer
                if(category.equalsIgnoreCase("Computer")){
                    HomeActivity.computer = false;
                }*/

                Intent startButtonIntent = new Intent(HelpActivity.this, MainActivity.class);
                startButtonIntent.putExtra(AppConstant.CATEGORY,category);
                startActivity(startButtonIntent);
            }
        });
    }
}
