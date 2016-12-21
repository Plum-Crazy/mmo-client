package io.cyberplum.client.tick;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class TickEventHandler {
    
    ScheduledExecutorService executorService;
    
    public TickEventHandler() {
        executorService = Executors.newScheduledThreadPool(1);
    }
    
    public void destroy() {
        executorService.shutdown();
    }
    
    public ScheduledFuture<?> schedule(final TickEvent tickEvent) {
        return schedule(tickEvent, tickEvent.getDelay());
    }
    
    public ScheduledFuture<?> schedule(final TickEvent tickEvent, long delay) {
        return executorService.schedule(() -> {
            long start = System.currentTimeMillis();
    
            if(!tickEvent.isRunning()) {
                return;
            }
            
            tickEvent.run();
            
            long elapsed = System.currentTimeMillis() - start;
            long remaining = tickEvent.getDelay() - elapsed;
            
            if(remaining <= 0) {
                remaining = 0;
            }
    
            schedule(tickEvent, remaining);
        }, delay, TimeUnit.MILLISECONDS);
    }
    
    public ScheduledFuture<?> scheduleConcurrent(final TickEvent tickEvent) {
        return executorService.schedule(() -> {
            if(!tickEvent.isRunning()) {
                return;
            }
            
            tickEvent.run();
            
            schedule(tickEvent);
        }, tickEvent.getDelay(), TimeUnit.MILLISECONDS);
    }
    
}
