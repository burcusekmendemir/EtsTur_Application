package com.burcu.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class AuthLoginRequestDto {

    @Size(min=3, max=20)
    @NotNull
    private String username;
    @Size(min=3, max=20)
    @NotNull
    private String password;
}
