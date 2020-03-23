package com.example.erlanggarizky.siakad_desain;

public class tugas {

    private String namaTugas, deskripsi, tanggalKumpul, status;

    public tugas(String namaTugas, String deskripsi, String tanggalKumpul, String status){
        this.namaTugas = namaTugas;
        this.deskripsi = deskripsi;
        this.tanggalKumpul = tanggalKumpul;
        this.status = status;
    }

    public String getNamaTugas() {
        return namaTugas;
    }

    public void setNamaTugas(String namaTugas) {
        this.namaTugas = namaTugas;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getTanggalKumpul() {
        return tanggalKumpul;
    }

    public void setTanggalKumpul(String tanggalKumpul) {
        this.tanggalKumpul = tanggalKumpul;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
