package com.burcu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EtsTurApplication {
    public static void main(String[] args) {

        SpringApplication.run(EtsTurApplication.class, args);

    }

    /**
     * https://www.etstur.com/
     *
     * Kiralamak istediği odayla ilgili temel bilgiler.
     * Drawsqlde bu yapıyı çıkartmak etstur
     * kategori sub kategori, resimler tagler, resimlerin de kategorileri var
     *
     * 1- Mevcut bir örnek incelenecek ve gereli bilgiler toplanacak madde madde yazılacak. (ets)
     * 2- Security (JWT)
     * 3- Swl Şeması
     * 4- MongoDB
     * 5- Redis
     * 6- Login-Register (UserProfile) fiyat düşünce bilgi gidecek
     */
}