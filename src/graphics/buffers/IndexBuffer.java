package graphics.buffers;

import utils.BufferUtils;

import static org.lwjgl.opengl.GL15.*;

public class IndexBuffer {

    private int m_BufferID, m_Count;

    public IndexBuffer(int[] data, int count) {
        m_Count = count;

        m_BufferID = glGenBuffers();
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, m_BufferID);
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, BufferUtils.toIntBuffer(data), GL_STATIC_DRAW);
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
    }

    public void bind() {
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, m_BufferID);
    }

    public void unbind() {
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
    }

    public int getCount() {
        return m_Count;
    }

}
