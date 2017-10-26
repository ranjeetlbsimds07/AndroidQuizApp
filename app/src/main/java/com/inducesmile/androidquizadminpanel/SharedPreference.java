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