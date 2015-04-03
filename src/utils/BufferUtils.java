package utils;

import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

public class BufferUtils {

    public static FloatBuffer toFloatBuffer(float[] array) {
        FloatBuffer buffer = org.lwjgl.BufferUtils.createFloatBuffer(array.length);
        buffer.put(array);
        buffer.flip();
        return buffer;
    }

    public static ShortBuffer toShortBuffer(short[] array) {
        ShortBuffer buffer = org.lwjgl.BufferUtils.createShortBuffer(array.length);
        buffer.put(array);
        buffer.flip();
        return buffer;
    }

}
