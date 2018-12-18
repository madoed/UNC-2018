package com.netcreker.meetup.entity.location;

import lombok.Data;

@Data
public class Location {

  private Long id;
  private String lng;
  private String lat;
  private String placeName;
}
