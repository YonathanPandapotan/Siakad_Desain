package com.example.erlanggarizky.siakad_desain;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class ViewTugasFragment extends Fragment {
    private String namaTugas, tanggal, deskripsi;

    public ViewTugasFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.namaTugas = getArguments().getString("namaTugas");
        this.tanggal= getArguments().getString("tanggal");
        this.deskripsi= getArguments().getString("deskripsi");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_view_tugas, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        TextView namaTugas, tanggal, deskripsi;

        namaTugas = getView().findViewById(R.id.namaTugas);
        namaTugas.setText(this.namaTugas);
        tanggal = getView().findViewById(R.id.tanggal);
        tanggal.setText(this.tanggal);
        deskripsi = getView().findViewById(R.id.deskripsi);
        deskripsi.setText(this.deskripsi);
    }
}

