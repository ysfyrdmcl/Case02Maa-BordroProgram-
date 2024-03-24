package org.mob;

 public class Yonetici extends Personel {
     private final Role role = Role.YONETICI;

     private double bonus; // Ek ödeme

     public Yonetici(String ad, String soyad, double maas,int calismaSaati,double bonus) {
         super(ad, soyad, maas,calismaSaati);
         this.bonus = bonus;
     }


     @Override
        public void maasHesapla() {
            // Yöneticinin saatlik ücreti en az 500 olmalı
            double saatlikUcret = Math.max(getMaas() / getCalismaSaati(), 500);
            double normalMaas = getCalismaSaati() * saatlikUcret;

            // Bonus eklenmesi
            normalMaas += bonus;

            setMaas(normalMaas);
        }


    public double getBonus() {
        return bonus;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

    public Role getRole() {
        return role;
    }

 }