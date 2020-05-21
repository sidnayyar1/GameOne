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

public class MainActivity extends AppCompatActivity {

    public EditText editextemail,edittextpassword;
    Button login;
    TextView tvsignin;
    FirebaseAuth mfirebaseauth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

mfirebaseauth = FirebaseAuth.getInstance();
editextemail = findViewById(R.id.edittextemail);
edittextpassword = findViewById(R.id.edittextpassword);
tvsignin = findViewById(R.id.tvsignin);
login = findViewById(R.id.btnlogin);

login.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        String Email = editextemail.getText().toString();
        String password = edittextpassword.getText().toString();
    if (Email.isEmpty()){
        editextemail.setError("please enter the email");
        editextemail.requestFocus();

        } else if (password.isEmpty()){
        edittextpassword.setError("please enter password");
        edittextpassword.requestFocus();

        }else if(Email.isEmpty() & password.isEmpty()){
        Toast.makeText(MainActivity.this,"fields are empty",Toast.LENGTH_LONG);
    }
    else if(!Email.isEmpty() && password.isEmpty()){
        mfirebaseauth.createUserWithEmailAndPassword(Email,password).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (!task.isSuccessful()){
                    Toast.makeText(MainActivity.this,"login unsuccesful.please try again later",Toast.LENGTH_LONG);
                }else {
                    startActivity(new Intent(MainActivity.this,HomeActivity.class));
                }
            }
        });
    }
    else {
        Toast.makeText(MainActivity.this,"Error ocured",Toast.LENGTH_LONG);
    }
    }
});
tvsignin.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent i= new Intent(MainActivity.this,LoginActivity.class);
        startActivity(i) ;
    }
});

    }
}
