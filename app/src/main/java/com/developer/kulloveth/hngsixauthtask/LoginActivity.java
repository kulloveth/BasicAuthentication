package com.developer.kulloveth.hngsixauthtask;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    EditText username, password;
    TextView login, register;
    String user, pass, email;
    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password_one);
        login = findViewById(R.id.sign_in);
        register = findViewById(R.id.register);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        bundle = getIntent().getExtras();
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        signIn();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    public void signIn() {
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (bundle != null) {
                    String registeredUser = bundle.getString("user");
                    String userEmail = bundle.getString("Email");
                    String userPassword = bundle.getString("pass");
                    String getUser = username.getText().toString();
                    String getPass = password.getText().toString();
                    if (getUser.equals(registeredUser) || getUser.equals(userEmail) && getPass.equals(userPassword)) {
                        Bundle bundle = new Bundle();
                        bundle.putString("email", userEmail);
                        bundle.putString("user", registeredUser);
                        Intent intent = new Intent(LoginActivity.this, DashBoardActivity.class);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    } else {
                        Toast.makeText(LoginActivity.this, "Incorrect username or password", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

}
