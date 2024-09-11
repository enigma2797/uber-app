package com.project.uber.repositories;

import java.util.List;
import java.util.Optional;

import com.project.uber.entities.User;
import org.locationtech.jts.geom.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.uber.entities.Driver;

@Repository
public interface DriverRepository extends JpaRepository<Driver,Long> {

	@Query(value = "SELECT *, ST_Distance(d.current_location,:point) AS distance from driver d "
			+ "WHERE d.available=true "
			+ "AND ST_DWithin(d.current_location,:point,10000) "
			+ "ORDER BY distance "
			+ "LIMIT 10",nativeQuery = true)
	List<Driver> findTenNearestDrivers(Point point);
	
	@Query(value = "SELECT d.* from driver d "
			+ "WHERE d.available=true "
			+ "AND ST_DWithin(d.current_location,:point,15000) "
			+ "ORDER BY rating DESC "
			+ "LIMIT 10",nativeQuery = true)
	List<Driver> findNearbyTopRatedDrivers(Point point);

    Optional<Driver> findByUser(User user);
}
