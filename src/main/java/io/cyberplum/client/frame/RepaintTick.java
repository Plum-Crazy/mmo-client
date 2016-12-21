package io.cyberplum.client.frame;

import io.cyberplum.client.tick.TickEvent;

/**
 * A scheduled tick event that tells the main GamePanel that it needs to repaint.
 */
public class RepaintTick extends TickEvent {
    
    private GamePanel gamePanel;
    
    public RepaintTick(GamePanel gamePanel) {
        super(1);
        
        this.gamePanel = gamePanel;
    }
    
    @Override
    public void run() {
        gamePanel.repaint();
    }
    
}
