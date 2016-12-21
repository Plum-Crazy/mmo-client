package io.cyberplum.client.frame;

import javax.swing.*;

public class GameFrame extends JFrame {
    
    private GamePanel gamePanel;
    
    public GameFrame() {
        super("Test");
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        
        gamePanel = new GamePanel();
        
        getContentPane().add(gamePanel);
        
        pack();
        
        setVisible(true);
    }
    
    public GamePanel getGamePanel() {
        return gamePanel;
    }
    
}
