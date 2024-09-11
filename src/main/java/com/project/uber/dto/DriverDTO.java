package com.project.uber.dto;


import org.locationtech.jts.geom.Point;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DriverDTO {
	
	private UserDTO user;
	private Double rating;
    private Boolean available;
	private PointDTO currentLocation;
	private String vehicleId;

}
