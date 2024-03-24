package org.mob;
import java.util.List;

public class Program {
    public static void main(String[] args) {
        // JSON dosyasından personel bilgilerini oku
        DosyaOku dosyaOku = new DosyaOku("C:\\Users\\yipyip\\Downloads\\personel.json");

        List<Personel> personelListesi = dosyaOku.getPersonelListesi();

        // Maaş bordrosu oluştur
        MaasBordro maasBordro = new MaasBordro("MART 2024", personelListesi);

        // Maaşları hesapla
        maasBordro.maaslariHesapla();

        // Bordroyu kaydet
        maasBordro.bordroyuKaydet();

        // Raporu ekrana yazdır
        raporuYazdir(personelListesi);
    }

    public static void raporuYazdir(List<Personel> personelListesi) {
        System.out.println("Maaş Raporu:");
        for (Personel personel : personelListesi) {
            System.out.println(personel.getAd() + " " + personel.getSoyad() + ":");
            System.out.println("Maaş: " + personel.getMaas());

            // 150 saatten az çalışanları belirt
            if (personel.getCalismaSaati() < 150) {
                System.out.println("Uyarı: " + personel.getAd() + " " + personel.getSoyad() + " 150 saatten az çalışmıştır.");
            }
            System.out.println();
        }
    }
}

