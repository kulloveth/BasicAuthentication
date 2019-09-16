package com.developer.kulloveth.hngsixauthtask;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    EditText username, email, passwordOne, passwordTwo;
    TextView register, login;

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String USER_NAME = "userName";
    public static final String EMAIL = "email";
    public static final String PASSWORD = "password";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username = findViewById(R.id.username);
        email = findViewById(R.id.email);
        passwordOne = findViewById(R.id.password_one);
        passwordTwo = findViewById(R.id.password_two);
        register = findViewById(R.id.register_text);
        login = findViewById(R.id.login);
        signUp();
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);


            }
        });

    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    public void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(USER_NAME, username.getText().toString());
        editor.putString(EMAIL, email.getText().toString());
        editor.apply();
    }

    public void signUp() {
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (passwordOne.getText().toString().equals(passwordTwo.getText().toString())) {
                    saveData();
                    SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
                    String getUsername = sharedPreferences.getString(USER_NAME, "");
                    String getEmail = sharedPreferences.getString(EMAIL, "");
                    Bundle bundle = new Bundle();
                    bundle.putString("user", getUsername);
                    bundle.putString("Email", getEmail);
                    bundle.putString("pass", passwordOne.getText().toString());
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    intent.putExtras(bundle);
                    startActivity(intent);
                    Toast.makeText(MainActivity.this, "Registered successfully", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MainActivity.this, "password fields dont match", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
