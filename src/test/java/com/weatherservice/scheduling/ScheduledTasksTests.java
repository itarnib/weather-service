package com.weatherservice.scheduling;

import org.junit.Test;

import java.io.IOException;

public class ScheduledTasksTests {
    @Test
    public void updateCacheTest() throws IOException {
        new ScheduledTasks().updateCache();
    }
}
