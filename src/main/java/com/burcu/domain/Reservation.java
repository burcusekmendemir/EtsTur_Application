package com.burcu.domain;

import com.burcu.utility.enums.EReservationStatus;
import lombok.AllArgsConstructor;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;


@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Reservation extends BaseEntity {
    @Id
    private String id;
    private String userProfileId;
    private String roomId;
    private LocalDate startDate;
    private LocalDate endDate;
    private Double totalPrice;
    private Double discountRate;
    private EReservationStatus status;
    private String paymentId;

}
