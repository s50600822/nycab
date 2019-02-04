package com.example.cab.repository;

import java.util.Collections;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.cab.model.Trip;
import com.example.cab.model.TripCount;
import com.example.cab.model.TripID;

public interface TripCountRepo extends JpaRepository<Trip, Long> {
//	@Query(value = "select medallion, count(*) from cab_trip_data where medallion=?0 pickup_datetim	e like :date%", nativeQuery = true)
//	List<TripCount> getByMedallionAndDate(String Medallion, String date);

//	@Query(value = "SELECT new com.example.cab.model.TripCount(t.medallion, COUNT(t)) FROM Trip t WHERE t.medallion=:medallion AND t.pickup_datetime LIKE :date%")
//	List<TripCount> getByMedallionAndDate(String medallion, String date);

	@Query(value = "SELECT new com.example.cab.model.TripCount(t.medallion, COUNT(t)) FROM Trip t WHERE t.medallion=:medallion AND DATE_FORMAT(t.pickup_datetime, '%Y-%m-%d') LIKE :date%")
	TripCount getByMedallionAndDate(String medallion, String date);
}