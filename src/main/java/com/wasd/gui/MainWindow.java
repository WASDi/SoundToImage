package com.wasd.gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Image;

public class MainWindow extends JFrame {

    private final Image renderedImage;

    public MainWindow(Image renderedImage) {
        super("SoundToImage");

        this.renderedImage = renderedImage;

        add(new DrawPanel());
        setSize(1000, 1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private class DrawPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            g.drawImage(renderedImage, 0, 0, null);
        }
    }

}
