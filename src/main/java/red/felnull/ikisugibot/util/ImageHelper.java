package red.felnull.ikisugibot.util;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageHelper {
    public static BufferedImage covWhiteAndBlack(BufferedImage bff) throws IOException {
        int w = bff.getWidth();
        int h = bff.getHeight();
        BufferedImage out = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                int c = bff.getRGB(x, y);
                int mono = (int) (0.299 * r(c) + 0.587 * g(c) + 0.114 * b(c));
                int rgb = (a(c) << 24) + (mono << 16) + (mono << 8) + mono;
                out.setRGB(x, y, rgb);
            }
        }

        return out;
    }

    private static int a(int c) {
        return c >>> 24;
    }

    private static int r(int c) {
        return c >> 16 & 0xff;
    }

    private static int g(int c) {
        return c >> 8 & 0xff;
    }

    private static int b(int c) {
        return c & 0xff;
    }
}
