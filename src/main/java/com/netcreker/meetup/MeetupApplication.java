package com.netcreker.meetup;

import com.netcreker.meetup.config.FileStorageConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({
    FileStorageConfiguration.class
})
public class MeetupApplication {
    public static void main(String[] args) {
        SpringApplication.run(MeetupApplication.class, args);
    }
}
