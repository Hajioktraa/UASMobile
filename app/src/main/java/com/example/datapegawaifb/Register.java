package com.example.datapegawaifb;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

public class Register extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText etEmail;
    private EditText etPass;
    private EditText etConfPass;
    private Button btnRegis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etEmail = findViewById(R.id.etEmail);
        etPass = findViewById(R.id.etPass);
        etConfPass = findViewById(R.id.etConfPass);
        btnRegis = findViewById(R.id.btnRegis);
        mAuth = FirebaseAuth.getInstance();

        btnRegis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etEmail.getText().toString();
                String password = etPass.getText().toString();
                String ConfirmPass = etConfPass.getText().toString();

                if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Terjadi Kesalahan Saat Pengisian Data!", Toast.LENGTH_LONG).show();
                } else {
                    mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(getApplicationContext(), "Registrasi Berhasil!", Toast.LENGTH_LONG).show();
                                //FirebaseUser user = mAuth.getCurrentUser();
                                Intent go = new Intent(Register.this, Login.class);
                                startActivity(go);
                                finish();
                            } else {
                                Toast.makeText(getApplicationContext(), "Registrasi Gagal!", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
            }
        });
    }
}