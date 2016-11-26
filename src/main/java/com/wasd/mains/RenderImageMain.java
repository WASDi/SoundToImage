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

    private static final boolean saveImageToFile = true;

    private static final String BASE_FILE_PATH = "/tmp/sound_to_image/";

    public static void main(String[] args) {
        List<Byte> bytes;

        try {
            new File(BASE_FILE_PATH).mkdir();

            long startTime = System.nanoTime();
            bytes = Mp3Util.bytesFromFile(Mp3Util.TEST_FILENAME);
            long totTime = System.nanoTime() - startTime;
            System.out.printf("Time to load file: %.2fms\n", totTime / 1e6f);
        } catch (IOException | UnsupportedAudioFileException e) {
            e.printStackTrace();
            return;
        }

        render(bytes);
        //batchRender(bytes);
    }

    private static void render(List<Byte> bytes) {
        RenderingStrategy renderingStrategy = new BasicGrayscaleRendering(700);
        BufferedImage renderedImage = renderImage(bytes, renderingStrategy);
        if (saveImageToFile) {
            saveImageToFile(renderedImage, "");
        } else {
            initGui(renderedImage);
        }
    }

    private static void batchRender(List<Byte> bytes) {
        for (int i = 800; i < 900; i++) {
            RenderingStrategy renderingStrategy = new BasicGrayscaleRendering(i);
            BufferedImage renderedImage = renderImage(bytes, renderingStrategy);

            saveImageToFile(renderedImage, "_" + i);
        }
    }

    private static void initGui(Image renderedImage) {
        MainWindow window = new MainWindow(renderedImage);
        window.setVisible(true);
    }

    private static void saveImageToFile(BufferedImage renderedImage, String suffix) {
        File outputFile = new File(BASE_FILE_PATH + "SoundToImage" + suffix + ".png");
        try {

            long startTime = System.nanoTime();
            ImageIO.write(renderedImage, "png", outputFile);
            long totTime = System.nanoTime() - startTime;
            System.out.printf("Time to write file: %.2fms\n", totTime / 1e6f);
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
