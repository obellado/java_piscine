package edu.school21.printer.app;

import edu.school21.printer.logic.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class StartUp {
    static char black;
    static char white;
    public static void main(String[] args) {
        if (args.length != 3)
            System.exit(-1);
        white = args[0].charAt(0);
        black = args[1].charAt(0);
        File file = new File(args[2]);
        try {
            BufferedImage image = ImageIO.read(file);
            Logic doIt = new Logic(white, black, image);
            doIt.printImage();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
