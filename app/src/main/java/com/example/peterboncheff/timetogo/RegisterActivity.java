package com.example.peterboncheff.timetogo;

import Database.Database;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static android.view.View.*;

public class RegisterActivity extends AppCompatActivity {

    private EditText username, password, confirmPassword, email;
    private Button registerButton, cancelRegistrationButton;
    private Database database = Database.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        init();
        //TODO add shared preferences...;
    }

    private void init(){
        this.username = findViewById(R.id.usernameField);
        this.password = findViewById(R.id.passwordField);
        this.confirmPassword = findViewById(R.id.confirmPasswordField);
        this.email = findViewById(R.id.emailField);
        this.registerButton = findViewById(R.id.registerButton);
        this.cancelRegistrationButton = findViewById(R.id.cancelRegisterButton);
        this.registerButton.setOnClickListener(new RegisterButton());
        this.cancelRegistrationButton.setOnClickListener(new CancelRegistrationButton());
    }

    class RegisterButton implements OnClickListener{
        @Override
        public void onClick(View v) {
            if(v.getId() == registerButton.getId()){
                // TODO: Test whether flag is needed at all
                boolean flag = false; // if user with the same details is found;
                for(int i = 0; i < database.accounts.size(); i++){
                    if(database.accounts.get(i).getUsername().contentEquals(username.getText().toString())
                            && database.accounts.get(i).getPassword().contentEquals(password.getText().toString())){
                        flag = true;
                        final Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                }
                if(!flag) {
                    final String ACCOUNT_FOUND_MESSAGE = "Someone else already uses these credentials";
                    Toast.makeText(getApplicationContext(), ACCOUNT_FOUND_MESSAGE, Toast.LENGTH_LONG).show();
                }
            }

        }
    }

    private boolean checkPassword(){
        // TODO check whether the password consists of
        //  FOR EXAMPLE:
        //  at least 10 characters,
        //  1 capital letter,
        //  1 lowercase letter,
        //  etc
        return true;
    }

    class CancelRegistrationButton implements OnClickListener{
        @Override
        public void onClick(View v) {
            if(v.getId() == cancelRegistrationButton.getId()) {
                final Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        }
    }




}
