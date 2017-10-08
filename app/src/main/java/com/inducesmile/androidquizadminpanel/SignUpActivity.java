package com.inducesmile.androidquizadminpanel;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.creativityapps.gmailbackgroundlibrary.BackgroundMail;
import com.inducesmile.androidquizadminpanel.database.DatabaseHelper;
import com.inducesmile.androidquizadminpanel.database.User;
import com.inducesmile.androidquizadminpanel.mail.GMailSender;

public class SignUpActivity extends AppCompatActivity {

    private static final String TAG = SignUpActivity.class.getSimpleName();

    private EditText fullname, email, password;
    private User user;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        fullname = (EditText)findViewById(R.id.name);
        email = (EditText)findViewById(R.id.email);
        password = (EditText)findViewById(R.id.password);
        databaseHelper = new DatabaseHelper(this);
        user = new User();

        TextView linkToLogin = (TextView)findViewById(R.id.link_to_login);
        linkToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent homeIntent = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(homeIntent);
            }
        });

        Button signUpButton = (Button)findViewById(R.id.btnRegister);
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(fullname.getText().toString().trim())){
                    Toast.makeText(SignUpActivity.this, "Enter full Name", Toast.LENGTH_LONG).show();
                    return;
                }

                if(TextUtils.isEmpty(email.getText().toString().trim())){
                    Toast.makeText(SignUpActivity.this, "Enter Email", Toast.LENGTH_LONG).show();
                    return;
                }

                if(TextUtils.isEmpty(password.getText().toString().trim())){
                    Toast.makeText(SignUpActivity.this, "Enter Password", Toast.LENGTH_LONG).show();
                    return;
                }

                if (!databaseHelper.checkUser(email.getText().toString().trim())) {

                    user.setName(fullname.getText().toString().trim());
                    user.setEmail(email.getText().toString().trim());
                    user.setPassword(password.getText().toString().trim());

                    databaseHelper.addUser(user);
                    sendEmail();
                    // Snack Bar to show success message that record saved successfully
                    Toast.makeText(SignUpActivity.this, "Registration Successful", Toast.LENGTH_LONG).show();
                    Intent intentRegister = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(intentRegister);
                    finish();

                } else {
                    // Snack Bar to show error message that record already exists
                    Toast.makeText(SignUpActivity.this, "Email Id Already Exist", Toast.LENGTH_LONG).show();
                }

                /*Intent homeIntent = new Intent(SignUpActivity.this, HomeActivity.class);
                startActivity(homeIntent);*/
            }
        });

    }

    private void sendEmail() {
        /*Intent i = new Intent(Intent.ACTION_SEND);
        //i.setType("message/rfc822");
        i.setType("text/plain");
        i.putExtra(Intent.EXTRA_EMAIL  , new String[]{email.getText().toString().trim()});
        i.putExtra(Intent.EXTRA_SUBJECT, "Quiz Registration");
        i.putExtra(Intent.EXTRA_TEXT   , "Congrats Your Registration has been successfully");
        try {
            startActivity(Intent.createChooser(i, "Send mail..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(SignUpActivity.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
        }*/
       /* runOnUiThread(new Runnable() {
            @Override
            public void run() {
                BackgroundMail.newBuilder(SignUpActivity.this)
                        .withUsername("ranjeetnovo@gmail.com")
                        .withPassword("novo@123")
                        .withMailto(email.getText().toString().trim())
                        .withType(BackgroundMail.TYPE_PLAIN)
                        .withSubject("this is the subject")
                        .withBody("this is the body")
                        .withOnSuccessCallback(new BackgroundMail.OnSuccessCallback() {
                            @Override
                            public void onSuccess() {
                                //do some magic
                            }
                        })
                        .withOnFailCallback(new BackgroundMail.OnFailCallback() {
                            @Override
                            public void onFail() {
                                //do some magic
                            }
                        })
                        .send();
            }
        });*/
        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    GMailSender sender = new GMailSender("ranjeetnovo@gmail.com", "password");
                    sender.sendMail("This is Subject",
                            "This is Body",
                            "user@gmail.com",
                            "user@yahoo.com");
                } catch (Exception e) {
                    Log.e("SendMail", e.getMessage(), e);
                }

            }

        }).start();

    }
}
