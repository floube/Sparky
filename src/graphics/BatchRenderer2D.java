package graphics;

import graphics.buffers.IndexBuffer;
import maths.vec2;
import maths.vec3;
import maths.vec4;

import java.awt.geom.Point2D;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

public class BatchRenderer2D implements Renderer2D {

    public final static int RENDERER_MAX_SPRITES    = 60000;
    public final static int RENDERER_VERTEX_SIZE    = (3 * 4) + (4 * 4);
    public final static int RENDERER_SPRITE_SIZE    = RENDERER_VERTEX_SIZE * 4;
    public final static int RENDERER_BUFFER_SIZE    = RENDERER_SPRITE_SIZE * RENDERER_MAX_SPRITES;
    public final static int RENDERER_INDICES_SIZE   = RENDERER_MAX_SPRITES * 6;

    public final static int SHADER_VERTEX_INDEX     = 0;
    public final static int SHADER_COLOR_INDEX      = 1;

    private int m_VAO;
    private int m_VBO;
    private IndexBuffer m_IBO;
    private int m_IndexCount;
    private FloatBuffer m_Buffer;

    public BatchRenderer2D() {
        init();
    }

    private void init() {
        m_VAO = glGenVertexArrays();
        m_VBO = glGenBuffers();

        glBindVertexArray(m_VAO);
        glBindBuffer(GL_ARRAY_BUFFER, m_VBO);
        glBufferData(GL_ARRAY_BUFFER, RENDERER_BUFFER_SIZE, GL_DYNAMIC_DRAW);
        glEnableVertexAttribArray(SHADER_VERTEX_INDEX);
        glEnableVertexAttribArray(SHADER_COLOR_INDEX);
        glVertexAttribPointer(SHADER_VERTEX_INDEX, 3, GL_FLOAT, false, RENDERER_VERTEX_SIZE, 0);
        glVertexAttribPointer(SHADER_COLOR_INDEX, 4, GL_FLOAT, false, RENDERER_VERTEX_SIZE, 3 * 4);
        glBindBuffer(GL_ARRAY_BUFFER, 0);

        int[] indices = new int[RENDERER_INDICES_SIZE];

        int offset = 0;
        for (int i = 0; i < RENDERER_INDICES_SIZE; i += 6) {
            indices[i + 0] = offset + 0;
            indices[i + 1] = offset + 1;
            indices[i + 2] = offset + 2;

            indices[i + 3] = offset + 2;
            indices[i + 4] = offset + 3;
            indices[i + 5] = offset + 0;

            offset += 4;
        }

        m_IBO = new IndexBuffer(indices, RENDERER_INDICES_SIZE);

        glBindVertexArray(0);
    }

    public void begin() {
        glBindBuffer(GL_ARRAY_BUFFER, m_VBO);
        m_Buffer = glMapBuffer(GL_ARRAY_BUFFER, GL_WRITE_ONLY).asFloatBuffer();
    }

    @Override
    public void submit(Renderable2D renderable) {
        vec3 position = renderable.getPosition();
        vec2 size = renderable.getSize();
        vec4 color = renderable.getColor();

        m_Buffer.put(position.x).put(position.y).put(position.z);
        m_Buffer.put(color.x).put(color.y).put(color.z).put(color.w);

        m_Buffer.put(position.x).put(position.y + size.y).put(position.z);
        m_Buffer.put(color.x).put(color.y).put(color.z).put(color.w);

        m_Buffer.put(position.x + size.x).put(position.y + size.y).put(position.z);
        m_Buffer.put(color.x).put(color.y).put(color.z).put(color.w);

        m_Buffer.put(position.x + size.x).put(position.y).put(position.z);
        m_Buffer.put(color.x).put(color.y).put(color.z).put(color.w);

        m_IndexCount += 6;
    }

    public void end() {
        glUnmapBuffer(GL_ARRAY_BUFFER);
        glBindBuffer(GL_ARRAY_BUFFER, 0);
    }

    @Override
    public void flush() {
        glBindVertexArray(m_VAO);
        m_IBO.bind();

        glDrawElements(GL_TRIANGLES, m_IndexCount, GL_UNSIGNED_INT, 0);

        m_IBO.unbind();
        glBindVertexArray(0);

        m_IndexCount = 0;
    }

}
