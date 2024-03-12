package com.burcu.domain;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Address extends BaseEntity {

    @Id
    private String id;
    private String distirctId;
    private String streetNumber;
    private String zipCode;
    private String description;


}
