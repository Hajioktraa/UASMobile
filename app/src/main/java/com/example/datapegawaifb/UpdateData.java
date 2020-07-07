package com.example.datapegawaifb;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static android.text.TextUtils.isEmpty;

public class UpdateData extends AppCompatActivity {

    private EditText nipBaru, namaBaru, jabatanBaru;
    private Button update;
    private DatabaseReference database;
    private FirebaseAuth auth;
    private String cekNIP, cekNama, cekJabatan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_data);

        getSupportActionBar().setTitle("Update Data");
        nipBaru = findViewById(R.id.new_nip);
        namaBaru = findViewById(R.id.new_nama);
        jabatanBaru = findViewById(R.id.new_jabatan);
        update = findViewById(R.id.btnUpdate);

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance().getReference();
        getData();

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Mendapatkan Data Mahasiswa yang akan dicek
                cekNIP = nipBaru.getText().toString();
                cekNama = namaBaru.getText().toString();
                cekJabatan = jabatanBaru.getText().toString();

                //Mengecek agar tidak ada data yang kosong, saat proses update
                if(isEmpty(cekNIP) || isEmpty(cekNama) || isEmpty(cekJabatan)){
                    Toast.makeText(UpdateData.this, "Data tidak boleh ada yang kosong", Toast.LENGTH_SHORT).show();
                }else {
                    //Menjalankan proses update data
                    data_pegawai setPegawai = new data_pegawai();
                    setPegawai.setNip(nipBaru.getText().toString());
                    setPegawai.setNama(namaBaru.getText().toString());
                    setPegawai.setJabatan(jabatanBaru.getText().toString());
                    updatePegawai(setPegawai);
                }
            }
        });
    }

    private boolean isEmpty(String s){
        return TextUtils.isEmpty(s);
    }

    private void getData(){
        final String getNIP = getIntent().getExtras().getString("dataNIP");
        final String getNama = getIntent().getExtras().getString("dataNama");
        final String getJabatan = getIntent().getExtras().getString("dataJabatan");
        nipBaru.setText(getNIP);
        namaBaru.setText(getNama);
        jabatanBaru.setText(getJabatan);
    }

    private void updatePegawai(data_pegawai pegawai){
        String userID = auth.getUid();
        String getKey = getIntent().getExtras().getString("getPrimaryKey");
        database.child("Pegawai")
                .child(userID)
                .child(getKey)
                .setValue(pegawai)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        nipBaru.setText("");
                        namaBaru.setText("");
                        jabatanBaru.setText("");
                        Toast.makeText(UpdateData.this, "Data Berhasil diubah", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
    }
}
