package com.example.erlanggarizky.siakad_desain;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Preferences {
    static final String KEY_USERNAME_SEDANG_LOGIN = "Username_logged_in";
    static final String KEY_Email_LOGIN = "Email_login";
    static final String KEY_Nim_LOGIN = "Nim_login";
    static final String KEY_Fakultas_LOGIN = "Fakultas_login";
    static final String KEY_Jurusan_LOGIN = "Jurusan_login";
    static final String KEY_Alamat_LOGIN = "Alamat_login";
    static final String KEY_No_hp_LOGIN = "No_hp_login";
    static final String KEY_Ipk_LOGIN = "IPK_login";
    static final String KEY_tanggal_LOGIN = "tanggal_login";
    static final String KEY_jeniskelamin_LOGIN = "jeniskelamin_login";
    static final String KEY_password_LOGIN = "password_login";


    public static SharedPreferences getSharedPreference(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static void setPasswordInUser(Context context, String pass){
        SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.putString(KEY_password_LOGIN, pass);
        editor.apply();
    }
    public static String getPasswordInUser(Context context){
        return getSharedPreference(context).getString(KEY_password_LOGIN,"");
    }

    public static void setLoggedInUser(Context context, String username){
        SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.putString(KEY_USERNAME_SEDANG_LOGIN, username);
        editor.apply();
    }
    public static String getLoggedInUser(Context context){
        return getSharedPreference(context).getString(KEY_USERNAME_SEDANG_LOGIN,"");
    }

    public static void setEmailInUser(Context context, String email){
        SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.putString(KEY_Email_LOGIN, email);
        editor.apply();
    }
    public static String getEmailInUser(Context context){
        return getSharedPreference(context).getString(KEY_Email_LOGIN,"");
    }
    public static void setNimInUser(Context context, String nim){
        SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.putString(KEY_Nim_LOGIN, nim);
        editor.apply();
    }
    public static String getNimInUser(Context context){
        return getSharedPreference(context).getString(KEY_Nim_LOGIN,"");
    }
    public static void setFakultasInUser(Context context, String fakultas){
        SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.putString(KEY_Fakultas_LOGIN, fakultas);
        editor.apply();
    }
    public static String getFakultasInUser(Context context){
        return getSharedPreference(context).getString(KEY_Fakultas_LOGIN,"");
    }
    public static void setJurusanInUser(Context context, String jurusan){
        SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.putString(KEY_Jurusan_LOGIN, jurusan);
        editor.apply();
    }
    public static String getJurusanInUser(Context context){
        return getSharedPreference(context).getString(KEY_Jurusan_LOGIN,"");
    }
    public static void setAlamatInUser(Context context, String alamat){
        SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.putString(KEY_Alamat_LOGIN, alamat);
        editor.apply();
    }
    public static String getAlamatInUser(Context context){
        return getSharedPreference(context).getString(KEY_Alamat_LOGIN,"");
    }

    public static void setNohpInUser(Context context, String nohp){
        SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.putString(KEY_No_hp_LOGIN, nohp);
        editor.apply();
    }
    public static String getNohpInUser(Context context){
        return getSharedPreference(context).getString(KEY_No_hp_LOGIN,"");
    }
    public static void setIpkInUser(Context context, String ipk){
        SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.putString(KEY_Ipk_LOGIN, ipk);
        editor.apply();
    }
    public static String getipkInUser(Context context){
        return getSharedPreference(context).getString(KEY_Ipk_LOGIN,"");
    }
    public static void settanggalInUser(Context context, String tanggal){
        SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.putString(KEY_tanggal_LOGIN, tanggal);
        editor.apply();
    }
    public static String gettanggalInUser(Context context){
        return getSharedPreference(context).getString(KEY_tanggal_LOGIN,"");
    }
    public static void setjeniskelaminInUser(Context context, String jenis_kelamin){
        SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.putString(KEY_jeniskelamin_LOGIN, jenis_kelamin);
        editor.apply();
    }
    public static String getjeniskelaminInUser(Context context){
        return getSharedPreference(context).getString(KEY_jeniskelamin_LOGIN,"");
    }



}
