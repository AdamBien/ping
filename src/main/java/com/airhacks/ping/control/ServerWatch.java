package com.airhacks.ping.control;

import java.time.ZonedDateTime;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;

/**
 *
 * @author airhacks.com
 */
@Startup
@Singleton
public class ServerWatch {

    private ZonedDateTime bootTime;

    @PostConstruct
    public void initialize() {
        this.setbootTime();
    }

    void setbootTime() {
        this.bootTime = ZonedDateTime.now();
    }

    public ZonedDateTime getDateTime() {
        return this.bootTime;
    }

}
