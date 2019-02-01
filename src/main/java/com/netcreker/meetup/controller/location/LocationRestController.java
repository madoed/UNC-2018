package com.netcreker.meetup.controller.location;

import com.netcreker.meetup.entity.location.Location;
import com.netcreker.meetup.service.location.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class LocationRestController {

  @Autowired
  private LocationService locationService;

  @PostMapping("/map")
  @CrossOrigin(origins = "http://localhost:4200")
  public ResponseEntity<?> addCard(@RequestBody Location newLocation) {
    locationService.addLocation(newLocation);
    return new ResponseEntity<String>(HttpStatus.CREATED);
  }

  @GetMapping("/map")
  @CrossOrigin(origins = "http://localhost:4200")
  public ArrayList<Location> getAllСards() {
    return locationService.getLocations();
  }

}
