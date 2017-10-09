package com.inducesmile.androidquizadminpanel;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.inducesmile.androidquizadminpanel.database.DatabaseHelper;
import com.inducesmile.androidquizadminpanel.database.User;
import com.inducesmile.androidquizadminpanel.mail.Config;
import com.inducesmile.androidquizadminpanel.mail.DataStatic;
import com.inducesmile.androidquizadminpanel.mail.SendMailTask;

import java.util.List;

/**
 * Created by Guest User on 10/9/2017.
 */

public class ForgotPasswordActivity  extends AppCompatActivity {

    private static final String TAG = LoginActivity.class.getSimpleName();

    private EditText email;
    private DatabaseHelper databaseHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        email = (EditText)findViewById(R.id.email);


        databaseHelper = new DatabaseHelper(this);


        Button loginButton = (Button)findViewById(R.id.btnLogin);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!DataStatic.emailValidator(email.getText().toString().trim())){
                    Toast.makeText(ForgotPasswordActivity.this, "Enter Valid Email", Toast.LENGTH_LONG).show();
                    return;
                }else {
                    List<User> userList = databaseHelper.checkUserEmail(email.getText().toString().trim());
                    if(userList !=null && userList.size() > 0) {
                        userList.get(0);
                        new SendMailTask(ForgotPasswordActivity.this).execute(Config.EMAIL,
                                Config.PASSWORD, email.getText().toString().trim(), "Quiz App Forgot Password", "User Details, user name: "+userList.get(0).getName()+", user email: "+userList.get(0).getEmail()+", user password: "+userList.get(0).getPassword());
                        Toast.makeText(ForgotPasswordActivity.this, "Sent details on your email.Please check your email", Toast.LENGTH_LONG).show();
                        Intent accountsIntent = new Intent(ForgotPasswordActivity.this, LoginActivity.class);
                        startActivity(accountsIntent);
                    }else{
                        Toast.makeText(ForgotPasswordActivity.this, "User Email does not exist", Toast.LENGTH_LONG).show();
                    }

                }

            }
        });
    }
}

