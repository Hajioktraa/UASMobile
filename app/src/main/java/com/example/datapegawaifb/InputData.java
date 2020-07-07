package com.example.datapegawaifb;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class InputData extends AppCompatActivity {

   private  EditText etnip;
    private EditText etnama;
    private EditText etjabatan;
    private Button btnsubmit;

    private data_pegawai datapegawai;

    private DatabaseReference database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_data);

        database = FirebaseDatabase.getInstance().getReference();

        etnip = findViewById(R.id.etNIP);
        etnama = findViewById(R.id.etNama);
        etjabatan = findViewById(R.id.etJabatan);
        btnsubmit = findViewById(R.id.btnSubmit);

        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nip = etnip.getText().toString();
                String nama = etnama.getText().toString();
                String jabatan = etjabatan.getText().toString();


                if (TextUtils.isEmpty(nip) ||TextUtils.isEmpty(nama) || TextUtils.isEmpty(jabatan)) {
                    Toast.makeText(getApplicationContext(), "Data Tidak Boleh Kosong!", Toast.LENGTH_LONG).show();
                } else {
                    database.child("Pegawai").push().setValue(new data_pegawai(nip, nama, jabatan)).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            etnip.setText("");
                            etnama.setText("");
                            etjabatan.setText("");
                            Toast.makeText(getApplicationContext(), "Data Berhasil Di Masukan!", Toast.LENGTH_LONG).show();
                            Intent main = new Intent(InputData.this, ListData.class);
                            startActivity(main);
                            finish();
                        }
                    });
                }
            }
        });
    }
}