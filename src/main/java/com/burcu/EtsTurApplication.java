package com.burcu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EtsTurApplication {
    public static void main(String[] args) {

        SpringApplication.run(EtsTurApplication.class, args);

    }


    /**
     *  Uygulamada olacak sayfalar ve Kullanılacak end-point listesi
     *   -- Login
     *   +----- auth/dologin (giriş için kullanılacak ve token dönecek)
     *  -- Register
     *    +----- auth/register (yeni kullanıcı üyeliği için kullanılacak)
     *  -- AnaSayfa
     *     +----- otel/filter-list (sol tarafta bulunan filtreleme başlıklarını listelemek için kullanılacak)
     *     +----- otel/find-all (popüler otellerden başlayarak ana sayfada gösterilecek otellerin listesini dönecek -)
     *     +----- otel/find-search (aram çubuğuna yazılan ifadeye göre filtreleme yaparak otel listesi dönecek)
     *  -- Otel Detay
     *      +----- otel/find-by-id (otel e ait detay bilgilerini dönecek, resimler, açıklamalar, odalar v.s.)
     *      +----- user/add-favori (kullanıcı otel i beğenebilecek.)
     *  -- Kullanıcı Sayfası
     *      +----- user/find-by-token (kullanıcıya ait bilgileri dönecek)
     *      +----- user/update (kullanıcı bilgilerini güncelleyecek)
     *      +----- user/favori (kullanıcının beğendiği otellerin listesini dönecek)
     */


}