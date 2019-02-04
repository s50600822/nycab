package com.example.cab.model;

import javax.persistence.Entity;

public class TripCount {
	String medallion;
	Long count;

	public TripCount(String medallion, Long count) {
		super();
		this.medallion = medallion;
		this.count = count;
	}
	
}
