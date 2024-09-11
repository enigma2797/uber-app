package com.project.uber.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignUpDTO {

	private Long id;
	private String email;
	private String name;
	private String password;
}
