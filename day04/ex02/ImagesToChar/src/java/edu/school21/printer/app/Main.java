package edu.school21.printer.app;



import edu.school21.printer.logic.Logic;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

@Parameters(separators = "=")
public class Main {
    @Parameter(names={"--black", "-b"})
    private static String black;
    @Parameter(names={"--white", "-w"})
    private static String white;
    public static void main(String[] args) {
        Main main = new Main();
//        if (args.length != 2)
//            System.exit(-1);
        JCommander.newBuilder().addObject(main).build().parse(args);
        File file = new File("target/resources/it.bmp");
        try {
            BufferedImage image = ImageIO.read(file);
            Logic doIt = new Logic(white, black, image);
            doIt.printImage();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
