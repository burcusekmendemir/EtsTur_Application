package com.burcu.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {



    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorMessage> globalHandler(RuntimeException runtimeException) {

        return new ResponseEntity<>(createErrorMessage(runtimeException,ErrorType.INTERNAL_ERROR)
                , HttpStatus.INTERNAL_SERVER_ERROR);
    }
    //olası bütün hatalar standardize edilmeli, aynı sonucu dönmeli bu sebeple errormessage yazıldı. code,message,fields dönsün istedik.
    @ExceptionHandler(AuthServiceException.class) // okulot.class dinle, hata bulursan yakala demek
    @ResponseBody //cevap olduğu için bunu yazdık, httpstatus kesin belirtilmeli, createerrormessage ile bodyi oluşturuyoruz
    public ResponseEntity<ErrorMessage> authServiceHandler(AuthServiceException authServiceException){
        return new ResponseEntity<>(createErrorMessage(authServiceException,authServiceException.getErrorType()),
                authServiceException.getErrorType().getHttpStatus());
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public final ResponseEntity<ErrorMessage> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException exception) {

        ErrorType errorType = ErrorType.BAD_REQUEST_ERROR;
        List<String> fields = new ArrayList<>();
        exception
                .getBindingResult()
                .getFieldErrors()
                .forEach(e -> fields.add(e.getField() + ": " + e.getDefaultMessage()));
        ErrorMessage errorMessage = createErrorMessage(exception,errorType);
        errorMessage.setFields(fields);
        return new ResponseEntity<>(errorMessage, errorType.getHttpStatus());
    }

    /**
     * Hata yakalama işlemleri bir çok hata için ayrı ayrı yapılmalıdır. bu nedenel tüm hataların
     * içerisine log alma işlemi yazmak zorunda kalırız. bu işlemleri tekelleştirmek ve hata log kayıtlarını
     * toplamak için tekbir method kullanmak daha doğru olacaktır.
     * @param exception
     * @param errorType
     * @return
     */
    private ErrorMessage createErrorMessage(Exception exception,ErrorType errorType){
        System.out.println("Tüm hataların geçtiği nokta...: "+ exception.getMessage());
        return ErrorMessage.builder()
                .message(errorType.getMessage())
                .code(errorType.getCode())
                .build();
    }
}
