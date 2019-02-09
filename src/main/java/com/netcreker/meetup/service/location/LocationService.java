package com.netcreker.meetup.service.location;

import com.netcreker.meetup.entity.location.Location;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class LocationService {
  public ArrayList<Location> getLocations(){
    //get from repository
    ArrayList<Location> locationsList=new ArrayList<Location>();
    Location location=new Location();
    location.setId((long)1);
    location.setLat("51.6754966");
    location.setLng("39.2088823");
    location.setPlaceName("mac");
    locationsList.add(location);
    location=new Location();
    location.setId((long)2);
    location.setLng("39.254592");
    location.setLat("51.685036");
    location.setPlaceName("cafe");
    locationsList.add(location);
    return locationsList;
  }

  public void addLocation(Location newLocation){
    //Location repository, save method
  }
}
