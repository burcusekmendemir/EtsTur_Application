package com.burcu.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class AddressSaveRequestDto {

    private String streetNumber;
    private String zipCode;
    private String distirctId;
    private String description;
}
