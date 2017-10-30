package com.inducesmile.androidquizadminpanel.lightquiz;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.inducesmile.androidquizadminpanel.HomeActivity;
import com.inducesmile.androidquizadminpanel.R;


public class MainActivity extends ActionBarActivity {
    //public static QuestionsGenerator generator;
    private Player currentPlayer;
    private String category="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            if(extras.getString(AppConstant.CATEGORY).equalsIgnoreCase("General Knowledge")){
                category="GeneralKnowledge";
            }else {
                category = extras.getString(AppConstant.CATEGORY);
            }
        }
        currentPlayer = ((LightQuiz) getApplication()).player; //gets player from lightquiz application
        updateHighScore();
        /*try {
            loadXmlQuestions();
        } catch (IOException | XmlPullParserException e) {
            e.printStackTrace();
        }*/

        //generator = new QuestionsGenerator();
     /*   SQLiteHelper db=new SQLiteHelper(this,"lq.db");
        try {
            db.createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
        db.openDataBase();
        db.loadQuestions(generator);
        db.close();
        Log.d("LOG MAIN", (String.valueOf(generator.size())));*/

    }

    @Override
    public void onResume() {
        super.onResume();
        ((LightQuiz) this.getApplicationContext()).clearQuestions();
        updateHighScore();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        this.finish();
    }

    public void startGame(View view) {
        switch (view.getId()) {
            case R.id.start_button:
                if(category.equalsIgnoreCase("GeneralKnowledge")){
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
                }
                Intent intent = new Intent(this, PlayGame.class);
                intent.putExtra("Genre", category);
                startActivity(intent);
                break;
            case R.id.other_game_button:
                selecGenre();
                break;
            default:
                throw new RuntimeException("Unknown button ID");
        }

    }

    void selecGenre() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.dialog_title);
        String[] genres = QuestionGenre.names();
        builder.setItems(QuestionGenre.names(), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                beginQuizGame(QuestionGenre.names()[which]);
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    void beginQuizGame(String genre) {
        Intent intent = new Intent(this, PlayGame.class);
        intent.putExtra("Genre", genre);
        startActivity(intent);
        finish();

    }

    private void updateHighScore() {
        TextView highScoreText = (TextView) findViewById(R.id.highscore);
        highScoreText.setText("High Score:  " + currentPlayer.getHighScore());
    }

    /*private void loadXmlQuestions() throws IOException, XmlPullParserException {
        XmlResourceParser xmlq = getResources().getXml(R.xml.questions);
        generator = new QuestionsGenerator(xmlq);
        xmlq.close();
    }*/
}

