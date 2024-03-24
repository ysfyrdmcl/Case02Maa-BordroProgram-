package org.mob;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.FileReader;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DosyaOku {
    private String dosyaAdi;

    public DosyaOku(String dosyaAdi) {
        this.dosyaAdi = dosyaAdi;
    }
    public List<Personel> getPersonelListesi() {
        try (FileReader reader = new FileReader(dosyaAdi)) {
            Gson gson = new Gson();
            JsonArray jsonArray = gson.fromJson(reader, JsonArray.class);
            List<Personel> personelListesi = new ArrayList<>();

            for (JsonElement jsonElement : jsonArray) {
                JsonObject jsonObject = jsonElement.getAsJsonObject();
                String role = jsonObject.get("role").getAsString();
                if (role.equalsIgnoreCase("Yonetici")) {
                    Yonetici yonetici = gson.fromJson(jsonObject, Yonetici.class);
                    personelListesi.add(yonetici);
                } else if (role.equalsIgnoreCase("Memur")) {
                    Memur memur = gson.fromJson(jsonObject, Memur.class);
                    personelListesi.add(memur);
                }
            }

            return personelListesi;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}