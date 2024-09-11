package com.project.uber.configs;



import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.locationtech.jts.geom.Point;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.project.uber.dto.PointDTO;
import com.project.uber.utils.GeometryUtils;

@Configuration
public class ModelMapperConfig {
	
	@Bean
	ModelMapper getModelMapper()
	{
		ModelMapper mapper = new ModelMapper();
		mapper.typeMap(PointDTO.class,Point.class).setConverter(converter->
		{
			PointDTO pointDto = converter.getSource();
			return GeometryUtils.createPoint(pointDto);
			
		});
		
		mapper.typeMap(Point.class, PointDTO.class).setConverter(converter->{
			Point point = converter.getSource();
			double X;
			double Y;
			if(point!=null)
				X = point.getX();
			else
				X = 0.0;
			if(point!=null)
				Y = point.getY();
			else
				Y = 0.0;
			double[] coordinates = {X,Y};
			return new PointDTO(coordinates);
		});
		return mapper;
	}

	@Bean
	public OpenAPI springShopOpenAPI() {
		return new OpenAPI()
				.info(new Info().title("Uber BE")
						.description("Ride booking app")
						.version("v0.0.1")
						.license(new License().name("Apache 2.0").url("http://springdoc.org")));
	}

}
