package com.example.farmbetter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    private Button CustomerLogin;
    private Button CustomerRegister;
    private EditText CustomerEmail;
    private EditText CustomerPassword;
    private ProgressDialog loadingBar;
    private FirebaseAuth mAuth;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth=FirebaseAuth.getInstance();
        CustomerRegister=(Button) findViewById(R.id.butt);
        CustomerLogin=(Button)findViewById(R.id.btnLogin);
        CustomerEmail=(EditText)findViewById(R.id.email);
        CustomerPassword=(EditText)findViewById(R.id.password);
        loadingBar=new ProgressDialog(this);

        CustomerRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email=CustomerEmail.getText().toString();
                String password=CustomerPassword.getText().toString();
                CustomerRegister(email,password);
            }
        });

        CustomerLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=CustomerEmail.getText().toString();
                String password=CustomerPassword.getText().toString();

                CustomerLogin(email,password);
            }
        });






        }

    private void CustomerLogin(String email, String password) {


        if(TextUtils.isEmpty(email)){
            Toast.makeText(RegisterActivity.this,"Please write email",Toast.LENGTH_SHORT).show();

        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(RegisterActivity.this,"Please write password",Toast.LENGTH_SHORT).show();

        }

        else {
            loadingBar.setTitle("Customer SignIn");
            loadingBar.setMessage("Please wait");
            loadingBar.show();


            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(RegisterActivity.this," Customer Logged In",Toast.LENGTH_SHORT).show();
                        loadingBar.dismiss();
                        Intent CustomerIntent= new Intent(RegisterActivity.this,ProductActivity.class);
                        startActivity(CustomerIntent);
                    }

                    else {
                        Toast.makeText(RegisterActivity.this," Unsuccessful",Toast.LENGTH_SHORT).show();
                        loadingBar.dismiss();
                    }


                }
            });




        }


    }

    private void CustomerRegister(String email, String password) {

        if(TextUtils.isEmpty(email)){
            Toast.makeText(RegisterActivity.this,"Please write email",Toast.LENGTH_SHORT).show();

        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(RegisterActivity.this,"Please write password",Toast.LENGTH_SHORT).show();

        }

        else {
            loadingBar.setTitle("Registration");
            loadingBar.setMessage("Please wait");
            loadingBar.show();


            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(RegisterActivity.this," Customer Registered",Toast.LENGTH_SHORT).show();
                        loadingBar.dismiss();
                        Intent CustomerIntent= new Intent(RegisterActivity.this,ProductActivity.class);
                        startActivity(CustomerIntent);

                    }

                    else {
                        Toast.makeText(RegisterActivity.this,"Registration Unsuccessful",Toast.LENGTH_SHORT).show();
                        loadingBar.dismiss();
                    }


                }
            });




        }

    }
}

