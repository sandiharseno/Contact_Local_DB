package com.marsindonesia.contactlocaldb.ui.dataobject;

/**
 * Created by Sandi on 10/7/2017.
 */
public class ContactObject {
    private int id;
    private String nama;
    private String nomorHP;
    private String nomorKantor;
    private String nomorRumah;
    private String email;
    private String website;
    private String icon;

    public ContactObject(int idIn, String namaIn, String nomorHPIn, String nomorKantorIn, String nomorRumahIn, String emailIn, String websiteIn, String iconIn){
        id = idIn;
        nama = namaIn;
        nomorHP = nomorHPIn;
        nomorKantor = nomorKantorIn;
        nomorRumah = nomorRumahIn;
        email = emailIn;
        website = websiteIn;
        icon = iconIn;
    }


    public ContactObject() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNomorHP() {
        return nomorHP;
    }

    public void setNomorHP(String nomorHP) {
        this.nomorHP = nomorHP;
    }

    public String getNomorKantor() {
        return nomorKantor;
    }

    public void setNomorKantor(String nomorKantor) {
        this.nomorKantor = nomorKantor;
    }

    public String getNomorRumah() {
        return nomorRumah;
    }

    public void setNomorRumah(String nomorRumah) {
        this.nomorRumah = nomorRumah;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
