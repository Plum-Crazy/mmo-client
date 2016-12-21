package io.cyberplum.client.graphics;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.Raster;

public class GameImage {
    
    private BufferedImage bufferedImage;
    private int width, height;
    private byte[] pixels;
    private int pixelIndexes;
    private boolean alphaChannel;
    private boolean updated;
    
    /**
     * Creates a new GameImage from an existing BufferedImage.
     * @param bufferedImage
     */
    public GameImage(BufferedImage bufferedImage) {
        this.bufferedImage = bufferedImage;
        width = this.bufferedImage.getWidth();
        height = this.bufferedImage.getHeight();
        
        setupImageData();
    }
    
    /**
     * Creates a new GameImage with the specified width and height.
     * @param width
     * @param height
     */
    public GameImage(int width, int height) {
        this.width = width;
        this.height = height;
        bufferedImage = new BufferedImage(this.width, this.height, BufferedImage.TYPE_4BYTE_ABGR);
        
        setupImageData();
    }
    
    private void setupImageData() {
        if(bufferedImage == null) {
            return;
        }
        
        pixels = ((DataBufferByte) bufferedImage.getRaster().getDataBuffer()).getData();
        alphaChannel = bufferedImage.getAlphaRaster() != null;
        pixelIndexes = (alphaChannel ? 4 : 3);
        updated = false;
    }
    
    public void setPixel(int[] pixelData, int x, int y) {
        if(x < 0 || y < 0 || x >= width || y >= height) {
            return;
        }
        
        int pos = (y * pixelIndexes * width) + (x * pixelIndexes);
    
        if(alphaChannel) {
            if(pixelData.length == 4) {
                pixels[pos++] = (byte) pixelData[3]; // a
            } else {
                pixels[pos++] = (byte) 255; // a
            }
        }
    
        pixels[pos++] = (byte) pixelData[2]; // b
        pixels[pos++] = (byte) pixelData[1]; // g
        pixels[pos++] = (byte) pixelData[0]; // r
        
        updated = true;
    }
    
    public void setPixel(int pixel, int x, int y) {
        if(x < 0 || y < 0 || x >= width || y >= height) {
            return;
        }
    
        int pos = (y * pixelIndexes * width) + (x * pixelIndexes);
    
        if(alphaChannel) {
            pixels[pos++] = (byte) (pixel >> 24); // a
        }
    
        pixels[pos++] = (byte) pixel; // b
        pixels[pos++] = (byte) (pixel >> 8); // g
        pixels[pos++] = (byte) (pixel >> 16); // r
        
        updated = true;
    }
    
    public int getPixel(int x, int y) {
        if(x < 0 || y < 0 || x >= width || y >= height) {
            return -1;
        }
    
        int pos = (y * pixelIndexes * width) + (x * pixelIndexes);
        
        int pixel = -16777216; // 255 alpha
        if(alphaChannel) {
            pixel = (((int) pixels[pos++] & 0xff) << 24); // a
        }
        
        pixel += ((int) pixels[pos++] & 0xff); // b
        pixel += (((int) pixels[pos++] & 0xff) << 8); // g
        pixel += (((int) pixels[pos++] & 0xff) << 16); // r
        
        return pixel;
    }
    
    public BufferedImage getBufferedImage() {
        if(updated) {
            // Only redraw if the image has been updated... Should help speed considerably.
            bufferedImage.setData(Raster.createRaster(bufferedImage.getSampleModel(), new DataBufferByte(pixels, pixels.length), new Point()));
        }
        
        return bufferedImage;
    }
    
    public int getWidth() {
        return width;
    }
    
    public int getHeight() {
        return height;
    }
    
}
