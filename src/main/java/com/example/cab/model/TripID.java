package com.example.cab.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Embeddable
public class TripID implements Serializable  {
	
	private String medallion;
	
	@Temporal(TemporalType.DATE)
	private java.util.Date startDate; 
}
