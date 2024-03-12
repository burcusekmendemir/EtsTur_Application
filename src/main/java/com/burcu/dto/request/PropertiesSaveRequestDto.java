package com.burcu.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class PropertiesSaveRequestDto {

    @NotNull
    private String name;
    private String description;
    private String parentId;
}
