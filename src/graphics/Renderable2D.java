package graphics;

import maths.vec2;
import maths.vec3;
import maths.vec4;

public class Renderable2D {

    protected vec3 m_Position;
    protected vec2 m_Size;
    protected vec4 m_Color;

    public Renderable2D(vec3 position, vec2 size, vec4 color) {
        m_Position = position;
        m_Size = size;
        m_Color = color;
    }

    public vec3 getPosition() {
        return m_Position;
    }

    public vec2 getSize() {
        return m_Size;
    }

    public vec4 getColor() {
        return m_Color;
    }

}
