package com.example.erlanggarizky.siakad_desain;

public class jadwalKuliah {
    public String idJawdal, namaMatkul, hari, jamMulai, jamSelesai, ruangan;

    public jadwalKuliah(String idJawdal, String namaMatkul, String hari, String jamMulai, String jamSelesai, String ruangan){
        this.idJawdal = idJawdal;
        this.namaMatkul = namaMatkul;
        this.hari = hari;
        this.jamMulai = jamMulai;
        this.jamSelesai = jamSelesai;
        this.ruangan = ruangan;
    }

    public String getIdJawdal() {
        return idJawdal;
    }

    public void setIdJawdal(String idJawdal) {
        this.idJawdal = idJawdal;
    }

    public String getNamaMatkul() {
        return namaMatkul;
    }

    public void setNamaMatkul(String namaMatkul) {
        this.namaMatkul = namaMatkul;
    }

    public String getHari() {
        return hari;
    }

    public void setHari(String hari) {
        this.hari = hari;
    }

    public String getJamMulai() {
        return jamMulai;
    }

    public void setJamMulai(String jamMulai) {
        this.jamMulai = jamMulai;
    }

    public String getJamSelesai() {
        return jamSelesai;
    }

    public void setJamSelesai(String jamSelesai) {
        this.jamSelesai = jamSelesai;
    }

    public String getRuangan() {
        return ruangan;
    }

    public void setRuangan(String ruangan) {
        this.ruangan = ruangan;
    }
}
