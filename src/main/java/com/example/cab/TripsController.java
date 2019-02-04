package com.example.cab;

import java.util.Map;

//import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.cab.repository.TripCountRepo;

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
	    return repo.getByMedallionAndDate("BBD41D16BDEE492B03A57F646424DD67", "2013-12-30").toString();
	}
}
