package com.inducesmile.androidquizadminpanel;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.inducesmile.androidquizadminpanel.fragment.HomeFragment;
import com.inducesmile.androidquizadminpanel.fragment.LadderBoardFragment;
import com.inducesmile.androidquizadminpanel.fragment.QuizTopicsFragment;
import com.inducesmile.androidquizadminpanel.fragment.SettingsFragment;
import com.inducesmile.androidquizadminpanel.lightquiz.AppConstant;
import com.inducesmile.androidquizadminpanel.lightquiz.GameOver;

public class HomeActivity extends AppCompatActivity {

    private static final String TAG = HomeActivity.class.getSimpleName();

    private Fragment selectedFragment = null;
    public static String userEmail="";
    private Button btnLogout;
    private TextView txtUserName;
    private TextView txtEmail;
    private Button txtSecondLabel;
    private SharedPreference sharedPreference;

    public static boolean generalKnowledge = true;
    public static boolean entertainment = true;
    public static boolean history = true;
    public static boolean sports = true;
    public static boolean businessts = true;
    public static boolean computer = true;
    public static boolean secondLabel = true;



    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;
            switch (item.getItemId()) {
                case R.id.quiz_mart:
                    selectedFragment = new QuizTopicsFragment();
                    //txtSecondLabel.setVisibility(View.VISIBLE);
                    if(GameOver.Getscore >=200 ){
                        txtSecondLabel.setVisibility(View.VISIBLE);
                    }else{
                        txtSecondLabel.setVisibility(View.GONE);
                    }
                    txtUserName.setVisibility(View.VISIBLE);
                    break;
                case R.id.home:
                    selectedFragment = new HomeFragment();
                    txtSecondLabel.setVisibility(View.GONE);
                    txtUserName.setVisibility(View.GONE);
                    break;
                case R.id.ladder_board:
                    selectedFragment = new LadderBoardFragment();
                    txtSecondLabel.setVisibility(View.GONE);
                    txtUserName.setVisibility(View.VISIBLE);
                    break;
                case R.id.settings:
                    selectedFragment = new SettingsFragment();
                    txtSecondLabel.setVisibility(View.GONE);
                    txtUserName.setVisibility(View.VISIBLE);
                    break;
            }
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.content, selectedFragment);
            transaction.commit();
            return true;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        btnLogout = (Button)findViewById(R.id.btnLogout);
        txtSecondLabel = (Button)findViewById(R.id.txtSecondLabel);
        txtUserName = (TextView) findViewById(R.id.txtUserName);
        txtEmail = (TextView) findViewById(R.id.txtEmail);
        sharedPreference = new SharedPreference();

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent accountsIntent = new Intent(HomeActivity.this, LoginActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                sharedPreference.userName(HomeActivity.this, "");
                sharedPreference.userEmail(HomeActivity.this,"");


                sharedPreference.setGeneralKnowledge(HomeActivity.this,0+"");
                sharedPreference.setComputer(HomeActivity.this,0+"");
                sharedPreference.setBusiness(HomeActivity.this,0+"");
                sharedPreference.setEntertainment(HomeActivity.this,0+"");
                sharedPreference.setSports(HomeActivity.this,0+"");
                sharedPreference.setHistory(HomeActivity.this,0+"");
                GameOver.Getscore = 0;

                generalKnowledge = true;
                entertainment = true;
                history = true;
                sports = true;
                businessts = true;
                computer = true;
                startActivity(accountsIntent);
                finish();
            }
        });


        txtSecondLabel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //if(!generalKnowledge && !entertainment && !history && !sports && !businessts && !computer){
                if(GameOver.Getscore >=200 ){
                    Intent profileIntent = new Intent(HomeActivity.this, HelpActivity.class);
                    profileIntent.putExtra(AppConstant.CATEGORY, AppConstant.SECOND_LABEL);
                    startActivity(profileIntent);
                }else {
                    /*Intent profileIntent = new Intent(HomeActivity.this, HelpActivity.class);
                    profileIntent.putExtra(AppConstant.CATEGORY, AppConstant.SECOND_LABEL);
                    startActivity(profileIntent);*/

                    Toast.makeText(HomeActivity.this,"Please attempt all topice then start second label ",Toast.LENGTH_LONG).show();
                }
            }
        });
        if(getIntent().getExtras() != null && getIntent().getStringExtra("EMAIL") != null){
            userEmail = getIntent().getStringExtra("EMAIL");
            txtUserName.setText(sharedPreference.getUserName(HomeActivity.this));
            txtEmail.setText(sharedPreference.getUserEmail(HomeActivity.this));
            txtUserName.setVisibility(View.VISIBLE);
            //txtEmail.setVisibility(View.VISIBLE);
        }
        if(userEmail.equalsIgnoreCase("")){
            //btnLogout.setVisibility(View.GONE);
            txtUserName.setVisibility(View.GONE);
            txtEmail.setVisibility(View.GONE);
        }

        if(!TextUtils.isEmpty(sharedPreference.getUserEmail(this))){
            btnLogout.setText("Logout");
        }else{
            btnLogout.setText("Exit");
        }

        selectedFragment = new QuizTopicsFragment();
        FragmentTransaction transactions = getSupportFragmentManager().beginTransaction();
        transactions.replace(R.id.content, selectedFragment);
        transactions.commit();

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }
    @Override
    public void onBackPressed() {
        if(TextUtils.isEmpty(userEmail)){
            super.onBackPressed();
        }else {

            // do nothing.
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(!TextUtils.isEmpty(sharedPreference.getUserName(HomeActivity.this))) {
            txtUserName.setText(sharedPreference.getUserName(HomeActivity.this));
            txtEmail.setText(sharedPreference.getUserEmail(HomeActivity.this));
            txtUserName.setVisibility(View.VISIBLE);
            //txtEmail.setVisibility(View.VISIBLE);
        }else{
            txtUserName.setVisibility(View.GONE);
            txtEmail.setVisibility(View.GONE);
        }

        //if(!generalKnowledge && !entertainment && !history && !sports && !businessts && !computer){
        if(GameOver.Getscore >=200 ){
            txtSecondLabel.setVisibility(View.VISIBLE);
        }else{
            txtSecondLabel.setVisibility(View.GONE);
        }
    }
}
