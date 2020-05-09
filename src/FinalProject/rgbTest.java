package FinalProject;

import java.awt.*;

public class rgbTest {

    //test instance to create integer representation of rgb
    public static void main(String[] args) {

        /*
        String RGB = Integer.toString(rgb);
        int r = Integer.parseInt(RGB.substring(0, 3));
        int g = Integer.parseInt(RGB.substring(3, 6));
        int b = Integer.parseInt(RGB.substring(6, 9));

        System.out.println(r);
        System.out.println(g);
        System.out.println(b);

        */
        int pixel = 0;
        int alpha = (pixel >> 24) & 0xff;
        int red = (pixel >> 16) & 0xff;
        int green = (pixel >> 8) & 0xff;
        int blue = (pixel) & 0xff;
        //System.out.println("argb: " + alpha + ", " + red + ", " + green + ", " + blue);

        Color c = new Color(0, 0, 0, 255);
        System.out.println(c.getRGB());
    }
}
