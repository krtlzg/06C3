package com.example.asus.bendevarm.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by asus on 14.8.2017.
 */

public class EtkinlikModel implements Serializable {

    int durum;
    String etkinlikAdi, kurulusAdi, logoUrl, tarih, yer, id, url;
    ArrayList<String> destekListe = new ArrayList<>();

    public EtkinlikModel() { }

    public EtkinlikModel(int durum, String etkinlikAdi, String kurulusAdi, String logoUrl, String tarih, String yer, String id, String url, ArrayList<String> destekListe) {
        this.durum = durum;
        this.etkinlikAdi = etkinlikAdi;
        this.kurulusAdi = kurulusAdi;
        this.logoUrl = logoUrl;
        this.tarih = tarih;
        this.yer = yer;
        this.id = id;
        this.destekListe = destekListe;
        this.url = url;
    }

    public int getDurum() {
        return durum;
    }

    public void setDurum(int durum) {
        this.durum = durum;
    }

    public String getEtkinlikAdi() {
        return etkinlikAdi;
    }

    public void setEtkinlikAdi(String etkinlikAdi) {
        this.etkinlikAdi = etkinlikAdi;
    }

    public String getKurulusAdi() {
        return kurulusAdi;
    }

    public void setKurulusAdi(String kurulusAdi) {
        this.kurulusAdi = kurulusAdi;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public String getTarih() {
        return tarih;
    }

    public void setTarih(String tarih) {
        this.tarih = tarih;
    }

    public String getYer() {
        return yer;
    }

    public void setYer(String yer) {
        this.yer = yer;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<String> getDestekListe() {
        return destekListe;
    }

    public void setDestekListe(ArrayList<String> destekListe) {
        this.destekListe = destekListe;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
