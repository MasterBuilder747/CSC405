package FinalProject;

import javafx.scene.paint.Color;

public class colorConvert {

    //this is here for reference, it will not be used

    public static int RGBtoInt(Color c) {
        int r = (int)c.getRed() * 255;
        int g = (int)c.getGreen() * 255;
        int b = (int)c.getBlue() * 255;
        int a = (int)c.getOpacity() * 255;
        return (a << 24) | (r << 16) | (g << 8) | b;
    }

    public static Color IntToRGB(int c) {
        //if (c < 16777215) {
            int r = (int) (((c >> 24) & 0xff) / 255.0);
            int g = (int) (((c >> 16) & 0xff) / 255.0);
            int b = (int) (((c >> 8) & 0xff) / 255.0);
            int a = (int) (((c) & 0xff) / 255.0);
            //this returns a hex value
            return Color.rgb(r, g, b, a);
            /*
        } else {
            throw new ArithmeticException("int color must be between - and 16777215");
        }
             */
    }

//    public static void main(String[] args) {
//        System.out.println(IntToRGB(1058107857).getBlue());
//    }
}
