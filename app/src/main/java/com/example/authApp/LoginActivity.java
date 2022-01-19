package com.example.authApp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    EditText etLogEmail;
    EditText etLogPass;
    Button btnLogin;
    TextView tvRegister;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etLogEmail = findViewById(R.id.etLogEmail);
        etLogPass = findViewById(R.id.etLogPass);
        btnLogin = findViewById(R.id.btnLogin);
        tvRegister = findViewById(R.id.tvRegister);

        mAuth = FirebaseAuth.getInstance();
    }

    public void btnLoginClick(View v){
        loginUser();
    }

    public void tvRegisterClick(View v){
        startActivity(new Intent(LoginActivity.this, RegisterActivity.class));

    }

    public void loginUser(){
        String email = etLogEmail.getText().toString();
        String password = etLogPass.getText().toString();

        if(TextUtils.isEmpty(email)){
            etLogEmail.setError("El email no puede estar vacío.");
            etLogEmail.requestFocus();
        }else if(TextUtils.isEmpty(email)) {
            etLogPass.setError("La contraseña no puede estar vacía.");
            etLogPass.requestFocus();
        }else{
            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(LoginActivity.this, "Usuario entra correctamente.", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    }else{
                        Toast.makeText(LoginActivity.this, "Error al entrar: "+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}