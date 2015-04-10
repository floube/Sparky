package maths;

import utils.BufferUtils;

import java.nio.FloatBuffer;

public class mat4 implements Cloneable {

    public float[] elements = new float[4 * 4];
    public vec4[] columns = new vec4[4];

    public mat4() {
        for (int i = 0; i < elements.length; i++) {
            elements[i] = 0.0f;
        }

        for (int i = 0; i < columns.length; i++) {
            columns[i] = new vec4(0.0f, 0.0f, 0.0f, 0.0f);
        }
    }

    public mat4(float diagonal) {
        for (int i = 0; i < elements.length; i++) {
            elements[i] = 0.0f;
        }

        for (int i = 0; i < columns.length; i++) {
            columns[i] = new vec4(0.0f, 0.0f, 0.0f, 0.0f);
        }

        elements[0 + 0 * 4] = diagonal;
        elements[1 + 1 * 4] = diagonal;
        elements[2 + 2 * 4] = diagonal;
        elements[3 + 3 * 4] = diagonal;

        columns[0].x = diagonal;
        columns[1].y = diagonal;
        columns[2].z = diagonal;
        columns[3].w = diagonal;
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

        copyColumnElements();

        return this;
    }

    public vec3 multiply(vec3 other) {
        return new vec3(
                columns[0].x * other.x + columns[1].x * other.y + columns[2].x * other.z + columns[3].x,
                columns[0].y * other.x + columns[1].y * other.y + columns[2].y * other.z + columns[3].y,
                columns[0].z * other.x + columns[1].z * other.y + columns[2].z * other.z + columns[3].z
        );
    }

    public vec4 multiply(vec4 other) {
        return new vec4(
                columns[0].x * other.x + columns[1].x * other.y + columns[2].x * other.z + columns[3].x * other.w,
                columns[0].y * other.x + columns[1].y * other.y + columns[2].y * other.z + columns[3].y * other.w,
                columns[0].z * other.x + columns[1].z * other.y + columns[2].z * other.z + columns[3].z * other.w,
                columns[0].w * other.x + columns[1].w * other.y + columns[2].w * other.z + columns[3].w * other.w
        );
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

        result.copyColumnElements();

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

        result.copyColumnElements();

        return result;
    }

    public static mat4 translation(vec3 translation) {
        mat4 result = new mat4(1.0f);

        result.elements[0 + 3 * 4] = translation.x;
        result.elements[1 + 3 * 4] = translation.y;
        result.elements[2 + 3 * 4] = translation.z;

        result.copyColumnElements();

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

        result.copyColumnElements();

        return result;
    }

    public static mat4 scale(vec3 scale) {
        mat4 result = new mat4(1.0f);

        result.elements[0 + 0 * 4] = scale.x;
        result.elements[1 + 1 * 4] = scale.y;
        result.elements[2 + 2 * 4] = scale.z;

        result.copyColumnElements();

        return result;
    }

    private void copyColumnElements() {
        columns[0].x = elements[0];
        columns[0].y = elements[1];
        columns[0].z = elements[2];
        columns[0].w = elements[3];

        columns[1].x = elements[4];
        columns[1].y = elements[5];
        columns[1].z = elements[6];
        columns[1].w = elements[7];

        columns[2].x = elements[8];
        columns[2].y = elements[9];
        columns[2].z = elements[10];
        columns[2].w = elements[11];

        columns[3].x = elements[12];
        columns[3].y = elements[13];
        columns[3].z = elements[14];
        columns[3].w = elements[15];
    }

    public void setElement(int index, float value) {
        elements[index] = value;
        copyColumnElements();
    }

    public void setElement(int row, int column, float value) {
        setElement(row + column * 4, value);
    }

    @Override
    public mat4 clone() {
        mat4 result = new mat4();

        for (int i = 0; i < elements.length; i++) {
            result.elements[i] = elements[i];
        }

        result.copyColumnElements();

        return result;
    }

}
