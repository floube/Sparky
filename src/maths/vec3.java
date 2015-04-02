package maths;

public class vec3 {

    public float x, y, z;

    public vec3() {
        x = 0.0f;
        y = 0.0f;
        z = 0.0f;
    }

    public vec3(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public vec3 add(vec3 other) {
        x += other.x;
        y += other.y;
        z += other.z;

        return this;
    }

    public vec3 subtract(vec3 other) {
        x -= other.x;
        y -= other.y;
        z -= other.z;

        return this;
    }

    public vec3 multiply(vec3 other) {
        x *= other.x;
        y *= other.y;
        z *= other.z;

        return this;
    }

    public vec3 divide(vec3 other) {
        x /= other.x;
        y /= other.y;
        z /= other.z;

        return this;
    }

    public boolean equals(vec3 other) {
        return (x == other.x && y == other.y && z == other.z);
    }

    public String toString() {
        return "vec3: (" + x + ", " + y + ", " + z + ")";
    }

}
