package com.example.asus.bendevarm.model;

/**
 * Created by asus on 22.9.2017.
 */

public class KurumsalModel {

    private String ad,eposta,sifre,tel,il,url;

    public KurumsalModel() {
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
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

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getIl() {
        return il;
    }

    public void setIl(String il) {
        this.il = il;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public KurumsalModel(String ad, String eposta, String sifre, String tel, String il, String url) {
        this.ad = ad;
        this.eposta = eposta;
        this.sifre = sifre;
        this.tel = tel;
        this.il = il;
        this.url = url;
    }
}
