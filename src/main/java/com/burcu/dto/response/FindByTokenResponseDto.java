package com.burcu.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class FindByTokenResponseDto {
    private String id;
    private String name;
    private String surname;
    private String username;
    private String email;
    private String phone;
    private String address;
}
