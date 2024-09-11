package com.project.uber.dto;

import java.util.Set;

import com.project.uber.entities.enums.Role;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class UserDTO {

	private String email;
	private String name;
	private Set<Role>roles;
}
