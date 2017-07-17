package com.example.tzulu.firstapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;

public class RegisterActivity extends AppCompatActivity {

    private EditText rNames;
    private EditText rEmail;
    private EditText rPassword;
    private EditText rConfirmPassword;
    private Button rButton;

    //Declaring firebase instance
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //Initialising firebaseAuth
        mAuth = FirebaseAuth.getInstance();

        rNames = (EditText) findViewById(R.id.edtNames);
        rEmail = (EditText) findViewById(R.id.edtEmail);
        rPassword = (EditText) findViewById(R.id.edtPassword);
        rConfirmPassword = (EditText) findViewById(R.id.edtConfirmPassword);
        rButton = (Button) findViewById(R.id.btnRegister);

        rButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               String fullname = rNames.getText().toString();
                String email = rEmail.getText().toString();
                String password = rPassword.getText().toString();
                String confirmPassword = rConfirmPassword.getText().toString();

                //registering users
                register_user(fullname,email,password);

            }
        });

    }

    private void register_user(String fullname, String email, String password) {
        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isComplete()){
                    Intent homeIntent = new Intent(RegisterActivity.this, HomeActivity.class);
                    startActivity(homeIntent);
                    finish();
                } else{
                    Toast.makeText(RegisterActivity.this, "You have some error", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
