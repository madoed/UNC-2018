package com.netcreker.meetup.service.scheduler;

import com.netcreker.meetup.entity.scheduler.Schedule;
import com.netcreker.meetup.entitymanager.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScheduleService {

    @Autowired
    private EntityManager em;

    public Schedule loadSchedule(long id) {
        return em.load(Schedule.class, id);
    }

    public void saveSchedule(Schedule schedule) {
        em.save(schedule);
    }

    public void deleteSchedule(long id) {
        em.delete(id);
    }
}
