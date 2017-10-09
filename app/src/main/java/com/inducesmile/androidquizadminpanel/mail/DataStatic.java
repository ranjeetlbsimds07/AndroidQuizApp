package com.inducesmile.androidquizadminpanel.mail;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Guest User on 10/9/2017.
 */

public class DataStatic {
    // static method
    public static boolean emailValidator(String email) {
        Pattern pattern;
        Matcher matcher;
        final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
