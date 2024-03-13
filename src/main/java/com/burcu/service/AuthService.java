package com.burcu.service;

import com.burcu.domain.Auth;
import com.burcu.dto.request.ActivateStatusRequestDto;
import com.burcu.dto.request.AuthLoginRequestDto;
import com.burcu.dto.request.AuthRegisterRequestDto;
import com.burcu.dto.response.RegisterResponseDto;
import com.burcu.exception.ErrorType;
import com.burcu.exception.OtelException;
import com.burcu.mapper.AuthMapper;
import com.burcu.repository.AuthRepository;
import com.burcu.utility.CodeGenerator;
import com.burcu.utility.JwtTokenManager;
import com.burcu.utility.ServiceManager;
import com.burcu.utility.enums.EStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService extends ServiceManager<Auth, String> {
    private final AuthRepository authRepository;
    private final JwtTokenManager jwtTokenManager;

    public AuthService(AuthRepository authRepository, JwtTokenManager jwtTokenManager) {
        super(authRepository);
        this.authRepository = authRepository;
        this.jwtTokenManager = jwtTokenManager;
    }

    public RegisterResponseDto register(AuthRegisterRequestDto dto) {
        Optional<Auth> authOptional=authRepository.findByEmail(dto.getEmail());
        Optional<Auth> authOptional1=authRepository.findByUsername(dto.getUsername());
        if (authOptional.isPresent() || authOptional1.isPresent()){
            throw new OtelException(ErrorType.USER_ALREADY_EXISTS);
        }

        Auth auth=AuthMapper.INSTANCE.fromAuthRegisterRequestDtoToAuth(dto);
        auth.setActivationCode(CodeGenerator.generateCode());
        save(auth);
        return AuthMapper.INSTANCE.fromAuthToRegisterResponseDto(auth);
    }


    public String doLogin(AuthLoginRequestDto dto) {
        Optional<Auth> authOptional = authRepository.findOptionalByUsernameAndPassword(dto.getUsername(), dto.getPassword());
        if (authOptional.isEmpty()) {
            throw new OtelException(ErrorType.LOGIN_ERROR);
        }
        if (authOptional.get().getStatus().equals(EStatus.ACTIVE)) {
            return jwtTokenManager.createToken(authOptional.get().getId(), authOptional.get().getRole())
                    .orElseThrow(() -> {
                        throw new OtelException(ErrorType.TOKEN_NOT_CREATED);
                    });
        } else {
            throw new OtelException(ErrorType.ACCOUNT_NOT_ACTIVE);
        }
    }

    public Boolean activateStatus(ActivateStatusRequestDto dto) {
        Optional<Auth> optionalAuth = findById(dto.getAuthId());
        if (optionalAuth.isEmpty()) {
            throw new OtelException(ErrorType.USER_NOT_FOUND);
        }
        if (optionalAuth.get().getActivationCode().equals(dto.getActivationCode())) {
            optionalAuth.get().setStatus(EStatus.ACTIVE);
            update(optionalAuth.get());
            return true;
        } else {
            throw new OtelException(ErrorType.ACTIVATION_CODE_ERROR);
        }
    }
}
