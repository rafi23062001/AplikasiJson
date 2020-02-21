package com.rafi.training.aplikasijson;

import com.google.gson.annotations.Expose;

public class Mahasiswa {
    @Expose
    String namaDepan;
    String namaBelakang;
    int umur;
    String jurusan;

    public Mahasiswa(String namaDepan, String namaBelakang, int umur, String jurusan) {
        this.namaDepan = namaDepan;
        this.namaBelakang = namaBelakang;
        this.umur = umur;
        this.jurusan = jurusan;
    }

    public String getNamaDepan() {
        return namaDepan;
    }

    public String getNamaBelakang() {
        return namaBelakang;
    }

    public int getUmur() {
        return umur;
    }

    public String getJurusan() {
        return jurusan;
    }
}
