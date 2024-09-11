package com.project.uber.services.impl;

import java.util.List;

import org.locationtech.jts.geom.Point;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.project.uber.services.DistanceService;

import lombok.Data;

@Service
public class DistanceServiceImpl implements  DistanceService{

	private static String OSRM_API = "http://router.project-osrm.org/route/v1/driving/";
	
	
	@Override
	public double calculateDistance(Point src, Point des) {
		// TODO Auto-generated method stub
		try
		{
		String uri = src.getX()+","+src.getY()+";"+des.getX()+","+des.getY();
		OSRMResponseDTO routes = RestClient.builder().baseUrl(OSRM_API)
				                .build()
				                .get()
				                .uri(uri)
				                .retrieve().body(new ParameterizedTypeReference<>() {
				                	
				                });
		
		return routes.getRoutes().get(0).getDistance()/1000.0;
		}
		catch(Exception e)
		{
			throw new RuntimeException("error getting data from osrm "+e.getMessage());
		}
	  
	}
	
	

}

@Data
class OSRMResponseDTO
{
	private List<OSRMRoute>routes;
	
}

@Data
class OSRMRoute
{
	private double distance;
}