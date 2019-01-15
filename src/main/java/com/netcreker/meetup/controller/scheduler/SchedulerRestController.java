package com.netcreker.meetup.controller.scheduler;

import com.netcreker.meetup.entity.scheduler.Schedule;
import com.netcreker.meetup.service.scheduler.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
public class SchedulerRestController {

    @Autowired
    ScheduleService scheduleService;

    @GetMapping("/schedule/{id}")
    public ResponseEntity<?> load(@PathVariable long id) {
        return new ResponseEntity<Schedule>(
                scheduleService.loadSchedule(id),
                HttpStatus.OK);
    }

    @PostMapping("/schedule")
    public ResponseEntity<?> create(@RequestBody Schedule schedule, UriComponentsBuilder ucBuilder) {
        scheduleService.saveSchedule(schedule);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/schedule/{id}").buildAndExpand(schedule.getId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

    @PutMapping("/schedule/{id}")
    public ResponseEntity<?> update(@PathVariable long id, @RequestBody Schedule schedule) {
        schedule.setId(id);
        scheduleService.saveSchedule(schedule);
        return new ResponseEntity<Schedule>(schedule, HttpStatus.OK);
    }

    @DeleteMapping("/schedule/{id}")
    public ResponseEntity<?> delete(@PathVariable long id) {
        scheduleService.deleteSchedule(id);
        return new ResponseEntity<Schedule>(HttpStatus.NO_CONTENT);
    }
}
