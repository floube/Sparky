package graphics;

import graphics.buffers.Buffer;
import graphics.buffers.IndexBuffer;
import graphics.buffers.VertexArray;
import maths.vec2;
import maths.vec3;
import maths.vec4;

public class StaticSprite extends Renderable2D {

    private VertexArray m_VertexArray;
    private IndexBuffer m_IndexBuffer;
    private Shader m_Shader;

    public StaticSprite(float x, float y, float width, float height, vec4 color, Shader shader) {
        super(new vec3(x, y, 0), new vec2(width, height), color);
        m_Shader = shader;

        m_Shader = shader;

        m_VertexArray = new VertexArray();

        float[] vertices = new float[] {
                0, 0, 0,
                0, height, 0,
                width, height, 0,
                width, 0, 0
        };

        float[] colors = new float[] {
                color.x, color.y, color.z, color.w,
                color.x, color.y, color.z, color.w,
                color.x, color.y, color.z, color.w,
                color.x, color.y, color.z, color.w
        };

        m_VertexArray.addBuffer(new Buffer(vertices, 4 * 3, 3), 0);
        m_VertexArray.addBuffer(new Buffer(colors, 4 * 4, 4), 1);

        int[] indices = new int[] { 0, 1, 2, 2, 3, 0 };
        m_IndexBuffer = new IndexBuffer(indices, 6);
    }

    public void dispose() {
        m_VertexArray.dispose();
        m_IndexBuffer.dispose();
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

}
