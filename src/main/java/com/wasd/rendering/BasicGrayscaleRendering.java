package com.wasd.rendering;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.List;

public class BasicGrayscaleRendering implements RenderingStrategy {
    @Override
    public BufferedImage render(List<Byte> bytes) {
        int sizeSquared = (int) Math.sqrt(bytes.size());
        if (sizeSquared > 1500) {
            sizeSquared = 1500;
        }
        BufferedImage image = new BufferedImage(sizeSquared, sizeSquared, BufferedImage.TYPE_INT_RGB);

        int data[] = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();

        if (data.length != sizeSquared * sizeSquared || data.length > bytes.size()) {
            System.out.println("WTF");
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
