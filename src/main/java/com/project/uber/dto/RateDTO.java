package com.project.uber.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RateDTO {

    private Long rideId;
    private Double rating;
}
