package com.burcu.domain;

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

public class UserProfile extends BaseEntity{

    @Id
    private String id;
    private String name;
    private String surname;
    private String username;
    private String email;
    private String phone;
    private String address;
    private String authId;
    private String password;
    @Builder.Default
    private List<String> favOtels= new ArrayList<>();



}
