package org.mob;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class MaasBordro {
    private String ay;
    private List<Personel> personelListesi;

    public MaasBordro(String ay, List<Personel> personelListesi) {
        this.ay = ay;
        this.personelListesi = personelListesi;
    }

    public void maaslariHesapla() {
        for (Personel personel : personelListesi) {
            personel.maasHesapla();
        }
    }

    public void bordroyuKaydet() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        // Bordro dizini oluştur
        String bordroDizini = ay.replace(" ", "_"); // Boşlukları alt tire ile değiştir
        File bordroKlasoru = new File(bordroDizini);
        bordroKlasoru.mkdirs();

        for (Personel personel : personelListesi) {
            JsonObject personelObject = new JsonObject();
            personelObject.addProperty("ismi", personel.getAd());
            personelObject.addProperty("calismaSaati", personel.getCalismaSaati());

            JsonObject odemeDetaylari = new JsonObject();
            odemeDetaylari.add("anaOdeme", new JsonPrimitive("₺" + personel.getMaas()));

            double toplamOdeme = personel.getMaas(); // Ana ödeme

            // Yonetici için bonus eklenmesi
            if (personel instanceof Yonetici) {
                Yonetici yonetici = (Yonetici) personel;
                odemeDetaylari.add("bonus", new JsonPrimitive("₺" + yonetici.getBonus()));
                toplamOdeme += yonetici.getBonus();
            }

            // Memur için ek mesai bilgisi eklenmesi
            if (personel instanceof Memur) {
                Memur memur = (Memur) personel;
                if (memur.getCalismaSaati() > 180) {
                    double ekMesaiUcreti = (memur.getSaatlikUcret() * 1.5 * (memur.getCalismaSaati() - 180));
                    odemeDetaylari.add("mesai", new JsonPrimitive("₺" + ekMesaiUcreti));
                    toplamOdeme += ekMesaiUcreti;
                }
                odemeDetaylari.addProperty("derece", memur.getDerece().name());
            }

            odemeDetaylari.add("toplamOdeme", new JsonPrimitive("₺" + toplamOdeme));
            personelObject.add("odemeDetaylari", odemeDetaylari);

            // Personelin adına göre .json dosyası oluştur
            String personelDosyaAdi = personel.getAd().replace(" ", "_") + ".json";
            File personelDosyasi = new File(bordroKlasoru, personelDosyaAdi);

            try (FileWriter fileWriter = new FileWriter(personelDosyasi)) {
                gson.toJson(personelObject, fileWriter);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
