package com.burcu.service;


import com.burcu.dto.response.RegisterResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class MailSenderService {

    private final JavaMailSender javaMailSender;


    public void sendMail(RegisterResponseDto dto){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("burcusekmen6@gmail.com");
        mailMessage.setTo(dto.getEmail());
        mailMessage.setSubject("EtsTur Activation Code");


        mailMessage.setText( "Kullanıcı adınız: " + dto.getUsername() + "\n" +
                "Hesabınızı aktifleştirmek için lütfen aşağıdaki linke tıklayın:\n" +
                "http://34.155.167.124:9090/dev/v1/auth/activate-status/" + dto.getActivationCode());


        javaMailSender.send(mailMessage);
    }


}
