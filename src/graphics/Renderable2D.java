package graphics;

import maths.vec2;
import maths.vec3;
import maths.vec4;

import java.util.ArrayList;
import java.util.List;

public class Renderable2D {

    protected vec3 m_Position;
    protected vec2 m_Size;
    protected vec4 m_Color;
    protected List<vec2> m_UV = new ArrayList<>();

    protected Renderable2D() {
        setUVDefaults();
    }

    public Renderable2D(vec3 position, vec2 size, vec4 color) {
        m_Position = position;
        m_Size = size;
        m_Color = color;

        setUVDefaults();
    }

    public void dispose() {

    }

    public void submit(Renderer2D renderer) {
        renderer.submit(this);
    }

    private void setUVDefaults() {
        m_UV.add(new vec2(0, 0));
        m_UV.add(new vec2(0, 1));
        m_UV.add(new vec2(1, 1));
        m_UV.add(new vec2(1, 0));
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

    public List<vec2> getUV() {
        return m_UV;
    }

}
