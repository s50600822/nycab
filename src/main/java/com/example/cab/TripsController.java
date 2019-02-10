package com.example.cab;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.cab.repository.TripCountRepo;
import com.example.cab.repository.TripCountRepo.CabTripCountPerDate;

@RestController
@RequestMapping(path = "/trip")
public class TripsController {
	TripCountRepo repo;

	public TripsController(TripCountRepo repo) {
		super();
		this.repo = repo;
	}

	@GetMapping(produces = "application/json")
	public ResponseEntity<Map<String, Map<String, Long>>> getByMedallionAndDate(
			@RequestParam("cabIds") Set<String> cabIds,
			@RequestParam(required = false, defaultValue = "false") boolean purgeCache) {
		if (purgeCache) {
			repo.clearCache();
		}
		return new ResponseEntity<Map<String, Map<String, Long>>>(getStat(repo.getByMedallions(cabIds)), HttpStatus.OK);
	}

	private Map<String, Map<String, Long>> getStat(final List<CabTripCountPerDate> stats) {
		final Map<String, Map<String, Long>> result = new HashMap<String, Map<String, Long>>();
		for (CabTripCountPerDate cab : stats) {
			final String id = cab.getMedallion();
			if (result.containsKey(id)) {
				result.get(id).put(cab.getPickupDate(), cab.getCount());
			} else {
				final Map<String, Long> countByDate = new HashMap<String, Long>();
				countByDate.put(cab.getPickupDate(), cab.getCount());
				result.put(id, countByDate);
			}
		}
		return result;
	}
}
