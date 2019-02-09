package com.example.cab;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

//import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.cab.repository.TripCountRepo;
import com.example.cab.repository.TripCountRepo.Cab;

//@Controller
@RestController
@RequestMapping(path="/trip")
public class TripsController {
	TripCountRepo repo;
	
	public TripsController(TripCountRepo repo) {
		super();
		this.repo = repo;
	}

	@GetMapping
	public String getByMedallionAndDate (){
//	    return repo.getByMedallionAndDate("BBD41D16BDEE492B03A57F646424DD67", "2013-12-30").toString();
		
		List<Cab> res = repo.getByMedallions2(Collections.emptyList());
		StringBuilder sb = new StringBuilder();
		res.stream().forEach(c -> sb.append(c.getMedallion()).append(c.getPickupDate()).append(c.getCount()).append("\n"));
		return sb.toString();
	}
}
