package com.project.uber.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.uber.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	User findByEmail(String email);

}
