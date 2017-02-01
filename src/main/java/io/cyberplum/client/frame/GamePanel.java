package io.cyberplum.client.frame;

import io.cyberplum.client.Client;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    
    protected GamePanel() {
        setPreferredSize(new Dimension(800, 600));
        setBackground(Color.BLACK);
        setDoubleBuffered(true);
    }
    
    @Override
    public void paintComponent(Graphics graphics) {
        Graphics2D g2d = (Graphics2D) graphics;
    
        if(Client.getInstance().isLoaded()) {
            Client.getInstance().getClientEngine().paint(g2d);
        }
    }
    
}
