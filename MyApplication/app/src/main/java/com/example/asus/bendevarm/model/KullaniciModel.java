package com.example.asus.bendevarm.model;

/**
 * Created by asus on 10.9.2017.
 */

public class KullaniciModel {
    private String ad, soyad, eposta, sifre, cep, il, fotourl, dogumtarihi;

    public KullaniciModel() {
    }

    public KullaniciModel(String ad, String soyad, String eposta, String sifre, String cep, String il, String fotourl, String dogumtarihi) {
        this.ad = ad;
        this.soyad = soyad;
        this.eposta = eposta;
        this.sifre = sifre;
        this.cep = cep;
        this.il = il;
        this.fotourl = fotourl;
        this.dogumtarihi = dogumtarihi;
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public String getSoyad() {
        return soyad;
    }

    public void setSoyad(String soyad) {
        this.soyad = soyad;
    }

    public String getEposta() {
        return eposta;
    }

    public void setEposta(String eposta) {
        this.eposta = eposta;
    }

    public String getSifre() {
        return sifre;
    }

    public void setSifre(String sifre) {
        this.sifre = sifre;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getIl() {
        return il;
    }

    public void setIl(String il) {
        this.il = il;
    }

    public String getFotourl() {
        return fotourl;
    }

    public void setFotourl(String fotourl) {
        this.fotourl = fotourl;
    }

    public String getDogumtarihi() {
        return dogumtarihi;
    }

    public void setDogumtarihi(String dogumtarihi) {
        this.dogumtarihi = dogumtarihi;
    }
}
