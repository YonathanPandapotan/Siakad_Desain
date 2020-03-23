package com.example.erlanggarizky.siakad_desain;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class UserProfileFragment extends Fragment {

    TextView nama,txt_email,txt_fakultas,txt_jurusan,txt_nim,txt_jenis_kelamin,txt_alamat,txt_nohp,txt_ipk,txt_tanggal;

    public UserProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_profile, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        nama = getView().findViewById(R.id.usernameout);
        txt_nim= getView().findViewById(R.id.Nimc);
        txt_email= getView().findViewById(R.id.emailc);
        txt_fakultas= getView().findViewById(R.id.fakultasc);
        txt_jurusan= getView().findViewById(R.id.Jurusanc);
        txt_alamat= getView().findViewById(R.id.alamatc);
        txt_nohp= getView().findViewById(R.id.nohpc);
        txt_ipk= getView().findViewById(R.id.ipkpc);
        txt_tanggal= getView().findViewById(R.id.tanggallahirc);
        txt_jenis_kelamin= getView().findViewById(R.id.jenis_kelaminc);

        nama.setText(Preferences.getLoggedInUser(getActivity().getBaseContext()));
        txt_email.setText(Preferences.getEmailInUser(getActivity().getBaseContext()));
        txt_nim.setText(Preferences.getNimInUser(getActivity().getBaseContext()));
        txt_fakultas.setText(Preferences.getFakultasInUser(getActivity().getBaseContext()));
        txt_jurusan.setText(Preferences.getJurusanInUser(getActivity().getBaseContext()));
        txt_alamat.setText(Preferences.getAlamatInUser(getActivity().getBaseContext()));
        txt_nohp.setText(Preferences.getNohpInUser(getActivity().getBaseContext()));
        txt_ipk.setText(Preferences.getipkInUser(getActivity().getBaseContext()));
        txt_tanggal.setText(Preferences.gettanggalInUser(getActivity().getBaseContext()));
        txt_jenis_kelamin.setText(Preferences.getjeniskelaminInUser(getActivity().getBaseContext()));

        downloadGambar();
    }

    public void downloadGambar(){
        
    }



}
