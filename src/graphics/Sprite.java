package graphics;

import maths.vec2;
import maths.vec3;
import maths.vec4;

public class Sprite extends Renderable2D {

    public Sprite(float x, float y, float width, float height, vec4 color) {
        super(new vec3(x, y, 0), new vec2(width, height), color);
    }

}
