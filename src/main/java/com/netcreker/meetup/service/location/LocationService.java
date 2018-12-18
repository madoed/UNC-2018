package com.netcreker.meetup.service.location;

import com.netcreker.meetup.entity.location.Location;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class LocationService {

  public ArrayList<Location> getLocations(Long id){
    //get from repository
    ArrayList<Location> locationsList=new ArrayList<Location>();
    locationsList.add(new Location());
    return locationsList;
  }

  public Location addLocation(Long id,Location newLocation){
    //Location repository, save method
    return newLocation;
  }
}
