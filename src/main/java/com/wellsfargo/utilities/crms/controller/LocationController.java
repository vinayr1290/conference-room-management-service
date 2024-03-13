package com.wellsfargo.utilities.crms.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wellsfargo.utilities.crms.exception.ResourceNotFoundException;
import com.wellsfargo.utilities.crms.model.Location;
import com.wellsfargo.utilities.crms.repository.LocationRepository;

import jakarta.validation.Valid;

@CrossOrigin(origins = "http://localhost:4200",methods = {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE})
@RestController
@RequestMapping("/api/v1")
public class LocationController {

    @Autowired
    private LocationRepository locationRepository;

    @GetMapping("/locations")
    public List<Location> getAllLocations() {
        return locationRepository.findAll();
    }

    @GetMapping("/locations/{id}")
    public ResponseEntity<Location> getLocationById(@PathVariable(value = "id") Integer locationId)
            throws ResourceNotFoundException {
        Location location = locationRepository.findById(locationId)
                .orElseThrow(() -> new ResourceNotFoundException("Location not found for this id :: " + locationId));
        return ResponseEntity.ok().body(location);
    }

    @PostMapping("/locations")
    public Location createLocation(@Valid @RequestBody Location location) {
        return locationRepository.save(location);
    }

    @DeleteMapping("/locations/{id}")
    public Map<String, Boolean> deleteLocation(@PathVariable(value = "id") Integer locationId)
            throws ResourceNotFoundException {
        Location location = locationRepository.findById(locationId)
                .orElseThrow(() -> new ResourceNotFoundException("Location not found for this id :: " + locationId));

        locationRepository.delete(location);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }


}
