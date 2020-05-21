package com.example.gameone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    public EditText editextemail, edittextpassword;
    Button login;
    TextView tvsignin;
    FirebaseAuth mfirebaseauth;
    private FirebaseAuth.AuthStateListener mAuthStateListeniner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        mfirebaseauth = FirebaseAuth.getInstance();
        editextemail = findViewById(R.id.edittextemail);
        edittextpassword = findViewById(R.id.edittextpassword);
        tvsignin = findViewById(R.id.tvsignin);
        login = findViewById(R.id.btnlogin);

        mAuthStateListeniner = new FirebaseAuth.AuthStateListener() {
            FirebaseUser mfirebaseUser = mfirebaseauth.getCurrentUser();

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                if (mfirebaseUser != null) {
                    Toast.makeText(LoginActivity.this, "You are logged in", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(LoginActivity.this, HomeActivity.class);
                    startActivity(i);
                } else {
                    Toast.makeText(LoginActivity.this, "Please Login", Toast.LENGTH_LONG).show();
                }
            }
        };
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Email = editextemail.getText().toString();
                String password = edittextpassword.getText().toString();
                if (Email.isEmpty()) {
                    editextemail.setError("please enter the email");
                    editextemail.requestFocus();

                } else if (password.isEmpty()) {
                    edittextpassword.setError("please enter password");
                    edittextpassword.requestFocus();

                } else if (Email.isEmpty() & password.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "fields are empty", Toast.LENGTH_LONG);
                } else if (!Email.isEmpty() && password.isEmpty()) {
                    mfirebaseauth.signInWithEmailAndPassword(Email, password).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                Toast.makeText(LoginActivity.this, "login unsuccesful please try again", Toast.LENGTH_LONG);
                            } else {
                                Intent i = new Intent(LoginActivity.this, HomeActivity.class);
                                startActivity(i);
                            }
                        }
                    });
                } else {
                    Toast.makeText(LoginActivity.this, "login unsuccesful please try again", Toast.LENGTH_LONG);
                }
            }
        });
    }
}