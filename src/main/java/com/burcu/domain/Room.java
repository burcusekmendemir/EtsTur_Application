package com.burcu.domain;

import com.burcu.utility.enums.ERoomType;
import com.burcu.utility.enums.ERoomProperties;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Room extends BaseEntity {

    @Id
    private String id;
    private String name;
    private int roomNumber;
    private ERoomType roomType;
    private int personCapacity;
    private int bedCount;
    private int bathCount;
    private int roomCount;
    private Double unitPrice;
    private String description;

    @Builder.Default
    private List<ERoomProperties> roomProperties=new ArrayList<>();

    @Builder.Default
    private List<Image> imageList=new ArrayList<>();
}
