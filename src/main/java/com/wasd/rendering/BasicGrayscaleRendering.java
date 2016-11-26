package com.wasd.rendering;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.List;

public class BasicGrayscaleRendering implements RenderingStrategy {

    private static final int IMAGE_WIDTH = 835;

    @Override
    public BufferedImage render(List<Byte> bytes) {
        int imageHeight = bytes.size() / IMAGE_WIDTH;
        if (imageHeight > 1000) {
            imageHeight = 1000;
        }
        BufferedImage image = new BufferedImage(IMAGE_WIDTH, imageHeight, BufferedImage.TYPE_INT_RGB);

        int data[] = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();

        if (data.length > bytes.size()) {
            System.out.printf("data.length (%d) > bytes.size() (%d)\n", data.length, bytes.size());
            return null;
        }

        for (int i = 0; i < data.length; i++) {
            byte b = bytes.get(i);
            int unsignedValue = b + Byte.MIN_VALUE;
            data[i] = unsignedValue + (unsignedValue << 8) + (unsignedValue << 16);
        }

        return image;
    }
}
