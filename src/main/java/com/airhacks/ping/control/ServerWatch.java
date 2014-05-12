package com.airhacks.ping.control;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
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

    private ZonedDateTime startTime;
    private MemoryUsage heapUsageAtStartTime;
    private MemoryMXBean memoryMxBean;

    @PostConstruct
    public void initialize() {
        this.initializeStartTime();
        this.memoryMxBean = ManagementFactory.getMemoryMXBean();
        this.heapUsageAtStartTime = this.memoryMxBean.getHeapMemoryUsage();

    }

    void initializeStartTime() {
        this.startTime = ZonedDateTime.now();
    }

    public ZonedDateTime getDateTime() {
        return this.startTime;
    }

    public double availableMemoryInMB() {
        MemoryUsage current = this.memoryMxBean.getHeapMemoryUsage();
        long available = (current.getCommitted() - current.getUsed());
        return asMb(available);
    }

    public double usedMemroyInMb() {
        MemoryUsage current = this.memoryMxBean.getHeapMemoryUsage();
        return asMb(current.getUsed());

    }

    double asMb(long bytes) {
        return bytes / 1024 / 1024;
    }

}
