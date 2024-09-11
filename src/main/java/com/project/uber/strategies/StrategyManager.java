package com.project.uber.strategies;

import java.time.LocalTime;

import java.time.LocalTime;

import org.springframework.stereotype.Component;

import com.project.uber.strategies.impl.DriverMatchingHighestRatedDriverStrategyImpl;
import com.project.uber.strategies.impl.DriverMatchingNearestDriverStrategyImpl;
import com.project.uber.strategies.impl.RideFareDefaultFareCalculationStrategy;
import com.project.uber.strategies.impl.RideFareSurgePricingCalculationStrategy;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class StrategyManager {

	private final DriverMatchingHighestRatedDriverStrategyImpl highestRatedDriverStrategy;
	private final DriverMatchingNearestDriverStrategyImpl nearestDriverStrategy;
    private final RideFareDefaultFareCalculationStrategy defaultFareStrategy;
    private final RideFareSurgePricingCalculationStrategy surgePricingStrategy;
    
    public DriverMatchingStrategy driverMatchingStrategy(double riderRating)
    {
    	if(riderRating >= 4.8)
    		return highestRatedDriverStrategy;
    	else
    		return nearestDriverStrategy;    	
    }
    
    public RideFareCalculationStrategy fareCalculationStrategy()
    {
    	LocalTime startTime = LocalTime.of(18, 0);
    	LocalTime endTime = LocalTime.of(21, 0);
    	LocalTime currentTime = LocalTime.now();
    	boolean isSurgeTime = currentTime.isAfter(startTime) && currentTime.isBefore(endTime);
    	
    	if(isSurgeTime)
    		return surgePricingStrategy;
    	else
    		return defaultFareStrategy;
    	
    }
}
