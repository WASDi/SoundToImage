package com.wasd.mains;

import com.wasd.Mp3Util;
import com.wasd.gui.MainWindow;
import com.wasd.rendering.BasicGrayscaleRendering;
import com.wasd.rendering.RenderingStrategy;

import javax.imageio.ImageIO;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class RenderImageMain {

    private static final boolean saveImageToFile = false;

    public static void main(String[] args) {
        List<Byte> bytes = null;

        try {
            long startTime = System.nanoTime();
            bytes = Mp3Util.bytesFromFile(Mp3Util.TEST_FILENAME);
            long totTime = System.nanoTime() - startTime;
            System.out.printf("Time to load file: %.2fms\n", totTime / 1e6f);
        } catch (IOException | UnsupportedAudioFileException e) {
            e.printStackTrace();
            return;
        }

        RenderingStrategy renderingStrategy = new BasicGrayscaleRendering();
        BufferedImage renderedImage = renderImage(bytes, renderingStrategy);

        if (saveImageToFile) {
            saveImageToFile(renderedImage);
        } else {
            initGui(renderedImage);
        }
    }

    private static void initGui(Image renderedImage) {
        MainWindow window = new MainWindow(renderedImage);
        window.setVisible(true);
    }

    private static void saveImageToFile(BufferedImage renderedImage) {
        File outputFile = new File("/tmp/SoundToImage.png");
        try {
            ImageIO.write(renderedImage, "png", outputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static BufferedImage renderImage(List<Byte> bytes, RenderingStrategy renderingStrategy) {
        long startTime = System.nanoTime();
        BufferedImage renderedImage = renderingStrategy.render(bytes);
        long totTime = System.nanoTime() - startTime;
        System.out.printf("Time to render image: %.2fms\n", totTime / 1e6f);

        return renderedImage;
    }

}
