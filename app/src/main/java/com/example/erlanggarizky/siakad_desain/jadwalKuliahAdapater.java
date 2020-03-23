package com.example.erlanggarizky.siakad_desain;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class jadwalKuliahAdapater extends RecyclerView.Adapter<jadwalKuliahAdapater.MyViewHolder> {

    private List<jadwalKuliah> jadwalList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView idJawdal, namaMatkul, hari, jamMulai, jamSelesai, ruangan;

        public MyViewHolder(View view) {
            super(view);
            idJawdal = (TextView) view.findViewById(R.id.idJadwal);
            namaMatkul = (TextView) view.findViewById(R.id.namaMatkul);
            hari = (TextView) view.findViewById(R.id.hari);
            jamMulai= (TextView) view.findViewById(R.id.jamMulai);
            jamSelesai= (TextView) view.findViewById(R.id.jamSelesai);
            ruangan= (TextView) view.findViewById(R.id.ruangan);
        }
    }

    public jadwalKuliahAdapater(List<jadwalKuliah> jadwalList) {
        this.jadwalList = jadwalList;
    }

    @Override
    public jadwalKuliahAdapater.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.jadwal_kuliah_list_row, parent, false);

        return new jadwalKuliahAdapater.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(jadwalKuliahAdapater.MyViewHolder holder, int position) {
        jadwalKuliah jadwal = jadwalList.get(position);
        holder.idJawdal.setText(jadwal.getIdJawdal());
        holder.namaMatkul.setText(jadwal.getNamaMatkul());
        holder.hari.setText(jadwal.getHari());
        holder.jamMulai.setText(jadwal.getJamMulai());
        holder.jamSelesai.setText(jadwal.getJamSelesai());
        holder.ruangan.setText(jadwal.getRuangan());

    }

    @Override
    public int getItemCount() {
        return jadwalList.size();
    }

}
