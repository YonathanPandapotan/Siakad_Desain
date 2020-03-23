package com.example.erlanggarizky.siakad_desain;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class tugasAdapter extends RecyclerView.Adapter<tugasAdapter.MyViewHolder>  {

    private List<tugas> jadwalList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView namaTugas, deskripsi, tanggalKumpul, status;

        public MyViewHolder(View view) {
            super(view);
            namaTugas = (TextView) view.findViewById(R.id.namaTugas);
            deskripsi = (TextView) view.findViewById(R.id.deskripsi);
            tanggalKumpul = (TextView) view.findViewById(R.id.tanggalKumpul);
        }
    }

    public tugasAdapter(List<tugas> jadwalList) {
        this.jadwalList = jadwalList;
    }

    @Override
    public tugasAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.tugas_list_row, parent, false);

        return new tugasAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(tugasAdapter.MyViewHolder holder, int position) {
        tugas jadwal = jadwalList.get(position);
        holder.namaTugas.setText(jadwal.getNamaTugas());
        holder.deskripsi.setText(jadwal.getDeskripsi());
        holder.tanggalKumpul.setText(jadwal.getTanggalKumpul());
    }

    @Override
    public int getItemCount() {
        return jadwalList.size();
    }

    public tugas getItem(int position) {
        return jadwalList.get(position);
    }

}
