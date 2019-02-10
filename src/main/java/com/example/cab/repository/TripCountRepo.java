package com.example.cab.repository;

import java.util.List;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.example.cab.model.Trip;

public interface TripCountRepo extends JpaRepository<Trip, Long> {
	public interface CabTripCountPerDate {
	    String getMedallion();
	    String getPickupDate();
	    Long getCount();
	}

	@Query(
			value = "select  medallion,  DATE_FORMAT(pickup_datetime, '%Y-%m-%d') pickupDate,  count(DATE_FORMAT(pickup_datetime, '%Y-%m-%d')) as count from cab_trip_data   where medallion in (:medallions)  group by medallion, DATE_FORMAT(pickup_datetime, '%Y-%m-%d');",
			nativeQuery=true
			)
	List<CabTripCountPerDate> getByMedallions(final Set<String> medallions);
}