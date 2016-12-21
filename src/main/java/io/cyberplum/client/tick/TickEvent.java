package io.cyberplum.client.tick;

public abstract class TickEvent {
    
    public static final int TICK_LENGTH = 300;
    
    private long delay;
    private boolean running;
    
    public TickEvent(int ticks) {
        this.delay = (ticks * 200);
        running = true;
    }
    
    public abstract void run();
    
    public final boolean isRunning() {
        return running;
    }
    
    public final long getDelay() {
        return delay;
    }
    
}
