package com.example.cab.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.cab.model.Trip;
import com.example.cab.model.TripCount;
import com.example.cab.model.TripsCount;

public interface TripCountRepo extends JpaRepository<Trip, Long> {
	@Query(value = "SELECT new com.example.cab.model.TripCount(t.medallion, COUNT(t)) FROM Trip t WHERE t.medallion=:medallion AND DATE_FORMAT(t.pickup_datetime, '%Y-%m-%d') LIKE :date%")
	TripCount getByMedallionAndDate(String medallion, String date);
	
	
	@Query(
			value = "select \n" + 
					"medallion, \n" + 
					"DATE_FORMAT(pickup_datetime, '%Y-%m-%d') as pickup_date, \n" + 
					"count(DATE_FORMAT(pickup_datetime, '%Y-%m-%d')) as count\n" + 
					"from cab_trip_data  \n" + 
					"where medallion in ('A0B5AF0F9B31690CEBB51ECD27D2BE71', '5AB4DE718E958FC082557F03BF439493') \n" + 
					"group by medallion, DATE_FORMAT(pickup_datetime, '%Y-%m-%d')",
			nativeQuery=true
			)
	List<TripsCount> getByMedallions(List<String> medallion);
	
	
	public interface Cab {
	    String getMedallion();
	    String getPickupDate();
	    Integer getCount();
	}
	
	@Query(
			value = "select  medallion,  DATE_FORMAT(pickup_datetime, '%Y-%m-%d') pickupDate,  count(DATE_FORMAT(pickup_datetime, '%Y-%m-%d')) as count from cab_trip_data   where medallion in ('A0B5AF0F9B31690CEBB51ECD27D2BE71', '5AB4DE718E958FC082557F03BF439493')  group by medallion, DATE_FORMAT(pickup_datetime, '%Y-%m-%d');",
			nativeQuery=true
			)
	List<Cab> getByMedallions2(List<String> medallion);
}