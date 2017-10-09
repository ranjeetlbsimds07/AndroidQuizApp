package com.inducesmile.androidquizadminpanel;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.inducesmile.androidquizadminpanel.database.DatabaseHelper;
import com.inducesmile.androidquizadminpanel.mail.DataStatic;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = LoginActivity.class.getSimpleName();

    private EditText email, password;

    private TextView forgottenPassword, signInLink;
    private DatabaseHelper databaseHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        email = (EditText)findViewById(R.id.email);
        password = (EditText)findViewById(R.id.password);

        forgottenPassword = (TextView)findViewById(R.id.forgotten_password);
        signInLink = (TextView)findViewById(R.id.link_to_registration);

        databaseHelper = new DatabaseHelper(this);

        forgottenPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent forgotIntent = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
                startActivity(forgotIntent);
            }
        });

        signInLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signInIntent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(signInIntent);
            }
        });

        Button loginButton = (Button)findViewById(R.id.btnLogin);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(email.getText().toString().trim()) && TextUtils.isEmpty(password.getText().toString().trim())){
                    Toast.makeText(LoginActivity.this, "Please Enter User Name and Password", Toast.LENGTH_LONG).show();
                    return;
                }

                if(TextUtils.isEmpty(email.getText().toString().trim())){
                    Toast.makeText(LoginActivity.this, "Please Enter Email Id ", Toast.LENGTH_LONG).show();
                    return;
                }

                if(!DataStatic.emailValidator(email.getText().toString().trim())){
                    Toast.makeText(LoginActivity.this, "Enter Valid Email", Toast.LENGTH_LONG).show();
                    return;
                }
                if(TextUtils.isEmpty(password.getText().toString().trim())){
                    Toast.makeText(LoginActivity.this, "Please Enter Password", Toast.LENGTH_LONG).show();
                    return;
                }
                if (databaseHelper.checkUser(email.getText().toString().trim()
                        , password.getText().toString().trim())) {


                    Intent accountsIntent = new Intent(LoginActivity.this, HomeActivity.class);
                    accountsIntent.putExtra("EMAIL", email.getText().toString().trim());
                    startActivity(accountsIntent);


                } else {
                    // Snack Bar to show success message that record is wrong
                    Toast.makeText(LoginActivity.this, "User Id and Password not correct", Toast.LENGTH_LONG).show();
                }
               /* Intent homeIntent = new Intent(LoginActivity.this, HomeActivity.class);
                startActivity(homeIntent);*/
            }
        });
    }
}
