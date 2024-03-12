package com.burcu.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum ErrorType {

    INTERNAL_ERROR(1000, "Sunucuda beklenmeyen hata oluştu, lütfen tekrar deneyin", HttpStatus.INTERNAL_SERVER_ERROR),
    ERROR_DUPLICATE_USERNAME(2000, "Bu kullanıcı adı zaten kayıtlıdır. Lütfen değiştirerek tekrar deneyiniz.", HttpStatus.BAD_REQUEST),
    ERROR_INVALID_LOGIN_PARAMETER(2001, "Girilen kullanıcı adı ya da parola hatalıdır. Lütfen tekrar deneyiniz", HttpStatus.BAD_REQUEST),
    ERROR_CREATE_TOKEN(1003, "Token oluşturma hatası, lütfen tekrar deneyiniz.", HttpStatus.INTERNAL_SERVER_ERROR),
    BAD_REQUEST_ERROR(1001,"Girilen parametreler hatalıdır, lütfen tekrar deneyin", HttpStatus.BAD_REQUEST),
    EMAIL_ALREADY_EXISTS(1002,"Bu email adresi ile daha önce kullanıcı kaydı oluşturulmuştur", HttpStatus.BAD_REQUEST),
    LOGIN_ERROR(1003,"Kullanıcı adı ya da şifre hatalıdır. Lütfen tekrar deneyiniz.", HttpStatus.BAD_REQUEST),
    CATEGORY_ALREADY_EXISTS(1004,"Bu kategori zaten kayıtlıdır",HttpStatus.BAD_REQUEST ),
    CATEGORY_PROPERTIES_ALREADY_EXISTS(1005,"Bu kategori ve özellik zaten kayıtlıdır",HttpStatus.BAD_REQUEST ),
    PROPERTIES_ALREADY_EXISTS(1006,"Bu özellik zaten kayıtlıdır",HttpStatus.BAD_REQUEST ),
    ROOM_ALREADY_EXISTS(1007, "Bu oda zaten kayıtlıdır" , HttpStatus.BAD_REQUEST ),
    ADDRESS_ALREADY_EXISTS(1008, "Bu adres zaten kayıtlıdır" , HttpStatus.BAD_REQUEST),
    IMAGE_ALREADY_EXISTS(1009, "Bu fotograf zaten kayıtlıdır" , HttpStatus.BAD_REQUEST),
    OTEL_ALREADY_EXISTS(1010,"Bu otel zaten kayıtlıdır" , HttpStatus.BAD_REQUEST ),
    USER_ALREADY_EXISTS(1011,"Bu kullanıcı zaten kayıtlıdır" , HttpStatus.BAD_REQUEST),
    INVALID_TOKEN(1012,"Gecersiz token" , HttpStatus.BAD_REQUEST),
    USER_NOT_FOUND(1013, "Kullanıcı bulunamadı" , HttpStatus.BAD_REQUEST),
    ACTIVATION_CODE_ERROR(1014, "Aktivasyon kodu hatalı" , HttpStatus.BAD_REQUEST ),
    TOKEN_NOT_CREATED(1015,"Token oluşturulamadı" ,HttpStatus.BAD_REQUEST ),
    ACCOUNT_NOT_ACTIVE(1016,"Hesap aktif degil" , HttpStatus.BAD_REQUEST );


    int code;
    String message;
    HttpStatus httpStatus;


}
