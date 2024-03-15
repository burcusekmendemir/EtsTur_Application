package com.burcu.controller;


import com.burcu.dto.request.ActivateStatusRequestDto;
import com.burcu.dto.request.AuthLoginRequestDto;
import com.burcu.dto.request.AuthRegisterRequestDto;
import com.burcu.dto.response.RegisterResponseDto;
import com.burcu.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.burcu.constants.RestApiUrls.*;
import static com.burcu.constants.RestApiUrls.ACTIVATE_STATUS;

@RestController
@RequiredArgsConstructor
@RequestMapping(AUTH)
public class AuthController {
    private final AuthService authService;


    @PostMapping(REGISTER)
    @CrossOrigin("*")
    public ResponseEntity<RegisterResponseDto> register(@RequestBody AuthRegisterRequestDto dto) {
        return ResponseEntity.ok(authService.register(dto));
    }

    @PostMapping(LOGIN)
    @CrossOrigin("*")
    public ResponseEntity<String> doLogin(@RequestBody AuthLoginRequestDto dto) {
        return ResponseEntity.ok(authService.doLogin(dto));
    }


    @GetMapping(ACTIVATE_STATUS + "/{activationCode}")
    @CrossOrigin("*")
    public ResponseEntity<String> activateStatus(@PathVariable String activationCode){
        return ResponseEntity.ok(authService.activateStatus(activationCode));

    }


}
