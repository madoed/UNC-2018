package com.netcreker.meetup.controller.location;

import com.netcreker.meetup.entity.location.Location;
import com.netcreker.meetup.service.location.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/{id}/location")
public class LocationRestController {

  @Autowired
  private LocationService locationService;

  @GetMapping()
  public ArrayList<Location> getLocations(@PathVariable Long id) {
    return locationService.getLocations(id);
  }

  @PostMapping()
  @ResponseStatus(CREATED)
  public Location addCard(@PathVariable Long id,@RequestBody Location newLocation) {
    return locationService.addLocation(id,newLocation);
  }

}
