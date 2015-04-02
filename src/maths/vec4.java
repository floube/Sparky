package maths;

public class vec4 {

    public float x, y, z, w;

    public vec4() {
        x = 0.0f;
        y = 0.0f;
        z = 0.0f;
        w = 0.0f;
    }

    public vec4(float x, float y, float z, float w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    public vec4 add(vec4 other) {
        x += other.x;
        y += other.y;
        z += other.z;
        w += other.w;

        return this;
    }

    public vec4 subtract(vec4 other) {
        x -= other.x;
        y -= other.y;
        z -= other.z;
        w -= other.w;

        return this;
    }

    public vec4 multiply(vec4 other) {
        x *= other.x;
        y *= other.y;
        z *= other.z;
        w *= other.w;

        return this;
    }

    public vec4 divide(vec4 other) {
        x /= other.x;
        y /= other.y;
        z /= other.z;
        w /= other.w;

        return this;
    }

    public boolean equals(vec4 other) {
        return (x == other.x && y == other.y && z == other.z && w == other.w);
    }

    public String toString() {
        return "vec4: (" + x + ", " + y + ", " + z + ", " + w + ")";
    }

}
