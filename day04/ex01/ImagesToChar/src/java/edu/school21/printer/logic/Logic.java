package edu.school21.printer.logic;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Logic {
    static char w;
    static char b;
    static BufferedImage im;
    public Logic(char white, char black, BufferedImage image){
        w = white;
        b = black;
        im = image;
    }
    public void printImage(){
        int color;
        for (int i = 0; i < im.getHeight(); i++){
            for (int j = 0; j < im.getWidth(); j++){
                color = im.getRGB(j, i);
                if (color == Color.BLACK.getRGB())
                    System.out.print(b);
                else
                    System.out.print(w);
            }
            System.out.println();
        }
    }
}
