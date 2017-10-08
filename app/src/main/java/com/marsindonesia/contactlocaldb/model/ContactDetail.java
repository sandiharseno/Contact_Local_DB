package com.marsindonesia.contactlocaldb.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Sandi on 10/7/2017.
 */

@DatabaseTable(tableName="contactDetail")
public class ContactDetail {
    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField
    private String nama;
    @DatabaseField
    private String nomorHP;
    @DatabaseField
    private String nomorKantor;
    @DatabaseField
    private String nomorRumah;
    @DatabaseField
    private String email;
    @DatabaseField
    private String website;
    @DatabaseField
    private String icon;

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
