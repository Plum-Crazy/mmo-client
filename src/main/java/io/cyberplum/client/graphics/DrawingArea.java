package io.cyberplum.client.graphics;

import io.cyberplum.client.Client;
import io.cyberplum.client.graphics.font.Font;

public class DrawingArea {
    
    private int x, y;
    private int width, height;
    private GameImage canvas;
    
    public DrawingArea(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        canvas = new GameImage(width, height);
    }
    
    public void drawString(int drawX, int drawY, Font font, String s, int[] color) {
        char[] chars = s.toCharArray();
        for(int i = 0; i < chars.length; i++) {
            char c = chars[i];
            GameImage img = font.get(c);
            
            if(img == null) {
                continue;
            }
            
            int width = img.getWidth();
            int height = img.getHeight();
            
            for(int x = drawX; x < drawX + width; x++) {
                for(int y = drawY; y < drawY + height; y++) {
                    int indexX = x - drawX;
                    int indexY = y - drawY;
                    
                    int pixel = img.getPixel(indexX, indexY);
                    
                    // Only need to pick out the black pixels in the char image.
                    // All else is just noise.
                    if(pixel != Color.BLACK) {
                        continue;
                    }
                    
                    canvas.setPixel(color, x, y);
                }
            }
            
            drawX += width + 1;
        }
    }
    
    public void drawBorderedRect(int drawX, int drawY, int drawWidth, int drawHeight, int[] bgColor, int[] borderColor, int borderSize) {
        drawRectBorder(drawX, drawY, drawWidth, drawHeight, borderColor, borderSize);
        
        // We could just draw the border OVER the rect, but what if we (for some reason) want a transparent border with
        // an opaque rectangle? Well shit. Now we have to draw the rectangle INSIDE the border...
        
        drawX += borderSize;
        drawY += borderSize;
        drawWidth -= borderSize * 2;
        drawHeight -= borderSize * 2;
        
        drawRect(drawX, drawY, drawWidth, drawHeight, bgColor);
    }
    
    public void drawRectBorder(int drawX, int drawY, int drawWidth, int drawHeight, int[] color, int size) {
        for(int x = drawX; x < drawX + drawWidth; x++) {
            for(int y = drawY; y < drawY + drawHeight; y++) {
                if(x >= width || y >= height) {
                    continue;
                }
            
                int indexX = x - drawX;
                int indexY = y - drawY;
                
                if(indexX < size || indexY < size || drawWidth - indexX <= size || drawHeight - indexY <= size) {
                    canvas.setPixel(color, x, y);
                }
            }
        }
    }
    
    public void drawRect(int drawX, int drawY, int drawWidth, int drawHeight, int[] color) {
        for(int x = drawX; x < drawX + drawWidth; x++) {
            for(int y = drawY; y < drawY + drawHeight; y++) {
                if(x >= width || y >= height) {
                    continue;
                }
                
                canvas.setPixel(color, x, y);
            }
        }
    }
    
    public int getX() {
        return x;
    }
    
    public int getY() {
        return y;
    }
    
    public int getWidth() {
        return width;
    }
    
    public int getHeight() {
        return height;
    }
    
    public GameImage getCanvas() {
        return canvas;
    }
    
}
