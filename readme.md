# KURULUMLAR VE PROJE TEKNOLOJÝLERÝ

## Docker üzerinde mongoDB çalýþtýrmak

```bash
    docker run --name java13MongoDB -d -e "MONGO_INITDB_ROOT_USERNAME=admin" -e "MONGO_INITDB_ROOT_PASSWORD=root" -p 27017:27017 mongo:jammy 
```
    MongoDB'yi yönetebilmek için bir araca ihtiaycýmýz var. Bu aracýn adý MongoDb Compas tool. Bu aracý indirip
    kurmanýz gereklidir. Adres: https://www.mongodb.com/try/download/compass

    Compass kurulumu bittikten sonra, açýlan yeni pencerede "New Connection +" butonuna týklýyorsunuz. Ekranýn ortasýnda
    "> Advanced connection options" butonuna týklayarak detayllý baðlantý ayarlarýný yapýyoruz.
     1- açýlan ekranda "Host" kýsmýna veritabanýnýzýn ip adrsini ve port numarasýný giriyorsunuz. Yerel bilgisayarýnýz
    için kullanýlacak ise ya da docker desktop üzerinde ise (localhost:27017) þeklinde yazýyoruz.
    2- Authentication kýsmýna geçiþ yaparak kurulum sýrasýnda girdiðiniz kullanýcý adý ve þifreyi yazýyorsunuz. Docker
    run komutu ile çalýþtýrdý iseniz -e (environment) ile giriþ yaptýðýnýz bilgileri yazýnýz. (admin-root)

    NOT: MongoDB'yi ilk kurulumlarý ve kullanýmlarý için admin kullanýcý ile iþlemleri yapabilirsiniz. Ancak, 
    veritabanlarýný yönetmek ve iþlemek için kullanmayýnýz. Her Db için ayrý kullanýcý ve yetkiler oluþturunuz
    root kullanýcýsý ve þifreleri sadece ilk kurulum için kullanýlmalý ve tekrar kullanýlmamalýdýr. Sadece gerekli
    olduðu durumlarda müdahale için kullanýnýz.
    Gerekli dokümantasyonlara: http://mongodb.com/docs/manual/

    Yetkilendirme Ýþlemleri:
    1- MONGOSH'a týklayarak açýyorsunuz.
    2- Açýlan kýsýmda test> þeklinde bir yer göreceksiniz, öncelikle test DB'den kendi DB'nize geçmek için 
    use [DB_adý] yazýp enter'a basýnýz.
    Örn: 
    use UserProfile
    switched to db UserProfile
     UserProfile > þeklinde bir görüntü elde edeceksiniz.
    3- Burada kullanýcý oluturmak için gerekli komutlarý giriyoruz.
    db.createUser({
        user: "bilgeUser",
        pwd: "bilgeUser*",
        roles: ["readWrite","dbAdmin"]
    })
    db kýsmý da yazýlmalý girerken

```
     db.createUser({ user: "bilgeUser", pwd: "bilgeUser*", roles: ["readWrite","dbAdmin"]})
```
