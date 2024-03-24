package org.mob;

public class Memur extends Personel {
    private static final double VARSAYILAN_SAATLIK_UCRET = 500;
    private static final int MAX_CALISMA_SAATI = 180;

    private double saatlikUcret;

    private final Role role = Role.MEMUR;
    private Derece derece;

    public Memur(String ad, String soyad, double maas, int calismaSaati, Derece derece, double saatlikUcret) {
        super(ad, soyad, maas, calismaSaati);
        this.derece = derece;
        this.saatlikUcret = saatlikUcret;
    }


    @Override
    public void maasHesapla() {
        // Maksimum 180 saatlik çalışma süresi
        int normalCalismaSaati = Math.min(getCalismaSaati(), MAX_CALISMA_SAATI);
        saatlikUcret = VARSAYILAN_SAATLIK_UCRET;

        double normalMaas = normalCalismaSaati * saatlikUcret;

        // Ek mesai hesaplanması (180 saat üzeri çalışma)
        if (getCalismaSaati() > MAX_CALISMA_SAATI) {
            int ekMesaiSaati = getCalismaSaati() - MAX_CALISMA_SAATI;
            double ekMesaiUcreti = saatlikUcret * 1.5;
            double ekMesaiMaasi = ekMesaiSaati * ekMesaiUcreti;
            normalMaas += ekMesaiMaasi;
        }

        // Dereceye göre maaş artışı
        switch (derece) {
            case JUNIOR -> normalMaas *= 1.1; // %10 artış

            case MID ->  normalMaas *= 1.15; // %15 artış

            case SENIOR -> normalMaas *= 1.2; // %20 artış

            default -> {
            }
        }
        setMaas(normalMaas);
    }


    public double getSaatlikUcret() {
        return saatlikUcret;
    }

    public void setSaatlikUcret(double saatlikUcret) {
        this.saatlikUcret = saatlikUcret;
    }

    public Derece getDerece() {
        return derece;
    }

    public void setDerece(Derece derece) {
        this.derece = derece;
    }

    public Role getRole() {
        return role;
    }
}