package com.project.uber.dto;



import com.project.uber.entities.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RiderDTO {

private Double rating;
private User user;

}
