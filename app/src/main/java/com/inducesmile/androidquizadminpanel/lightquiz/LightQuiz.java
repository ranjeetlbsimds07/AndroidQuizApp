package com.inducesmile.androidquizadminpanel.lightquiz;

import android.app.Application;
import android.database.Cursor;

import java.io.IOException;


/**
 * Created by demiurgosoft - 4/24/15
 */
public class LightQuiz extends Application {
    private final String databaseName = "lq.db";
    private final String databaseQuery = "SELECT * FROM QUESTIONS";
    //"Global" Classes
    public Player player;
    public SoundHandler soundHandler;

    @Override
    public void onCreate() {
        super.onCreate();
        player = new Player(this.getApplicationContext());
        soundHandler = new SoundHandler(this.getApplicationContext());
        //generalKnowledgeList
        AnswerUtility.generalKnowledgeList.clear();
        AnswerUtility.generalKnowledgeList.add("Sindhi");
        AnswerUtility.generalKnowledgeList.add("Sahara");
        AnswerUtility.generalKnowledgeList.add("Russia");
        AnswerUtility.generalKnowledgeList.add("Silver");
        AnswerUtility.generalKnowledgeList.add("Cuttack");

        //entertainmentList
        AnswerUtility.entertainmentList.clear();
        AnswerUtility.entertainmentList.add("Punjab National Bank");
        AnswerUtility.entertainmentList.add("IIT Kharagpur");
        AnswerUtility.entertainmentList.add("George Kurian");
        AnswerUtility.entertainmentList.add("Maharashtra");
        AnswerUtility.entertainmentList.add("West indies");

        //historyList
        AnswerUtility.historyList.clear();
        AnswerUtility.historyList.add("Nagalapur");
        AnswerUtility.historyList.add("Lodi");
        AnswerUtility.historyList.add("Ramabai");
        AnswerUtility.historyList.add("Trukish");


        //sportsList
        AnswerUtility.sportsList.clear();
        AnswerUtility.sportsList.add("India");
        AnswerUtility.sportsList.add("Cricket");
        AnswerUtility.sportsList.add("National Hockey in India");
        AnswerUtility.sportsList.add("Chess");
        AnswerUtility.sportsList.add("France");

        //sportsList
        AnswerUtility.businesstsList.clear();
        AnswerUtility.businesstsList.add("Durga Thakur");
        AnswerUtility.businesstsList.add("Novak Djokovic");
        AnswerUtility.businesstsList.add("Australia");
        AnswerUtility.businesstsList.add("All of the above");
        AnswerUtility.businesstsList.add("Mary Kom");

        //ComputerList
        AnswerUtility.computerList.clear();
        AnswerUtility.computerList.add("World Wide Web");
        AnswerUtility.computerList.add("Arithmetic logic unit Control unit");
        AnswerUtility.computerList.add("Vaccum Tubes and Magnetic Drum");
        AnswerUtility.computerList.add("Mother Board");
        AnswerUtility.computerList.add("Multiprocessor");
    }

    public void loadRawQuestions() throws IOException {
        SQLiteHelper database = new SQLiteHelper(this, databaseName);
        if (!database.openDataBase()) throw new RuntimeException("database not loaded");
        else {
            Cursor cursor = database.query(databaseQuery);
            Question.questionList = new QuestionSet(cursor);
        }
        database.close();
    }

    public void loadRawQuestions(String genre) throws IOException {
        SQLiteHelper database = new SQLiteHelper(this, databaseName);
        if (!database.openDataBase()) throw new RuntimeException("database not loaded");
        else {
            String query = databaseQuery;
            if (genre != null)
                //query = query + " WHERE CATEGORY=\"" + genre.toLowerCase() + "\"";
                query = query + " WHERE CATEGORY=\"" + genre + "\"";
            Cursor cursor = database.query(query);
            Question.questionList = new QuestionSet(cursor);
        }
        database.close();
    }

    public void clearQuestions() {
        if (Question.questionList != null) Question.questionList.clear();
        Question.questionList = null;
    }

}
