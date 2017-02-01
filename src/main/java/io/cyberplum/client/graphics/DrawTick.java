package io.cyberplum.client.graphics;

import io.cyberplum.client.frame.GamePanel;
import io.cyberplum.client.tick.TickEvent;

/**
 * A scheduled tick event that tells the main GamePanel that it needs to repaint.
 */
public class DrawTick extends TickEvent {
    
    private GamePanel gamePanel;
    
    public DrawTick(GamePanel gamePanel) {
        super(1);
        
        this.gamePanel = gamePanel;
    }
    
    @Override
    public void run() {
        gamePanel.repaint();
    }
    
}
