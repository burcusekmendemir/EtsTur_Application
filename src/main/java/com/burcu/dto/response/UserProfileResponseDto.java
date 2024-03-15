package com.burcu.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class UserProfileResponseDto {

    private String id;
    private String name;
    private String surname;
    private String username;
    private String email;
    private String address;
    private String password;

    private List<String> favOtels;
}
