package utils;

import java.nio.FloatBuffer;

public class BufferUtils {

    public static FloatBuffer toFloatBuffer(float[] array) {
        FloatBuffer buffer = org.lwjgl.BufferUtils.createFloatBuffer(array.length);
        buffer.put(array);
        buffer.flip();
        return buffer;
    }

}
