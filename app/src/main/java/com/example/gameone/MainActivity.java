package com.example.gameone;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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
        
    }
});

    }
}
