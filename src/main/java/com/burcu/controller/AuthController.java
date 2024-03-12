package com.burcu.controller;


import com.burcu.dto.request.ActivateStatusRequestDto;
import com.burcu.dto.request.AuthLoginRequestDto;
import com.burcu.dto.request.AuthRegisterRequestDto;
import com.burcu.dto.response.RegisterResponseDto;
import com.burcu.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static com.burcu.constants.RestApiUrls.*;
import static com.burcu.constants.RestApiUrls.ACTIVATE_STATUS;

@RestController
@RequiredArgsConstructor
@RequestMapping(AUTH)
public class AuthController {
    private final AuthService authService;


    @PostMapping(REGISTER)
    public ResponseEntity<RegisterResponseDto> register(@RequestBody AuthRegisterRequestDto dto) {
        return ResponseEntity.ok(authService.register(dto));
    }

    @PostMapping(LOGIN)
    public ResponseEntity<String> doLogin(@RequestBody AuthLoginRequestDto dto) {
        return ResponseEntity.ok(authService.doLogin(dto));
    }

    @PostMapping(ACTIVATE_STATUS)
    public ResponseEntity<Boolean> activateStatus(@RequestBody ActivateStatusRequestDto dto){
        return ResponseEntity.ok(authService.activateStatus(dto));
    }



}
