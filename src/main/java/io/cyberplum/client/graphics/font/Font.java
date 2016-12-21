package io.cyberplum.client.graphics.font;

import io.cyberplum.client.graphics.GameImage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Font {
    
    public static final char[] CHARACTERS = {
        'A', 'B'
    };
    
    private Map<Character, GameImage> images;
    
    public Font(String name) throws IOException {
        File dir = new File("assets/font/" + name);
        if(!dir.exists() || !dir.isDirectory()) {
            throw new FileNotFoundException("Could not locate font directory for " + name);
        }
        
        images = new HashMap<>();
        
        for(int i = 0; i < CHARACTERS.length; i++) {
            File file = new File("assets/font/" + name + "/" + CHARACTERS[i] + ".png");
            if(!file.exists()) {
                throw new FileNotFoundException("Could not find image file for character " + CHARACTERS[i] + " in font " + name);
            }
    
            BufferedImage bufferedImage = ImageIO.read(file);
            GameImage img = new GameImage(bufferedImage);
            images.put(CHARACTERS[i], img);
        }
    }
    
    public GameImage get(char c) {
        return images.get(c);
    }
    
}
