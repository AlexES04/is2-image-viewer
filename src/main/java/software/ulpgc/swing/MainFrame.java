package software.ulpgc.swing;

import software.ulpgc.command.Command;
import software.ulpgc.view.ImageDisplay;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class MainFrame extends JFrame {
    private SwingImageDisplay imageDisplay;
    private final Map<String, Command> commands;

    public MainFrame() {
        commands = new HashMap<>();
        this.setTitle("Image Viewer");
        this.setSize(1200, 800);
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(toolbar(), BorderLayout.SOUTH);
        this.add(createImageDisplay());
        this.setJMenuBar(createMenuBar());

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.setLocationRelativeTo(null);
        this.setResizable(true);
        this.add(createImageDisplay());
    }

    private Component toolbar() {
        JPanel panel = new JPanel();
        panel.add(createButton("Next"));
        panel.add(createButton("Previous"));
        return panel;
    }

    private Component createButton(String label) {
        JButton button = new JButton(label);
        button.addActionListener(e -> {commands.get(label).execute();});
        return button;
    }

    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.addActionListener(e -> System.exit(0));
        fileMenu.add(exitMenuItem);
        menuBar.add(fileMenu);
        return menuBar;
    }

    public SwingImageDisplay getImageDisplay() {
        return imageDisplay;
    }

    public void add(String name, Command command) {
        commands.put(name, command);
    }

    private Component createImageDisplay() {
        SwingImageDisplay display = new SwingImageDisplay();
        this.imageDisplay = display;
        return display;
    }

    public ImageDisplay imageDisplay() {
        return this.imageDisplay;
    }
}
