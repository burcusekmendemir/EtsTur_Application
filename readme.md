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
     db.createUser({ user: "bilgeUser", pwd: "bilgeUser*", roles: ["readWrite","dbAdmin"]})
```
