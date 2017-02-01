package io.cyberplum.client.graphics;

public class Color {
    
    public static final int
            BLACK = -16777216;
    
    public static final int[]
            BLACK_RGB = new int[] { 0, 0, 0 },
            BLACK_RGBA = new int[] { 0, 0, 0, 255 },
            WHITE_RGB = new int[] { 255, 255, 255 },
            WHITE_RGBA = new int[] { 255, 255, 255, 255 };
    
    public static int[] rgb(int r, int g, int b) {
        return new int[] { r, g, b };
    }
    
    public static int[] rgba(int r, int g, int b, int a) {
        return new int[] { r, g, b, a };
    }
    
}
