package com.example.datapegawaifb;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText etEmail;
    private EditText etPass;
    private Button btnLogin;
    private TextView tvRegis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmail = findViewById(R.id.etEmail);
        etPass = findViewById(R.id.etPass);
        btnLogin = findViewById(R.id.btnLogin);
        tvRegis = findViewById(R.id.tvRegis);
        mAuth = FirebaseAuth.getInstance();

        tvRegis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Register.class);
                startActivity(intent);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etEmail.getText().toString();
                String password = etPass.getText().toString();

                if ((TextUtils.isEmpty(email) || TextUtils.isEmpty(password))){
                    Toast.makeText(getApplicationContext(), "Harap Isi Data Dengan Lengkap", Toast.LENGTH_LONG).show();
                } else {
                    mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){

                                Toast.makeText(getApplicationContext(), "Login Berhasil!", Toast.LENGTH_LONG).show();
                                Intent main = new Intent(Login.this, ListData.class);
                                startActivity(main);
                                finish();
                            }else{
                                Toast.makeText(getApplicationContext(), "Login Gagal!", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
            }
        });

    }
}
