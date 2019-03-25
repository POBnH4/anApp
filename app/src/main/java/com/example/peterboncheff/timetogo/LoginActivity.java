package com.example.peterboncheff.timetogo;

import Database.Database;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static android.view.View.*;

public class LoginActivity extends AppCompatActivity implements OnClickListener {

    private EditText username;
    private EditText password;
    private Button login;
    private TextView forgottenPasswordTextView, signUpTextView;
    private Database database = Database.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
    }

    /** Set up all variables method, making the code more compact**/
    private void init(){
        this.username = findViewById(R.id.usernameTextField);
        this.password = findViewById(R.id.passwordTextField);
        this.login = findViewById(R.id.loginButton);
        this.forgottenPasswordTextView = findViewById(R.id.forgottenPasswordTextView);
        this.signUpTextView = findViewById(R.id.signUpTextView);
        this.login.setOnClickListener(this);
        this.signUpTextView.setClickable(true);
        this.signUpTextView.setOnClickListener(this);
    }

    /** Check the user's details whether they match any in the database**/
    private void checkUserDetails(){
        boolean found = false; // if the user is not found;
        for(int i = 0; i < database.accounts.size(); i++){
            if(database.accounts.get(i).getUsername().contentEquals(this.username.getText().toString())
                && database.accounts.get(i).getPassword().contentEquals(this.password.getText().toString())){
                found = true;
                final Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }
        }
        if(!found) {
            final String ACCOUNT_NOT_FOUND_MESSAGE = "Incorrect password or username";
            Toast.makeText(getApplicationContext(), ACCOUNT_NOT_FOUND_MESSAGE, Toast.LENGTH_LONG).show();
        }

    }

    private void signUp(){
        final Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);
    }

    private void setForgottenPassword(){
        //do stuff;
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.loginButton:
                checkUserDetails();
                break;
            case R.id.signUpTextView:
                signUp();
                break;

            default:
                break;
        }
    }

}
