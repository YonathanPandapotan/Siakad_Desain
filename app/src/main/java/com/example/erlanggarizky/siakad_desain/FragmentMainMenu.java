package com.example.erlanggarizky.siakad_desain;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FragmentMainMenu extends Fragment {

    private List<jadwalKuliah> listJadwal = new ArrayList<>();
    private List<tugas> listTugas = new ArrayList<>();
    private RecyclerView recyclerView, recyclerView2;
    private jadwalKuliahAdapater jAdapter;
    private tugasAdapter tugasAdapter;
    private LinearLayout linearLayout;
    private String url_login = "http://192.168.8.104/tugas/getJadwalDanTugas.php";

    TextView tanggal, waktu,nama,nim;
    String waktuSekarang;

    public FragmentMainMenu() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment_main_menu, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        final Activity activ = getActivity();
        nama = getView().findViewById(R.id.namaMaha);
        nama.setText(Preferences.getLoggedInUser(getActivity().getBaseContext()));
        nim = getView().findViewById(R.id.nimMaha);
        nim.setText(Preferences.getNimInUser(getActivity().getBaseContext()));

        waktu = (TextView) getView().findViewById(R.id.waktu);

        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    while (!isInterrupted()) {
                        Thread.sleep(1000);

                        activ.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                waktuSekarang = getCurrentTimeUsingDate();
                                waktu.setText(String.valueOf(waktuSekarang));
                            }
                        });
                    }
                } catch (InterruptedException e) {
                }
            }
        };

        thread.start();

        recyclerView = (RecyclerView) getView().findViewById(R.id.my_recycler_view2);

        jAdapter = new jadwalKuliahAdapater(listJadwal);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext().getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(jAdapter);

        recyclerView2 = (RecyclerView) getView().findViewById(R.id.my_recycler_view);

        tugasAdapter = new tugasAdapter(listTugas);
        RecyclerView.LayoutManager mLayoutManager1 = new LinearLayoutManager(getContext().getApplicationContext());
        recyclerView2.setLayoutManager(mLayoutManager1);
        recyclerView2.setItemAnimator(new DefaultItemAnimator());
        recyclerView2.setAdapter(tugasAdapter);

        first(Preferences.getNimInUser(getActivity().getBaseContext()));

        recyclerView2.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), recyclerView2, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                // ...
                tugas listTugas2 = listTugas.get(position);
                Bundle bundle = new Bundle();
                bundle.putString("namaTugas", listTugas2.getNamaTugas());
                bundle.putString("tanggal", listTugas2.getTanggalKumpul());
                bundle.putString("deskripsi", listTugas2.getDeskripsi());
                // set MyFragment Arguments
                ViewTugasFragment v = new ViewTugasFragment();
                v.setArguments(bundle);
                ((MainMenu)getActivity()).loadFragment(v);
            }

            @Override
            public void onLongItemClick(View view, int position) {
                // ...
            }
        }));

    }

    public static String getCurrentTimeUsingDate() {
        Date currentTime = Calendar.getInstance().getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy \nHH:mm:ss");
        String currentDateandTime = sdf.format(new Date());
        return currentDateandTime;
    }

    private void preparedJadwalData(JSONObject pass){
        try{
        jadwalKuliah jadwalKuliah = new jadwalKuliah(pass.getString("idJadwal"),pass.getString("matkul"),pass.getString("hari"),pass.getString("jamMulai"),pass.getString("jamSelesai"),pass.getString("ruangan"));
        listJadwal.add(jadwalKuliah);
        } catch (JSONException e) {
            Context context = getActivity().getApplicationContext();
            CharSequence text = "Error Chatched : " + e;
            int duration = Toast.LENGTH_LONG;
            Toast toast = Toast.makeText(context, text, duration);
            // Do something to recover ... or kill the app.
        }
    }

    private void preparedTugasData(JSONObject pass){
        try{
            tugas tugas = new tugas (pass.getString("namaTugas"),pass.getString("deskripsi"),pass.getString("tanggalKumpul"),pass.getString("status"));
            listTugas.add(tugas);
        } catch (JSONException e) {
            Context context = getActivity().getApplicationContext();
            CharSequence text = "Error Chatched : " + e;
            int duration = Toast.LENGTH_LONG;
            Toast toast = Toast.makeText(context, text, duration);
            // Do something to recover ... or kill the app.
        }

    }

    private void first(final String user) {
        final Context context = getActivity().getApplicationContext();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url_login, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String success = jsonObject.getString("success");

                    if(success.equals("1")) {
                        JSONArray jsonArray = jsonObject.getJSONArray("jadwal");
                        int len = jsonArray.length();

                        for (int i=0;i<len;i++){
                            preparedJadwalData(jsonArray.getJSONObject(i));
                        }
                        jAdapter.notifyDataSetChanged();
                        JSONArray jsonArray2 = jsonObject.getJSONArray("tugas");
                        int len2 = jsonArray2.length();

                        for (int i=0;i<len2;i++){
                            preparedTugasData(jsonArray2.getJSONObject(i));
                        }
                        tugasAdapter.notifyDataSetChanged();
                    }
                    else {
                        Toast.makeText(context, "Something went wrong", Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "Some Error Occured: "+error, Toast.LENGTH_SHORT).show();
            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("nim", user);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }

}
