package io.cyberplum.client;

import io.cyberplum.client.graphics.DrawingArea;
import io.cyberplum.client.frame.RepaintTick;
import io.cyberplum.client.graphics.Color;
import io.cyberplum.client.graphics.font.Font;
import io.cyberplum.client.tick.TickEventHandler;

import java.awt.*;
import java.io.IOException;

public class ClientEngine {
    
    private TickEventHandler tickEventHandler;
    private RepaintTick repaintTick;
    
    private Font mainFont;
    private DrawingArea mainDrawingArea;
    
    protected ClientEngine() {
        try {
            mainFont = new Font("main");
        } catch(IOException e) {
            e.printStackTrace();
        }
    
        mainDrawingArea = new DrawingArea(0, 0, 500, 300);
        //mainDrawingArea.drawBorderedRect(10, 10, 300, 150, Color.rgb(255, 0, 255), Color.rgb(0, 0, 255), 10);
        mainDrawingArea.drawString(10, 10, mainFont, "AAAABABBBBABABA", Color.rgb(255, 0, 255));
    
        tickEventHandler = new TickEventHandler();
        
        repaintTick = new RepaintTick(Client.getInstance().getGameFrame().getGamePanel());
        tickEventHandler.scheduleConcurrent(repaintTick);
    }
    
    public void paint(Graphics2D g) {
        g.drawImage(mainDrawingArea.getCanvas().getBufferedImage(), mainDrawingArea.getX(), mainDrawingArea.getY(), null);
    }
    
    public Font getMainFont() {
        return mainFont;
    }
    
}
