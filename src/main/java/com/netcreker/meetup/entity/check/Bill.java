package com.netcreker.meetup.entity.check;

import com.netcreker.meetup.annotation.Id;
import lombok.Data;

@Data
public class Bill {

    @Id
    private long id;
    private long bill_fn;
    private long bill_fd;
}
