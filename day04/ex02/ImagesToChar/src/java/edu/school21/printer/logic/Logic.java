package edu.school21.printer.logic;

import java.awt.*;
import java.awt.image.BufferedImage;

import com.diogonunes.jcolor.Ansi;
import com.diogonunes.jcolor.Attribute;

public class Logic {
    static Attribute w;
    static Attribute b;
    static BufferedImage im;
    public Logic(String white, String black, BufferedImage image){
        w = Attribute.RED_BACK();
        b = Attribute.BLUE_BACK();
        im = image;
    }
    public void printImage(){
        int color;
        for (int i = 0; i < im.getHeight(); i++){
            for (int j = 0; j < im.getWidth(); j++){
                color = im.getRGB(j, i);
                if (color == Color.BLACK.getRGB())
                    System.out.print(Ansi.colorize(" ", b));
                else
                    System.out.print(Ansi.colorize(" ", w));
            }
            System.out.println();
        }
    }
}
