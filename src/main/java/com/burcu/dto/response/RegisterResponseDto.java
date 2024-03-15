package com.burcu.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class RegisterResponseDto implements Serializable {

    private String email;
    private String username;
    private String activationCode;
}
