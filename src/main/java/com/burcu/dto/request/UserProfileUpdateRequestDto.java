package com.burcu.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class UserProfileUpdateRequestDto {

    private String token;
    private String name;
    private String surname;
    private String username;
    private String email;
    private String address;
    private String password;

}
