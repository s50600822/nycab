package com.example.cab.model;

public class TripsCount {
	String medallion;
	String pickup_date;
	Long count;
	public TripsCount(String medallion, String pickup_date, Long count) {
		super();	
		this.medallion = medallion;
		this.pickup_date = pickup_date;
		this.count = count;
	}
	@Override
	public String toString() {
		return "TripsCount [medallion=" + medallion + ", pickup_date=" + pickup_date + ", count=" + count + "]";
	}
};