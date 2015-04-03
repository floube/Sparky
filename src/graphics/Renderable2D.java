package graphics;

import graphics.buffers.Buffer;
import graphics.buffers.IndexBuffer;
import graphics.buffers.VertexArray;
import maths.vec2;
import maths.vec3;
import maths.vec4;

public class Renderable2D {

    protected vec3 m_Position;
    protected vec2 m_Size;
    protected vec4 m_Color;

    protected VertexArray m_VertexArray;
    protected IndexBuffer m_IndexBuffer;
    protected Shader m_Shader;

    public Renderable2D(vec3 position, vec2 size, vec4 color, Shader shader) {
        m_Position = position;
        m_Size = size;
        m_Color = color;
        m_Shader = shader;

        m_VertexArray = new VertexArray();

        float[] vertices = new float[] {
                0, 0, 0,
                0, size.y, 0,
                size.x, size.y, 0,
                size.x, 0, 0
        };

        float[] colors = new float[] {
                color.x, color.y, color.z, color.w,
                color.x, color.y, color.z, color.w,
                color.x, color.y, color.z, color.w,
                color.x, color.y, color.z, color.w
        };

        m_VertexArray.addBuffer(new Buffer(vertices, 4 * 3, 3), 0);
        m_VertexArray.addBuffer(new Buffer(colors, 4 * 4, 4), 1);

        short[] indices = new short[] { 0, 1, 2, 2, 3, 0 };
        m_IndexBuffer = new IndexBuffer(indices, 6);
    }

    public VertexArray getVAO() {
        return m_VertexArray;
    }

    public IndexBuffer getIBO() {
        return m_IndexBuffer;
    }

    public Shader getShader() {
        return m_Shader;
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
