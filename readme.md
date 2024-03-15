# KURULUMLAR VE PROJE TEKNOLOJ�LER�

## Docker �zerinde mongoDB �al��t�rmak

```bash
    docker run --name java13MongoDB -d -e "MONGO_INITDB_ROOT_USERNAME=admin" -e "MONGO_INITDB_ROOT_PASSWORD=root" -p 27017:27017 mongo:jammy 
```
    MongoDB'yi y�netebilmek i�in bir araca ihtiayc�m�z var. Bu arac�n ad� MongoDb Compas tool. Bu arac� indirip
    kurman�z gereklidir. Adres: https://www.mongodb.com/try/download/compass

    Compass kurulumu bittikten sonra, a��lan yeni pencerede "New Connection +" butonuna t�kl�yorsunuz. Ekran�n ortas�nda
    "> Advanced connection options" butonuna t�klayarak detayll� ba�lant� ayarlar�n� yap�yoruz.
     1- a��lan ekranda "Host" k�sm�na veritaban�n�z�n ip adrsini ve port numaras�n� giriyorsunuz. Yerel bilgisayar�n�z
    i�in kullan�lacak ise ya da docker desktop �zerinde ise (localhost:27017) �eklinde yaz�yoruz.
    2- Authentication k�sm�na ge�i� yaparak kurulum s�ras�nda girdi�iniz kullan�c� ad� ve �ifreyi yaz�yorsunuz. Docker
    run komutu ile �al��t�rd� iseniz -e (environment) ile giri� yapt���n�z bilgileri yaz�n�z. (admin-root)

    NOT: MongoDB'yi ilk kurulumlar� ve kullan�mlar� i�in admin kullan�c� ile i�lemleri yapabilirsiniz. Ancak, 
    veritabanlar�n� y�netmek ve i�lemek i�in kullanmay�n�z. Her Db i�in ayr� kullan�c� ve yetkiler olu�turunuz
    root kullan�c�s� ve �ifreleri sadece ilk kurulum i�in kullan�lmal� ve tekrar kullan�lmamal�d�r. Sadece gerekli
    oldu�u durumlarda m�dahale i�in kullan�n�z.
    Gerekli dok�mantasyonlara: http://mongodb.com/docs/manual/

    Yetkilendirme ��lemleri:
    1- MONGOSH'a t�klayarak a��yorsunuz.
    2- A��lan k�s�mda test> �eklinde bir yer g�receksiniz, �ncelikle test DB'den kendi DB'nize ge�mek i�in 
    use [DB_ad�] yaz�p enter'a bas�n�z.
    �rn: 
    use UserProfile
    switched to db UserProfile
     UserProfile > �eklinde bir g�r�nt� elde edeceksiniz.
    3- Burada kullan�c� oluturmak i�in gerekli komutlar� giriyoruz.
    db.createUser({
        user: "bilgeUser",
        pwd: "bilgeUser*",
        roles: ["readWrite","dbAdmin"]
    })
    db k�sm� da yaz�lmal� girerken

```
     db.createUser({ user: "burcuser", pwd: "root", roles: ["readWrite","dbAdmin"]})
```

## Docker Image Olu�turma
    �mage olu�turmak i�in
    - �lk olarak gradle ile ilgili mod�l�n build i�lemi yap�l�r.
    - �kinci a�amada gradle ile builddependets yap�l�r.
    - Sonra e�er konum olarak ilgili mod�l�n dizini i�inde de�il isek, terminal ekran�ndan
    ilgili mod�l�n dizinine ge�i� yapar�z. Bunu yapmak i�in kullanabilece�iniz komutlar
    > cd.. // bir �st dizine ge�er
    > cd <modul_adi> ilgili mod�l�n i�ine giri� yapar
    - terminal ekran�nda a�a��da bulunan ilgili mod�l i�in docker build komutu �al��t�r�l�r.
    - son olarak ta docker desktop �zerinden docker hub'a push i�lemi yap�l�r. 
    docker build -t <HUB_REPOSITORY_NAME/IMAGE_NAME:VERSION> .
    D�KKAT!! MacOS M Chipset kullananlar �zellikle platform belirtmelidirler.

    1- docker build -t burcudemir/etstur-service:v.0.6 .

## Kubernetes POD
    Pod, Nodes i�inde yer alan sanal PC'lerdir. ��erisinde image ya da imagelar bar�nd�rabilir. Bir ya�am d�ng�s�
    vard�r, bu nedenle ba�lar, i�lemlerini y�r�t�r, bir s�re sonra kaybolur. Bu nedenle bir pod restart olsa bile ayn� 
    �ekilde kalmaz yani bir pod yeniden ba�lamaz yeniden do�ar. Bu nedenle her yeni ba�lamada yeni bir pod olu�ur ve 
    ip adresi de�i�ir.
    - Podlar yeniden do�du�u i�in i�inde bar�nd�rd��� bilgiler silinir.
    - Ba�lant�lar var ise kaybolur.
     Bir pod DB olarak kullan�l�yor ise i�inde tuttu�u t�m bilgiler restart etti�inde kaybolur. Peki ��z�m nedir? 
     her PC'nin bir harddiski vard�r ve i�inde bulunur, ancak kubernetes yap�s�nda harddisk olarak adland�rd���m�z
     bile�enlere kar��l�k gelen Volume kavram� bir pod'un i�inden kubernetes cluster i�ine al�nabilir. B�ylece pod 
     aya�a kalkarken kendisine tahsis edilen volume'e ba�lanarak verilerini oradan �ekebilr.

## KUBERNETES SORUNLAR - ��Z�M �NER�LER�

    -Temel Komutlar
    * kubectl get [KUBERNETES OBJESI] --> g�r�nt�lemek istedi�iniz objelerin listesini getirir.
        - kubectl get pods
        - kubectl get deployments
        - kubectl get services
        - kubectl get nodes
        - kubectl get ingresses
        - kuebctl get cronjobs
        - kubectl get jobs
        - kubectl get secrets
    ** kubectl get pods -o wide --> objenin daha detayl� bilgisini listeler

    * kubectl describe [KUBERNETES OBJESI] [OBJENIN ADI] --> objenin detay�n� getirir.
        - kubectl describe pods <POD ADI>
        - kubectl describe deployments <DEPLOYMENT ADI>
        - kubectl describe services <SERVICE ADI>
        - kubectl describe nodes <NODE ADI>
        - kubectl describe ingresses <INGRESS ADI>
        - kubectl describe cronjobs <CRONJOB ADI>
        - kubectl describe jobs <JOB ADI>
        - kubectl describe secrets <SECRET ADI>

    * kubectl top [KUBERNETES OBJESI] --> objenin cpu ve memory bilgisini getirir.
        - kubectl top pods <POD ADI>
        - kubectl top nodes <DEPLOYMENT ADI>

    * kubectl logs [KUBERNETES OBJESI] [OBJENIN ADI] --> objenin loglar�n� getirir.
    D�KKAT!!!  bu kullan�m log bilgisini tek seferlik o an i�in olu�mu� loglar� verir.
       -kubectl logs pods <POD ADI>
    * kubectl logs -f [KUBERNETES OBJESI] [OBJENIN ADI] --> objenin loglar�n� anl�k olarak takip etmek i�in kullan�l�r.
    D�KKAT!!! Bu kullan�mda loglar s�rekli izlendi�i i�in ��kmak istedi�iniz CTRL+C tu� kombinasyonu kullan�n.

    *kubectl delete [KUBERNETES OBJESI] [OBJENIN ADI] --> objenin silinmesi i�in kullan�l�r.
      -kubectl delete pods <POD ADI>
      -kubectl delete deployments <DEPLOYMENT ADI>
      -kubectl delete services <SERVICE ADI>
    D�KKAT!!!  Podlar e�er bir deployment objesine ba�l� ise sadece pod silinir sonra ayn� �zellikte yeni bir pod
               aya�a kalkar. E�er tamamen podlar� silmek istiyorsan�z o zaman ba�l� bulundu�u deployment objesini
               silmek zorundas�n�z.

    ** Auth Micro Servisine ula�am�yorum (ip:9094/swagger-ui/index.html). Connection Error hatas� al�yorum.
      - Pod ayakta m� reset at�yor mu buna tak�n (kubectl get pods)
      - Kubernetes cluster� i�inde olan bir poda eri�im servis objesi ile sa�lan�r bu neden�e servis objelerini 
        listeleyin (kubectl get services)
      - D��ar�dan eri�im i�in External-IP gereklidir, bu nedenle ip adresini ve servis objesinin LoadBalancer olup
        olmad�g�n� kontrol edin.
      - Servis objesinin i� yap�s�nda bulunan ClusterIP baca��na podalr�n ba�lanm�� olmas� gereklidir. Bunu kontrol
        etmelisiniz. Servis objesine describe komutu ile bak��n. Burada Endpoints �eklinde bir �zellik olacak orada
        sizin podlr�n�z�n ip bilgisinin burada olmas� gereklidir. E�er buras� bo� ise sorun vard�r.
        Bu sorunun temel kayna�� deployment objesini olu�tururken girdi�iniz label ile servis objesinin olu�tururken
        girmi� oldu�unuz "selector" bilgileri uyumsuz oldu�u i�in bu sorun olu�abilir kontrol ederek d�zeltiniz.
      - Etiketlerin do�rulu�unu nas�l test edebiliriz? �ki objeye de (POD, SERV�CE) describe komutu ile detayland�r�p
        kontrol edebilirsiniz.
      - Yukar�da olu�an sorunlar ��z�ld�kten sonra bile hata al�n�yor ise, sorun sistemseldir ��zebilmek i�in servis
        ve deployment objelerini de silip tekrar olu�turmal�y�z.