package com.inducesmile.androidquizadminpanel.lightquiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.inducesmile.androidquizadminpanel.HomeActivity;
import com.inducesmile.androidquizadminpanel.R;
import com.inducesmile.androidquizadminpanel.lightquiz.LightQuiz;
import com.inducesmile.androidquizadminpanel.lightquiz.Player;


public class GameOver extends ActionBarActivity {
    int score;
    private Player currentPlayer;
    private int correctAns;
    public static int Getscore = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        score = intent.getIntExtra("Score", -1);
        correctAns = intent.getIntExtra("correctAns", -1);
        if (score == -1) throw new RuntimeException("Score couldn't be loaded");
        currentPlayer = ((LightQuiz) getApplication()).player;
        setContentView(R.layout.activity_game_over);
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
            Getscore =  correctAns * 10;
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
