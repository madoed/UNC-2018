package com.netcreker.meetup.controller.check;

import com.netcreker.meetup.entitymanager.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class RestController {

    @Autowired
    EntityManager entityManager;

    @GetMapping("testLoad")
    public String getAll() throws InstantiationException, IllegalAccessException {
        entityManager.load();
        return "test";
    }
}
