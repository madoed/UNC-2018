package com.netcreker.meetup.entity.user;

import com.netcreker.meetup.annotation.ObjectType;
import com.netcreker.meetup.annotation.Parameter;
import com.netcreker.meetup.annotation.Reference;
import com.netcreker.meetup.entity.Entity;
import lombok.Data;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Date;
import java.util.List;


@Data
@ObjectType(1)
public class User extends Entity {

    @Parameter(attrId=1001)
    private String name;

    @Parameter(attrId=1013)
    private String login;

    @Parameter(attrId=1014)
    private String email;

    @Parameter(attrId=1015)
    private String password;

    @Reference(attrId=1016)
    private List<User> friends;

    @Parameter(attrId=1017)
    private Date lastVisit;

    public void setPassword(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        this.password = passwordEncoder.encode(password);
    }
}
