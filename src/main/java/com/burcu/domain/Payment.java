package com.burcu.domain;

import com.burcu.utility.enums.EPaymentType;
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
public class Payment extends BaseEntity{

    @Id
    private String id;
    private String reservationId;
    private Double totalPrice;
    private EPaymentType paymentType;
}
