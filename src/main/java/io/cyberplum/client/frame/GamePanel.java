package io.cyberplum.client.frame;

import io.cyberplum.client.Client;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    
    protected GamePanel() {
        setPreferredSize(new Dimension(500, 300));
        setBackground(Color.BLACK);
        setDoubleBuffered(true);
    }
    
    @Override
    public void paintComponent(Graphics graphics) {
        Graphics2D g2d = (Graphics2D) graphics;
        
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 0, 500, 300);
    
        if(Client.getInstance().isLoaded()) {
            Client.getInstance().getClientEngine().paint(g2d);
        }
    }
    
}
