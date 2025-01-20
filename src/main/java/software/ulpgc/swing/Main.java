package software.ulpgc.swing;

import software.ulpgc.command.NextImageCommand;
import software.ulpgc.command.PreviousImageCommand;
import software.ulpgc.io.FileImageLoader;
import software.ulpgc.model.Image;

import java.io.File;


public class Main {
    public static final String path = "images";
    public static void main(String[] args) {
        MainFrame frame = new MainFrame();
        Image image = new FileImageLoader(new File(path)).load();
        frame.getImageDisplay().show(image);
        frame.add("Next", new PreviousImageCommand(frame.imageDisplay()));
        frame.add("Previous", new NextImageCommand(frame.imageDisplay()));
        frame.setVisible(true);
    }

}
