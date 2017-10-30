package com.inducesmile.androidquizadminpanel.lightquiz;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.inducesmile.androidquizadminpanel.HomeActivity;
import com.inducesmile.androidquizadminpanel.R;

import java.io.IOException;
import java.util.ArrayList;


public class PlayGame extends ActionBarActivity {
    private final int questionsDelay = 500;
    private final int questionsPoints = 10;
    //private final int questionSeconds = 8;
    private final int questionSeconds = 40;
    private int points;
    private int lives;
    private int correctAnswer;
    //Layout Stuff
    private ImageView correctImg;
    private ImageView wrongImg;
    private ImageView questionImg;
    private TextView questionText;
    private Button soundButton;
    private TextView pointsText;
    private TextView lifeText;
    private TextView countdownText;
    private Button[] answerButtons = new Button[4];

    private CountDownTimer countdown;
    private QuestionsGenerator generator;
    private ProgressDialog progress;

    private String genreSelection = null;
    private boolean gameFinished = false;
    private int questionNumber=0;
    private int correctAns=0;
    private ArrayList<String> arrayListResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_game);

        Intent intent = getIntent();
        genreSelection = intent.getStringExtra("Genre");
        arrayListResult = new ArrayList<>();
        arrayListResult.clear();
        if(genreSelection.equalsIgnoreCase(QuestionGenre.GeneralKnowledge.toString())){
            arrayListResult = AnswerUtility.generalKnowledgeList;
        }

        if(genreSelection.equalsIgnoreCase(QuestionGenre.Entertainment.toString())){
            arrayListResult = AnswerUtility.entertainmentList;
        }

        if(genreSelection.equalsIgnoreCase(QuestionGenre.History.toString())){
            arrayListResult = AnswerUtility.historyList;
        }

        if(genreSelection.equalsIgnoreCase(QuestionGenre.Sports.toString())){
            arrayListResult = AnswerUtility.sportsList;
        }

        if(genreSelection.equalsIgnoreCase(QuestionGenre.Business.toString())){
            arrayListResult = AnswerUtility.businesstsList;
        }

        if(genreSelection.equalsIgnoreCase(QuestionGenre.Computer.toString())){
            arrayListResult = AnswerUtility.computerList;
        }

        if(genreSelection.equalsIgnoreCase(QuestionGenre.GeneralKnowledge.toString())){
            arrayListResult = AnswerUtility.generalKnowledgeList;
        }

        if(genreSelection.equalsIgnoreCase(QuestionGenre.SecondLabel.toString())){
            arrayListResult = AnswerUtility.secondLabelList;
        }


        progress = new ProgressDialog(this);
        progress.setMessage("Loading Database ");
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.setIndeterminate(true);
        progress.show();



        new Thread(new Runnable() {
            public void run() {
                try {
                    loadQuestions();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                loadLayout();


                runOnUiThread(new Runnable() {
                    public void run() {
                        startGame();
                    }
                });
            }
        }).start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_play_game, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;
            case android.R.id.home:
                gameOver();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        countdown.cancel();
        hideQuestionMultimedia();
        this.finish();
        gameFinished = true;
        // gameOver();

    }


    @Override
    public void onBackPressed() {
        // this.finish();
        //gameOver();
    }

    //an answer was clicked
    public void answerClicked(View view) {
        countdown.cancel();
        int answer; //-1 by default
        buttonsActive(false);
        switch (view.getId()) {
            case R.id.answer_1:
                answer = 1;
                //Toast.makeText(PlayGame.this,answerButtons[0].getText().toString(),Toast.LENGTH_SHORT).show();
                if(arrayListResult.contains(answerButtons[0].getText().toString())){
                    correctAns++;
                    correctAnswer();
                }else{
                    correctAns--;
                    wrongAnswer();
                }
                break;
            case R.id.answer_2:
                answer = 2;
                //Toast.makeText(PlayGame.this,answerButtons[1].getText().toString(),Toast.LENGTH_SHORT).show();
                if(arrayListResult.contains(answerButtons[1].getText().toString())){
                    correctAns++;
                    correctAnswer();
                }else{
                    correctAns--;
                    wrongAnswer();
                }
                break;
            case R.id.answer_3:
                answer = 3;
                //Toast.makeText(PlayGame.this,answerButtons[2].getText().toString(),Toast.LENGTH_SHORT).show();
                if(arrayListResult.contains(answerButtons[2].getText().toString())){
                    correctAns++;
                    correctAnswer();
                }else{
                    correctAns--;
                    wrongAnswer();
                }
                break;
            case R.id.answer_4:
                answer = 4;
                //Toast.makeText(PlayGame.this,answerButtons[3].getText().toString(),Toast.LENGTH_SHORT).show();
                if(arrayListResult.contains(answerButtons[3].getText().toString())){
                    correctAns++;
                    correctAnswer();
                }else{
                    correctAns--;
                    wrongAnswer();
                }
                break;
            default:
                throw new RuntimeException("Unknown button ID");
        }
        /*if (correctAnswer == answer) correctAnswer();
        else wrongAnswer();*/
        updateTexts();
        nextQuestion();
    }

    public void soundClick(View view) {
        switch (view.getId()) {
            case R.id.sound_button:
                playSound();
                break;
            default:
                throw new RuntimeException("Unknown button ID");

        }
    }

    //set a new question from generator

    private void playSound() {
        if (!gameFinished)
            ((LightQuiz) this.getApplicationContext()).soundHandler.playQuestionSound();
    }

    private void setQuestion() {
        if (!gameFinished) {
            hideQuestionMultimedia();
            buttonsActive(true);
            Question quest = generator.getQuestion();//get a randomized question

            if (!quest.isValid()) throw new RuntimeException("Invalid Question");
            this.correctAnswer = quest.getCorrectAnswer();
            for (int i = 0; i < 4; i++)
                answerButtons[i].setText(quest.getAnswer(i)); //set questions layout

            hideAnswerImage();
            questionText.setText(quest.getText());
            questionNumber++;
            lifeText.setText("Question:" + questionNumber);
            QuestionType qt = quest.type();
            switch (qt) {
                case TEXT:
                    //showQuestionText(((TextQuestion) quest).text);
                    break;
                case IMAGE:
                    showQuestionImage(((ImageQuestion) quest).image);
                    break;
                case SOUND:
                    showQuestionSound(((SoundQuestion) quest).sound);
                    playSound();
                    break;
                default:
                    throw new RuntimeException("Invalid question type");
            }
            countdown = new CountDownTimer(questionSeconds * 1000, 500) {
                @Override
                public void onTick(long millisUntilFinished) {
                    countdownText.setText("Time: "+Integer.toString((int) (millisUntilFinished / 1000) + 1));
            /* if(millisUntilFinished/1000 == 1) {
                 wrongAnswer();
                 updateTexts();
                 nextQuestion();
             }*/
                }

                @Override
                public void onFinish() {
                    buttonsActive(false);
                    wrongAnswer();
                    updateTexts();
                    nextQuestion();
                }
            }.start();
        }
    }

    private void showQuestionSound(String sound) {
        soundButton.setVisibility(View.VISIBLE);
        soundButton.setClickable(true);
        ((LightQuiz) this.getApplicationContext()).soundHandler.setQuestionSound(sound);
    }

    private void showQuestionImage(String imagename) {
        int resourceId = this.getResources().getIdentifier(imagename, "drawable", this.getPackageName());
        questionImg.setImageResource(resourceId);
        questionImg.setVisibility(View.VISIBLE);
        //questionImg.setMaxHeight();
    }

    private void hideQuestionMultimedia() {
        questionImg.setVisibility(View.INVISIBLE);
        soundButton.setVisibility(View.INVISIBLE);
        ((LightQuiz) this.getApplicationContext()).soundHandler.stopQuestionSound();
        soundButton.setClickable(false);
    }
    //next question after som time
    private void nextQuestion() {
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (generator.size() == 0) gameOver(); //no more questions left
                    else setQuestion();
                }
            }, questionsDelay);
        }

    //What happens when a correct answer was clicked
    private void correctAnswer() {
        points += questionsPoints;
        wrongImg.setVisibility(View.INVISIBLE);
        correctImg.setVisibility(View.VISIBLE);
        ((LightQuiz) this.getApplicationContext()).soundHandler.playCorrectSound();
    }

    //What happens when a wrong answer was clicked
    private void wrongAnswer() {
        correctImg.setVisibility(View.INVISIBLE);
        wrongImg.setVisibility(View.VISIBLE);
        ((LightQuiz) this.getApplicationContext()).soundHandler.playWrongSound();
        lives--;
        //if (lives == 0) gameOver();
    }

    //Hides any answer image (tick or cross)
    private void hideAnswerImage() {
        correctImg.setVisibility(View.INVISIBLE);
        wrongImg.setVisibility(View.INVISIBLE);
    }

    private void buttonsActive(boolean b) {
        for (Button button : answerButtons) {
            button.setClickable(b);
        }
    }

    private void loadLayout() {
        correctImg = (ImageView) findViewById(R.id.correct_img);
        wrongImg = (ImageView) findViewById(R.id.wrong_img);
        pointsText = (TextView) findViewById(R.id.points_text);
        lifeText = (TextView) findViewById(R.id.life_text);
        countdownText = (TextView) findViewById(R.id.countdown_text);
        questionImg = (ImageView) findViewById(R.id.question_image);
        questionText = (TextView) findViewById(R.id.question);
        soundButton = (Button) findViewById(R.id.sound_button);

        answerButtons[0] = (Button) findViewById(R.id.answer_1);
        answerButtons[1] = (Button) findViewById(R.id.answer_2);
        answerButtons[2] = (Button) findViewById(R.id.answer_3);
        answerButtons[3] = (Button) findViewById(R.id.answer_4);

        hideAnswerImage();
        if(HomeActivity.userEmail.equalsIgnoreCase("")){
            pointsText.setVisibility(View.INVISIBLE);
        }
    }

    //Updates life and score texts
    private void updateTexts() {
        //lifeText.setText("Question:" + lives);
        /*if(correctAns > 0) {
            pointsText.setText("Score:" + correctAns * 10);
        }else{
            pointsText.setText("Score:" + 0);
        }*/
        pointsText.setText("Score:" + correctAns * 10);
        countdownText.setText("");
    }

    private void gameOver() {
        //hideQuestionMultimedia();
        gameFinished = true;
        ((LightQuiz) this.getApplicationContext()).soundHandler.stopQuestionSound();
        soundButton.setClickable(false);
        countdown.cancel();
        buttonsActive(false);
        Intent intent = new Intent(this, GameOver.class);
        intent.putExtra("Score", points);
        intent.putExtra("correctAns", correctAns);
        startActivity(intent);
        this.finish();
    }

    //reloads questions from xml (old)
   /* private void reload_questions() throws IOException, XmlPullParserException {
        XmlResourceParser xmlq = getResources().getXml(R.xml.questions);
        LaunchActivity.generator.loadQuestions(xmlq);
        xmlq.close();
    }*/

    //Starts game
    private void startGame() {
        points = 0;
        lives = 3;
        gameFinished = false;
        buttonsActive(true);
        updateTexts();
        setQuestion();
        progress.dismiss();
        Toast toast = Toast.makeText(this, "Quiz is Ready", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
        View fore = findViewById(R.id.foreground);
        fore.setVisibility(View.INVISIBLE);
    }


    private void loadQuestions() throws IOException {
        if (!Question.isQuestionListReady()) {
            ((LightQuiz) this.getApplicationContext()).loadRawQuestions(genreSelection);
        }
        this.generator = new QuestionsGenerator();
    }


}