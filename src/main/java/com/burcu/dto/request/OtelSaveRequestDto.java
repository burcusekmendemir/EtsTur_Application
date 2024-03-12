package com.burcu.dto.request;

import com.burcu.domain.Image;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class OtelSaveRequestDto {

    private String name;
    private String addressId;
    private String phoneNumber;
    private LocalDateTime entryTime;
    private LocalDateTime exitTime;

    private List<String> rooms;
    private List<String> comments;
    private List<Image> images;
}
