package com.project.uber.controllers;

import com.project.uber.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import com.project.uber.services.RiderService;

import lombok.RequiredArgsConstructor;

import java.util.List;

@RestController
@RequestMapping(path="/rider")
@RequiredArgsConstructor
@Secured("ROLE_RIDER")
public class RiderController {
	
	private final RiderService riderService;
	@PostMapping(path="/requestRide")
	public ResponseEntity<RideRequestDTO> requestRide(@RequestBody RideRequestDTO rideRequestDTO)
	{
		return ResponseEntity.ok(riderService.requestRide(rideRequestDTO));
	}

	@PostMapping(path="/cancelRide/{rideId}")
	public ResponseEntity<RideDTO> cancelRide(@PathVariable Long rideId)
	{
		return ResponseEntity.ok(riderService.cancelRide(rideId));
	}

	@PostMapping(path="/rateDriver")
	public ResponseEntity<DriverDTO> rateDriver(@RequestBody RateDTO rateDTO)
	{
		return ResponseEntity.ok(riderService.rateDriver(rateDTO.getRideId(),rateDTO.getRating()));
	}

	@GetMapping(path="/getMyProfile")
	public ResponseEntity<RiderDTO>getMyProfile()
	{
		return ResponseEntity.ok(riderService.getMyProfile());
	}

	@GetMapping(path="/getAllMyRides")
	public ResponseEntity<List<RideDTO>>getAllMyRides(@RequestParam(defaultValue = "1",required = false) int pageNo, @RequestParam(defaultValue = "10",required = false) int pageSize)
	{
		Pageable pageable = PageRequest.of(pageNo,pageSize,Sort.by(Sort.Direction.DESC,"createdTime","id"));
		return ResponseEntity.ok(riderService.getAllMyRides(pageable).getContent());
	}


}
