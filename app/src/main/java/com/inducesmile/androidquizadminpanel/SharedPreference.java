package com.inducesmile.androidquizadminpanel;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Guest User on 10/26/2017.
 */

public class SharedPreference {

    public static final String PREFS_NAME = "USER_NAME";
    public static final String PREFS_KEY = "USER_NAME_KEY";

    public static final String PREFS_EMAIL = "USER_EMAIL";
    public static final String PREFS_KEY_EMAIL = "USER_EMAIL_KEY";

//GeneralKnowledge
    public static final String GeneralKnowledge= "GeneralKnowledge";
    public static final String GeneralKnowledge_KEY= "GeneralKnowledge_KEY";

//Entertainment
    public static final String Entertainment = "Entertainment";
    public static final String Entertainment_KEY = "Entertainment_KEY";

//History
    public static final String History = "History";
    public static final String History_KEY = "History_KEY";

//Sports
    public static final String Sports = "Sports";
    public static final String Sports_KEY = "Sports_KEY";

//Business
    public static final String Business = "Business";
    public static final String Business_KEY = "Business_KEY";

//Computer
    public static final String Computer = "Computer";
    public static final String Computer_KEY = "Computer_KEY";



	public SharedPreference() {
        super();
    }

    public void userName(Context context, String text) {
        SharedPreferences settings;
        SharedPreferences.Editor editor;

        //settings = PreferenceManager.getDefaultSharedPreferences(context);
        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE); //1
        editor = settings.edit(); //2

        editor.putString(PREFS_KEY, text); //3

        editor.commit(); //4
    }

    public String getUserName(Context context) {
        SharedPreferences settings;
        String text;

        //settings = PreferenceManager.getDefaultSharedPreferences(context);
        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        text = settings.getString(PREFS_KEY, null);
        return text;
    }


    public void userEmail(Context context, String text) {
        SharedPreferences settings;
        SharedPreferences.Editor editor;

        //settings = PreferenceManager.getDefaultSharedPreferences(context);
        settings = context.getSharedPreferences(PREFS_EMAIL, Context.MODE_PRIVATE); //1
        editor = settings.edit(); //2

        editor.putString(PREFS_KEY_EMAIL, text); //3

        editor.commit(); //4
    }

    public String getUserEmail(Context context) {
        SharedPreferences settings;
        String text;

        //settings = PreferenceManager.getDefaultSharedPreferences(context);
        settings = context.getSharedPreferences(PREFS_EMAIL, Context.MODE_PRIVATE);
        text = settings.getString(PREFS_KEY_EMAIL, null);
        return text;
    }
//GeneralKnowledge

    public void setGeneralKnowledge(Context context, String text) {
        SharedPreferences settings;
        SharedPreferences.Editor editor;

        //settings = PreferenceManager.getDefaultSharedPreferences(context);
        settings = context.getSharedPreferences(GeneralKnowledge, Context.MODE_PRIVATE); //1
        editor = settings.edit(); //2

        editor.putString(GeneralKnowledge_KEY, text); //3

        editor.commit(); //4
    }

    public String getGeneralKnowledge(Context context) {
        SharedPreferences settings;
        String text;

        //settings = PreferenceManager.getDefaultSharedPreferences(context);
        settings = context.getSharedPreferences(GeneralKnowledge, Context.MODE_PRIVATE);
        text = settings.getString(GeneralKnowledge_KEY, "0");
        return text;
    }


    //Entertainment

    public void setEntertainment(Context context, String text) {
        SharedPreferences settings;
        SharedPreferences.Editor editor;

        //settings = PreferenceManager.getDefaultSharedPreferences(context);
        settings = context.getSharedPreferences(Entertainment, Context.MODE_PRIVATE); //1
        editor = settings.edit(); //2

        editor.putString(Entertainment_KEY, text); //3

        editor.commit(); //4
    }

    public String getEntertainment(Context context) {
        SharedPreferences settings;
        String text;

        //settings = PreferenceManager.getDefaultSharedPreferences(context);
        settings = context.getSharedPreferences(Entertainment, Context.MODE_PRIVATE);
        text = settings.getString(Entertainment_KEY, "0");
        return text;
    }


    //History

    public void setHistory(Context context, String text) {
        SharedPreferences settings;
        SharedPreferences.Editor editor;

        //settings = PreferenceManager.getDefaultSharedPreferences(context);
        settings = context.getSharedPreferences(History, Context.MODE_PRIVATE); //1
        editor = settings.edit(); //2

        editor.putString(History_KEY, text); //3

        editor.commit(); //4
    }

    public String getHistory(Context context) {
        SharedPreferences settings;
        String text;

        //settings = PreferenceManager.getDefaultSharedPreferences(context);
        settings = context.getSharedPreferences(History, Context.MODE_PRIVATE);
        text = settings.getString(History_KEY, "0");
        return text;
    }

    //Sports

    public void setSports(Context context, String text) {
        SharedPreferences settings;
        SharedPreferences.Editor editor;

        //settings = PreferenceManager.getDefaultSharedPreferences(context);
        settings = context.getSharedPreferences(Sports, Context.MODE_PRIVATE); //1
        editor = settings.edit(); //2

        editor.putString(Sports_KEY, text); //3

        editor.commit(); //4
    }

    public String getSports(Context context) {
        SharedPreferences settings;
        String text;

        //settings = PreferenceManager.getDefaultSharedPreferences(context);
        settings = context.getSharedPreferences(Sports, Context.MODE_PRIVATE);
        text = settings.getString(Sports_KEY, "0");
        return text;
    }

//Business

    public void setBusiness(Context context, String text) {
        SharedPreferences settings;
        SharedPreferences.Editor editor;

        //settings = PreferenceManager.getDefaultSharedPreferences(context);
        settings = context.getSharedPreferences(Business, Context.MODE_PRIVATE); //1
        editor = settings.edit(); //2

        editor.putString(Business_KEY, text); //3

        editor.commit(); //4
    }

    public String getBusiness(Context context) {
        SharedPreferences settings;
        String text;

        //settings = PreferenceManager.getDefaultSharedPreferences(context);
        settings = context.getSharedPreferences(Business, Context.MODE_PRIVATE);
        text = settings.getString(Business_KEY, "0");
        return text;
    }

    //Computer

    public void setComputer(Context context, String text) {
        SharedPreferences settings;
        SharedPreferences.Editor editor;

        //settings = PreferenceManager.getDefaultSharedPreferences(context);
        settings = context.getSharedPreferences(Computer, Context.MODE_PRIVATE); //1
        editor = settings.edit(); //2

        editor.putString(Computer_KEY, text); //3

        editor.commit(); //4
    }

    public String getComputer(Context context) {
        SharedPreferences settings;
        String text;

        //settings = PreferenceManager.getDefaultSharedPreferences(context);
        settings = context.getSharedPreferences(Computer, Context.MODE_PRIVATE);
        text = settings.getString(Computer_KEY, "0");
        return text;
    }

   /* public void clearSharedPreference(Context context) {
        SharedPreferences settings;
        SharedPreferences.Editor editor;

        //settings = PreferenceManager.getDefaultSharedPreferences(context);
        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        editor = settings.edit();

        editor.clear();
        editor.commit();
    }

    public void removeValue(Context context) {
        SharedPreferences settings;
        SharedPreferences.Editor editor;

        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        editor = settings.edit();

        editor.remove(PREFS_KEY);
        editor.commit();
    }*/
}