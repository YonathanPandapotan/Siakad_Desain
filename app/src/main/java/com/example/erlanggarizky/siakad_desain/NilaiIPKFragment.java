package com.example.erlanggarizky.siakad_desain;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class NilaiIPKFragment extends Fragment {

    classPerhitungan hitung;
    EditText[] textSemester1 = new EditText[8];
    EditText nilaiIpk;
    Button btnHitung, btnReset;
    private String url_login = "http://192.168.8.102/tugas/getNilai.php";
    ArrayList<Double> nilaiIP = new ArrayList<Double>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_nilai_ipk, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        hitung = new classPerhitungan();
        initiateNilaiSemester();

        first(Preferences.getNimInUser(getActivity().getBaseContext()));

        btnHitung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double[] nilaiIps = new double[8];

                nilaiIps[0] = Double.parseDouble(textSemester1[0].getText().toString());
                nilaiIps[1] = Double.parseDouble(textSemester1[1].getText().toString());
                nilaiIps[2] = Double.parseDouble(textSemester1[2].getText().toString());
                nilaiIps[3] = Double.parseDouble(textSemester1[3].getText().toString());
                nilaiIps[4] = Double.parseDouble(textSemester1[4].getText().toString());
                nilaiIps[5] = Double.parseDouble(textSemester1[5].getText().toString());
                nilaiIps[6] = Double.parseDouble(textSemester1[6].getText().toString());
                nilaiIps[7] = Double.parseDouble(textSemester1[7].getText().toString());

                for(int i=0; i<nilaiIps.length ;i++){
                    if(nilaiIps[i]>4.0){
                        Context context = getActivity().getApplicationContext();
                        CharSequence text = "Terdapat Nilai yang lebih besar dari 4";
                        int duration = Toast.LENGTH_SHORT;

                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                        return;
                    }
                }

                double ipk = Double.parseDouble(nilaiIpk.getText().toString());
                if(ipk>4.0){
                    Context context = getActivity().getApplicationContext();
                    CharSequence text = "Terdapat nilai Ipk yang melewati batas";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                    return;
                }

                nilaiIps = hitung.hitungIPS(nilaiIps, ipk);

                for(int i=0; i<nilaiIps.length ;i++){
                    if(nilaiIps[i]>4.0){
                        Context context = getActivity().getApplicationContext();
                        CharSequence text = "Maaf, tetapi sudah tidak mungkin lagi anda dapat IPK kelulusan " + ipk;
                        int duration = Toast.LENGTH_SHORT;

                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                        return;
                    }
                }

                textSemester1[0].setText(String.valueOf(nilaiIps[0]));
                textSemester1[1].setText(String.valueOf(nilaiIps[1]));
                textSemester1[2].setText(String.valueOf(nilaiIps[2]));
                textSemester1[3].setText(String.valueOf(nilaiIps[3]));
                textSemester1[4].setText(String.valueOf(nilaiIps[4]));
                textSemester1[5].setText(String.valueOf(nilaiIps[5]));
                textSemester1[6].setText(String.valueOf(nilaiIps[6]));
                textSemester1[7].setText(String.valueOf(nilaiIps[7]));
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                zeroing();
            }
        });
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
                        JSONArray jsonArray = jsonObject.getJSONArray("ip");
                        int len = jsonArray.length();
                        for (int i=0;i<len;i++){
                            nilaiIP.add(Double.parseDouble(jsonArray.get(i).toString()));
                        }
                        setNilai();
                    }
                    else {
                        Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT).show();
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

    public void setNilai(){
        for(int i=0; i<nilaiIP.size(); i++){
            textSemester1[i].setText(String.valueOf(nilaiIP.get(i)));
            textSemester1[i].setEnabled(false);
        }
        nilaiIpk.setText(Preferences.getipkInUser(getActivity().getBaseContext()));
    }

    public void zeroing(){
        textSemester1[0].setText(String.valueOf(0.0));
        textSemester1[1].setText(String.valueOf(0.0));
        textSemester1[2].setText(String.valueOf(0.0));
        textSemester1[3].setText(String.valueOf(0.0));
        textSemester1[4].setText(String.valueOf(0.0));
        textSemester1[5].setText(String.valueOf(0.0));
        textSemester1[6].setText(String.valueOf(0.0));
        textSemester1[7].setText(String.valueOf(0.0));
        nilaiIpk.setText(String.valueOf(0.0));
    }

    private void initiateNilaiSemester(){
        textSemester1[0] = (EditText)getView().findViewById(R.id.ipsSemester1);
        textSemester1[1] = (EditText)getView().findViewById(R.id.ipsSemester2);
        textSemester1[2] = (EditText)getView().findViewById(R.id.ipsSemester3);
        textSemester1[3] = (EditText)getView().findViewById(R.id.ipsSemester4);
        textSemester1[4] = (EditText)getView().findViewById(R.id.ipsSemester5);
        textSemester1[5] = (EditText)getView().findViewById(R.id.ipsSemester6);
        textSemester1[6] = (EditText)getView().findViewById(R.id.ipsSemester7);
        textSemester1[7] = (EditText)getView().findViewById(R.id.ipsSemester8);
        nilaiIpk = (EditText)getView().findViewById(R.id.ipkEdit);
        btnHitung = (Button)getView().findViewById(R.id.btnHitung);
        btnReset = (Button)getView().findViewById(R.id.btnReset);
    }

}
