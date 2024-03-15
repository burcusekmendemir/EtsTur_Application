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
     db.createUser({ user: "burcuser", pwd: "root", roles: ["readWrite","dbAdmin"]})
```

## Docker Image Oluþturma
    Ýmage oluþturmak için
    - Ýlk olarak gradle ile ilgili modülün build iþlemi yapýlýr.
    - Ýkinci aþamada gradle ile builddependets yapýlýr.
    - Sonra eðer konum olarak ilgili modülün dizini içinde deðil isek, terminal ekranýndan
    ilgili modülün dizinine geçiþ yaparýz. Bunu yapmak için kullanabileceðiniz komutlar
    > cd.. // bir üst dizine geçer
    > cd <modul_adi> ilgili modülün içine giriþ yapar
    - terminal ekranýnda aþaðýda bulunan ilgili modül için docker build komutu çalýþtýrýlýr.
    - son olarak ta docker desktop üzerinden docker hub'a push iþlemi yapýlýr. 
    docker build -t <HUB_REPOSITORY_NAME/IMAGE_NAME:VERSION> .
    DÝKKAT!! MacOS M Chipset kullananlar özellikle platform belirtmelidirler.

    1- docker build -t burcudemir/etstur-service:v.0.6 .

## Kubernetes POD
    Pod, Nodes içinde yer alan sanal PC'lerdir. Ýçerisinde image ya da imagelar barýndýrabilir. Bir yaþam döngüsü
    vardýr, bu nedenle baþlar, iþlemlerini yürütür, bir süre sonra kaybolur. Bu nedenle bir pod restart olsa bile ayný 
    þekilde kalmaz yani bir pod yeniden baþlamaz yeniden doðar. Bu nedenle her yeni baþlamada yeni bir pod oluþur ve 
    ip adresi deðiþir.
    - Podlar yeniden doðduðu için içinde barýndýrdýðý bilgiler silinir.
    - Baðlantýlar var ise kaybolur.
     Bir pod DB olarak kullanýlýyor ise içinde tuttuðu tüm bilgiler restart ettiðinde kaybolur. Peki çözüm nedir? 
     her PC'nin bir harddiski vardýr ve içinde bulunur, ancak kubernetes yapýsýnda harddisk olarak adlandýrdýðýmýz
     bileþenlere karþýlýk gelen Volume kavramý bir pod'un içinden kubernetes cluster içine alýnabilir. Böylece pod 
     ayaða kalkarken kendisine tahsis edilen volume'e baðlanarak verilerini oradan çekebilr.

## KUBERNETES SORUNLAR - ÇÖZÜM ÖNERÝLERÝ

    -Temel Komutlar
    * kubectl get [KUBERNETES OBJESI] --> görüntülemek istediðiniz objelerin listesini getirir.
        - kubectl get pods
        - kubectl get deployments
        - kubectl get services
        - kubectl get nodes
        - kubectl get ingresses
        - kuebctl get cronjobs
        - kubectl get jobs
        - kubectl get secrets
    ** kubectl get pods -o wide --> objenin daha detaylý bilgisini listeler

    * kubectl describe [KUBERNETES OBJESI] [OBJENIN ADI] --> objenin detayýný getirir.
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

    * kubectl logs [KUBERNETES OBJESI] [OBJENIN ADI] --> objenin loglarýný getirir.
    DÝKKAT!!!  bu kullaným log bilgisini tek seferlik o an için oluþmuþ loglarý verir.
       -kubectl logs pods <POD ADI>
    * kubectl logs -f [KUBERNETES OBJESI] [OBJENIN ADI] --> objenin loglarýný anlýk olarak takip etmek için kullanýlýr.
    DÝKKAT!!! Bu kullanýmda loglar sürekli izlendiði için çýkmak istediðiniz CTRL+C tuþ kombinasyonu kullanýn.

    *kubectl delete [KUBERNETES OBJESI] [OBJENIN ADI] --> objenin silinmesi için kullanýlýr.
      -kubectl delete pods <POD ADI>
      -kubectl delete deployments <DEPLOYMENT ADI>
      -kubectl delete services <SERVICE ADI>
    DÝKKAT!!!  Podlar eðer bir deployment objesine baðlý ise sadece pod silinir sonra ayný özellikte yeni bir pod
               ayaða kalkar. Eðer tamamen podlarý silmek istiyorsanýz o zaman baðlý bulunduðu deployment objesini
               silmek zorundasýnýz.

    ** Auth Micro Servisine ulaþamýyorum (ip:9094/swagger-ui/index.html). Connection Error hatasý alýyorum.
      - Pod ayakta mý reset atýyor mu buna takýn (kubectl get pods)
      - Kubernetes clusterý içinde olan bir poda eriþim servis objesi ile saðlanýr bu nedenþe servis objelerini 
        listeleyin (kubectl get services)
      - Dýþarýdan eriþim için External-IP gereklidir, bu nedenle ip adresini ve servis objesinin LoadBalancer olup
        olmadýgýný kontrol edin.
      - Servis objesinin iç yapýsýnda bulunan ClusterIP bacaðýna podalrýn baðlanmýþ olmasý gereklidir. Bunu kontrol
        etmelisiniz. Servis objesine describe komutu ile bakýýn. Burada Endpoints þeklinde bir özellik olacak orada
        sizin podlrýnýzýn ip bilgisinin burada olmasý gereklidir. Eðer burasý boþ ise sorun vardýr.
        Bu sorunun temel kaynaðý deployment objesini oluþtururken girdiðiniz label ile servis objesinin oluþtururken
        girmiþ olduðunuz "selector" bilgileri uyumsuz olduðu için bu sorun oluþabilir kontrol ederek düzeltiniz.
      - Etiketlerin doðruluðunu nasýl test edebiliriz? Ýki objeye de (POD, SERVÝCE) describe komutu ile detaylandýrýp
        kontrol edebilirsiniz.
      - Yukarýda oluþan sorunlar çözüldükten sonra bile hata alýnýyor ise, sorun sistemseldir çözebilmek için servis
        ve deployment objelerini de silip tekrar oluþturmalýyýz.