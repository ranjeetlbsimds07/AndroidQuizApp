package com.inducesmile.androidquizadminpanel.lightquiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.inducesmile.androidquizadminpanel.HomeActivity;
import com.inducesmile.androidquizadminpanel.R;
import com.inducesmile.androidquizadminpanel.SharedPreference;
import com.inducesmile.androidquizadminpanel.lightquiz.LightQuiz;
import com.inducesmile.androidquizadminpanel.lightquiz.Player;


public class GameOver extends ActionBarActivity {
    int score;
    private Player currentPlayer;
    private int correctAns;
    private int getCorrectAns;
    public static int Getscore = 0;
    private String category="";
    private int generalKnowledgeCorrectAns;
    private int entertainmentCorrecAns;
    private int historyCorrectAns;
    private int sportsCorrectAns;
    private int businesstsCorrectAns;
    private int computerCorrectAns;
    private int seconLabelScore;
    private SharedPreference sharedPreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        score = intent.getIntExtra("Score", -1);
        //correctAns = intent.getIntExtra("correctAns", -1);
        getCorrectAns = intent.getIntExtra("correctAns", -1);
        category = intent.getStringExtra("genreSelection");// get for gettting Score
        if (score == -1) throw new RuntimeException("Score couldn't be loaded");
        currentPlayer = ((LightQuiz) getApplication()).player;
        setContentView(R.layout.activity_game_over);
        sharedPreference = new SharedPreference();
        if (category.equalsIgnoreCase("GeneralKnowledge")) {
            generalKnowledgeCorrectAns = getCorrectAns;
            Log.d("generalAns==",generalKnowledgeCorrectAns+"");
            sharedPreference.setGeneralKnowledge(this,generalKnowledgeCorrectAns+"");
        }
        //Entertainment
        if (category.equalsIgnoreCase("Entertainment")) {
            entertainmentCorrecAns = getCorrectAns;
            Log.d("entertainmentAns==",entertainmentCorrecAns+"");
            sharedPreference.setEntertainment(this,entertainmentCorrecAns+"");
        }
        //History
        if (category.equalsIgnoreCase("History")) {
            historyCorrectAns = getCorrectAns;
            Log.d("historyCorrectAns==",historyCorrectAns+"");
            sharedPreference.setHistory(this,historyCorrectAns+"");
        }
        //Sports
        if (category.equalsIgnoreCase("Sports")) {
            sportsCorrectAns = getCorrectAns;
            Log.d("sportsCorrectAns==",sportsCorrectAns+"");
            sharedPreference.setSports(this,sportsCorrectAns+"");
        }
        //Business
        if (category.equalsIgnoreCase("Business")) {
            businesstsCorrectAns = getCorrectAns;
            Log.d("businesstsCorrectAns==",businesstsCorrectAns+"");
            sharedPreference.setBusiness(this,businesstsCorrectAns+"");
        }
        //Computer
        if (category.equalsIgnoreCase("Computer")) {
            computerCorrectAns = getCorrectAns;
            Log.d("computerCorrectAns==",computerCorrectAns+"");
            sharedPreference.setComputer(this,computerCorrectAns+"");
        }
        //seconLabelScore
        if (category.equalsIgnoreCase(AppConstant.SECOND_LABEL)) {
            correctAns = getCorrectAns;
        }else {
            correctAns = (Integer.parseInt(sharedPreference.getGeneralKnowledge(this)))
                    + (Integer.parseInt(sharedPreference.getEntertainment(this)))
                    + (Integer.parseInt(sharedPreference.getHistory(this)))
                    + (Integer.parseInt(sharedPreference.getSports(this)))
                    + (Integer.parseInt(sharedPreference.getBusiness(this)))
                    + (Integer.parseInt(sharedPreference.getComputer(this)));

            Getscore =  correctAns * 10;
            Log.d("correctAns==", correctAns + "");
        }
        setHighScore();
        showFinalScore();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_game_over, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        //((LightQuiz) this.getApplicationContext()).clearQuestions();
        this.finish();
    }
    //shows gameOver information
    private void showFinalScore() {
        TextView scoreText = (TextView) findViewById(R.id.score_text);
        //scoreText.setText("Score: " + score);
        if(HomeActivity.userEmail.equalsIgnoreCase("")){
            scoreText.setText("Without registration your score will not be store, To see your score please create login.");
            scoreText.setTextSize(TypedValue.COMPLEX_UNIT_PX,getResources().getDimension(R.dimen.font_size));

        }else {
            scoreText.setText("Score: " + correctAns * 10);
            //Getscore =  correctAns * 10;
        }
        if (setHighScore()) {
            TextView newHighScoreText = (TextView) findViewById(R.id.new_highscore_text);
            newHighScoreText.setVisibility(View.VISIBLE);
        }
    }

    //Another button was clicked
    public void buttonClick(View view) {
        switch (view.getId()) {
            case R.id.restart_button:
                Intent intent = new Intent(this, PlayGame.class);
                startActivity(intent);
                this.finish();
                break;
            case R.id.return_button:
                // ((LightQuiz) this.getApplicationContext()).clearQuestions();
                Intent intentHome = new Intent(this, HomeActivity.class);
                startActivity(intentHome);
                this.finish();
                break;
            default:
                throw new RuntimeException("Unknown button ID");
        }

    }

    //returns true if new highscore
    private boolean setHighScore() {
        return currentPlayer.setHighScore(score);
    }
}
