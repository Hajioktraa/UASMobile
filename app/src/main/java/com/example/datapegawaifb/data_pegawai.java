package com.example.datapegawaifb;

public class data_pegawai {

    private String nip;
    private String nama;
    private String jabatan;
    private String key;

    public String getKey(){
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getNip(){
        return nip;
    }

    public void setNip(String nip){
        this.nip = nip;
    }

    public String getNama(){
        return nama;
    }

    public void setNama(String nama){
        this.nama = nama;
    }


    public String getJabatan(){
        return jabatan;
    }

    public void setJabatan(String jabatan){
        this.jabatan = jabatan;
    }

    public data_pegawai(){

    }

    public data_pegawai(String nip, String nama, String jabatan){

        this.nip = nip;
        this.nama = nama;
        this.jabatan = jabatan;
    }

}
