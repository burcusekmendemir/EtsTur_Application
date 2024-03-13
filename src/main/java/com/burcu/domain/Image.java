package com.burcu.domain;

import com.burcu.utility.enums.EImageCategory;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Image extends BaseEntity {

    @Id
    private String id;
    @Indexed(unique = true)
    private String name;
    private String url;
    private EImageCategory category;
    private String roomId;
    private String otelId;

}
