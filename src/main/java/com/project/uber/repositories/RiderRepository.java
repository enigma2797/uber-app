package com.project.uber.repositories;

import com.project.uber.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.uber.entities.Rider;

import java.util.Optional;

@Repository
public interface RiderRepository extends JpaRepository<Rider, Long>{

    Optional<Rider> findByUser(User user);
}
