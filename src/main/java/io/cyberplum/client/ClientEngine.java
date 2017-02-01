package io.cyberplum.client;

import io.cyberplum.client.graphics.Color;
import io.cyberplum.client.graphics.DrawingArea;
import io.cyberplum.client.graphics.DrawTick;
import io.cyberplum.client.graphics.font.Font;
import io.cyberplum.client.tick.TickEventHandler;

import java.awt.*;
import java.io.IOException;

public class ClientEngine {
    
    private TickEventHandler tickEventHandler;
    private DrawTick drawTick;
    
    private Font mainFont;
    private DrawingArea mapDrawingArea;
    
    protected ClientEngine() {
        try {
            mainFont = new Font("main");
        } catch(IOException e) {
            e.printStackTrace();
        }
    
        mapDrawingArea = new DrawingArea(0, 0, 550, 425);
        mapDrawingArea.drawRect(0, 0, 550, 425, Color.WHITE_RGB);
        mapDrawingArea.drawRect(0, 0, 39, 39, Color.rgb(255, 0, 0));
        //mapDrawingArea.drawBorderedRect(10, 10, 300, 150, Color.rgb(255, 0, 255), Color.rgb(0, 0, 255), 10);
        /*mapDrawingArea.drawString(10, 10, mainFont, "ABCDEFGHIJKLMNOPQRSTUVWXYZ", Color.rgb(255, 0, 255));
        mapDrawingArea.drawString(10, 30, mainFont, "_ - + =", Color.rgb(255, 0, 255));
        mapDrawingArea.drawString(10, 50, mainFont, "abcdefghijklmnopqrstuvwxyz", Color.rgb(255, 0, 255));
        mapDrawingArea.drawString(10, 80, mainFont, "the quick brown fox jumps over the lazy dog", Color.WHITE_RGB);
        mapDrawingArea.drawString(10, 100, mainFont, "THE QUICK BROWN FOX JUMPS OVER THE LAZY DOG", Color.WHITE_RGB);
        mapDrawingArea.drawString(10, 120, mainFont, "Mopar", Color.WHITE_RGB);*/
    
        tickEventHandler = new TickEventHandler();
        
        drawTick = new DrawTick(Client.getInstance().getGameFrame().getGamePanel());
        tickEventHandler.scheduleConcurrent(drawTick);
    }
    
    public void paint(Graphics2D g) {
        g.drawImage(mapDrawingArea.getCanvas().getBufferedImage(), mapDrawingArea.getX(), mapDrawingArea.getY(), null);
    }
    
    public Font getMainFont() {
        return mainFont;
    }
    
}
