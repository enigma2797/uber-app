package com.project.uber.entities;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Builder
@Table(indexes = {
        @Index(name="idx_rating_rider",columnList = "rider_id"),
        @Index(name="idx_rating_driver",columnList = "driver_id")
})
public class Rating {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;

     @ManyToOne
     private Driver driver;

     @ManyToOne
     private Rider rider;

     @OneToOne
     private Ride ride;
     private Double driverRating;
     private Double riderRating;
}
