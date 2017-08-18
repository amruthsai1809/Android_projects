package com.example.sunil.inclass11;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private String email, password;
    private EditText emailTxt, passwordTxt;
    private Button signInBtn, signupBtn;
    private ProgressDialog progressDialogue;
    private FirebaseAuth firebaseAuthentication;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressDialogue = new ProgressDialog(this);

        firebaseAuthentication = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = firebaseAuthentication.getCurrentUser();

        if(firebaseUser != null)
            startActivity(new Intent(this, HomeActivity.class));

        emailTxt = (EditText) findViewById(R.id.emailInput);
        passwordTxt = (EditText) findViewById(R.id.password1Input);
        signInBtn = (Button) findViewById(R.id.loginBtn);
        signupBtn = (Button) findViewById(R.id.signupBtn);

        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!validInput())
                    Toast.makeText(MainActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                else
                login(email, password);


            }
        });
        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), RegistrationActivity.class));

            }
        });
    }

    private void login(String e, String p) {
        progressDialogue.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialogue.show();
        progressDialogue.setMessage("Logging in...");
        firebaseAuthentication.signInWithEmailAndPassword(e, p)
                .addOnFailureListener(this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressDialogue.dismiss();
                        Toast.makeText(MainActivity.this, "Error while logging in", Toast.LENGTH_SHORT).show();
                        return;
                    }
                })
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialogue.dismiss();
                        if(task.isSuccessful());
                            startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                    }
                });
    }

    private boolean validInput() {
        email = emailTxt.getText().toString().trim();
        password = passwordTxt.getText().toString().trim();
        if(TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Input invalid", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
