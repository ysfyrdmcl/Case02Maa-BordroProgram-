package org.mob;

public abstract class Personel {
    private String ad;
    private String soyad;
    private int calismaSaati;
    private double maas;


    public Personel(String ad, String soyad, double maas,int calismaSaati) {
        this.ad = ad;
        this.soyad = soyad;
        this.maas = maas;
        this.calismaSaati = calismaSaati;
    }

    public abstract void maasHesapla();

    // Getter ve setter metotları
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
    public double getMaas() {
        return maas;
    }

    public void setMaas(double maas) {
        this.maas = maas;
    }

    public int getCalismaSaati() {
        return calismaSaati;
    }

    public void setCalismaSaati(int calismaSaati) {
        this.calismaSaati = calismaSaati;
    }
}