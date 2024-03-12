package com.burcu.dto.request;

import jakarta.validation.constraints.Email;
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

public class AuthRegisterRequestDto {

    @Email
    @NotNull
    private String email;
    @Size(min=3, max=20, message = "Username must be between 3 and 20 characters")
    @NotNull
    private String username;
    @Size(min=8, max=32, message = "Password must be between 3 and 20 characters")
    @NotNull
    private String password;
}
