package com.wasd.rendering;

import java.awt.image.BufferedImage;
import java.util.List;

public interface RenderingStrategy {
    BufferedImage render(List<Byte> bytes);
}
