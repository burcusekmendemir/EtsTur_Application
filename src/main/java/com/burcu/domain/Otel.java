package com.burcu.domain;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Otel extends BaseEntity {

    @Id
    private String id;
    private String name;
    private String addressId;
    private String phoneNumber;
    private LocalDateTime entryTime;
    private LocalDateTime exitTime;
    private Double point;










}
