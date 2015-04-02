package maths;

public class vec2 {

    public float x, y;

    public vec2() {
        x = 0.0f;
        y = 0.0f;
    }

    public vec2(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public vec2 add(vec2 other) {
        x += other.x;
        y += other.y;

        return this;
    }

    public vec2 subtract(vec2 other) {
        x -= other.x;
        y -= other.y;

        return this;
    }

    public vec2 multiply(vec2 other) {
        x *= other.x;
        y *= other.y;

        return this;
    }

    public vec2 divide(vec2 other) {
        x /= other.x;
        y /= other.y;

        return this;
    }

    public boolean equals(vec2 other) {
        return (x == other.x && y == other.y);
    }

    public String toString() {
        return "vec2: (" + x + ", " + y + ")";
    }

}
