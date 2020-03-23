package com.example.erlanggarizky.siakad_desain;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private Button btn_login;
    private EditText username, password;
    private String url_login = "http://192.168.8.104/tugas/login2.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        btn_login = findViewById(R.id.btn_login);


        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mUsername = username.getText().toString().trim();
                String mPassword = password.getText().toString().trim();

                if (!mUsername.isEmpty() && !mPassword.isEmpty()){
                    Login(mUsername, mPassword);
                }
                else if (mUsername.isEmpty() && !mPassword.isEmpty()) {
                    username.setError("Username Required");
                }
                else if (mPassword.isEmpty() && !mUsername.isEmpty() ) {
                    password.setError("Password Required");
                }
                else {
                    username.setError("Username Required");
                    password.setError("Password Required");
                }
                //startActivity(new Intent(MainActivity.this, MainMenu.class));
            }
        });

    }

    private void Login(final String user, final String pass) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url_login, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String success = jsonObject.getString("success");

                    if(success.equals("1")) {
                        startActivity(new Intent(MainActivity.this, MainMenu.class));
                        Toast.makeText(MainActivity.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                        Preferences.setPasswordInUser(getBaseContext(),pass);
                        String userName = jsonObject.getString("nama");
                        Preferences.setLoggedInUser(getBaseContext(),userName);
                        String memail = jsonObject.getString("email");
                        Preferences.setEmailInUser(getBaseContext(),memail);
                        String mnim = jsonObject.getString("nim");
                        Preferences.setNimInUser(getBaseContext(),mnim);
                        String mfakultas = jsonObject.getString("fakultas");
                        Preferences.setFakultasInUser(getBaseContext(),mfakultas);
                        String mjurusan = jsonObject.getString("jurusan");
                        Preferences.setJurusanInUser(getBaseContext(),mjurusan);
                        String malamat = jsonObject.getString("alamat");
                        Preferences.setAlamatInUser(getBaseContext(),malamat);
                        String mnohp = jsonObject.getString("nohp");
                        Preferences.setNohpInUser(getBaseContext(),mnohp);
                        String mipk = jsonObject.getString("ipk");
                        Preferences.setIpkInUser(getBaseContext(),mipk);
                        String mtanggal = jsonObject.getString("tanggal_lahir");
                        Preferences.settanggalInUser(getBaseContext(),mtanggal);
                        String mjenisKelamin = jsonObject.getString("jenis_kelamin");
                        Preferences.setjeniskelaminInUser(getBaseContext(),mjenisKelamin);

                    }
                    else {
                        Toast.makeText(MainActivity.this, "Incorrect Details", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Some Error Occured: "+error, Toast.LENGTH_SHORT).show();
            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("username", user);
                params.put("password", pass);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
        requestQueue.add(stringRequest);
    }


}