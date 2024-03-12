package com.burcu.dto.request;

import com.burcu.domain.Image;
import com.burcu.utility.enums.ERoomProperties;
import com.burcu.utility.enums.ERoomType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class RoomSaveRequestDto {

    private String name;
    private int roomNumber;
    private ERoomType roomType;
    private int personCapacity;
    private int bedCount;
    private int bathCount;
    private int roomCount;
    private Double unitPrice;
    private String description;

    private List<ERoomProperties> roomProperties;
    private List<Image> imageList;
}
