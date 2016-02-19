package com.airhacks.ping.control;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import java.lang.management.OperatingSystemMXBean;
import java.time.ZonedDateTime;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.json.Json;
import javax.json.JsonObject;

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

    public double usedMemoryInMb() {
        MemoryUsage current = this.memoryMxBean.getHeapMemoryUsage();
        return asMb(current.getUsed());
    }

    public String usedMemoryInMbAtStartTime() {
        return this.heapUsageAtStartTime.toString();
    }

    public JsonObject osInfo() {
        OperatingSystemMXBean osBean = ManagementFactory.getOperatingSystemMXBean();
        return Json.createObjectBuilder().
                add("System Load Average", osBean.getSystemLoadAverage()).
                add("Available CPUs", osBean.getAvailableProcessors()).
                add("Architecture", osBean.getArch()).
                add("OS Name", osBean.getName()).
                add("Version", osBean.getVersion()).build();

    }

    double asMb(long bytes) {
        return bytes / 1024 / 1024;
    }

}
