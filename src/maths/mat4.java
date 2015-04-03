package maths;

import utils.BufferUtils;

import java.nio.FloatBuffer;

public class mat4 {

    public float[] elements = new float[4 * 4];

    public mat4() {
        for (int i = 0; i < elements.length; i++) {
            elements[i] = 0.0f;
        }
    }

    public mat4(float diagonal) {
        for (int i = 0; i < elements.length; i++) {
            elements[i] = 0.0f;
        }

        elements[0 + 0 * 4] = diagonal;
        elements[1 + 1 * 4] = diagonal;
        elements[2 + 2 * 4] = diagonal;
        elements[3 + 3 * 4] = diagonal;
    }

    public mat4 multiply(mat4 other) {
        float[] data = new float[4 * 4];

        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                float sum = 0.0f;
                for (int e = 0; e < 4; e++) {
                    sum += elements[x + e * 4] * other.elements[e + y * 4];
                }
                data[x + y * 4] = sum;
            }
        }

        for (int i = 0; i < data.length; i++) {
            elements[i] = data[i];
        }

        return this;
    }

    public vec4 getColumn(int index) {
        index *= 4;
        return new vec4(elements[index], elements[index + 1], elements[index + 2], elements[index + 3]);
    }

    public FloatBuffer getBuffer() {
        return BufferUtils.toFloatBuffer(elements);
    }

    public static mat4 identity() {
        return new mat4(1.0f);
    }

    public static mat4 orthographic(float left, float right, float bottom, float top, float near, float far) {
        mat4 result = new mat4(1.0f);

        result.elements[0 + 0 * 4] = 2.0f / (right - left);

        result.elements[1 + 1 * 4] = 2.0f / (top - bottom);

        result.elements[2 + 2 * 4] = 2.0f / (near - far);

        result.elements[0 + 3 * 4] = (left + right) / (left - right);
        result.elements[1 + 3 * 4] = (bottom + top) / (bottom - top);
        result.elements[2 + 3 * 4] = (far + near) / (far - near);

        return result;
    }

    public static mat4 perspective(float fov, float aspectRatio, float near, float far) {
        mat4 result = new mat4(1.0f);

        float q = (float) (1.0f / Math.tan(Math.toRadians(0.5 * fov)));
        float a = q / aspectRatio;

        float b = (near + far) / (near - far);
        float c = (2.0f * near * far) / (near - far);

        result.elements[0 + 0 * 4] = a;
        result.elements[1 + 1 * 4] = q;
        result.elements[2 + 2 * 4] = b;
        result.elements[3 + 2 * 4] = -1.0f;
        result.elements[2 + 3 * 4] = c;

        return result;
    }

    public static mat4 translation(vec3 translation) {
        mat4 result = new mat4(1.0f);

        result.elements[0 + 3 * 4] = translation.x;
        result.elements[1 + 3 * 4] = translation.y;
        result.elements[2 + 3 * 4] = translation.z;

        return result;
    }

    public static mat4 rotation(float angle, vec3 axis) {
        mat4 result = new mat4(1.0f);

        float r = (float) Math.toRadians(angle);
        float c = (float) Math.cos(r);
        float s = (float) Math.sin(r);
        float omc = 1.0f - c;

        float x = axis.x;
        float y = axis.y;
        float z = axis.z;

        result.elements[0 + 0 * 4] = x * omc + c;
        result.elements[1 + 0 * 4] = y * x * omc + z * s;
        result.elements[2 + 0 * 4] = x * z * omc - y * s;

        result.elements[0 + 1 * 4] = x * y * omc - z * s;
        result.elements[1 + 1 * 4] = y * omc + c;
        result.elements[2 + 1 * 4] = y * z * omc + x * s;

        result.elements[0 + 2 * 4] = x * z * omc + y * s;
        result.elements[1 + 2 * 4] = y * z * omc - x * s;
        result.elements[2 + 2 * 4] = z * omc + c;

        return result;
    }

    public static mat4 scale(vec3 scale) {
        mat4 result = new mat4(1.0f);

        result.elements[0 + 0 * 4] = scale.x;
        result.elements[1 + 1 * 4] = scale.y;
        result.elements[2 + 2 * 4] = scale.z;

        return result;
    }

}
