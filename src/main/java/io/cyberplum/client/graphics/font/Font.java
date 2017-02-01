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
        'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
        'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
            
        ' ', '_', '-', '+', '=',
            
        'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
        'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'
    };
    
    public static final char[] LOWERCASE_CHARACTERS = {
        'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
        'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'
    };
    
    private Map<Character, GameImage> images;
    
    public Font(String name) throws IOException {
        File dir = new File("assets/font/" + name);
        if(!dir.exists() || !dir.isDirectory()) {
            throw new FileNotFoundException("Could not locate font directory for " + name);
        }
        
        images = new HashMap<>();
    
        for(char c : CHARACTERS) {
            String fileName = String.valueOf(c);
            
            for(char cc : LOWERCASE_CHARACTERS) {
                if(cc == c) {
                    fileName = "lowercase_" + c;
                    break;
                }
            }
        
            File file = new File("assets/font/" + name + "/" + fileName + ".png");
            if(!file.exists()) {
                throw new FileNotFoundException("Could not find image file for character " + c + " in font " + name);
            }
        
            BufferedImage bufferedImage = ImageIO.read(file);
            GameImage img = new GameImage(bufferedImage);
            images.put(c, img);
        }
    }
    
    public boolean isLowerCase(char c) {
        for(char cc : LOWERCASE_CHARACTERS) {
            if(cc == c) {
                return true;
            }
        }
        
        return false;
    }
    
    public GameImage get(char c) {
        return images.get(c);
    }
    
}
