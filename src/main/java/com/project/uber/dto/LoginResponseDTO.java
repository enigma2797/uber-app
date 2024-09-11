package com.project.uber.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class LoginResponseDTO {

    private Long id;
    private String accessToken;
    private String refreshToken;

}
