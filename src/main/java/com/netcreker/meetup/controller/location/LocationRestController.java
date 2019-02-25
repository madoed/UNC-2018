package com.netcreker.meetup.controller.location;

import com.netcreker.meetup.entity.location.Location;
import com.netcreker.meetup.service.location.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class LocationRestController {

  @Autowired
  private LocationService locationService;

  @PostMapping("/add-meeting-location/{id}")//id of participant
  @CrossOrigin(origins = "http://localhost:4200")
  public ResponseEntity<?> addMeetingLocation(@PathVariable long id, @RequestBody Location newLocation) {
    locationService.addMeetingLocation(id, newLocation);
    return new ResponseEntity<String>(HttpStatus.CREATED);
  }

  @PostMapping("/set-meeting-location/{id}")//id of meeting
  @CrossOrigin(origins = "http://localhost:4200")
  public ResponseEntity<?> setMeetingLocation(@PathVariable long id, @RequestBody Location newLocation) {
    locationService.setMeetingLocation(id, newLocation);
    return new ResponseEntity<String>(HttpStatus.CREATED);
  }

  @GetMapping("/locations-meeting/{id}")
  @CrossOrigin(origins = "http://localhost:4200")
  public ResponseEntity<List<Location>> getMeetingLocations(@PathVariable long id) {
    List<Location> locations = locationService.getMeetingLocations(id);
    if(locations.isEmpty()){
      return new ResponseEntity<List<Location>>(HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<List<Location>>(locations, HttpStatus.OK);
  }
}
