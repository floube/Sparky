package utils;

import de.matthiasmann.twl.utils.PNGDecoder;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public class ImageUtils {

    public static class DecodedImage {

        private ByteBuffer m_Buffer;
        private int m_Width, m_Height;

        public DecodedImage(ByteBuffer buffer, int width, int height) {
            m_Buffer = buffer;
            m_Width = width;
            m_Height = height;
        }

        public ByteBuffer getBuffer() {
            return m_Buffer;
        }

        public int getWidth() {
            return m_Width;
        }

        public int getHeight() {
            return m_Height;
        }

    }

    public static DecodedImage loadImage(String filename) {
        try {
            InputStream input = new FileInputStream(filename);
            PNGDecoder decoder = new PNGDecoder(input);

            int width = decoder.getWidth();
            int height = decoder.getHeight();

            ByteBuffer buffer = ByteBuffer.allocateDirect(4 * width * height);
            decoder.decode(buffer, decoder.getWidth() * 4, PNGDecoder.Format.RGBA);
            buffer.flip();

            input.close();

            return new DecodedImage(buffer, width, height);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

}
