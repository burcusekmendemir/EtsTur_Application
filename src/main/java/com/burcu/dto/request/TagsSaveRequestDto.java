package com.burcu.dto.request;

import com.burcu.utility.enums.EImageCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class TagsSaveRequestDto {

    private String name;
}
