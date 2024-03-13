package com.burcu.dto.response;


import com.burcu.domain.Image;
import com.burcu.domain.Tags;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class OtelHomePageResponseDto {

    private String name;
    private String addressName;
    private Double point;
    private List<Image> images;
    private List<Tags> tags;
    private int commentNumber; //size eklenecek listeden çekilecek
}
