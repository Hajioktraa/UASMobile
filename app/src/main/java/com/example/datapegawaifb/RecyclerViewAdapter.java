package com.example.datapegawaifb;

import android.content.Context;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>
{

    private ArrayList<data_pegawai> listPegawai;
    private Context context;


    public RecyclerViewAdapter(ArrayList<data_pegawai> listPegawai, Context context) {
        this.listPegawai = listPegawai;
        this.context = context;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView NIP, Nama, Jabatan;
        private LinearLayout ListItem;

        ViewHolder(View itemView) {
            super(itemView);
            NIP = itemView.findViewById(R.id.nip);
            Nama = itemView.findViewById(R.id.nama);
            Jabatan = itemView.findViewById(R.id.jabatan);
            ListItem = itemView.findViewById(R.id.list_item);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View V = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_view_data, parent, false);
        return new ViewHolder(V);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final String NIP = listPegawai.get(position).getNip();
        final String Nama = listPegawai.get(position).getNama();
        final String Jabatan = listPegawai.get(position).getJabatan();

        holder.NIP.setText("NIP : " + NIP);
        holder.Nama.setText("Nama : " + Nama);
        holder.Jabatan.setText("Jabatan : " + Jabatan);


        holder.ListItem.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(final View v) {
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return listPegawai.size();
    }

}
