package graphics.buffers;

import utils.BufferUtils;

import static org.lwjgl.opengl.GL15.*;

import static org.lwjgl.glfw.GLFW.*;

public class Buffer {

    private int m_BufferID, m_ComponentCount;

    public Buffer(float[] data, int count, int componentCount) {
        m_ComponentCount = componentCount;

        m_BufferID = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, m_BufferID);
        glBufferData(GL_ARRAY_BUFFER, BufferUtils.toFloatBuffer(data), GL_STATIC_DRAW);
        glBindBuffer(GL_ARRAY_BUFFER, 0);
    }

    public void bind() {
        glBindBuffer(GL_ARRAY_BUFFER, m_BufferID);
    }

    public void unbind() {
        glBindBuffer(GL_ARRAY_BUFFER, 0);
    }

    public int getComponentCount() {
        return m_ComponentCount;
    }

}
