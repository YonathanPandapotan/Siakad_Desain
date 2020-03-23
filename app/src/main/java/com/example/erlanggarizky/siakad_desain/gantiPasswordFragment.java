package com.example.erlanggarizky.siakad_desain;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class gantiPasswordFragment extends Fragment {

    TextView passLama, passBaru, konfirmasiPass;
    Button btn_update;
    private String url_login = "http://192.168.8.102/tugas/updatePassword.php";

    public gantiPasswordFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ganti_password, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        passLama = getView().findViewById(R.id.passLama);
        passBaru = getView().findViewById(R.id.passBaru);
        konfirmasiPass = getView().findViewById(R.id.passBaru2);

        btn_update = getView().findViewById(R.id.btn_update);

        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String passwordLama = passLama.getText().toString().trim();
                String passwordBaru = passBaru.getText().toString().trim();
                String konfirmPass = konfirmasiPass.getText().toString().trim();

                if (!passwordLama.isEmpty() && !passwordBaru.isEmpty() && !konfirmPass.isEmpty()){
                    if(passwordLama.equals(Preferences.getPasswordInUser(getActivity().getBaseContext())) && passwordBaru.equals(konfirmPass)){
                        Preferences.setPasswordInUser(getActivity().getBaseContext(),passwordBaru);
                        update(passwordBaru, Preferences.getNimInUser(getActivity().getBaseContext()));
                    }
                    else if(!passwordBaru.equals(konfirmPass)){
                        Toast.makeText(getActivity().getApplicationContext(), "Password baru anda tidak cocok", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(getActivity().getApplicationContext(), "Password lama anda salah", Toast.LENGTH_SHORT).show();
                    }
                }
                else if (passwordLama.isEmpty()) {
                    passLama.setError("Tolong isi");
                }
                else if (passwordBaru.isEmpty()) {
                    passBaru.setError("Tolong isi");
                }
                else if (konfirmPass.isEmpty()) {
                    konfirmasiPass.setError("Tolong isi");
                }
                else {
                    Toast.makeText(getActivity().getApplicationContext(), "Tolong isi semua kolom yang tersedia", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    private void update(final String pass, final String nim){
        final Context context = getActivity().getApplicationContext();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url_login, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String success = jsonObject.getString("success");

                    if(success.equals("1")) {
                        Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(context, "Incorrect Details", Toast.LENGTH_SHORT).show();
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
                params.put("password", pass);
                params.put("nim", nim);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }

}
